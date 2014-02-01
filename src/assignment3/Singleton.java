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
     */
    public LinkedList<User> getUsersLoggedIn()
    {
        return instance.usersLoggedInList;
    }
    
    /**
     *
     * @param aUser
     */
    public void addUserLogedIn(User aUser){
        usersLoggedInList.add(aUser);
    }
    
    /**
     *
     * @param aUser
     */
    public void removeUserLoggedIn(User aUser){
        usersLoggedInList.remove(aUser);
    }
   
}
