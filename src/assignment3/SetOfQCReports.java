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
public class SetOfQCReports extends Vector<QCReport>{
    
    public SetOfQCReports(){
        super();
    }
    public void addQCComments(QCReport aQCReport){
        super.add(aQCReport);
    }
    public void removeQCComments(QCReport aQCReport){
        super.remove(aQCReport);
    }
}
