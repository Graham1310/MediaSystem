/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author b0010899
 */
public class testFrame3Tim extends javax.swing.JFrame {

    /**
     * Creates new form testFrame3Tim
     */
    public testFrame3Tim() {
        initComponents();
    }

    private SetOfTasks usersTasks = new SetOfTasks();
    private SetOfUsers allUsers = new SetOfUsers();
    private SetOfClients allClients = new SetOfClients();
    private SetOfClientReps allClientReps = new SetOfClientReps();
    private SetOfStaff allStaff = new SetOfStaff();
    private SetOfAssets allAssets = new SetOfAssets();
    private SetOfElements allElements = new SetOfElements();
    private SetOfProjects allProjects = new SetOfProjects();
    private User UserLoggedIn;
    
    
    private void loadAllUsers(){//COMPLETE
        try {        
            ResultSet dbAllUsers = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllUsers = statement.executeQuery( "SELECT User.userID, User.firstName, User.surname, User.username, User.password FROM [User];");                     

            while(dbAllUsers.next())
            {
//                User tempUser = new User(dbAllUsers.getInt("userID"), dbAllUsers.getString("firstName"), dbAllUsers.getString("surname"),
//                        dbAllUsers.getString("userName"), dbAllUsers.getString("password"), dbAllUsers.getString("role"));
//                allUsers.addUser(tempUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllClients(){//needs the address fixing
        try {        
            ResultSet dbAllClients = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllClients = statement.executeQuery( "SELECT Client.clientID, Client.organizationName, Client.address1, Client.address2, Client.address3, Client.Town, Client.postcode, Client.country FROM Client;");                     

            while(dbAllClients.next())
            {
                Client newClient = Client(dbAllClients.getInt("clientID"), dbAllClients.getString("organisationName"), dbAllClients.getString("address1 + address2 + address3 + Town + postcode + country"));
                allClients.addClient(newClient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllClientReps(){//COMPLETE
        try {        
            ResultSet dbAllClientReps = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllClientReps = statement.executeQuery( "SELECT ClientRep.clientRepID, ClientRep.organisationID, ClientRep.contactNo, ClientRep.email FROM ClientRep;");                     

            while(dbAllClientReps.next())
            {
                Client tempClient = null;
                User tempUser = null;
                for (int i=0; i<allClients.size();i++){
                    if(allClients.get(i).getClientID()==(dbAllClientReps.getInt("organisationID")))
                    {
                        tempClient = (allClients.get(i));
                    }
                }
                for (int i=0; i<allUsers.size();i++){
                    if(allUsers.get(i).getUserID()==(dbAllClientReps.getInt("clientRepID")))
                    {
                        tempUser = (allUsers.get(i));
                    }
                }
                ClientRep tempClientRep = new ClientRep(tempUser, tempClient, dbAllClientReps.getString("contactNo"), dbAllClientReps.getString("email"));
                allClientReps.addClientRep(tempClientRep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllStaff(){//COMPLETE
        try {               
            ResultSet dbAllStaff = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllStaff = statement.executeQuery( "SELECT Staff.staffID, Staff.role FROM Staff;");
            
            while(dbAllStaff.next())
            {
                User tempUser = null;
                for (int i=0; i<allUsers.size();i++){
                        if(allUsers.get(i).getUserID()==(dbAllStaff.getInt("staffID")))
                        {
                            tempUser = (allUsers.get(i));
                        }
                    }
                Staff newStaff = new Staff(tempUser, dbAllStaff.getString("role"));
                allStaff.addStaff(newStaff);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllAssets(){//COMPLETE
        try {        
            ResultSet dbAllAssets = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllAssets = statement.executeQuery( "SELECT Asset.ID, Asset.assetName, Asset.assetType FROM Asset;");                     

            while(dbAllAssets.next())
            {
               // Asset tempAsset = new Asset(dbAllAssets.getInt("ID"), dbAllAssets.getString("assetName"), dbAllAssets.getString("type"));
                //allAssets.addAsset(tempAsset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllElements(){//REALLY NOT SURE IF THIS ONE WILL WORK RIGHT
        try {
            ResultSet dbAllElements = null;
            Statement statement;
            statement = connection.createStatement();  
            dbAllElements = statement.executeQuery( "SELECT Element.elementID, Element.elementName, SetOFAssets.assetID" +
                    "FROM Element INNER JOIN SetOFAssets ON Element.elementID = SetOFAssets.elementID;");
            while(dbAllElements.next())
            {
                SetOfAssets elementAssets = new SetOfAssets();
                for(int i=0; i< allAssets.size() ; i++)
                {
                    if(allAssets.get(i).getAssetID() == dbAllElements.getInt("assetID"))
                    {
                        elementAssets.addAsset(allAssets.get(i));
                    }
                }
                Element tempElement = new Element(dbAllElements.getInt("elementID"), dbAllElements.getString("elementName"), elementAssets);
            }
                  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    private void loadAllProjects(){//requires components and QC stuff
        try {
            ResultSet dbAllProjects = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllProjects = statement.executeQuery( "SELECT Project.projectID, Project.projectName, Project.rootComponent, Project.teamLeader, "                     
                    + "Project.clientRep, Project.priority FROM Project;");                     

            while(dbAllProjects.next())
            {
                int tempTeamLeaderID = dbAllProjects.getInt("teamLeader");
                int tempClientRepID = dbAllProjects.getInt("clientRep");
                User tempTeamLeader = null;
                User tempClientRep = null;
                
                for (int i=0; i<allUsers.size();i++){
                    if(allUsers.get(i).getUserID()==(tempTeamLeaderID))
                    {
                        tempTeamLeader = (allUsers.get(i));
                    }
                    if(allUsers.get(i).getUserID()==(tempClientRepID))
                    {
                        tempClientRep = (allUsers.get(i));
                    }
                }
                //project should have correct components and QCReports added
                //Project tempProject = new Project(dbAllProjects.getInt("projectID"), null, null, tempTeamLeader, tempClientRep, dbAllProjects.getInt("priority"), null, null);
                //allProjects.addProject(tempProject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void displayUsersTasks(){
        //find tasks for user logged in
        try {
            Project tempProject = null;
            
            ResultSet dbUsersTasks = null;
            Statement statement;
            statement = connection.createStatement();
            dbUsersTasks = statement.executeQuery( "SELECT Staff.staffID, Task.taskID, Task.projectID, Task.responsiblePerson, Task.taskPriority,"
                    + " Task.status, Task.taskName, Task.assetID FROM Staff INNER JOIN Task ON "
                    + "Staff.staffID = Task.responsiblePerson WHERE Staff.staffID =" + UserLoggedIn.getUserID() + ";");                     

            while(dbUsersTasks.next())
            {
                //get Project from SetOfProjects where projectID = 
                int tempProjectID = dbUsersTasks.getInt("Task.projectID");
                
                for (int i=0; i<allProjects.size();i++){
                    if(allProjects.get(i).getProjectID()==(tempProjectID))
                    {
                        tempProject = (allProjects.get(i));
                    }
                }
                //Task task = new Task(dbUsersTasks.getInt("Task.taskID"), UserLoggedIn, dbUsersTasks.getString("Task.TaskName"), dbUsersTasks.getInt("Task.taskPriority"),
                                //dbUsersTasks.getString("Task.taskStatus"), tempProject);
                //usersTasks.addTask(task);
                jList1.setListData(usersTasks);
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Display Tasks");

        jButton1.setText("Change Status to:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(testFrame3Tim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testFrame3Tim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testFrame3Tim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testFrame3Tim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testFrame3Tim().setVisible(true);
                
                //call functions here
            }
        });
        
         //Connection String for Tim
        //String fileName = "C:\\Users\\Tim Beale\\Documents\\Uni Work\\Year 3 again\\Case Studies\\Assignment3\\CSSD.mdb";
        //Connection String for Tim on Uni PC
        String fileName = "F:\\MyWork\\Year 3 again\\CSSD\\Assignment 3 - Code\\CSSD.mdb";
        //Connection String for Marcin
        //String fileName = "C:\\Users\\Neverborn\\Documents\\NetBeansProjects\\MediaSystem\\CSSD.accdb";
        /*Connction String for Graham */
        //String fileName = "F:\\MyWork\\NetBeansProjects\\JavaApplication1\\CSSD.mdb"; 
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private Client Client(int aInt, String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
