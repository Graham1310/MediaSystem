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

    public QCReport(int qCReportID, int projectID, SetOfQCComments comments, User reviewer, int overallSeverity) {
        this.qCReportID = qCReportID;
        this.ProjectID = projectID;
        this.comments = comments;
        this.reviewer = reviewer;
        this.overallSeverity = overallSeverity;
    }
    
    private int qCReportID;
    private int ProjectID;
    private SetOfQCComments comments;
    private User reviewer;
    private int overallSeverity;

    public int getqCReportID() {
        return qCReportID;
    }

    public void setqCReportID(int qCReportID) {
        this.qCReportID = qCReportID;
    }
    
     public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }
    
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
