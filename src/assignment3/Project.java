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

    public Project(int projectID, String projectName, ProjectComponent rootComponent, SetOfTasks projectTasks, User teamLeader, User clientRep, int priority, SetOfComponents componentCollection, SetOfQCReports reports, SetOfStaff setOfStaff) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.rootComponent = rootComponent;
        this.projectTasks = projectTasks;
        this.teamLeader = teamLeader;
        this.clientRep = clientRep;
        this.priority = priority;
        this.componentCollection = componentCollection;
        this.reports = reports;
        this.setOfStaff = setOfStaff;
        
    }
    private int projectID;
    private String projectName;
    private ProjectComponent rootComponent;
    private SetOfTasks projectTasks;
    private User teamLeader;
    private User clientRep;
    private int priority;
    private SetOfComponents componentCollection;
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

    public ProjectComponent getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(ProjectComponent rootComponent) {
        this.rootComponent = rootComponent;
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

    public SetOfComponents getComponentCollection() {
        return componentCollection;
    }

    public void setComponentCollection(SetOfComponents componentCollection) {
        this.componentCollection = componentCollection;
    }
    
    public SetOfStaff getSetOfStaff() {
        return setOfStaff;
    }

    public void setSetOfStaff(SetOfStaff setOfStaff) {
        this.setOfStaff = setOfStaff;
    }
    
}
