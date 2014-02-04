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

    /**
     * Declares variables required
     */
    private SetOfProjects listOfProjects = new SetOfProjects();
    private SetOfUsers listOfUsers = new SetOfUsers();
    private SetOfElements listOfElements = new SetOfElements();
    private SetOfTasks listOfTasks = new SetOfTasks();
    private SetOfAssets listOfAssets = new SetOfAssets();
    private Project selectedProject;
    private User selectedStaff;
    private Task selectedTask;
    private Element selectedElement;
    private Asset selectedAsset;
    private  randomSQLFunctionsReady randSQL = new randomSQLFunctionsReady();
    
    /**
     * Creates new form ManagerUI
     */
    public ManagerUI() {
        initComponents();
        fillInProjectsList();        
    }
    
    /**
     * Create connection
     * Select all projects from the database
     * For every project in the database, load it into the system
     */
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
                priority = projectsResultSet.getInt("priority");
                int clientRepId = projectsResultSet.getInt("clientRepID");;
                String clientRepFirstName = projectsResultSet.getString("firstName");
                String clientrepSurname = projectsResultSet.getString("surname");
                clientRep =  new User(clientRepId,clientRepFirstName,clientrepSurname);
                project = new Project(projectID,projectName,priority,clientRep);
                listOfProjects.add(project);
                listProjectsList.setListData(listOfProjects);
                ProjectListCellRenderer renderer = new ProjectListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                listProjectsList.setCellRenderer(renderer);
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
    }
    /**
     * 
     * @param projectID 
     * Pass in projectID
     * Select everything from the project in the database with the ID, and load to system
     * Display project in UI
     */
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
    
    /**
     * 
     * @param projectID
     * Pass in projectID and create connection
     * Select elements from database that belong to selected project
     * Load all elements into system and display in UI
     */
    private void fillInElementsOnProjectList(int projectID){
        listOfElements.clear();
        if (projectID >=1){
            try{
                ResultSet elementsOnProjectListResultSet = null;
                Statement statement;
                statement = connection.createStatement();
                elementsOnProjectListResultSet = statement.executeQuery("SELECT Element.elementID, Element.elementName, SetOFElements.ProjectID FROM Element INNER JOIN SetOFElements ON Element.[elementID] = SetOFElements.[elementID] WHERE ProjectID="+ projectID +";"); 
                int elementID;
                String elementName;

                while(elementsOnProjectListResultSet.next())
                {
                    elementID = elementsOnProjectListResultSet.getInt("elementID");
                    elementName = elementsOnProjectListResultSet.getString("elementName");
                    Element element = new Element(elementID,elementName);
                    listOfElements.add(element);
                    listElementsList.setListData(listOfElements);
                    ProjectElementsListCellRenderer renderer = new ProjectElementsListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                    listElementsList.setCellRenderer(renderer);
                }
            }catch(SQLException err)
                    {
                        System.out.println("ERROR: " + err);
                        JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                        System.exit(1);
                    }
        }else{
            listOfElements.clear();
            listElementsList.setListData(listOfElements);
            listElementsList.repaint();
        }
    }
    
    /**
     * 
     * @param projectElementID
     * Pass in ProjectElementID
     * Create connection and select all assets belonging to element selected
     * For each asset, load to system and add to element
     */
    private void fillInAssetOnElementsList(int projectElementID){
    try{
        ResultSet assetsOnElementsListResultSet = null;
        listOfAssets.clear();
        listAssetList.repaint();
        Statement statement;
        Element element = (Element) listElementsList.getSelectedValue();
        int SElementID = element.getElementID();

        statement = connection.createStatement();
        assetsOnElementsListResultSet = statement.executeQuery("SELECT Asset.ID,Asset.assetName, Asset.assetType, SetOFAssets.elementID" +
                                                " FROM Asset LEFT JOIN SetOFAssets ON Asset.ID = SetOFAssets.assetID" +
                                                " WHERE (((SetOFAssets.elementID) = " + SElementID  + " ));");
        int assetID;
        String assetName;      
        String assetType; 
        SetOfTasks assetTasks = new SetOfTasks();
        
        while(assetsOnElementsListResultSet.next()){
            assetID = assetsOnElementsListResultSet.getInt("ID");
            assetName = assetsOnElementsListResultSet.getString("assetName");
            assetType = assetsOnElementsListResultSet.getString("assetType");                      
            Asset newAsset = new Asset(assetID, assetName, assetType, assetTasks);
            listOfAssets.add(newAsset);
            listAssetList.setListData(listOfAssets);
            AssetsListCellRenderer renderer = new AssetsListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
            listAssetList.setCellRenderer(renderer);  
        }
    }catch(SQLException err)
            {
                System.out.println("ERROR: " + err);
                    JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                    System.exit(1);
            }
    }
    
    /**
     * 
     * @param projectID 
     * Pass in project and create statement
     * Delete everything associated with the project in the database
     */
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
    
    /**
     * 
     * @param userID 
     * Pass in User and create statement
     * Delete all from StaffOnProjects where the user is on the same project as the selected project
     */
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
    
    /**
     * 
     * @param taskID 
     * Pass in taskID and create statement
     * Delete task in database with taskID passed in
     */
    private void deleteTask(int taskID) {
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
        listElementsList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listTasksList = new javax.swing.JList();
        btnRemoveStaffFromProject = new javax.swing.JButton();
        btnRemoveElementsOnProject = new javax.swing.JButton();
        btnRemoveTaskFromProject = new javax.swing.JButton();
        btnDeleteProject = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtClientRep = new javax.swing.JTextField();
        btnAddStaffToProject = new javax.swing.JButton();
        btnEditStaffOnProject = new javax.swing.JButton();
        btnEditComponentsOnProject = new javax.swing.JButton();
        btnEditTaskOnProject = new javax.swing.JButton();
        btnAddTaskToProject = new javax.swing.JButton();
        btnAddElement = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnAddAsset = new javax.swing.JButton();
        btnEditAsset = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listAssetList = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        btnRemoveAsset = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        contentHeirachyBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("List Of Projects:");

        listProjectsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        listElementsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listElementsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listElementsListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listElementsList);

        jLabel8.setText("Tasks:");

        listTasksList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(listTasksList);

        btnRemoveStaffFromProject.setText("Remove");
        btnRemoveStaffFromProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStaffFromProjectActionPerformed(evt);
            }
        });

        btnRemoveElementsOnProject.setText("Remove");
        btnRemoveElementsOnProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveElementsOnProjectActionPerformed(evt);
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
        btnAddTaskToProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskToProjectActionPerformed(evt);
            }
        });

        btnAddElement.setText("Add");
        btnAddElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddElementActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh View");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnAddAsset.setText("Add");
        btnAddAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAssetActionPerformed(evt);
            }
        });

        btnEditAsset.setText("Edit");

        listAssetList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listAssetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listAssetListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(listAssetList);

        jLabel9.setText("Assets");

        btnRemoveAsset.setText("Remove");
        btnRemoveAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAssetActionPerformed(evt);
            }
        });

        jButton1.setText("Project Overview");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        contentHeirachyBtn.setText("Content Heriachy");
        contentHeirachyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentHeirachyBtnActionPerformed(evt);
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
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contentHeirachyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddElement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditComponentsOnProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoveElementsOnProject, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddAsset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditAsset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddTaskToProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditTaskOnProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveTaskFromProject))
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCreateProject)
                                .addGap(9, 9, 9)
                                .addComponent(btnDeleteProject)
                                .addGap(7, 7, 7)
                                .addComponent(jButton1)
                                .addGap(9, 9, 9)
                                .addComponent(contentHeirachyBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtRootComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane4))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAddStaffToProject)
                                .addComponent(btnEditStaffOnProject))
                            .addComponent(btnRemoveStaffFromProject)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddElement)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRemoveElementsOnProject)
                                .addComponent(btnEditComponentsOnProject)
                                .addComponent(btnAddAsset)
                                .addComponent(btnEditAsset)
                                .addComponent(btnRemoveAsset)
                                .addComponent(btnAddTaskToProject)
                                .addComponent(btnEditTaskOnProject)
                                .addComponent(btnRemoveTaskFromProject)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtClientRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(104, 104, 104))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * On button click, open CreateProjectUI 
     * @param evt 
     */
    private void btnCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProjectActionPerformed
        new CreateProjectUI().setVisible(true);
    }//GEN-LAST:event_btnCreateProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click, re-fresh all lists in UI
     * Re-load all lists
     */
    private void listProjectsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listProjectsListValueChanged
       selectedProject = (Project) listProjectsList.getSelectedValue();
       listOfUsers.clear();
       listOfElements.clear();
       listOfTasks.clear();
       listOfAssets.clear();
       listAssetList.repaint();
       if (selectedProject !=null)
       {
        txtPriority.setText(String.valueOf(selectedProject.getPriority())); 
        txtClientRep.setText(selectedProject.getClientRep().getFirstName() + " " + selectedProject.getClientRep().getSurname());
        fillInStaffOnProjectList(selectedProject.getProjectID());
        fillInElementsOnProjectList(selectedProject.getProjectID());
       }
       else
       {
        txtRootComponent.setText("");  
        txtPriority.setText(""); 
        txtClientRep.setText("");
        fillInStaffOnProjectList(0);
        fillInElementsOnProjectList(0);
       }
       listTasksList.repaint();
       listElementsList.repaint();
       listStaffOnProject.repaint();
    }//GEN-LAST:event_listProjectsListValueChanged

    /**
     * 
     * @param evt 
     * On button click, clear lists
     * Re-load lists
     */
    private void listElementsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listElementsListValueChanged
        listOfTasks.clear();
        listTasksList.repaint();
        selectedElement = (Element) listElementsList.getSelectedValue();
        if(selectedElement != null)
            fillInAssetOnElementsList(selectedElement.getElementID());
    }//GEN-LAST:event_listElementsListValueChanged
