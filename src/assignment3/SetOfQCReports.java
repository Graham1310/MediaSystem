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
     * Adds a QCReport to the set of QCReports
     */
    public void addReport(QCReport aQCReport){
        super.add(aQCReport);
    }

    /**
     *
     * @param aQCReport
     * Removes a QCReport from the set of QCReports
     */
    public void removeReport(QCReport aQCReport){
        super.remove(aQCReport);
    }
}
