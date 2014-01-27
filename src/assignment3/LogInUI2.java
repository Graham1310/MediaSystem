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
     * Creates new form LogInUI2
     */
      private SetOfUsers allUsers = new SetOfUsers();
      public User UserLoggedIn; 
      static Connection connection;
      static Statement statement;
        
    public LogInUI2() {
        initComponents();
        
        //add users via database connection
        
            /*
            User user1 = new User("Jane", "Air", "user1", "user1");
            User user2 = new User("Amir", "Air", "user2", "user2");
            User user3 = new User("Astrid", "Air", "user3", "user3");
            User user4 = new User("Andy", "Air", "user4", "user4");

            allUsers.add(user1);
            allUsers.add(user2);
            allUsers.add(user3);
            allUsers.add(user4);
            */
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
                            .addComponent(usernameTxt)))))
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

    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
        try {
            //int num = Integer.parseInt(SearchBookBox.getText());
                     String username = usernameTxt.getText();
                     String password = passTxt.getText();
                     Boolean found = false;
                     
                     ResultSet loginResults = null;
                     Statement statement;
                     
                     statement = connection.createStatement();
                     loginResults = statement.executeQuery( "SELECT * FROM User WHERE username='" + username + "' AND password='" + password + "'");
                     
                     
                     while (loginResults.next())
                    {
                             UserLoggedIn = new User(loginResults.getString("firstName"), loginResults.getString("surname"), loginResults.getString("username"), loginResults.getString("password"));

                    }
                     
                     
                     
                    if (loginResults.next() )
                    {
                        //int userID = loginResults.getInt("userID");
                        String firstName = loginResults.getString("firstName");
                        String surname = loginResults.getString("surname");
                                            
                        UserLoggedIn = new User(firstName, surname, username, password);
                    }
                     
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Username or password not found. Please see your manager");
                    }

        } catch (SQLException ex) {
            Logger.getLogger(LogInUI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logInBtnActionPerformed

    
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String args[])  throws  SQLException{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogInUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogInUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogInUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//variables for use in the program
		
	final int PORT = 1234;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInUI2().setVisible(true);
            }
        });
        
        //Connection String for Tim
        //String fileName = "C:\\Users\\Tim Beale\\Documents\\Uni Work\\Year 3 again\\Case Studies\\Assignment3\\CSSD.mdb"; 
        //Connection String for Marcin
        String fileName = "C:\\Users\\Neverborn\\Documents\\NetBeansProjects\\MediaSystem\\CSSD.accdb";
        /*Connction String for Graham */
        /*String fileName = "C:\\Users\\Graham\\Desktop\\CSSD.mdb" */
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
