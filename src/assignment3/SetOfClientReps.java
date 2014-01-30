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
    public SetOfClientReps(){
        super();
    }

    public void addClientRep(ClientRep aClientRep){
        super.add(aClientRep);
    }

    public void removeClientRep(ClientRep aClientRep){
        super.remove(aClientRep);
    }
}
