/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Graham
 */
public class ProjectOverview extends javax.swing.JFrame {

    
    /**
     *Declares variables required
     */
    private SetOfTasks waitingTasks = new SetOfTasks();
    private SetOfTasks allocatedTasks= new SetOfTasks();
    private SetOfTasks tasksInProgress= new SetOfTasks();
    private SetOfTasks completedTasks= new SetOfTasks();
    private randomSQLFunctionsReady randSQL = new randomSQLFunctionsReady();
    private int projectID;

    /**
     * Creates new form ProjectOverview
     * Initialises components
     */
    public ProjectOverview() {
        initComponents();
    }
    
    /**
     *
     * @param ProjectID
     * Passes in the projectID
     * loads all lists associated with project
     */
    public ProjectOverview(int ProjectID) {
        initComponents();
        projectID = ProjectID;
        LoadAllLists();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        waitTaskList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allocatedTaskList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tIPList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        completedTaskList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Project Overview");

        jLabel2.setText("Waiting Tasks:");

        waitTaskList.setPreferredSize(new java.awt.Dimension(0, 130));
        jScrollPane1.setViewportView(waitTaskList);

        jLabel3.setText("Allocated But Not Started Tasks:");

        allocatedTaskList.setPreferredSize(new java.awt.Dimension(0, 130));
        jScrollPane2.setViewportView(allocatedTaskList);

        jLabel4.setText("Tasks In Progress:");

        tIPList.setPreferredSize(new java.awt.Dimension(0, 130));
        jScrollPane3.setViewportView(tIPList);

        completedTaskList.setPreferredSize(new java.awt.Dimension(0, 130));
        jScrollPane4.setViewportView(completedTaskList);

        jLabel5.setText("Completed Tasks:");

        jButton1.setText("Ok");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(76, 76, 76)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     * On button click, close window
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     * Create and display the form
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectOverview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList allocatedTaskList;
    private javax.swing.JList completedTaskList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList tIPList;
    private javax.swing.JList waitTaskList;
    // End of variables declaration//GEN-END:variables

    /**
     * Create connection statement
     * Select all tasks from database associated with selected project
     * For every task, load information into system
     * Sort tasks into category based on status, and display in UI
     */
    private void LoadAllLists() {
            ResultSet tasksOnAssetListResultSet = null;   
            Statement statement;
            try {
            statement = connection.createStatement();
       
            tasksOnAssetListResultSet = statement.executeQuery("SELECT * FROM Task WHERE projectID = " + projectID + ";");  
                        
                  
            randSQL.loadAllUsers();
            SetOfUsers allusers = randSQL.getAllUsers();

            while (tasksOnAssetListResultSet.next()){
               int taskID;
               int projectID;                        
               User responsibleUser = null ;
               int taskPriority;
               String status;
               String taskName;
               String taskType;

                taskID = tasksOnAssetListResultSet.getInt("taskID");
                projectID = tasksOnAssetListResultSet.getInt("projectID");
                int userResponsible = tasksOnAssetListResultSet.getInt("responsiblePerson");

               for(int i=0; i<allusers.size();i++)
               {                                
                   int userID = allusers.get(i).getUserID();
                   if(userResponsible == userID)
                   {
                       responsibleUser = allusers.get(i);
                       break;
                   }     
                }

                 taskPriority = tasksOnAssetListResultSet.getInt("taskPriority");
                 status = tasksOnAssetListResultSet.getString("status");
                 taskName = tasksOnAssetListResultSet.getString("taskName");
                 taskType = tasksOnAssetListResultSet.getString("type");

                 Task newTask = new Task(taskID, responsibleUser,taskName, taskPriority , status,projectID, null, taskType);

                 if (responsibleUser == null )
                 {
                     //add to waiting
                     waitingTasks.add(newTask);
                 }
                 else if (newTask.getStatus().equals("Not Started") && newTask.getResponsiblePerson() != null)
                 {
                     //Allocated
                     allocatedTasks.add(newTask);
                 }
                 else if (newTask.getStatus().equals("In Progress"))
                 {
                     //add to inprogress
                     tasksInProgress.add(newTask);
                 }
                 else if (newTask.getStatus().equals("Completed"))
                 {
                     //add to completed
                     completedTasks.add(newTask);
                 }                             
                  allocatedTaskList.setListData(allocatedTasks);; 
                  completedTaskList.setListData(completedTasks);;
                  tIPList.setListData(tasksInProgress);;
                  waitTaskList.setListData(waitingTasks);;
                  OverviewTaskCellRenderer renderer = new OverviewTaskCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
                  allocatedTaskList.setCellRenderer(renderer);
                  completedTaskList.setCellRenderer(renderer);
                  tIPList.setCellRenderer(renderer);
                  waitTaskList.setCellRenderer(renderer);     
            }
            } catch (SQLException ex) {
Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
