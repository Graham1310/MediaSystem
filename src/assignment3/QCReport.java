/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;


/**
 *
 * @author Tim Beale
 */
public class QCReport {
    
    private SetOfQCComments comments;
    private User reviewer;
    private int overallSeverity;

    public SetOfQCComments getComments() {
        return comments;
    }

    public void setComments(SetOfQCComments comments) {
        this.comments = comments;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public int getOverallSeverity() {
        return overallSeverity;
    }

    public void setOverallSeverity(int overallSeverity) {
        this.overallSeverity = overallSeverity;
    }
    
}
