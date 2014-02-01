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

    /**
     *
     * @param qCReportID
     * @param projectID
     * @param comments
     * @param reviewer
     * @param overallSeverity
     */
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

    /**
     *
     * @return
     */
    public int getqCReportID() {
        return qCReportID;
    }

    /**
     *
     * @param qCReportID
     */
    public void setqCReportID(int qCReportID) {
        this.qCReportID = qCReportID;
    }

    /**
     *
     * @return
     */
    public int getProjectID() {
        return ProjectID;
    }

    /**
     *
     * @param ProjectID
     */
    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }
    
    /**
     *
     * @return
     */
    public SetOfQCComments getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(SetOfQCComments comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public User getReviewer() {
        return reviewer;
    }

    /**
     *
     * @param reviewer
     */
    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    /**
     *
     * @return
     */
    public int getOverallSeverity() {
        return overallSeverity;
    }

    /**
     *
     * @param overallSeverity
     */
    public void setOverallSeverity(int overallSeverity) {
        this.overallSeverity = overallSeverity;
    }
    
}
