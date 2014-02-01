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
    
    /**
     *
     */
    public SetOfQCReports(){
        super();
    }

    /**
     *
     * @param aQCReport
     */
    public void addReport(QCReport aQCReport){
        super.add(aQCReport);
    }

    /**
     *
     * @param aQCReport
     */
    public void removeReport(QCReport aQCReport){
        super.remove(aQCReport);
    }
}
