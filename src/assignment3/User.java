/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class User {
    
    private int userID;
    private String firstName;
    private String surname;
    private String username;
    private String password;
    private String role;
    

    
     User(int aUserID, String aFirstname, String aSurname, String aUsername, String aPassword){
     userID = aUserID;
     firstName = aFirstname;
     surname = aSurname;
     username = aUsername;
     password = aPassword;
     
    }

    User(int aUserID, String aFirstname, String aSurname){
     userID = aUserID;
     firstName = aFirstname;
     surname = aSurname;
     //role= aRole;
     
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    private SetOfProjects workingOnProjects = new SetOfProjects();

    /**
     *
     * @return
     */
    public SetOfProjects getWorkingOnProjects() {
        return workingOnProjects;
    }

    /**
     *
     * @param workingOnProjects
     */
    public void setWorkingOnProjects(SetOfProjects workingOnProjects) {
        this.workingOnProjects = workingOnProjects;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     *
     * @return
     */
    public String getRole(){
        return role;
    }
    
    /**
     *
     * @param role
     */
    public void setRole(String role){
        this.role = role;
    }
    
    /**
     *
     */
    public void allocateTask() {
        //allocate to task/set of tasks
    }
    
    /**
     *
     */
    public void removeTask() {
        //remove user from working on a task
    }
    
}
