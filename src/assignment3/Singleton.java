/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.LinkedList;

/**
 *
 * @author Neverborn
 */
public class Singleton {
    private static Singleton instance = null;
    
    private Singleton (){}
    
    /**
     *
     * @return
     * Create a new instance if there is no current instance
     */
    public static synchronized Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    
    private LinkedList<User> usersLoggedInList = new LinkedList<User>();
    
    /**
     *
     * @return
     * Returns users logged in
     */
    public LinkedList<User> getUsersLoggedIn()
    {
        return instance.usersLoggedInList;
    }
    
    /**
     *
     * @param aUser
     * Adds user currently logged in
     */
    public void addUserLogedIn(User aUser){
        usersLoggedInList.add(aUser);
    }
    
    /**
     *
     * @param aUser
     * Removes user currently logged in
     */
    public void removeUserLoggedIn(User aUser){
        usersLoggedInList.remove(aUser);
    }
   
}
