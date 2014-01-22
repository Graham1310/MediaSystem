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
class SetOfComponents extends Vector<Component>{

    public SetOfComponents(){
        super();
    }
    public void addComponent(Component aComponent){
        super.add(aComponent);
    }
    public void removeComponent(Component aComponent){
        super.remove(aComponent);
    }
}
