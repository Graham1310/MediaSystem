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
import javax.swing.JOptionPane;

/**
 *
 * @author Graham
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Declares variable required
     */
    private SetOfProjects listOfUserProjects = new SetOfProjects();
    private SetOfUsers listOfUsers = new SetOfUsers();
    private SetOfElements listOfElements = new SetOfElements();
    private SetOfTasks listOfTasks = new SetOfTasks();
    private SetOfAssets listOfAssets = new SetOfAssets();
    private SetOfAssets selectedAssets = new SetOfAssets();
    private Project selectedProject;
    private User selectedStaff;
    private Task selectedTask;
    private Element selectedElement;
    private Asset selectedAsset;
    private Asset selectedAssetToRemove;
    private  randomSQLFunctionsReady randSQL = new randomSQLFunctionsReady();
    private int userLoggedIn;
  
    /**
     * Creates new form MainUI
     * Initialises components
     */
    public MainUI(int userLoggedInID) {
        this.userLoggedIn = userLoggedInID;
        initComponents();
        fillInProjectsList(userLoggedIn);           
    }

   private void fillInProjectsList(int userID){
        try{
            ResultSet projectsResultSet = null;
            listOfUserProjects.clear();
            Statement statement;
            
            statement = connection.createStatement();
            projectsResultSet = statement.executeQuery("SELECT User.userID, Project.projectID, Project.projectName, "
                  + "Project.teamLeader, Project.clientRep, Project.priority FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN "
                          + "StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID)"                     
                          + " ON User.userID = Staff.staffID WHERE User.userID=" + userID + ";");
 
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
                int clientRepId = projectsResultSet.getInt("clientRep");
                clientRep =  getClientRep(clientRepId);
                project = new Project(projectID,projectName,priority,clientRep);
                listOfUserProjects.add(project);
                listUserProjectsList.setListData(listOfUserProjects);
                ProjectListCellRenderer renderer = new ProjectListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                listUserProjectsList.setCellRenderer(renderer);
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
    }   
   
   private User getClientRep(int clientRepID){
       User clientRep=null;
       String clientRepFirstName;
       String clientRepSurname;
       try{
            ResultSet clientRepresultSet = null;
            Statement statement;
            statement = connection.createStatement();
            clientRepresultSet = statement.executeQuery("SELECT firstName, surname FROM User WHERE userID="+ clientRepID +";");
                
            while(clientRepresultSet.next())
            {
                clientRepFirstName = clientRepresultSet.getString("firstName");
                String clientrepSurname = clientRepresultSet.getString("surname");
                clientRep = new User(clientRepID,clientRepFirstName,clientrepSurname);
                return clientRep;
            } 
         }catch(SQLException err)
                 {
                     System.out.println("ERROR: " + err);
                         JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                         System.exit(1);
                 }
        return clientRep;      
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
                    User user= new User(userID,firstName,surname, "","");
                    listOfUsers.add(user);
                    listProjectStaffList.setListData(listOfUsers);
                    UserListCellRenderer renderer = new UserListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
                    listProjectStaffList.setCellRenderer(renderer);
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
            listProjectStaffList.setListData(listOfUsers);
            listProjectStaffList.repaint();
        }
    } 
    
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
    
    private void fillInAssetOnElementsList(int projectElementID){
    try{
        ResultSet assetsOnElementsListResultSet = null;
        listOfAssets.clear();
        listAssetsList.repaint();
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
            listAssetsList.setListData(listOfAssets);
            AssetsListCellRenderer renderer = new AssetsListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
            listAssetsList.setCellRenderer(renderer);  
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
     * @param asset 
     * Clear the UI and create connection
     * Select all tasks in the database for the passed in asset
     * For every task returned, load in all information on the task into the system
     * Display all tasks for the asset in the UI
     */
    private void fillInInboundTaskOnAssetList(Asset asset) {
           
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
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        listUserProjectsList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listProjectStaffList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listElementsList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listAssetsList = new javax.swing.JList();
        txtRootComponent = new javax.swing.JTextField();
        txtPriority = new javax.swing.JTextField();
        txtClientRep = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnCreateInboundQCTask = new javax.swing.JButton();
        btnEditInboundQCTask = new javax.swing.JButton();
        btnRemoveInboundQCTask = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTasksList = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listSelectedAssetsList = new javax.swing.JList();
        btnAddAssetToTheList = new javax.swing.JButton();
        btnRemoveAssetFromTheList = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("List of Your Projects:");

        listUserProjectsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUserProjectsListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listUserProjectsList);

        jLabel2.setText("Root Component:");

        jLabel3.setText("Priority:");

        jLabel4.setText("Project Staff:");

        jScrollPane3.setViewportView(listProjectStaffList);

        jLabel6.setText("Client Rep:");

        jLabel7.setText("Elements:");

        listElementsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listElementsListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listElementsList);

        jLabel8.setText("Assets:");

        listAssetsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listAssetsListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(listAssetsList);

        txtRootComponent.setEditable(false);

        txtPriority.setEditable(false);

        txtClientRep.setEditable(false);

        jLabel9.setText("Project Details:");

        btnCreateInboundQCTask.setText("Create Inbound QC Task For Selected Assets");
        btnCreateInboundQCTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateInboundQCTaskActionPerformed(evt);
            }
        });

        btnEditInboundQCTask.setText("Edit Inbound QC Task");

        btnRemoveInboundQCTask.setText("Remove Inbound QC Task");

        jLabel5.setText("Inbound Tasks:");

        jScrollPane1.setViewportView(listTasksList);

        jLabel10.setText("Selected Assets List:");

        listSelectedAssetsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSelectedAssetsListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(listSelectedAssetsList);

        btnAddAssetToTheList.setText("Add Asset To the List");
        btnAddAssetToTheList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAssetToTheListActionPerformed(evt);
            }
        });

        btnRemoveAssetFromTheList.setText("Remove Asset From the List");
        btnRemoveAssetFromTheList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAssetFromTheListActionPerformed(evt);
            }
        });

        jButton1.setText("Manage Your Tasks");
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
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCreateInboundQCTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditInboundQCTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRemoveInboundQCTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtRootComponent))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(57, 57, 57)
                                        .addComponent(txtPriority))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(44, 44, 44)
                                        .addComponent(txtClientRep, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7)
                                .addGap(144, 144, 144)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAddAssetToTheList, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnRemoveAssetFromTheList, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtClientRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddAssetToTheList)
                            .addComponent(btnRemoveAssetFromTheList)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtRootComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreateInboundQCTask)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditInboundQCTask)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveInboundQCTask)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 
     * @param evt 
     * On button click, re-fresh all lists in UI
     * Re-load all lists
     */
    private void listUserProjectsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUserProjectsListValueChanged
       selectedProject = (Project) listUserProjectsList.getSelectedValue();
       listOfUsers.clear();
       listOfElements.clear();
       listOfTasks.clear();
       listOfAssets.clear();
       selectedAssets.clear();
       
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
       listAssetsList.repaint();       
       listElementsList.repaint();
       listProjectStaffList.repaint();
       listSelectedAssetsList.repaint();
    }//GEN-LAST:event_listUserProjectsListValueChanged

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
     * Fill QC tasks related to asset
     */    
    private void listAssetsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAssetsListValueChanged
        selectedAsset = (Asset) listAssetsList.getSelectedValue();
        if(selectedAsset != null)
            fillInInboundTaskOnAssetList(selectedAsset);          
    }//GEN-LAST:event_listAssetsListValueChanged

    private void btnAddAssetToTheListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAssetToTheListActionPerformed
        selectedAsset = (Asset) listAssetsList.getSelectedValue();
        boolean exists=false; // check if asset id exists (can't check for object because of different hash code)
        if(selectedAsset != null){
            for (Asset asset: selectedAssets){ 
                    if(asset.getAssetID() == selectedAsset.getAssetID())
                        exists = true;
                }

                if(!exists){
                    selectedAssets.add(selectedAsset);
                    fillInSelectedAssets(selectedAssets);
                }
       }
    }//GEN-LAST:event_btnAddAssetToTheListActionPerformed

    private void btnRemoveAssetFromTheListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAssetFromTheListActionPerformed
        selectedAssetToRemove = (Asset) listSelectedAssetsList.getSelectedValue();
        if(selectedAssetToRemove != null){
            selectedAssets.remove(selectedAssetToRemove);
            fillInSelectedAssets(selectedAssets);
       }   
    }//GEN-LAST:event_btnRemoveAssetFromTheListActionPerformed

    private void listSelectedAssetsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSelectedAssetsListValueChanged
        selectedAssetToRemove = (Asset) listSelectedAssetsList.getSelectedValue();
    }//GEN-LAST:event_listSelectedAssetsListValueChanged

    private void btnCreateInboundQCTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateInboundQCTaskActionPerformed
        new AddQCTaskUI(selectedProject.getProjectID(),selectedAssets).setVisible(true);
    }//GEN-LAST:event_btnCreateInboundQCTaskActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ManageTasksUI(userLoggedIn).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fillInSelectedAssets(SetOfAssets aSelectedAssets)
    {
           listSelectedAssetsList.setListData(aSelectedAssets);
           AssetsListCellRenderer renderer = new AssetsListCellRenderer();  //custom cell renderer to display property rather than useless object.toString()
           listSelectedAssetsList.setCellRenderer(renderer);  
           listSelectedAssetsList.repaint();       
    }
    
    /**
     * @param args the command line arguments
     * Create and display the form
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//               new MainUI().setVisible(true);               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAssetToTheList;
    private javax.swing.JButton btnCreateInboundQCTask;
    private javax.swing.JButton btnEditInboundQCTask;
    private javax.swing.JButton btnRemoveAssetFromTheList;
    private javax.swing.JButton btnRemoveInboundQCTask;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList listAssetsList;
    private javax.swing.JList listElementsList;
    private javax.swing.JList listProjectStaffList;
    private javax.swing.JList listSelectedAssetsList;
    private javax.swing.JList listTasksList;
    private javax.swing.JList listUserProjectsList;
    private javax.swing.JTextField txtClientRep;
    private javax.swing.JTextField txtPriority;
    private javax.swing.JTextField txtRootComponent;
    // End of variables declaration//GEN-END:variables
}
