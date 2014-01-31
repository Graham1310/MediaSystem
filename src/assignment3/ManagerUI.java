/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment3;
import static assignment3.LogInUI2.connection;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Neverborn
 */
public class ManagerUI extends javax.swing.JFrame {

    private SetOfProjects listOfProjects = new SetOfProjects();
    private SetOfUsers listOfUsers = new SetOfUsers();
    private SetOfElements listOfElements = new SetOfElements();
    private SetOfTasks listOfTasks = new SetOfTasks();
    private Project selectedProject;
    private User selectedStaff;
    private Task selectedTask;
    /**
     * Creates new form ManagerUI
     */
    public ManagerUI() {
        initComponents();
        fillInProjectsList();        
    }
    
    private void fillInProjectsList(){
        try{
            ResultSet projectsResultSet = null;
            listOfProjects.clear();
            Statement projects;
            
            projects = connection.createStatement();
            projectsResultSet = projects.executeQuery("SELECT Project.projectID, Project.projectName, Project.priority, Project.clientRep, ClientRep.clientRepID, User.userID, User.firstName, User.surname FROM ([User] INNER JOIN ClientRep ON User.[userID] = ClientRep.[clientRepID]) INNER JOIN Project ON ClientRep.[clientRepID] = Project.[clientRep];");
            
            Project project;
            int projectID;
            String projectName;
            User teamLeader;
            User clientRep;
            int priority;
            SetOfElements elementCollection;
            SetOfQCReports reports;
            SetOfStaff setOfStaff;
            SetOfTasks projectTasks;
            
            while(projectsResultSet.next())
            {
                projectID = projectsResultSet.getInt("projectId");
                projectName = projectsResultSet.getString("ProjectName");
//                teamLeader=;
//                clientRep=;
                priority = projectsResultSet.getInt("priority");
             
                //project = new Project(projectID, projectName, teamLeader, clientRep, priority, projectTasks, elementCollection, reports, setOfStaff);
                //listOfProjects.add(project);

                int clientRepId = projectsResultSet.getInt("userID");;
                String clientRepFirstName = projectsResultSet.getString("firstName");
                String clientrepSurname = projectsResultSet.getString("surname");
                clientRep =  new User(clientRepId,clientRepFirstName,clientrepSurname);
                
                project = new Project(projectID,projectName,priority,clientRep);
                listOfProjects.add(project);
                listProjectsList.setListData(listOfProjects);
                ProjectListCellRenderer renderer = new ProjectListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                listProjectsList.setCellRenderer(renderer);
                //Project(int projectID, ProjectComponent rootComponent, SetOfTasks projectTasks, User teamLeader, User clientRep, int priority, SetOfComponents componentCollection, SetOfQCReports reports)
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
          
    }
    
    private void fillInStaffOnProjectList(int projectID){
        listOfUsers.clear();
        if (projectID >=1)
        {
            try{

                ResultSet staffOnProjectResultSet = null;

                Statement staff;

                staff = connection.createStatement();
               
                staffOnProjectResultSet = staff.executeQuery("SELECT Project.projectID, User.userID, User.firstName, User.surname, Staff.role FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID) ON User.userID = Staff.staffID WHERE Project.projectID=" + projectID + ";"); 


                int userID;
                String firstName;
                String surname;
                String role;

                while(staffOnProjectResultSet.next())
                {
                    userID = staffOnProjectResultSet.getInt("userID");
                    firstName = staffOnProjectResultSet.getString("firstName");
                    surname = staffOnProjectResultSet.getString("surname");
                    role = staffOnProjectResultSet.getString("role");

                    //User(int aUserID, String aFirstname, String aSurname, String aUsername, String aPassword, String aRole)
                    User user= new User(userID,firstName,surname, "","");
                    listOfUsers.add(user);
                    listStaffOnProject.setListData(listOfUsers);
                    UserListCellRenderer renderer = new UserListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
                    listStaffOnProject.setCellRenderer(renderer);

                }
            }catch(SQLException err)
                    {
                        System.out.println("ERROR: " + err);
                            JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                            System.exit(1);
                    }
        }
        else{
            listOfUsers.clear();
            listStaffOnProject.setListData(listOfUsers);
            listStaffOnProject.repaint();
        }
          
    }
    
    private void fillInComponentsOnProjectList(int projectID){
        try{
            ResultSet componentsOnProjectListResultSet = null;
            Statement projectComponents;
            
            projectComponents = connection.createStatement();
            componentsOnProjectListResultSet = projectComponents.executeQuery("SELECT * FROM Component WHERE componentID="+projectID+";"); //TO DO: replace this with proper query
            
            
            int componentID;
            String componentName;
            Date componentDate;
            SetOfElements componentElements;
            
            while(componentsOnProjectListResultSet.next())
            {
                componentID = componentsOnProjectListResultSet.getInt("componentID");
                componentName =componentsOnProjectListResultSet.getString("componentName");


                //ProjectComponent(int componentID, String componentName, Date componentDate, SetOfElements componentElements)
                //listOfComponents.add(projectComponent);
                //listComponentList.setListData(listOfComponents);
//                ProjectComponentsListCellRenderer renderer = new ProjectComponentsListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
               // listComponentList.setCellRenderer(renderer);
                
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
          
    }
    
    private void fillInTaskOnComponentsList(int projectComponentID){
    try{
        ResultSet taskOnComponentsListResultSet = null;
        listOfTasks.clear();
        listTasksList.repaint();
        Statement tasks;

        tasks = connection.createStatement();
        taskOnComponentsListResultSet = tasks.executeQuery("SELECT * FROM Task WHERE taskID="+projectComponentID+";"); //TO DO: replace this with proper query


        int taskID;
        User responsiblePerson;
        String taskName;
        int priority;
        String status;
        Project project;


        while(taskOnComponentsListResultSet.next())
        {
            taskID = taskOnComponentsListResultSet.getInt("taskID");
            taskName = taskOnComponentsListResultSet.getString("taskName");
            priority = taskOnComponentsListResultSet.getInt("taskPriority");
            status = taskOnComponentsListResultSet.getString("status");



            //Task(int taskID, User responsiblePerson, String taskName, int priority, String status, Project project)
            //Task task= new Task(taskID, null, taskName, priority, status, null );
            //listOfTasks.add(task);
            listTasksList.setListData(listOfTasks);
            TasksListCellRenderer renderer = new TasksListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
            listTasksList.setCellRenderer(renderer);

        }
    }catch(SQLException err)
            {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                    System.exit(1);
            }

    }
    
    private void deleteProject(int projectID){
            
        try {  
            Statement projects;
            projects = connection.createStatement();
            projects.executeUpdate("DELETE FROM Task WHERE projectID = " + projectID + ";");            
            projects.executeUpdate("DELETE FROM Project WHERE projectID="+ projectID+";");
            projects.executeUpdate("DELETE FROM StaffOnProjects WHERE projectID = " + projectID + ";");
            projects.executeUpdate("DELETE FROM QCReport WHERE projectID = " + projectID + ";");

              
        } catch (SQLException err) {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                    System.exit(1);
        }
    }
    
    private void deleteStaffOnProject(int userID) {
        try {  
        Statement staff;
        staff = connection.createStatement();

        staff.executeUpdate("DELETE FROM StaffOnProjects WHERE projectID = " + selectedProject.getProjectID() + " AND staffID = " + userID + ";");
              
        } catch (SQLException err) {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *"); //TO DO: replace this with proper query
                    System.exit(1);
        }
    }
    
    private void deleteComponentOnProject(int componentID) {
        try {  
        Statement component;
        component = connection.createStatement();

        component.executeUpdate("DELETE FROM Component WHERE componentID="+ componentID+";"); //TO DO: replace this with proper query

        } catch (SQLException err) {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                    System.exit(1);
        }
    }
    
    private void deleteTaskOnComponent(int taskID) {
        try {  
        Statement task;
        task = connection.createStatement();

        task.executeUpdate("DELETE FROM Task WHERE taskID="+ taskID+";"); //TO DO: replace this with proper query

        } catch (SQLException err) {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                    System.exit(1);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProjectsList = new javax.swing.JList();
        btnCreateProject = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRootComponent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPriority = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStaffOnProject = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listComponentList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listTasksList = new javax.swing.JList();
        btnRemoveStaffFromProject = new javax.swing.JButton();
        btnRemoveComponentsToProject = new javax.swing.JButton();
        btnRemoveTaskFromProject = new javax.swing.JButton();
        btnDeleteProject = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtClientRep = new javax.swing.JTextField();
        btnAddStaffToProject = new javax.swing.JButton();
        btnEditStaffOnProject = new javax.swing.JButton();
        btnEditComponentsOnProject = new javax.swing.JButton();
        btnEditTaskOnProject = new javax.swing.JButton();
        btnAddTaskToProject = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("List Of Projects:");

        listProjectsList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        listProjectsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listProjectsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listProjectsList);

        btnCreateProject.setText("Create New Project");
        btnCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProjectActionPerformed(evt);
            }
        });

        jLabel3.setText("Project Details:");

        jLabel4.setText("Root Component:");

        txtRootComponent.setEditable(false);

        jLabel5.setText("Priority:");

        txtPriority.setEditable(false);

        jLabel6.setText("Project Staff:");

        jScrollPane2.setViewportView(listStaffOnProject);

        jLabel7.setText("Elements");

        listComponentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listComponentListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listComponentList);

        jLabel8.setText("Tasks:");

        jScrollPane4.setViewportView(listTasksList);

        btnRemoveStaffFromProject.setText("Remove");
        btnRemoveStaffFromProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStaffFromProjectActionPerformed(evt);
            }
        });

        btnRemoveComponentsToProject.setText("Remove");
        btnRemoveComponentsToProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveComponentsToProjectActionPerformed(evt);
            }
        });

        btnRemoveTaskFromProject.setText("Remove");
        btnRemoveTaskFromProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTaskFromProjectActionPerformed(evt);
            }
        });

        btnDeleteProject.setText("Delete Project");
        btnDeleteProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProjectActionPerformed(evt);
            }
        });

        jLabel2.setText("Client Rep:");

        txtClientRep.setEditable(false);

        btnAddStaffToProject.setText("Add");
        btnAddStaffToProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStaffToProjectActionPerformed(evt);
            }
        });

        btnEditStaffOnProject.setText("Edit");

        btnEditComponentsOnProject.setText("Edit");

        btnEditTaskOnProject.setText("Edit");

        btnAddTaskToProject.setText("Add");

        jButton1.setText("Add Element");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh View");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(btnCreateProject, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnDeleteProject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRootComponent, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(txtPriority)))
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(41, 41, 41)
                            .addComponent(txtClientRep, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAddStaffToProject)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditStaffOnProject)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRemoveStaffFromProject)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRemoveComponentsToProject, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditComponentsOnProject)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddTaskToProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditTaskOnProject)
                        .addGap(11, 11, 11)
                        .addComponent(btnRemoveTaskFromProject))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateProject)
                        .addGap(9, 9, 9)
                        .addComponent(btnDeleteProject))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtRootComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAddStaffToProject)
                                .addComponent(btnEditStaffOnProject))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRemoveStaffFromProject)
                                .addComponent(jButton1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAddTaskToProject)
                                .addComponent(btnEditTaskOnProject))
                            .addComponent(btnRemoveTaskFromProject))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEditComponentsOnProject)
                        .addGap(8, 8, 8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtClientRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemoveComponentsToProject)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(104, 104, 104))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProjectActionPerformed
        new CreateProjectUI().setVisible(true);
    }//GEN-LAST:event_btnCreateProjectActionPerformed

    private void listProjectsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listProjectsListValueChanged
       selectedProject = (Project) listProjectsList.getSelectedValue();
       listOfUsers.clear();
       //listOfComponents.clear();
       listOfTasks.clear();
       if (selectedProject !=null)
       {
        txtPriority.setText(String.valueOf(selectedProject.getPriority())); 
        txtClientRep.setText(selectedProject.getClientRep().getFirstName() + " " + selectedProject.getClientRep().getSurname());

        fillInStaffOnProjectList(selectedProject.getProjectID());
        fillInComponentsOnProjectList(selectedProject.getProjectID());
       }
       else
       {
        txtRootComponent.setText("");  
        txtPriority.setText(""); 
        txtClientRep.setText("");
        
        fillInStaffOnProjectList(0);
        fillInComponentsOnProjectList(0);
       }
       listTasksList.repaint();
       listComponentList.repaint();
       listStaffOnProject.repaint();


    }//GEN-LAST:event_listProjectsListValueChanged

    private void listComponentListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listComponentListValueChanged
       /* selectedComponent = (ProjectComponent) listComponentList.getSelectedValue();
        if(selectedComponent != null)
            fillInTaskOnComponentsList(selectedComponent.getComponentID());*/
    }//GEN-LAST:event_listComponentListValueChanged

    private void btnDeleteProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProjectActionPerformed
        if(selectedProject !=null)
        {
            selectedProject = (Project) listProjectsList.getSelectedValue();
            deleteProject(selectedProject.getProjectID());
            fillInProjectsList();
            listProjectsList.repaint();
        }
    }//GEN-LAST:event_btnDeleteProjectActionPerformed

    private void btnRemoveStaffFromProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStaffFromProjectActionPerformed
        selectedStaff = (User)listStaffOnProject.getSelectedValue();
        if(selectedStaff !=null && selectedProject !=null)
        {
            deleteStaffOnProject(selectedStaff.getUserID());
            fillInStaffOnProjectList(selectedProject.getProjectID());
        }
        listStaffOnProject.repaint();
    }//GEN-LAST:event_btnRemoveStaffFromProjectActionPerformed

    private void btnRemoveComponentsToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveComponentsToProjectActionPerformed
       /* selectedComponent = (ProjectComponent)listComponentList.getSelectedValue();
         if(selectedComponent !=null && selectedProject !=null)
        {
            deleteComponentOnProject(selectedComponent.getComponentID());
            fillInComponentsOnProjectList(selectedProject.getProjectID());
        }
        listComponentList.repaint();     */ 
    }//GEN-LAST:event_btnRemoveComponentsToProjectActionPerformed

    private void btnRemoveTaskFromProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTaskFromProjectActionPerformed
       /* selectedTask = (Task)listTasksList.getSelectedValue();
         if(selectedTask !=null && selectedComponent !=null && selectedProject !=null)
        {
            deleteTaskOnComponent(selectedTask.getTaskID());
            fillInTaskOnComponentsList(selectedComponent.getComponentID());
        }
        listTasksList.repaint(); */
    }//GEN-LAST:event_btnRemoveTaskFromProjectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Project mySelectedProject = (Project) listProjectsList.getSelectedValue();
        new AddElement(mySelectedProject.getProjectID()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAddStaffToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffToProjectActionPerformed
        if(selectedProject !=null)
            new AddStaffUI(selectedProject).setVisible(true);
    }//GEN-LAST:event_btnAddStaffToProjectActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        fillInProjectsList();
        fillInStaffOnProjectList(0);
        listProjectsList.repaint();
        listStaffOnProject.repaint();
    }//GEN-LAST:event_btnRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManagerUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStaffToProject;
    private javax.swing.JButton btnAddTaskToProject;
    private javax.swing.JButton btnCreateProject;
    private javax.swing.JButton btnDeleteProject;
    private javax.swing.JButton btnEditComponentsOnProject;
    private javax.swing.JButton btnEditStaffOnProject;
    private javax.swing.JButton btnEditTaskOnProject;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemoveComponentsToProject;
    private javax.swing.JButton btnRemoveStaffFromProject;
    private javax.swing.JButton btnRemoveTaskFromProject;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList listComponentList;
    private javax.swing.JList listProjectsList;
    private javax.swing.JList listStaffOnProject;
    private javax.swing.JList listTasksList;
    private javax.swing.JTextField txtClientRep;
    private javax.swing.JTextField txtPriority;
    private javax.swing.JTextField txtRootComponent;
    // End of variables declaration//GEN-END:variables


}
