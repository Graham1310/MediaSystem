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

    
    

    public Project(int projectID, String projectName, User teamLeader, User clientRep, int priority, SetOfTasks projectTasks, SetOfElements elementCollection, SetOfQCReports reports, SetOfUsers setOfUsers) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.teamLeader = teamLeader;
        this.clientRep = clientRep;
        this.priority = priority;
        this.projectTasks = projectTasks;
        this.elementCollection = elementCollection;
        this.reports = reports;
        this.setOfUsers = setOfUsers;
    }
    
    
    private int projectID;
    private final SetOfUsers setOfUsers;
    private String projectName;
    private User teamLeader;
    private User clientRep;
    private int priority;
    private SetOfTasks projectTasks;
    private SetOfElements elementCollection;

    public SetOfElements getElementCollection() {
        return elementCollection;
    }

    public void setElementCollection(SetOfElements elementCollection) {
        this.elementCollection = elementCollection;
    }
    private SetOfQCReports reports;
    private SetOfStaff setOfStaff;
    
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
    
public SetOfStaff getSetOfStaff() {
        return setOfStaff;
    }

    public void setSetOfStaff(SetOfStaff setOfStaff) {
        this.setOfStaff = setOfStaff;
    }
    
}