/**
 * 
 * @param evt 
 * On button click
 * Call function to delete selected project
 * Refresh UI
 */
    private void btnDeleteProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProjectActionPerformed
        if(selectedProject !=null)
        {
            selectedProject = (Project) listProjectsList.getSelectedValue();
            deleteProject(selectedProject.getProjectID());
            fillInProjectsList();
            listProjectsList.repaint();
        }
    }//GEN-LAST:event_btnDeleteProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * Get selected staff and project
     * Call function to delete selected staff from project
     */
    private void btnRemoveStaffFromProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStaffFromProjectActionPerformed
        selectedStaff = (User)listStaffOnProject.getSelectedValue();
        if(selectedStaff !=null && selectedProject !=null)
        {
            deleteStaffOnProject(selectedStaff.getUserID());
            fillInStaffOnProjectList(selectedProject.getProjectID());
        }
        listStaffOnProject.repaint();
    }//GEN-LAST:event_btnRemoveStaffFromProjectActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnRemoveElementsOnProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveElementsOnProjectActionPerformed
       
    }//GEN-LAST:event_btnRemoveElementsOnProjectActionPerformed
/**
 * 
 * @param evt 
 */
    private void btnRemoveTaskFromProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTaskFromProjectActionPerformed
       /* selectedTask = (Task)listTasksList.getSelectedValue();
         if(selectedTask !=null && selectedComponent !=null && selectedProject !=null)
        {
            deleteTaskOnComponent(selectedTask.getTaskID());
            fillInTaskOnElementsList(selectedComponent.getComponentID());
        }
        listTasksList.repaint(); */
    }//GEN-LAST:event_btnRemoveTaskFromProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click, get selected project
     * Open up AddElement UI for selected project
     */
    private void btnAddElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddElementActionPerformed
       Project mySelectedProject = (Project) listProjectsList.getSelectedValue();
        new AddElement(mySelectedProject.getProjectID()).setVisible(true);
    }//GEN-LAST:event_btnAddElementActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * If a project is selected, open AddStaffUI
     */
    private void btnAddStaffToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffToProjectActionPerformed
        if(selectedProject !=null)
            new AddStaffUI(selectedProject).setVisible(true);
    }//GEN-LAST:event_btnAddStaffToProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * Refresh the lists in the UI
     */
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        fillInProjectsList();
        fillInStaffOnProjectList(0);
        listProjectsList.repaint();
        listStaffOnProject.repaint();
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * Call function to add selected asset to element
     */
    private void btnAddAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAssetActionPerformed
      Element selectedElement = (Element) listElementsList.getSelectedValue();
        new AddAssetToElement(selectedElement.getElementID()).setVisible(true);
    }//GEN-LAST:event_btnAddAssetActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * Fill tasks related to asset
     */
    private void listAssetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAssetListValueChanged
        selectedAsset = (Asset) listAssetList.getSelectedValue();
        if(selectedAsset != null)
            fillInTaskOnAssetList(selectedAsset);            
    }//GEN-LAST:event_listAssetListValueChanged

    private void btnRemoveAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAssetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveAssetActionPerformed

    /**
     * 
     * @param evt
     * On button click
     * Open AddTaskUI and pass in asset and project
     */
    private void btnAddTaskToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskToProjectActionPerformed
        Project selectedProject = (Project) listProjectsList.getSelectedValue();
         Asset SelectedAsset = (Asset) listAssetList.getSelectedValue();
         new AddTaskUI(SelectedAsset.getAssetID(), selectedProject.getProjectID()).setVisible(true);
    }//GEN-LAST:event_btnAddTaskToProjectActionPerformed

    /**
     * 
     * @param evt 
     * On button click
     * Open ProjectOverview UI and pass in selected project
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Project selectedProject = (Project) listProjectsList.getSelectedValue();
        new ProjectOverview(selectedProject.getProjectID()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void contentHeirachyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentHeirachyBtnActionPerformed
        new ContentHeirachy(selectedProject, listOfProjects).setVisible(true);
    }//GEN-LAST:event_contentHeirachyBtnActionPerformed

    /**
     * @param args the command line arguments
     * Create and display the form
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ManagerUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAsset;
    private javax.swing.JButton btnAddElement;
    private javax.swing.JButton btnAddStaffToProject;
    private javax.swing.JButton btnAddTaskToProject;
    private javax.swing.JButton btnCreateProject;
    private javax.swing.JButton btnDeleteProject;
    private javax.swing.JButton btnEditAsset;
    private javax.swing.JButton btnEditComponentsOnProject;
    private javax.swing.JButton btnEditStaffOnProject;
    private javax.swing.JButton btnEditTaskOnProject;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemoveAsset;
    private javax.swing.JButton btnRemoveElementsOnProject;
    private javax.swing.JButton btnRemoveStaffFromProject;
    private javax.swing.JButton btnRemoveTaskFromProject;
    private javax.swing.JButton contentHeirachyBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList listAssetList;
    private javax.swing.JList listElementsList;
    private javax.swing.JList listProjectsList;
    private javax.swing.JList listStaffOnProject;
    private javax.swing.JList listTasksList;
    private javax.swing.JTextField txtClientRep;
    private javax.swing.JTextField txtPriority;
    private javax.swing.JTextField txtRootComponent;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     * @param asset 
     * Clear the UI and create connection
     * Select all tasks in the database for the passed in asset
     * For every task returned, load in all information on the task into the system
     * Display all tasks for the asset in the UI
     */
    private void fillInTaskOnAssetList(Asset asset) {
           
        listOfTasks.clear();
        listTasksList.repaint();
        ResultSet tasksOnAssetListResultSet = null;   
        Statement statement;
        try {
            statement = connection.createStatement();
            tasksOnAssetListResultSet = statement.executeQuery("SELECT * FROM Task WHERE assetID = " + asset.getAssetID() + ";");  
                         randSQL.loadAllUsers();
                         SetOfUsers allusers = randSQL.getAllUsers();

                        while (tasksOnAssetListResultSet.next()){
                            int taskID;
                            int projectID;                        
                            User responsible = null;
                            int taskPriority;
                            String status;
                            String taskName;
                            String taskType;
                            taskID = tasksOnAssetListResultSet.getInt("taskID");
                            projectID = tasksOnAssetListResultSet.getInt("projectID");
   
                            for(int i=0; i<allusers.size();i++){
                                if(tasksOnAssetListResultSet.getInt("responsiblePerson")==allusers.get(i).getUserID());
                                {
                                    responsible = allusers.get(i);
                                    break;
                                }     
                             }                 
                              taskPriority = tasksOnAssetListResultSet.getInt("taskPriority");
                              status = tasksOnAssetListResultSet.getString("status");
                              taskName = tasksOnAssetListResultSet.getString("taskName");
                              taskType = tasksOnAssetListResultSet.getString("type");                   
                              Task newTask = new Task(taskID, responsible,taskName, taskPriority , status,projectID, asset, taskType);                     
                              listOfTasks.add(newTask);
                              listTasksList.setListData(listOfTasks);
                              TasksListCellRenderer renderer = new TasksListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
                              listTasksList.setCellRenderer(renderer);                 
                         }                     
                        asset.setSetOfTasks(listOfTasks);
                         } catch (SQLException ex) {
            Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
