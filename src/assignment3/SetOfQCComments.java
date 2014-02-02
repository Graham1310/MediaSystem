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
class SetOfQCComments extends Vector<QCComments>{

    public SetOfQCComments(){
        super();
    }
    /**
     * 
     * @param aQCComments
     * Adds a QCComment to the set of QCComments
     */
    public void addQCComments(QCComments aQCComments){
        super.add(aQCComments);
    }
    /**
     * 
     * @param aQCComments 
     * Removes a QCComment from the set of QCComments
     */
    public void removeQCComments(QCComments aQCComments){
        super.remove(aQCComments);
    }
}
