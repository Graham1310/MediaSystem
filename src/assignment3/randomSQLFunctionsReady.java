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
            
            int componentID;
            String componentName;
            Date componentDate;
    
    
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
    
     private void createNewComponent(){
         
         //IMPORTANT: Requires import java.sql.Date;
         //Consider using timestamp instead of date
                try {
                    Statement statement;
                                statement = connection.createStatement();
                                statement.executeUpdate(" INSERT INTO Component (componentName, componentDate)"
                                            + "VALUES ('" + componentName + "', '" + componentDate + "');" );
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
     private void assignElementsToComponent(){//assigns list of elements to a component
        try {
                    SetOfElements componentElements = new SetOfElements();
                    //requires code to put each element selected in GUI to be put into "componentElements"
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<componentElements.size(); i++){
                        statement.executeUpdate(" INSERT INTO SetOfElements (elementID, componentID)"
                                + "VALUES (" + componentElements.get(i).getElementID() + ", " + componentID + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
     private void assignComponentsToProject(){//assigns list of components to a project
        try {
                    SetOfComponents projectComponents = new SetOfComponents();
                    //requires code to put each element selected in GUI to be put into "projectComponents"
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<projectComponents.size(); i++){
                        statement.executeUpdate(" INSERT INTO SetOfComponents (componentID, projectID)"
                                + "VALUES (" + projectComponents.get(i).getComponentID() + ", " + projectID + ");" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
     private void findStaffOnProject(){//displays all staff members for a given project
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
          try{
            
            //make sure GUI gets the info on userLogged in --OR-- the userID, and looks up the correct userID in the query below          
                     ResultSet projectResults = null;
                     Statement statement;
                     statement = connection.createStatement();
                     projectResults = statement.executeQuery( "SELECT User.userID, Project.projectID, Project.projectName, Project.rootComponent, "
                     + "Project.teamLeader, Project.clientRep, Project.priority FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN "
                             + "StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID)"
                             + " ON User.userID = Staff.staffID WHERE User.userID=" + userID + ";");                     
                     
                     while (projectResults.next())
                    {
                        //add projectResults to SetOfProjects and the GUI
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
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
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "DELETE FROM Task WHERE projectID = " + projectID + ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
         
     }
     
     private void deleteProject(){
         //NEEDS TO REMOVE ALL DEPENDANCIES
     }
     
     private void removeStafFromProject(){
           try {
                    ResultSet projectResults = null;
                    Statement statement;
                    statement = connection.createStatement();
                    projectResults = statement.executeQuery( "DELETE FROM StaffOnProjects WHERE projectID = " + projectID + ";");                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(testFrame2.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     
     
}
