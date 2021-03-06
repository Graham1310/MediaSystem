/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Neverborn
 */
public class CreateProjectUI extends javax.swing.JFrame {

    /**
     * Declares variables required
     */
    private Project project;
    private int projectID;
    private String projectName;
    private int priority;
    private SetOfUsers clientRepList = new SetOfUsers();
    private User clientRep;
    int rootComponentID;
    int teamLeaderID;
    int clientRepID;
    int tempProjectID=0; //no need for deletion upon exit when 0

    /**
     * Creates new form CreateProjectUI
     */
    public CreateProjectUI() {
        initComponents();
        insertTempProjectIntoDataBase();
        setLastProjectIDFromDataBase();
        fillInClientRepList();
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
        txtProjectName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboxPriority = new javax.swing.JComboBox();
        btnCreateProject = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listClientRepList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Create New Project:");

        jLabel2.setText("Project Name:");

        jLabel3.setText("Client Rep:");

        jLabel4.setText("Priority:");

        cboxPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        btnCreateProject.setText("Create Project");
        btnCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProjectActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        listClientRepList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listClientRepList.setPreferredSize(new java.awt.Dimension(200, 150));
        jScrollPane1.setViewportView(listClientRepList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(33, 33, 33)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProjectName)
                                    .addComponent(cboxPriority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnCreateProject, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboxPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateProject)
                    .addComponent(btnCancel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     * On button click, retrieve information from UI
     * If the fields are correctly formatted, create project
     * Close UI
     */
    private void btnCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProjectActionPerformed
        String projectName = txtProjectName.getText();
        int priority =  Integer.valueOf(cboxPriority.getSelectedItem().toString());
        clientRep = (User)listClientRepList.getSelectedValue();
        if(priority>=1 && clientRep!=null){
            project = new Project(projectName,priority,clientRep);
            insertProjectIntoDataBase(project);
            this.dispose();   
        }
    }//GEN-LAST:event_btnCreateProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click, delete temporary variable from database and close UI
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        deleteTempProjectFromDataBase(tempProjectID);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

  /**
   * 
   * @param project 
   * Pass in project and create connection
   * Create project in database
   */
    private void insertProjectIntoDataBase(Project project){
        try {
            
            Statement statement;
            statement = connection.createStatement();
            clientRepID = project.getClientRep().getUserID();
            statement.executeUpdate("UPDATE Project SET projectName='"+project.getProjectName()+"' , clientRep="
                    + clientRepID +", priority="+project.getPriority()+" WHERE projectID="+tempProjectID+";");
        } catch (SQLException ex) {
            Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    /**
     * Create connection
     * Create a temporary project in database
     */
     private void insertTempProjectIntoDataBase(){
        try {
            
            Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate( "INSERT INTO Project(projectName) VALUES ('TempProject');");
        } catch (SQLException ex) {
            Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
     /**
      * Create connection
      * Select last projectID in database
      */
     private void setLastProjectIDFromDataBase(){
        try {
            ResultSet tempProjectIDResults= null;
            Statement statement;
            statement = connection.createStatement();
            tempProjectIDResults= statement.executeQuery( "SELECT LAST(projectID) FROM Project;"); // select last only supported in ms access
            
            while (tempProjectIDResults.next()){
                tempProjectID = tempProjectIDResults.getInt(1);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
      * 
      * @param tempProjectID 
      * Pass in tempProjectID and create connection
      * Delete from Project in database where ID matches passed ID
      */
     private void deleteTempProjectFromDataBase(int tempProjectID){
         if(tempProjectID !=0){
            try {
                Statement statement;
                statement = connection.createStatement();
                statement.executeUpdate( "DELETE FROM Project WHERE projectID="+tempProjectID+";");
            } catch (SQLException ex) {
                Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    };
     
     /**
      * Create connection
      * Select all Client Representatives
      * For every ClientRep, add them to list of all ClientReps
      * Display list of ClientReps
      */
    private void fillInClientRepList(){
        try{
            ResultSet clientRepResultSet = null;
            clientRepList.clear();
            Statement statement;
            
            statement = connection.createStatement();
            clientRepResultSet = statement.executeQuery("SELECT User.userID, User.firstName, User.surname, ClientRep.clientRepID FROM [User] INNER JOIN ClientRep ON User.[userID] = ClientRep.[clientRepID];");
            
            while(clientRepResultSet.next())
            {
                int userID = clientRepResultSet.getInt("userID");
                String firstName = clientRepResultSet.getString("firstName");
                String surname = clientRepResultSet.getString("surname");
                clientRep = new User(userID,firstName,surname);
                clientRepList.add(clientRep);
                listClientRepList.setListData(clientRepList);
                UserListCellRenderer renderer = new UserListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                listClientRepList.setCellRenderer(renderer);
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
    }     
    /**
     * @param args the command line arguments
     * Create and display the form
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateProjectUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateProject;
    private javax.swing.JComboBox cboxPriority;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listClientRepList;
    private javax.swing.JTextField txtProjectName;
    // End of variables declaration//GEN-END:variables

}
