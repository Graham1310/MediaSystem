/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Project {

    public Project(int projectID, String projectName, User teamLeader, User clientRep, int priority, SetOfTasks projectTasks, SetOfQCReports reports, SetOfElements elementCollection, SetOfUsers setOfUsers) {
        this.projectID = projectID;
        this.setOfUsers = setOfUsers;
        this.projectName = projectName;
        this.teamLeader = teamLeader;
        this.clientRep = clientRep;
        this.priority = priority;
        this.projectTasks = projectTasks;
        this.elementCollection = elementCollection;
        this.reports = reports;
    }

    public Project(String projectName, ProjectComponent rootComponent, SetOfTasks projectTasks, User teamLeader, User clientRep, int priority, SetOfComponents componentCollection, SetOfQCReports reports) {
        this.projectName = projectName;
        this.rootComponent = rootComponent;
        this.projectTasks = projectTasks;
        this.teamLeader = teamLeader;
        this.clientRep = clientRep;
        this.priority = priority;
        this.componentCollection = componentCollection;
        this.reports = reports;
    }
    public Project(String projectName,int priority, User clientRep) {
        this.projectName = projectName;
        this.clientRep = clientRep;
        this.priority = priority;

    }
    
    public Project(int projectID, String projectName,int priority, User clientRep) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.clientRep = clientRep;
        this.priority = priority;

    } 
    //for db dependencies
    public Project(String projectName, int rootComponentID, int teamLeaderID, int clientRepID, int priority) {
        this.projectName = projectName;
        this.rootComponentID = rootComponentID;
        this.teamLeaderID = teamLeaderID;
        this.clientRepID = clientRepID;
        this.priority = priority;
    }    
    
    private int projectID;
    private SetOfUsers setOfUsers;
    private String projectName;
    private User teamLeader;
    private User clientRep;
    private int priority;
    private SetOfTasks projectTasks;
    private SetOfElements elementCollection;
    private ProjectComponent rootComponent; // remove?

    public SetOfElements getElementCollection() {
        return elementCollection;
    }

    public void setElementCollection(SetOfElements elementCollection) {
        this.elementCollection = elementCollection;
    }
    private SetOfQCReports reports;
    private SetOfStaff setOfStaff;

    private SetOfComponents componentCollection;
    
    //for db dependencies
    int rootComponentID ;
    int teamLeaderID;
    int clientRepID;

    
    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public SetOfQCReports getReports() {
        return reports;
    }

    public void setReports(SetOfQCReports reports) {
        this.reports = reports;
    }

    public SetOfTasks getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(SetOfTasks projectTasks) {
        this.projectTasks = projectTasks;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    public User getClientRep() {
        return clientRep;
    }

    public void setClientRep(User clientRep) {
        this.clientRep = clientRep;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public SetOfUsers getSetOfUsers() {
        return setOfUsers;
    }

    public void setSetOfUsers(SetOfUsers setOfUsers) {
        this.setOfUsers = setOfUsers;
    }

    public int getRootComponentID(){
        return rootComponentID;
    }
    
    public void setRootComponentID(int rootComponentID){
        this.rootComponentID = rootComponentID;
    }
  
    public int getTeamLeaderID(){
        return teamLeaderID;
    }
    
    public void setTeamLeaderID(int teamLeaderID){
        this.teamLeaderID = teamLeaderID;
    }
    
    public int getClientRepID(){
        return clientRepID;
    }
    
    public void setClientRepID(int clientRepID){
        this.clientRepID = clientRepID;
    }    

    
}
