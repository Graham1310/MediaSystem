/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Vector;

/**
 *
 * @author Tim Beale
 */
public class SetOfUsers extends Vector<User>{

    /**
     *
     */
    public SetOfUsers(){
        super();
    }

    /**
     *
     * @param aUser
     */
    public void addUser(User aUser){
        super.add(aUser);
    }

    /**
     *
     * @param aUser
     */
    public void removeUser(User aUser){
        super.remove(aUser);
    }
}
