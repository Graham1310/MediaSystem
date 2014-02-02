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
class SetOfElements extends Vector<Element>{

    public SetOfElements(){
        super();
    }
    /**
     * 
     * @param aElement 
     * Adds an element to the set of elements
     */
    public void addElement(Element aElement){
        super.add(aElement);
    }
    /**
     * 
     * @param aElement 
     * Removes an element from the set of elements
     */
    public void removeElement(Element aElement){
        super.remove(aElement);
    }
}
