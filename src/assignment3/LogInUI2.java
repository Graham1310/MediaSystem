/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment3;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Graham
 */
public class LogInUI2 extends javax.swing.JFrame {

    /**
     *Declares variables required
     */
      private SetOfUsers allUsers = new SetOfUsers();
      public User UserLoggedIn;
      public Singleton newInstance = Singleton.getInstance();
      static Connection connection;
      static Statement statement;
        
    /**
     * Creates new form LogInUI2
     * Initialises components
     */
    public LogInUI2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        logInBtn = new javax.swing.JButton();
        usernameTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        logInBtn.setText("Log In");
        logInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logInBtn, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(usernameTxt))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(47, 47, 47)
                .addComponent(logInBtn)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param userID
     * @return 
     * Passes in the userID and creates connection
     * Selects the role of the user from the database
     * Returns role of the user
     */
    private String getUserRole(int userID){
        String role="";
        try{
            ResultSet userResults = null;
            Statement user;
            
            user = connection.createStatement();
            userResults = user.executeQuery("SELECT Staff.role, User.userID FROM [User] INNER JOIN Staff ON User.[userID] = Staff.[staffID] WHERE User.userID="+userID+";");
           
            while(userResults.next())
            {
                role = userResults.getString("role");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogInUI2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return role;
    }
    private boolean checkIfCLientRep(int userID){
        boolean clientRep=false;
        try{
            ResultSet userResults = null;
            Statement user;
            
            user = connection.createStatement();
            userResults = user.executeQuery("SELECT clientRepID FROM ClientRep WHERE clientRepID="+userID+";");
           
            while(userResults.next())
            {
               clientRep = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogInUI2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientRep;
    }
    
    /**
     * 
     * @param evt 
     * On button click
     * Create connection and check database for user with correct details
     * If details are incorrect, display error message
     * If details are correct, display appropriate UI for user
     */
    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
        try {
                     String username = usernameTxt.getText();
                     String password = passTxt.getText();
                     ResultSet loginResults = null;
                     Statement statement;                  
                     statement = connection.createStatement();
                     loginResults = statement.executeQuery( "SELECT * FROM User WHERE username='" + username +
                                                        "' AND password='" + password + "'");
                 
                     while (loginResults.next())
                    {
                        //finds any matching user and puts them into UserLoggedIn
                        UserLoggedIn = new User(loginResults.getInt("userID"), loginResults.getString("firstName"), 
                                loginResults.getString("surname"), loginResults.getString("username"), 
                                loginResults.getString("password"));
                    }              
                    if (UserLoggedIn == null )
                    {
                       JOptionPane.showMessageDialog(null, "Username or password not found. Please see your manager");
                    }              
                    else
                    {
                        newInstance.addUserLogedIn(UserLoggedIn);
                        int userID = UserLoggedIn.getUserID();
                        String role;
                        if (checkIfCLientRep(userID))
                            role = "Client Representative";
                        else
                            role = getUserRole(userID);
                        switch(role){ // load different starting frame depending on role

                            case "Manager": new ManagerUI().setVisible(true);
                                break;
                            case "QC Team Lead": new MainUI(userID).setVisible(true);
                                break;
                            case "QC Member":new MainUI(userID).setVisible(true);
                                break;
                            case "Product Architect":new MainUI(userID).setVisible(true);
                                break; 
                            case "Client Representative":new ClientRepUI(userID).setVisible(true);
                                break;     
                        }
                        this.setVisible(false);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LogInUI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logInBtnActionPerformed
  
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * Opens UI
     * Creates connection to database
     */
    public static void main(String args[])  throws  SQLException{
//variables for use in the program
		
	final int PORT = 1234;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInUI2().setVisible(true);
            }
        });
        
        //Connection String for Tim
        //String fileName = "C:\\Users\\Tim Beale\\Documents\\Uni Work\\Year 3 again\\Case Studies\\Assignment 3\\CSSD.mdb";
        //Connection String for Tim on Uni PC
//        String fileName = "F:\\MyWork\\NetBeansProjects\\CSSD\\CSSD.mdb";
        //Connection String for Marcin
        String fileName = "C:\\Users\\Neverborn\\Documents\\NetBeansProjects\\MediaSystem\\CSSD.mdb";
        /*Connction String for Graham */
//       String fileName = "C:\\Users\\Graham\\Desktop\\MediaSystem\\CSSD.mdb" ;
        //relative path test - doesnt work :/
        //String fileName = "..\\..\\CSSD.mdb";
        String dbString ="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + fileName + ";"; //Change back to *mdb for 32bit access  		
                
    	try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			connection = DriverManager.getConnection(dbString,"","");
			System.out.println("Server Connected To Database");
		}
		
		catch(ClassNotFoundException | SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
  
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logInBtn;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}
