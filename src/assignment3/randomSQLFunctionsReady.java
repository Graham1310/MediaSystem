/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.Date;
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
public class randomSQLFunctionsReady {
     
            int userID;
            String firstName;
            String surname;
            String username;
            String password;
            
            int passwordID = 2;
            String projectName;
            int rootComponent;
            int teamLeader;
            int clientRep;
            int priority;
            
            int taskID;
            int projectID;
            int responsiblePerson;
            int taskPriority;
            String status;
            String taskName;
            
            int assetID;
            String assetName;
            String assetType;
            
            int elementID;
            String elementName;
    
            SetOfQCReports allQCReports = new SetOfQCReports();
            SetOfQCComments allComments = new SetOfQCComments();
            SetOfTasks usersTasks = new SetOfTasks();
            SetOfTasks allTasks = new SetOfTasks();
            SetOfUsers allUsers = new SetOfUsers();
            SetOfClients allClients = new SetOfClients();
            SetOfClientReps allClientReps = new SetOfClientReps();
            SetOfStaff allStaff = new SetOfStaff();
            SetOfAssets allAssets = new SetOfAssets();
            SetOfElements allElements = new SetOfElements();
            SetOfProjects allProjects = new SetOfProjects();
            User UserLoggedIn;
    
    
    private void loadAllUsers(){//COMPLETE
        try {        
            ResultSet dbAllUsers = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllUsers = statement.executeQuery( "SELECT User.userID, User.firstName, User.surname, User.username, User.password FROM [User];");                     

            while(dbAllUsers.next())
            {
                User tempUser = new User(dbAllUsers.getInt("userID"), dbAllUsers.getString("firstName"), dbAllUsers.getString("surname"),
                        dbAllUsers.getString("userName"), dbAllUsers.getString("password"), dbAllUsers.getString("role"));
                allUsers.addUser(tempUser);
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
                Client newClient = new Client(dbAllClients.getInt("clientID"), dbAllClients.getString("organisationName"), dbAllClients.getString("address1 + address2 + address3 + Town + postcode + country"));
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
                Asset tempAsset = new Asset(dbAllAssets.getInt("ID"), dbAllAssets.getString("assetName"), dbAllAssets.getString("type"));
                allAssets.addAsset(tempAsset);
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
    
    private void loadAllQCComments(){
                try {
                    allComments = null;
                    ResultSet dbAllQCComments = null;
                    Statement statement;
                    statement = connection.createStatement();
                    dbAllQCComments = statement.executeQuery( "SELECT QCComment.qCCommentID, QCComment.qCReportID, QCComment.qCDate, "
                            + "QCComment.qCCommentText, QCComment.qCSeverityRating, QCComment.qCTimeInSeconds, QCComment.assetID FROM QCComment;"); 
                    
                    while(dbAllQCComments.next()){
                        
                        QCComments newComment = new QCComments(dbAllQCComments.getInt("qCCommentID"),dbAllQCComments.getInt("qCReportID"), dbAllQCComments.getDate("qCDate"),
                                dbAllQCComments.getString("qCCommentText"), dbAllQCComments.getInt("qCSeverityRating"), dbAllQCComments.getInt("qCTimeInSeconds"),
                                dbAllQCComments.getInt("assetID"));
                        allComments.addQCComments(newComment);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void loadAllQCReports(){
                try {
                    allQCReports = null;
                    ResultSet dbAllQCReports = null;
                    Statement statement;
                    statement = connection.createStatement();
                    dbAllQCReports = statement.executeQuery("SELECT QCReport.qCReportID, QCReport.projectID, QCReport.reviewer,"
                                                             + " QCReport.overallSeverityRating FROM QCReport;");
                    
                    while (dbAllQCReports.next()){
                        SetOfQCComments reportComments = new SetOfQCComments();
                        User reviewer;
                        for(int i=0;i<allComments.size();i++){
                            if(allComments.get(i).getcQCommentID()==dbAllQCReports.getInt("qCReportID")){
                                reportComments.addQCComments(allComments.get(i));
                            }
                        }
                        for(int i=0;i<allUsers.size();i++){
                            if(allUsers.get(i).getUserID()==dbAllQCReports.getInt("reviewer")){
                                reviewer = allUsers.get(i);
                            }
                        }
                        
                        QCReport newReport = new QCReport(dbAllQCReports.getInt("qCReportID"),dbAllQCReports.getInt("projectID"),
                                reportComments, reviewer, dbAllQCReports.getInt("overallSeverityRating"));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    
    private void loadAllProjects(){
        try {
                //requires components and QC stuff
            
                allProjects = null;
                
                ResultSet dbAllProjects = null;
                ResultSet dbAllProjectElements = null;
                Statement statement;
                statement = connection.createStatement();
                dbAllProjects = statement.executeQuery( "SELECT Project.projectID, Project.projectName, Project.rootComponent, Project.teamLeader, "                     
                        + "Project.clientRep, Project.priority FROM Project;");  
                dbAllProjectElements = statement.executeQuery("SELECT SetOFElements.ProjectID, SetOFElements.elementID, Element.elementName" +
                                                            "FROM Element INNER JOIN SetOFElements ON Element.elementID = SetOFElements.elementID;");
                
                while(dbAllProjects.next())
                {
                    int tempTeamLeaderID = dbAllProjects.getInt("teamLeader");
                    int tempClientRepID = dbAllProjects.getInt("clientRep");
                    User tempTeamLeader = null;
                    User tempClientRep = null;
                    SetOfElements projectElements = new SetOfElements();
                    SetOfTasks projectTasks = new SetOfTasks();

                    for (int i=0; i<allUsers.size();i++){  
                        if(allUsers.get(i).getUserID()==(tempTeamLeaderID))  //adds Team Leader
                        {
                            tempTeamLeader = (allUsers.get(i));
                        }
                        if(allUsers.get(i).getUserID()==(tempClientRepID))   //adds Client Rep
                        {
                            tempClientRep = (allUsers.get(i));
                        }
                    }
                    
                    for (int i=0;i<allTasks.size();i++){
                        if(allTasks.get(i).getProjectID()==dbAllProjects.getInt("projectID")){  //adds project tasks
                            projectTasks.addTask(allTasks.get(i));
                        }
                    }
                    
                    while(dbAllProjectElements.next()){         //adds project elements
                        if(dbAllProjectElements.getInt("projectID") == dbAllProjects.getInt("projectID")){
                            for(int i=0;i<allElements.size();i++){
                                if(allElements.get(i).getElementID()==dbAllProjectElements.getInt("elementID")){
                                    projectElements.addElement(allElements.get(i));
                                }
                            }
                        }
                    }
                   
                    Project newProject = new Project (dbAllProjects.getInt("projectID"), dbAllProjects.getString("projectName"), tempTeamLeader, tempClientRep, dbAllProjects.getInt("priority"), projectTasks, projectElements, SetOfQCReports reports, SetOfStaff setOfStaff)
                    
                    
                }              
        } catch (SQLException ex) {                  
            Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);                   
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
                Task task = new Task(dbUsersTasks.getInt("Task.taskID"), UserLoggedIn, dbUsersTasks.getString("Task.TaskName"), dbUsersTasks.getInt("Task.taskPriority"),
                                dbUsersTasks.getString("Task.taskStatus"), dbUsersTasks.getInt("Task.projectID"));
                usersTasks.addTask(task);
                //jList1.setListData(usersTasks);
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
            
            
     private void FillCombo(){
        try{
                     ResultSet loginResults = null;
                     Statement statement;              
                     statement = connection.createStatement();
                     loginResults = statement.executeQuery( "SELECT * FROM User");                                        
                     //This gets stuff from the database and populates in to a combobox
                     while (loginResults.next())
                    {
                        //String name = loginResults.getString("firstName");
                        //UserCbo.addItem(name);
                    }     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void SelectIndividualTask(){
        try{
            
            //make sure GUI gets the info on selected task in the JList, and looks up the correct task ID in the query below          
                     ResultSet taskResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     taskResults = statement.executeQuery( "SELECT taskID, projectID, responsiblePerson, "
                             + "taskPriority, status, taskName, assetID FROM Task WHERE taskID=" + taskID + ";");                     
                     //This gets stuff from the database and populates in to a combobox
                     while (taskResults.next())
                    {
                        //String name = taskResults.getString("firstName");
                        //UserCbo.addItem(name);
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void CreateNewProject(){//create a new project
                try {
                    Statement statement;
                    statement = connection.createStatement();
                    statement.executeUpdate( "INSERT INTO Project(projectName, rootComponent, teamLeader, clientRep, priority) "
                            + "VALUES ('" + projectName + "', '" + rootComponent + "','" + teamLeader + "', '" + clientRep + "', '" + priority + "');");
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void ChangeTaskStatus(){//edit the task
        //requires IF statement to make sure only appropriate QC Team Member can change the task status
        try {
            
            //I think this should be a Update maybe? 
                    Statement statement;
                    statement = connection.createStatement();
                    statement.executeUpdate("UPDATE Task SET [projectID] =" + projectID + ", [responsiblePerson] =" + userID + ", [taskPriority] =" + taskPriority
                            + ", [status] = '" + status + "', [taskName] ='" + taskName + "', [assetID] =" + assetID + ");");
                            
                    } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void defineProjectTeam(){//creates team to work on a project
                try {
                    SetOfUsers projectTeam = new SetOfUsers();
                    //requires code to put each person selected in GUI to be put into "projectTeam"
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<projectTeam.size(); i++){
                        
                        statement.executeUpdate(" INSERT INTO StaffOnProjects (staffID, projectID)"
                                + "VALUES (" + projectTeam.get(i).getUserID() + ", " + projectID + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void createNewElement(){
                try {
                    Statement statement;
                                statement = connection.createStatement();
                                statement.executeUpdate(" INSERT INTO Element (elementName)"
                                            + "VALUES ('" + elementName + "');" );
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void createNewAsset(){
                try {
                    Statement statement;
                                statement = connection.createStatement();
                                statement.executeUpdate(" INSERT INTO Asset (assetName, assetType)"
                                            + "VALUES ('" + assetName + "', '" + assetType + "');" );
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void assignAssetsToElement(){//assigns list of assets to an element
        try {
                    SetOfAssets elementAssets = new SetOfAssets();
                    //requires code to put each asset selected in GUI to be put into "elementAssets"
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<elementAssets.size(); i++){
                        
                        statement.executeUpdate(" INSERT INTO SetOfAssets (assetID, elementID)"
                                + "VALUES (" + elementAssets.get(i).getAssetID() + ", " + elementID + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
     private void assignElementsToProject(){//assigns list of components to a project
        try {
                    SetOfElements projectElements = new SetOfElements();
                    
                    //requires code to put each element selected in GUI to be put into "projectComponents"
                    
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<projectElements.size(); i++){
                        statement.executeUpdate(" INSERT INTO SetOfElements (elementID, projectID)"
                                + "VALUES (" + projectElements.get(i).getElementID() + ", " + projectID + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
     private void findStaffOnProject(){//update this when database relationships and place of "role" has been decided
          try{
            
            //make sure GUI gets the info on selected project in the JList, and looks up the correct projectID in the query below          
                     ResultSet staffResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     staffResults = statement.executeQuery( "SELECT Project.projectID, User.userID, User.firstName, User.surname, Staff.role" +
                    "FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN StaffOnProjects ON Project.projectID = StaffOnProjects.projectID)"
                             + " ON Staff.staffID = StaffOnProjects.staffID) ON User.userID = Staff.staffID"
                             + "WHERE Project.projectID=" + projectID + ";");                     
                     
                     while (staffResults.next())
                    {
                        //add staffResults to setOFUsers and the GUI
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
     
     private void findStaffProjects(){//displays all projects that the staff is working on
     
            
            //make sure GUI gets the info on userLogged in --OR-- the userID, and looks up the correct userID in the query below          
                     ResultSet projectResults = null;
                     Statement statement;
                try {
                    statement = connection.createStatement();
                
                     SetOfProjects allProjects;
                     SetOfProjects allStaffProjects;

                  projectResults = statement.executeQuery( "SELECT User.userID, Project.projectID, Project.projectName, Project.rootComponent, "
                  + "Project.teamLeader, Project.clientRep, Project.priority FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN "
                          + "StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID)"                     
                          + " ON User.userID = Staff.staffID WHERE User.userID=" + userID + ";");
                  while (projectResults.next())
                 {
                   // for(int i=0; i<allProjects.g
                    //}

                 }
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     
     private void displayManagingProjects(){//displays all projects where user is a team leader
                try {
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "SELECT User.userID, Project.*" +
                    "FROM (User INNER JOIN Staff ON User.userID = Staff.staffID) INNER JOIN Project "
                            + "ON Staff.staffID = Project.teamLeader; WHERE User.userID=" + userID + ";");                     
                    while (projectResults.next())
                   {
                       //add projectResults to SetOfProjects and the GUI
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     

     
     private void GetComponentsOnProject(){
         try{
            
            //make sure GUI gets the info on userLogged in --OR-- the userID, and looks up the correct userID in the query below          
                     ResultSet projectResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     projectResults = statement.executeQuery( "SELECT * FROM SetOfComponenets WHERE projectID =" +projectID + ";");                     
                     
                     while (projectResults.next())
                    {
                        //add projectResults to SetOfProjects and the GUI
                    }
          }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         
         
     }
     
     private void GetTasksOnProject(){
           try{
            //THIS NEEDS TO BE CAHNGED, TASKS ARE ASSIGNED TO ASSETS AND ELEMENTS NOT PROJECTS
            //make sure GUI gets the info on userLogged in --OR-- the userID, and looks up the correct userID in the query below          
                     ResultSet projectResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     projectResults = statement.executeQuery( "SELECT * FROM Task WHERE projectID = " + projectID + ";");                     
                     
                     while (projectResults.next())
                    {
                        //add projectResults to SetOfProjects and the GUI
                    }
          }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
     
     private void deleteTask(){
         try {
                    ResultSet delTaskResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    delTaskResults = statement.executeQuery( "DELETE FROM Task WHERE projectID = " + projectID + ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
         
     }
     
     private void deleteProject(Project projectToDelete){
                try {
                    //NEEDS TO REMOVE ALL DEPENDANCIES
                       ResultSet delProjectResults = null;
                       Statement statement;
                       statement = connection.createStatement();   
                       delProjectResults = statement.executeQuery( "DELETE FROM Task WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM QCReport WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       //check if we need to remove component and change to element
                       delProjectResults = statement.executeQuery( "DELETE FROM SetOfComponents WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM Project WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       
                       
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     
     private void removeStafFromProject(Project removeFromProject, User usertoRemove){
           try {
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + removeFromProject.getProjectID() + ", AND staffID = " + usertoRemove.getUserID() +  ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     
     
}
