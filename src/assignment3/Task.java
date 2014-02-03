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
    
    /**
     * 
     * @param taskID
     * @param responsiblePerson
     * @param taskName
     * @param priority
     * @param status
     * @param projectID
     * @param asset
     * @param type 
     * Creates constructor
     */
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
 
     /**
     * 
     * @param taskID
     * @param responsiblePerson
     * @param taskName
     * @param priority
     * @param status
     * @param projectID
     * @param assetID
     * @param type 
     * Creates constructor
     */
    public Task(int taskID, int responsiblePersonID, String taskName, int priority, String status, int projectID, int assetID, String type) {
        this.taskID = taskID;
        this.responsiblePersonID = responsiblePersonID;
        this.taskName = taskName;
        this.priority = priority;
        this.status = status;
        this.projectID = projectID;
        this.assetID = assetID;
        this.type = type;
    }    
    /**
     * Declares variables required
     */
    private int taskID;
    private User responsiblePerson;
    private int responsiblePersonID;    
    private String taskName;
    private int priority;
    private String status;
    private int projectID;
    private Asset asset;
    private int assetID;
    private String type;

    /**
     * 
     * @return 
     */
    public String getType() {
        return type;
    }
/**
 * 
 * @param type 
 */
    public void setType(String type) {
        this.type = type;
    }
/**
 * 
 * @return 
 */
    public Asset getAsset() {
        return asset;
    }
    
/**
 * 
 * @return 
 */
    public int getAssetID() {
        return assetID;
    }
    
/**
 * 
 * @param asset 
 */
    public void setAsset(Asset asset) {
        this.asset = asset;
    }
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
    public int getTaskID() {
        return taskID;
    }
/**
 * 
 * @param taskID 
 */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
  /**
   * 
   * @return 
   */  
    public String getTaskName() {
        return taskName;
    }
/**
 * 
 * @param taskName 
 */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
/**
 * 
 * @return 
 */
    public User getResponsiblePerson() {
        return responsiblePerson;
    }
/**
 * 
 * @param responsiblePerson 
 */
    public void setResponsiblePerson(User responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
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
    public String getStatus() {
        return status;
    }
/**
 * 
 * @param status 
 */
    public void setStatus(String status) {
        this.status = status;
    }

    
}
