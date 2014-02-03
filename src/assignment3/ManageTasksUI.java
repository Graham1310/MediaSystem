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
public class ManageTasksUI extends javax.swing.JFrame {

    private Task selectedTask;
    private Task selectedUnallocatedTask;
    private int selectedTaskId;
    private String selectedTaskName;
    private String selectedTaskProject;
    private int selectedTaskPriorioty;
    private String selectedTaskStatus;
    private String selectedTaskAsset;
    private String selectedTaskType;
    private int selectedTaskResponsiblePersonID;
    private SetOfTasks userTasks = new SetOfTasks();
    private SetOfTasks unallocatedTasks = new SetOfTasks();
    private int userLoggedInID;
    private randomSQLFunctionsReady randSQL = new randomSQLFunctionsReady();
    
    /**
     * Creates new form ManageTasksUI
     */
    public ManageTasksUI(int userLoggedInID) {
        this.userLoggedInID = userLoggedInID;
        initComponents();
        fillInUserTaskList(userLoggedInID);
        fillInUnAllocatedTaskList(userLoggedInID);
    }
    
    private void fillInUserTaskList(int userID){
            userTasks.clear();
            ResultSet userTasksResultSet = null;
            Statement statement;
            Task task;
            try {
                statement = connection.createStatement();
                userTasksResultSet = statement.executeQuery("SELECT Staff.staffID, Task.taskID, Task.projectID, Task.responsiblePerson, Task.taskPriority,"
                    + " Task.status, Task.taskName, Task.assetID, Task.type FROM Staff INNER JOIN Task ON "
                    + "Staff.staffID = Task.responsiblePerson WHERE Staff.staffID =" + userID + ";");
                
                int taskID;
                String name;
                int projectID;
                int priority;
                String status;
                int assetID;
                String type;
                int responsiblePersonID;
                
                while(userTasksResultSet.next()){
                    
                    taskID = userTasksResultSet.getInt("taskID");
                    responsiblePersonID = userTasksResultSet.getInt("responsiblePerson");
                    name = userTasksResultSet.getString("taskName");
                    priority = userTasksResultSet.getInt("taskPriority");
                    status = userTasksResultSet.getString("status");
                    projectID = userTasksResultSet.getInt("projectID");
                    assetID = userTasksResultSet.getInt("assetID");
                    type = userTasksResultSet.getString("type");
                    
                //Task(int taskID, User responsiblePerson, String taskName, int priority, String status, int projectID, int assetID, String type)    
                    task = new Task(taskID,responsiblePersonID,name,priority,status,projectID,assetID,type);
                    userTasks.add(task);
                    listUserTasksList.setListData(userTasks);
                    TasksListCellRenderer renderer = new TasksListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                    listUserTasksList.setCellRenderer(renderer);                    
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }
    
    private void fillInUnAllocatedTaskList(int userID) {
            unallocatedTasks.clear();
            //unallocatedTasks = randSQL.GetUnassignedTasksForUser(userID);
            SetOfTasks unassignedTasks = new SetOfTasks();
                try{
                     
                     ResultSet taskResults = null;
                     Statement statement;
                     Task task;
                     statement = connection.createStatement();
                     taskResults = statement.executeQuery( "SELECT Task.*, StaffOnProjects.staffID, StaffOnProjects.projectID FROM"
                                            + " (Project INNER JOIN StaffOnProjects ON Project.projectID = StaffOnProjects.projectID)"
                                            + " INNER JOIN Task ON Project.projectID = Task.projectID WHERE (((StaffOnProjects.staffID)="
                                            + userID + ") AND ((Task.responsiblePerson) Is Null));");                                         
                     //AND ((Task.responsiblePerson) Is Null))
                     
                     int taskID ;
                        String taskName;
                        int taskPriority;
                        String status ;
                        int projectID ;
                        String type ;
                        int assetID;
                        int responsiblePersonID;
                        //User emptyUser;
                     
                     while (taskResults.next())
                    {
                        //emptyUser = new User();
                         taskID = taskResults.getInt("taskID");
                         responsiblePersonID = taskResults.getInt("responsiblePerson");
                         taskName = taskResults.getString("taskName");
                         taskPriority = taskResults.getInt("taskPriority");
                         status = taskResults.getString("status");
                         projectID = taskResults.getInt("projectID");
                         type =  taskResults.getString("type");
                         assetID = taskResults.getInt("assetID");
                         task = new Task(taskID, responsiblePersonID, taskName, taskPriority, status, projectID, assetID, type);
                         unallocatedTasks.addTask(task);
                       
            
                         
                          UnallocatedTasksList.setListData(unallocatedTasks);
                          TasksListCellRenderer renderer = new TasksListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                          UnallocatedTasksList.setCellRenderer(renderer);
                    }
                }catch(Exception e){
                  JOptionPane.showMessageDialog(null,e);
              }
            
                
           
    }
    
    private String getProjectName(int projectID){
        String projectName="";
        ResultSet ProjectResultSet = null;
        Statement statement;

        try{
            statement = connection.createStatement();
            ProjectResultSet = statement.executeQuery("SELECT projectID, projectName FROM Project WHERE projectID="+projectID+";");

            while(ProjectResultSet.next())
            {
                projectName = ProjectResultSet.getString("projectName");
            }

        } catch (SQLException ex) {
           Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return projectName;
    }
    
    private String getAssetName(int assetID){
        String assetName="";
        ResultSet assetResultSet = null;
        Statement statement;

        try{
            statement = connection.createStatement();
            assetResultSet = statement.executeQuery("SELECT ID, assetName FROM Asset WHERE ID="+assetID+";");

            while(assetResultSet.next())
            {
                assetName = assetResultSet.getString("assetName");
            }

        } catch (SQLException ex) {
           Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return assetName;        
    }
    
    private void setTaskDetails(Task task){
        
            txtTaskTitle.setText(task.getTaskName());
            txtTaskProject.setText(getProjectName(task.getProjectID()));
            txtTaskAsset.setText(getAssetName(task.getAssetID()));
            txtTaskPriority.setText(String.valueOf(task.getPriority()));
            txtTaskType.setText(task.getType());
            txtTaskStatus.setText(task.getStatus());
    }
    
    private void startTask(int taskID){
        String statusChange ="In Progress";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Task SET status='"+ statusChange + "' WHERE taskID="+ taskID +";");
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void completeTask(int taskID){
        String statusChange ="Completed";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Task SET status='"+ statusChange + "' WHERE taskID="+ taskID +";");
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void AllocateTask(int taskID){
        
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Task SET responsiblePerson='"+ userLoggedInID + "' WHERE taskID="+ taskID +";");
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageTasksUI.class.getName()).log(Level.SEVERE, null, ex);
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
        listUserTasksList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnStartTask = new javax.swing.JButton();
        btnCompleteTask = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTaskTitle = new javax.swing.JTextField();
        txtTaskProject = new javax.swing.JTextField();
        txtTaskPriority = new javax.swing.JTextField();
        txtTaskAsset = new javax.swing.JTextField();
        txtTaskType = new javax.swing.JTextField();
        txtTaskStatus = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtUnallocatedTaskPriority = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtUnallocatedTaskAsset = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtUnallocatedTaskType = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtUnallocatedTaskStatus = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        allocateTaskBtn = new javax.swing.JButton();
        txtUnallocatedTaskProject = new javax.swing.JTextField();
        txtUnallocatedTaskTitle = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UnallocatedTasksList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listUserTasksList.setPreferredSize(new java.awt.Dimension(200, 215));
        listUserTasksList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUserTasksListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listUserTasksList);

        jLabel1.setText("Your Tasks:");

        btnStartTask.setText("Start Task");
        btnStartTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartTaskActionPerformed(evt);
            }
        });

        btnCompleteTask.setText("Complete Task");
        btnCompleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteTaskActionPerformed(evt);
            }
        });

        jLabel2.setText("Details Of Selected Task:");

        jLabel3.setText("Title:");

        jLabel4.setText("In Project:");

        jLabel5.setText("On Asset:");

        jLabel6.setText("Priority:");

        jLabel7.setText("Type:");

        jLabel8.setText("Status:");

        txtTaskTitle.setEditable(false);

        txtTaskProject.setEditable(false);

        txtTaskPriority.setEditable(false);

        txtTaskAsset.setEditable(false);

        txtTaskType.setEditable(false);

        txtTaskStatus.setEditable(false);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel9.setText("Status:");

        txtUnallocatedTaskPriority.setEditable(false);

        jLabel10.setText("Priority:");

        txtUnallocatedTaskAsset.setEditable(false);

        jLabel11.setText("Type:");

        txtUnallocatedTaskType.setEditable(false);

        jLabel12.setText("In Project:");

        txtUnallocatedTaskStatus.setEditable(false);

        jLabel13.setText("On Asset:");

        jLabel14.setText("Details Of Selected Task:");

        jLabel15.setText("Title:");

        allocateTaskBtn.setText("Allocate Task");
        allocateTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allocateTaskBtnActionPerformed(evt);
            }
        });

        txtUnallocatedTaskProject.setEditable(false);

        txtUnallocatedTaskTitle.setEditable(false);

        jLabel16.setText("Unallocated Tasks");

        UnallocatedTasksList.setPreferredSize(new java.awt.Dimension(200, 215));
        UnallocatedTasksList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                UnallocatedTasksListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(UnallocatedTasksList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(allocateTaskBtn)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskProject, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUnallocatedTaskType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnStartTask)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCompleteTask, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskProject, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaskType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTaskProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTaskAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTaskPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTaskType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartTask)
                    .addComponent(btnCompleteTask))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtUnallocatedTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtUnallocatedTaskProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtUnallocatedTaskAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtUnallocatedTaskPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtUnallocatedTaskType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtUnallocatedTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allocateTaskBtn)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void listUserTasksListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUserTasksListValueChanged
        selectedTask = (Task) listUserTasksList.getSelectedValue();
        if (selectedTask !=null)
        {
           setTaskDetails(selectedTask); 
        }
    }//GEN-LAST:event_listUserTasksListValueChanged

    private void btnStartTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartTaskActionPerformed
        selectedTask = (Task) listUserTasksList.getSelectedValue();
        if ((selectedTask !=null) && selectedTask.getStatus().equalsIgnoreCase("Not Started"))
        {
           startTask(selectedTask.getTaskID());
           fillInUserTaskList(userLoggedInID);
           listUserTasksList.repaint();
        }        
    }//GEN-LAST:event_btnStartTaskActionPerformed

    private void btnCompleteTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteTaskActionPerformed
        selectedTask = (Task) listUserTasksList.getSelectedValue();
        if ((selectedTask !=null) && selectedTask.getStatus().equalsIgnoreCase("In Progress"))
        {
           completeTask(selectedTask.getTaskID());
           fillInUserTaskList(userLoggedInID);
           listUserTasksList.repaint();
        }  
    }//GEN-LAST:event_btnCompleteTaskActionPerformed

    private void allocateTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allocateTaskBtnActionPerformed
        // TODO add your handling code here:
         selectedTask = (Task) UnallocatedTasksList.getSelectedValue();
         
         AllocateTask(selectedTask.getTaskID());
         fillInUserTaskList(userLoggedInID);
         listUserTasksList.repaint();
         
         fillInUnAllocatedTaskList(userLoggedInID);
         UnallocatedTasksList.repaint();
         
    }//GEN-LAST:event_allocateTaskBtnActionPerformed

    private void UnallocatedTasksListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_UnallocatedTasksListValueChanged
        selectedUnallocatedTask = (Task) UnallocatedTasksList.getSelectedValue();
        if (selectedUnallocatedTask !=null)
        {
           setUnAllocatedTaskDetails(selectedUnallocatedTask); 
        }
    }//GEN-LAST:event_UnallocatedTasksListValueChanged

     private void setUnAllocatedTaskDetails(Task task){
        
            txtUnallocatedTaskTitle.setText(task.getTaskName());
            txtUnallocatedTaskProject.setText(getProjectName(task.getProjectID()));
            txtUnallocatedTaskAsset.setText(getAssetName(task.getAssetID()));
            txtUnallocatedTaskPriority.setText(String.valueOf(task.getPriority()));
            txtUnallocatedTaskType.setText(task.getType());
            txtUnallocatedTaskStatus.setText(task.getStatus());
                        
    }
    
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
            java.util.logging.Logger.getLogger(ManageTasksUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTasksUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTasksUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTasksUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManageTasksUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList UnallocatedTasksList;
    private javax.swing.JButton allocateTaskBtn;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCompleteTask;
    private javax.swing.JButton btnStartTask;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listUserTasksList;
    private javax.swing.JTextField txtTaskAsset;
    private javax.swing.JTextField txtTaskPriority;
    private javax.swing.JTextField txtTaskProject;
    private javax.swing.JTextField txtTaskStatus;
    private javax.swing.JTextField txtTaskTitle;
    private javax.swing.JTextField txtTaskType;
    private javax.swing.JTextField txtUnallocatedTaskAsset;
    private javax.swing.JTextField txtUnallocatedTaskPriority;
    private javax.swing.JTextField txtUnallocatedTaskProject;
    private javax.swing.JTextField txtUnallocatedTaskStatus;
    private javax.swing.JTextField txtUnallocatedTaskTitle;
    private javax.swing.JTextField txtUnallocatedTaskType;
    // End of variables declaration//GEN-END:variables


}
