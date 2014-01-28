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
    public SetOfClients(){
        super();
    }

    public void addClient(Client aClient){
        super.add(aClient);
    }

    public void removeUser(Client aClient){
        super.remove(aClient);
    }
}
