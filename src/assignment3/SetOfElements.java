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
    public void addComponent(Element aElement){
        super.add(aElement);
    }
    public void removeComponent(Element aElement){
        super.remove(aElement);
    }
}