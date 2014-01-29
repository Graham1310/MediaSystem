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
class SetOfComponents extends Vector<ProjectComponent>{

    public SetOfComponents(){
        super();
    }
    public void addComponent(ProjectComponent aComponent){
        super.add(aComponent);
    }
    public void removeComponent(ProjectComponent aComponent){
        super.remove(aComponent);
    }
}
