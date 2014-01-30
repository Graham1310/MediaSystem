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
    

    
     User(int aUserID, String aFirstname, String aSurname, String aUsername, String aPassword/*, String aRole*/){
     userID = aUserID;
     firstName = aFirstname;
     surname = aSurname;
     username = aUsername;
     password = aPassword;
     //role= aRole;
     
    }

   

     public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    private SetOfProjects workingOnProjects = new SetOfProjects();

    public SetOfProjects getWorkingOnProjects() {
        return workingOnProjects;
    }

    public void setWorkingOnProjects(SetOfProjects workingOnProjects) {
        this.workingOnProjects = workingOnProjects;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public void allocateTask() {
        //allocate to task/set of tasks
    }
    
    public void removeTask() {
        //remove user from working on a task
    }
    
}
