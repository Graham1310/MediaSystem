/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
class Task {
    private int taskID;
    private User responsiblePerson;

    public Task(int taskID, User responsiblePerson, String taskName, int priority, String status, int projectID, Asset asset, String type) {
        this.taskID = taskID;
        this.responsiblePerson = responsiblePerson;
        this.taskName = taskName;
        this.priority = priority;
        this.status = status;
        this.projectID = projectID;
        this.asset = asset;
        this.type = type;
    }
    
    
    
    private String taskName;
    private int priority;
    private String status;
    private int projectID;
    private Asset asset;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

   public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public User getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(User responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void startTask(){
        //set string start to true?
    }
    
}
