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
public class SetOfClients extends Vector<Client>{

    /**
     *
     */
    public SetOfClients(){
        super();
    }

    /**
     *
     * @param aClient
     */
    public void addClient(Client aClient){
        super.add(aClient);
    }

    /**
     *
     * @param aClient
     */
    public void removeUser(Client aClient){
        super.remove(aClient);
    }
}
