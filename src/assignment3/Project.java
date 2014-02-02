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

    /**
     *
     * @param projectID
     * @param projectName
     * @param teamLeader
     * @param clientRep
     * @param priority
     * @param projectTasks
     * @param reports
     * @param elementCollection
     * @param setOfUsers
     * Creates constructor
     */
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

    /**
     *
     * @param projectName
     * @param projectTasks
     * @param teamLeader
     * @param clientRep
     * @param priority
     * @param reports
     * Creates constructor
     */
    public Project(String projectName, SetOfTasks projectTasks, User teamLeader, User clientRep, int priority, SetOfQCReports reports) {
        this.projectName = projectName;
        //this.rootComponent = rootComponent;
        this.projectTasks = projectTasks;
        this.teamLeader = teamLeader;
        this.clientRep = clientRep;
        this.priority = priority;
        this.reports = reports;
    }

    /**
     *
     * @param projectName
     * @param priority
     * @param clientRep
     * Creates constructor
     */
    public Project(String projectName,int priority, User clientRep) {
        this.projectName = projectName;
        this.clientRep = clientRep;
        this.priority = priority;

    }
    
    /**
     *
     * @param projectID
     * @param projectName
     * @param priority
     * @param clientRep
     * Creates constructor
     */
    public Project(int projectID, String projectName,int priority, User clientRep) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.clientRep = clientRep;
        this.priority = priority;

    } 
    //for db dependencies

    /**
     *
     * @param projectName
     * @param rootComponentID
     * @param teamLeaderID
     * @param clientRepID
     * @param priority
     * Creates constructor
     */
        public Project(String projectName, int rootComponentID, int teamLeaderID, int clientRepID, int priority) {
        this.projectName = projectName;
        this.rootComponentID = rootComponentID;
        this.teamLeaderID = teamLeaderID;
        this.clientRepID = clientRepID;
        this.priority = priority;
    }    
    
    /**
     * Declares variables required
     */
    private int projectID;
    private SetOfUsers setOfUsers;
    private String projectName;
    private User teamLeader;
    private User clientRep;
    private int priority;
    private SetOfTasks projectTasks;
    private SetOfElements elementCollection;

    /**
     *
     * @return
     */
    public SetOfElements getElementCollection() {
        return elementCollection;
    }

    /**
     *
     * @param elementCollection
     */
    public void setElementCollection(SetOfElements elementCollection) {
        this.elementCollection = elementCollection;
    }
    private SetOfQCReports reports;
    private SetOfStaff setOfStaff;
    
    //for db dependencies
    int rootComponentID ;
    int teamLeaderID;
    int clientRepID;

    /**
     *
     * @return
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     *
     * @param projectID
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    
    /**
     *
     * @return
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     *
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    /**
     *
     * @return
     */
    public SetOfQCReports getReports() {
        return reports;
    }

    /**
     *
     * @param reports
     */
    public void setReports(SetOfQCReports reports) {
        this.reports = reports;
    }

    /**
     *
     * @return
     */
    public SetOfTasks getProjectTasks() {
        return projectTasks;
    }

    /**
     *
     * @param projectTasks
     */
    public void setProjectTasks(SetOfTasks projectTasks) {
        this.projectTasks = projectTasks;
    }

    /**
     *
     * @return
     */
    public User getTeamLeader() {
        return teamLeader;
    }

    /**
     *
     * @param teamLeader
     */
    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     *
     * @return
     */
    public User getClientRep() {
        return clientRep;
    }

    /**
     *
     * @param clientRep
     */
    public void setClientRep(User clientRep) {
        this.clientRep = clientRep;
    }

    /**
     *
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     *
     * @return
     */
    public SetOfUsers getSetOfUsers() {
        return setOfUsers;
    }

    /**
     *
     * @param setOfUsers
     */
    public void setSetOfUsers(SetOfUsers setOfUsers) {
        this.setOfUsers = setOfUsers;
    }

    /**
     *
     * @return
     */
    public int getRootComponentID(){
        return rootComponentID;
    }
    
    /**
     *
     * @param rootComponentID
     */
    public void setRootComponentID(int rootComponentID){
        this.rootComponentID = rootComponentID;
    }
  
    /**
     *
     * @return
     */
    public int getTeamLeaderID(){
        return teamLeaderID;
    }
    
    /**
     *
     * @param teamLeaderID
     */
    public void setTeamLeaderID(int teamLeaderID){
        this.teamLeaderID = teamLeaderID;
    }
    
    /**
     *
     * @return
     */
    public int getClientRepID(){
        return clientRepID;
    }
    
    /**
     *
     * @param clientRepID
     */
    public void setClientRepID(int clientRepID){
        this.clientRepID = clientRepID;
    }    

    
}
