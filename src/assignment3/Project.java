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
    private Component rootComponent;
    private SetOfTasks projectTasks;
    private User teamLeader;
    private User clientRep;
    private int priority;
    private SetOfComponents componentCollection;
    private SetOfQCReports reports;

    public SetOfQCReports getReports() {
        return reports;
    }

    public void setReports(SetOfQCReports reports) {
        this.reports = reports;
    }

    public Component getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(Component rootComponent) {
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
    
    
}
