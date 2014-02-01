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
public class SetOfStaff extends Vector<Staff>{

    /**
     *
     */
    public SetOfStaff(){
        super();
    }

    /**
     *
     * @param aStaff
     */
    public void addStaff(Staff aStaff){
        super.add(aStaff);
    }

    /**
     *
     * @param aStaff
     */
    public void removeStaff(Staff aStaff){
        super.remove(aStaff);
    }
}
