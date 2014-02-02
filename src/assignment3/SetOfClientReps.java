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
public class SetOfClientReps extends Vector<ClientRep>{

    /**
     *
     */
    public SetOfClientReps(){
        super();
    }

    /**
     *
     * @param aClientRep
     * Adds a Client Representative to the list
     */
    public void addClientRep(ClientRep aClientRep){
        super.add(aClientRep);
    }

    /**
     *
     * @param aClientRep
     * Removes a Client Representative from the list
     */
    public void removeClientRep(ClientRep aClientRep){
        super.remove(aClientRep);
    }
}
