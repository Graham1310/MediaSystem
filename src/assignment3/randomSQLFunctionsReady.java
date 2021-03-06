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

    /**
     *
     * @param UserLoggedIn
     * @param allQCReports
     * @param allComments
     * @param allTasks
     * @param allUsers
     * @param allClients
     * @param allClientReps
     * @param allStaff
     * @param allAssets
     * @param allElements
     * @param allProjects
     * Create constructor
     */
    public randomSQLFunctionsReady(User UserLoggedIn, SetOfQCReports allQCReports,  SetOfQCComments allComments,
            SetOfTasks allTasks, SetOfUsers allUsers, SetOfClients allClients, SetOfClientReps allClientReps,
            SetOfStaff allStaff, SetOfAssets allAssets, SetOfElements allElements, SetOfProjects allProjects ) {
        this.UserLoggedIn = UserLoggedIn;
        this.allAssets = allAssets;
        this.allClientReps = allClientReps;
        this.allClients = allClients;
        this.allComments = allComments;
        this.allElements = allElements;
        this.allProjects = allProjects;
        this.allQCReports = allQCReports;
        this.allStaff = allStaff;
        this.allTasks = allTasks;
        this.allUsers = allUsers;
    }

    /**
     * Declares variables required
     */
            SetOfQCReports allQCReports = new SetOfQCReports();
            SetOfQCComments allComments = new SetOfQCComments();
            SetOfTasks allTasks = new SetOfTasks();
            SetOfUsers allUsers = new SetOfUsers();
            SetOfClients allClients = new SetOfClients();
            SetOfClientReps allClientReps = new SetOfClientReps();
            SetOfStaff allStaff = new SetOfStaff();
            SetOfAssets allAssets = new SetOfAssets();
            SetOfElements allElements = new SetOfElements();
            SetOfProjects allProjects = new SetOfProjects();
            User UserLoggedIn;

    randomSQLFunctionsReady() {
        //throw new UnsupportedOperationException("Not yet implemented");
    }
            
    /**
     *
     * @return
     */
    public SetOfQCReports getAllQCReports() {
        return allQCReports;
    }

    /**
     *
     * @param allQCReports
     */
    public void setAllQCReports(SetOfQCReports allQCReports) {
        this.allQCReports = allQCReports;
    }

    /**
     *
     * @return
     */
    public SetOfQCComments getAllComments() {
        return allComments;
    }

    /**
     *
     * @param allComments
     */
    public void setAllComments(SetOfQCComments allComments) {
        this.allComments = allComments;
    }

    /**
     *
     * @return
     */
    public SetOfTasks getAllTasks() {
        return allTasks;
    }

    /**
     *
     * @param allTasks
     */
    public void setAllTasks(SetOfTasks allTasks) {
        this.allTasks = allTasks;
    }

    /**
     *
     * @return
     */
    public SetOfUsers getAllUsers() {
        return allUsers;
    }

    /**
     *
     * @param allUsers
     */
    public void setAllUsers(SetOfUsers allUsers) {
        this.allUsers = allUsers;
    }

    /**
     *
     * @return
     */
    public SetOfClients getAllClients() {
        return allClients;
    }

    /**
     *
     * @param allClients
     */
    public void setAllClients(SetOfClients allClients) {
        this.allClients = allClients;
    }

    /**
     *
     * @return
     */
    public SetOfClientReps getAllClientReps() {
        return allClientReps;
    }

    /**
     *
     * @param allClientReps
     */
    public void setAllClientReps(SetOfClientReps allClientReps) {
        this.allClientReps = allClientReps;
    }

    /**
     *
     * @return
     */
    public SetOfStaff getAllStaff() {
        return allStaff;
    }

    /**
     *
     * @param allStaff
     */
    public void setAllStaff(SetOfStaff allStaff) {
        this.allStaff = allStaff;
    }

    /**
     *
     * @return
     */
    public SetOfAssets getAllAssets() {
        return allAssets;
    }

    /**
     *
     * @param allAssets
     */
    public void setAllAssets(SetOfAssets allAssets) {
        this.allAssets = allAssets;
    }

    /**
     *
     * @return
     */
    public SetOfElements getAllElements() {
        return allElements;
    }

    /**
     *
     * @param allElements
     */
    public void setAllElements(SetOfElements allElements) {
        this.allElements = allElements;
    }

    /**
     *
     * @return
     */
    public SetOfProjects getAllProjects() {
        return allProjects;
    }

    /**
     *
     * @param allProjects
     */
    public void setAllProjects(SetOfProjects allProjects) {
        this.allProjects = allProjects;
    }

    /**
     *
     * @return
     */
    public User getUserLoggedIn() {
        return UserLoggedIn;
    }

    /**
     *
     * @param UserLoggedIn
     */
    public void setUserLoggedIn(User UserLoggedIn) {
        this.UserLoggedIn = UserLoggedIn;
    }

    /**
     *Create connection
     * Select all users from database
     * For every user, load into system
     */
    public void loadAllUsers(){
        try {        
            ResultSet dbAllUsers = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllUsers = statement.executeQuery( "SELECT User.userID, User.firstName, User.surname, User.username, User.password FROM [User];");                     

            while(dbAllUsers.next())
            {
                User tempUser = new User(dbAllUsers.getInt("userID"), dbAllUsers.getString("firstName"), dbAllUsers.getString("surname"),
                        dbAllUsers.getString("userName"), dbAllUsers.getString("password"));
               allUsers.addUser(tempUser);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Creates connection
     * Selects all Clients from the database
     * For every Client, load client into system
     */
    public void loadAllClients(){
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
    
    /**
     *Creates connection
     * Selects all Client Representatives from the database
     * For every ClientRep, load ClientRep into system
     */
    public void loadAllClientReps(){//COMPLETE
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
    
    /**
     *Creates connection
     * Select all Staff from database
     * For every Staff selected, load Staff into system
     */
    public void loadAllStaff(){//COMPLETE
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
    
    /**
     *Creates connection
     * Select all Assets from database
     * For every asset selected, load asset into system
     */
    public void loadAllAssets(){
        try {        
            ResultSet dbAllAssets = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllAssets = statement.executeQuery( "SELECT Asset.ID, Asset.assetName, Asset.assetType, Task.taskID" +
                                                    "FROM Asset INNER JOIN Task ON Asset.ID = Task.assetID;");                     
            while(dbAllAssets.next())
            {
                SetOfTasks assetTasks = new SetOfTasks();
                for(int i=0; i<allTasks.size();i++){
                    if(allTasks.get(i).getTaskID()==dbAllAssets.getInt("assetID")){
                        assetTasks.addTask(allTasks.get(i));
                    }
                }
                Asset tempAsset = new Asset(dbAllAssets.getInt("ID"), dbAllAssets.getString("assetName"), dbAllAssets.getString("type"), assetTasks);
                allAssets.addAsset(tempAsset);
            }
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Create connection
     * Select all elements in the database
     * For every element in the database, load into system and add any assets it has a set of
     */
    public void loadAllElements(){
        try {
            ResultSet dbAllElements = null;
            Statement statement;
            statement = connection.createStatement();  
            dbAllElements = statement.executeQuery( "SELECT Element.elementID, Element.elementName, SetOFAssets.assetID" +
                    "FROM Element LEFT OUTER JOIN SetOFAssets ON Element.elementID = SetOFAssets.elementID;");
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
    
    /**
     *Creates statement
     * Selects all QCComments from the database
     * For every QCComment, load QCComment into system
     */
    public void loadAllQCComments(){
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
    
    /**
     *Creates statement
     * Selects all QCReports from the database
     * For every QCReport, load QCReport into system
     * Add any users and comments to the report as necessary 
     */
    public void loadAllQCReports(){
                try {
                    allQCReports = null;
                    ResultSet dbAllQCReports = null;
                    Statement statement;
                    statement = connection.createStatement();
                    dbAllQCReports = statement.executeQuery("SELECT QCReport.qCReportID, QCReport.projectID, QCReport.reviewer,"
                                                             + " QCReport.overallSeverityRating FROM QCReport;");
                    
                    while (dbAllQCReports.next()){
                        SetOfQCComments reportComments = new SetOfQCComments();
                        User reviewer = null;
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
                        allQCReports.addReport(newReport);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    /**
     *Create connection
     * Select all Projects from the database
     * Select all elements associated with projects from the database
     * Select staff working on projects from database
     * For every project;
     * -Load project details, including team leader and client representative
     * -Load all elements for project into the system and associate with the project
     * -Load all users on the project into the system and associate with the project
     */
    public void loadAllProjects(){
        try {
                allProjects = null;
                ResultSet dbAllProjects = null;
                ResultSet dbAllProjectElements = null;
                ResultSet dbAllProjectStaff = null;
                Statement statement;
                statement = connection.createStatement();
                dbAllProjects = statement.executeQuery( "SELECT Project.projectID, Project.projectName, Project.teamLeader, "                     
                                                        + "Project.clientRep, Project.priority FROM Project;");  
                dbAllProjectElements = statement.executeQuery("SELECT SetOFElements.ProjectID, SetOFElements.elementID, Element.elementName" +
                                                            "FROM Element INNER JOIN SetOFElements ON Element.elementID = SetOFElements.elementID;");
                dbAllProjectStaff = statement.executeQuery( "SELECT StaffOnProjects.staffID, StaffOnProjects.projectID FROM StaffOnProjects;"); 

                while(dbAllProjects.next())
                {
                    int tempTeamLeaderID = dbAllProjects.getInt("teamLeader");
                    int tempClientRepID = dbAllProjects.getInt("clientRep");
                    User tempTeamLeader = null;
                    User tempClientRep = null;
                    SetOfElements projectElements = new SetOfElements();
                    SetOfTasks projectTasks = new SetOfTasks();
                    SetOfQCReports projectReports = new SetOfQCReports();
                    SetOfUsers allProjectStaff = new SetOfUsers();

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
                    
                    for(int i=0; i<allQCReports.size(); i++){   //add project reports
                        if(allQCReports.get(i).getProjectID()==dbAllProjects.getInt("projectID")){
                            projectReports.addReport(allQCReports.get(i));
                        }
                    }
                    
                    while(dbAllProjectStaff.next()){
                            if(dbAllProjects.getInt("projectID") == dbAllProjectStaff.getInt("projectID")){
                                for(int i=0;i<allUsers.size();i++){
                                    if(allUsers.get(i).getUserID() == dbAllProjectStaff.getInt("staffID")){                                       
                                        allProjectStaff.addUser(allUsers.get(i));
                                    }
                                }      
                        }
                    }
                   
                    Project newProject = new Project (dbAllProjects.getInt("projectID"), dbAllProjects.getString("projectName"), tempTeamLeader, tempClientRep, dbAllProjects.getInt("priority"), projectTasks, projectReports, projectElements, allProjectStaff);   
                    allProjects.addProject(newProject);
                }              
        } catch (SQLException ex) {                  
            Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);                   
        }
            

    }
    
    /**
     *
     * @param project
     * @param user
     * @param priority
     * @param status
     * @param name
     * @param asset
     * Passes in all variables for a task
     * Inserts into database new task
     */
    public void createNewTask(Project project, User user, int priority, String status, String name, Asset asset){
                try {
                    Statement statement;
                        statement = connection.createStatement();
                        statement.executeUpdate( "INSERT INTO Task(projectID, responsiblePerson, taskPriority, status, name, assetID) "
                                        + "VALUES (" + project.getProjectID() + ", " + user.getUserID() + ", " + priority + ", '" + status + "', '" + name + "', " + asset.getAssetID() + ");");
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *Create connection statement
     * Select all tasks from the database for the user logged into the system
     * Loads all tasks into the system
     */
    public void displayUsersTasks(){
        SetOfTasks usersTasks = new SetOfTasks();
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
                for(int i=0; i<allAssets.size();i++){
                    if(allAssets.get(i).getAssetID()==dbUsersTasks.getInt("assetID")){
                        Task task = new Task(dbUsersTasks.getInt("Task.taskID"), UserLoggedIn, dbUsersTasks.getString("Task.TaskName"), dbUsersTasks.getInt("Task.taskPriority"),
                                dbUsersTasks.getString("Task.taskStatus"), dbUsersTasks.getInt("Task.projectID"), allAssets.get(i), dbUsersTasks.getString("Task.type"));
                        usersTasks.addTask(task);
                    }
                }
                //jList1.setListData(usersTasks);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
            
            
     /*public void FillCombo(){
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
    }*/
    
    /*private void SelectIndividualTask(){
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
    }*/

    /**
     *
     * @param projectName
     * @param teamLeader
     * @param clientRep
     * @param priority
     * Passes in variables for a project
     * Inserts new project into database
     */
    public void CreateNewProject(String projectName, User teamLeader, User clientRep, int priority){//create a new project
                try {
                    Statement statement;
                    statement = connection.createStatement();
                    statement.executeUpdate( "INSERT INTO Project(projectName, teamLeader, clientRep, priority) "
                            + "VALUES ('" + projectName + "', '" + teamLeader + "', '" + clientRep + "', '" + priority + "');");
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *
     * @param project
     * @param responsiblePerson
     * @param priority
     * @param status
     * @param taskName
     * @param asset
     * Passes in new task details
     * Updates task selected in database
     */
    public void ChangeTaskStatus(Project project, User responsiblePerson, int priority, String status, String taskName, Asset asset){//edit the task
        //requires IF statement to make sure only appropriate QC Team Member can change the task status
        try {
            
            //I think this should be a Update maybe? 
                    Statement statement;
                    statement = connection.createStatement();
                    statement.executeUpdate("UPDATE Task SET [projectID] =" + project.getProjectID() + ", [responsiblePerson] =" + responsiblePerson.getUserID()
                                                + ", [taskPriority] =" + priority + ", [status] = '" + status + 
                                            "', [taskName] ='" + taskName + "', [assetID] =" + asset.getAssetID() + ");");
                    } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *
     * @param projectTeam
     * @param project
     * Pass through set of users and project
     * Assign set of users to project in the database
     */
    public void defineProjectTeam(SetOfUsers projectTeam, Project project){//creates team to work on a project
                try {
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<projectTeam.size(); i++){
                        
                        statement.executeUpdate(" INSERT INTO StaffOnProjects (staffID, projectID)"
                                + "VALUES (" + projectTeam.get(i).getUserID() + ", " + project.getProjectID() + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *
     * @param elementName
     * Pass through new element name
     * Create new element in the database
     */
    public void createNewElement(String elementName){
                try {
                    Statement statement;
                                statement = connection.createStatement();
                                statement.executeUpdate(" INSERT INTO Element (elementName)"
                                            + "VALUES ('" + elementName + "');" );
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *
     * @param assetName
     * @param assetType
     * Pass through asset details
     * Create new asset in the database
     */
    public void createNewAsset(String assetName, String assetType){
                try {
                    Statement statement;
                                statement = connection.createStatement();
                                statement.executeUpdate(" INSERT INTO Asset (assetName, assetType)"
                                            + "VALUES ('" + assetName + "', '" + assetType + "');" );
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     *
     * @param elementAssets
     * @param element
     * Pass through set of assets and element
     * Assign set of assets to element in the database
     */
    public void assignAssetsToElement(SetOfAssets elementAssets, Element element){//assigns list of assets to an element
        try {
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<elementAssets.size(); i++){
                        
                        statement.executeUpdate(" INSERT INTO SetOfAssets (assetID, elementID)"
                                + "VALUES (" + elementAssets.get(i).getAssetID() + ", " + element.getElementID() + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    /**
     *
     * @param projectElements
     * @param project
     * Pass in set of elements and project
     * Assign set of elements to project in database
     */
    public void assignElementsToProject(SetOfElements projectElements, Project project){//assigns list of components to a project
        try {     
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<projectElements.size(); i++){
                        statement.executeUpdate(" INSERT INTO SetOfElements (elementID, projectID)"
                                + "VALUES (" + projectElements.get(i).getElementID() + ", " + project.getProjectID() + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    /**
     *
     * @param project
     * Create statement
     * Select all Staff in database who are working on selected project
     * Load all staff into system
     */
    public void findStaffOnProject(Project project){
          try{
                     SetOfUsers staffOnProject = new SetOfUsers();
                     ResultSet staffResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     staffResults = statement.executeQuery( "SELECT Project.projectID, User.userID, User.firstName, User.surname, Staff.role" +
                    "FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN StaffOnProjects ON Project.projectID = StaffOnProjects.projectID)"
                             + " ON Staff.staffID = StaffOnProjects.staffID) ON User.userID = Staff.staffID"
                             + "WHERE Project.projectID=" + project.getProjectID() + ";");                     
                     
                     while (staffResults.next())
                    {
                        for(int i=0;i<allUsers.size();i++){
                            if(allUsers.get(i).getUserID()==staffResults.getInt("staffID")){
                                staffOnProject.addUser(allUsers.get(i));
                            }
                        }
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }

    /**
     *
     * @param user
     * @param project
     * Pass through selected user and project
     * Removes user from project in system and removes project from user's project list in system
     * Removes user from project in database
     */
//    public void removeStaffFromProject(User user, Project project){
//                try {
//                    project.getSetOfUsers().removeUser(user);
//                    user.getWorkingOnProjects().removeProject(project);
//                    
//                    ResultSet removeStaffFromProject = null;
//                    Statement statement;
//                    statement = connection.createStatement();
//                    removeStaffFromProject = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + project.getProjectID() 
//                                                                        + " AND staffID = " + user.getUserID() + ";");
//                } catch (SQLException ex) {
//                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
//                }
//     }

    /**
     *
     * @param user
     * Passes in user
     * Selects all projects in database that user is working on
     * Loads all projects into system
     */
    public void findStaffProjects(User user){
     
                     ResultSet projectResults = null;
                     Statement statement;
                try {
                    statement = connection.createStatement();
                
                     SetOfProjects allProjects;
                     SetOfProjects allStaffProjects;

                  projectResults = statement.executeQuery( "SELECT User.userID, Project.projectID, Project.projectName, "
                  + "Project.teamLeader, Project.clientRep, Project.priority FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN "
                          + "StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID)"                     
                          + " ON User.userID = Staff.staffID WHERE User.userID=" + user.getUserID() + ";");
                  while (projectResults.next())
                 {
                    //
                 }
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
     }

    /**
     *
     * @param manager
     * Passes in manager
     * Selects all projects that the manager is in charge of
     * Loads all projects into system
     */
    public void displayManagingProjects(User manager){//displays all projects where user is a team leader
                try {
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "SELECT User.userID, Project.*" +
                    "FROM (User INNER JOIN Staff ON User.userID = Staff.staffID) INNER JOIN Project "
                            + "ON Staff.staffID = Project.teamLeader; WHERE User.teamLeader=" + manager.getUserID() + ";");                     
                    while (projectResults.next())
                   {
                       //add projectResults to SetOfProjects and the GUI
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
     }

    /**
     *
     * @param project
     * Pass in project
     * Select all elements from the database associated with the project
     * Load all elements for the project into the system
     */
    public void GetElementsOnProject(Project project){
         try{
            
                     SetOfElements projectElements = new SetOfElements();
                     ResultSet projectResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     projectResults = statement.executeQuery( "SELECT * FROM SetOfElements WHERE projectID =" + project.getProjectID() + ";");                     
                     
                     while (projectResults.next())
                    {
                        for(int i=0; i<allElements.size();i++){
                            if(allElements.get(i).getElementID()==projectResults.getInt("elementID")){
                                projectElements.addElement(allElements.get(i));
                            }
                        }
                    }
          }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         
         
     }

    /**
     *
     * @param project
     * Passes in project
     * Selects all tasks for the project in the database
     * Loads all selected tasks into the system
     */
    public void GetTasksOnProject(Project project){
           try{
                     SetOfTasks projectTasks = new SetOfTasks();
                     ResultSet projectResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     projectResults = statement.executeQuery( "SELECT * FROM Task WHERE projectID = " + project.getProjectID() + ";");                     
                     
                     while (projectResults.next())
                    {
                        for(int i=0; i<allTasks.size();i++){
                            if(allTasks.get(i).getTaskID()==projectResults.getInt("taskID")){
                                projectTasks.addTask(allTasks.get(i));
                            }
                        }
                    }
          }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }

    /**
     *
     * @param task
     * Passes in task
     * Deletes task from database
     */
    public void deleteTask(Task task){
         try {
                    ResultSet delTaskResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    delTaskResults = statement.executeQuery( "DELETE FROM Task WHERE taskID = " + task.getTaskID() + ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
         
     }

    /**
     *
     * @param projectToDelete
     * Passes in project
     * Deletes tasks in database associated with the project
     * Deletes set of staff in database associated with project
     * Deletes QCReports in database associated with project
     * Deletes set of elements in database associated with project
     * Deletes the project itself
     */
    public void deleteProject(Project projectToDelete){
                try {
                    //NEEDS TO REMOVE ALL DEPENDANCIES
                       ResultSet delProjectResults = null;
                       Statement statement;
                       statement = connection.createStatement();   
                       delProjectResults = statement.executeQuery( "DELETE FROM Task WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM QCReport WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM SetOfElements WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       delProjectResults = statement.executeQuery( "DELETE FROM Project WHERE projectID = " + projectToDelete.getProjectID() + ";");
                       
                       
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
     }

    /**
     *
     * @param element
     * @param asset
     * Passes in element and asset
     * Removes asset from element in system
     * Removes asset from element in database
     */
    public void removeAssetFromElement(Element element, Asset asset){
        try {
            element.getSetOfAssets().removeAsset(asset);
            ResultSet delProjectResults = null;
            Statement statement;
            statement = connection.createStatement();
            delProjectResults = statement.executeQuery( "DELETE FROM SetOfAssets WHERE assetID = " + asset.getAssetID() + ";");
        } catch (SQLException ex) {
            Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }

    /**
     *
     * @param task
     * @param asset
     * Passes in task and asset
     * Removes task from asset in system
     * Removes task from asset in database
     */
    public void removeTaskFromAsset(Task task, Asset asset){
         try {
            asset.getSetOfTasks().removeTask(task);
            ResultSet delTaskResults = null;
            Statement statement;
            statement = connection.createStatement();
            delTaskResults = statement.executeQuery( "DELETE FROM Tasks WHERE taskID = " + task.getTaskID() + ";");
        } catch (SQLException ex) {
            Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    /**
     *
     * @param removeFromProject
     * @param usertoRemove
     * Passes in Project and user
     * Removes staff from project in database
     */
    public void removeStafFromProject(Project removeFromProject, User usertoRemove){
           try {
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + removeFromProject.getProjectID() + ", AND staffID = " + usertoRemove.getUserID() +  ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
     }

    /**
     *Creates connection
     * Selects all assets in database that are not assigned to an element
     * Loads all selected assets into system
     */
    public void displayUnassignedAssets(){
                try {
                    SetOfAssets unassignedAssets = new SetOfAssets();
                    ResultSet dbAssetResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    dbAssetResults = statement.executeQuery("SELECT Asset.ID, SetOFAssets.elementID" +
                                                "FROM Asset LEFT JOIN SetOFAssets ON Asset.ID = SetOFAssets.assetID" +
                                                "WHERE (((SetOFAssets.elementID) Is Null));");
                    while(dbAssetResults.next()){
                        for(int i=0;i<allAssets.size();i++){
                            if(allAssets.get(i).getAssetID()==dbAssetResults.getInt("assetID")){
                                unassignedAssets.addAsset(allAssets.get(i));
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(randomSQLFunctionsReady.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
    
     /**
     * Re-sets all tasks in system
     * Selects all tasks from the database
     * For every task, assign the appropriate asset and load to the system
     */
    public void loadAllTasks(){
        allTasks = null;
        try {
            ResultSet dbAllTasks = null;
            Statement statement;
            statement = connection.createStatement();
            dbAllTasks = statement.executeQuery( "SELECT Task.* FROM Task;");                     

            while(dbAllTasks.next())
            {
                for(int i=0; i<allAssets.size();i++){
                    if(allAssets.get(i).getAssetID()==dbAllTasks.getInt("assetID")){
                        Task task = new Task(dbAllTasks.getInt("Task.taskID"), UserLoggedIn, dbAllTasks.getString("Task.TaskName"), dbAllTasks.getInt("Task.taskPriority"),
                                dbAllTasks.getString("Task.taskStatus"), dbAllTasks.getInt("Task.projectID"), allAssets.get(i), dbAllTasks.getString("Task.type"));
                        allTasks.addTask(task);
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(testFrame3Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param user
     * @return 
     * Load all tasks in database
     * Select all tasks in the database where the user is on the project and the task currently has no one assigned to it
     * Add all the retrieved tasks to a list of unassigned tasks
     * Return the list of unassigned tasks
     */
    public SetOfTasks GetUnassignedTasksForUser(int userID) {
       //loadAllTasks();
        //loadAllAssets();
                SetOfTasks unassignedTasks = new SetOfTasks();
                try{
                     
                     ResultSet taskResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     taskResults = statement.executeQuery( "SELECT Task.*, StaffOnProjects.staffID, StaffOnProjects.projectID FROM"
                                            + " (Project INNER JOIN StaffOnProjects ON Project.projectID = StaffOnProjects.projectID)"
                                            + " INNER JOIN Task ON Project.projectID = Task.projectID WHERE (((StaffOnProjects.staffID)="
                                            + userID + ") AND ((Task.responsiblePerson) Is Null));");                                         
                     //AND ((Task.responsiblePerson) Is Null))
                     
                     
                     while (taskResults.next())
                    {
                        int taskID = taskResults.getInt("taskID");
                        String taskName = taskResults.getString("taskName");
                        int taskPriority = taskResults.getInt("taskPriority");
                        String status = taskResults.getString("status");
                        int projectID = taskResults.getInt("projectID");
                        String type =  taskResults.getString("type");
                        Task task = new Task(taskID, null, taskName, taskPriority,
                                status, projectID, null, type);
                        unassignedTasks.addTask(task);
                    }
                }catch(Exception e){
                  JOptionPane.showMessageDialog(null,e);
              }
           return unassignedTasks; }

}
