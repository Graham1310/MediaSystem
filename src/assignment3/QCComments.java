/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Date;

/**
 *
 * @author Tim Beale
 */

class QCComments {
    
    /**
     * 
     * @param cQCommentID
     * @param QCReportID
     * @param date
     * @param commentText
     * @param severityRating
     * @param faultTimeInSeconds
     * @param assetID 
     * Creates constructor
     */
    public QCComments(int cQCommentID, int QCReportID, Date date, String commentText, int severityRating, int faultTimeInSeconds, int assetID) {
        this.cQCommentID = cQCommentID;
        this.QCReportID = QCReportID;
        this.date = date;
        this.commentText = commentText;
        this.severityRating = severityRating;
        this.faultTimeInSeconds = faultTimeInSeconds;
        this.assetID = assetID;
    }
    
    /**
     * Declares variables required
     */
    private int cQCommentID;
    private int QCReportID;
    private Date date;
    private String commentText;
    private int severityRating;
    private int faultTimeInSeconds;
    private int assetID;

    /**
     * 
     * @return 
     */
    public int getAssetID() {
        return assetID;
    }
/**
 * 
 * @param assetID 
 */
    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
/**
 * 
 * @return 
 */
    public int getcQCommentID() {
        return cQCommentID;
    }
/**
 * 
 * @param cQCommentID 
 */
    public void setcQCommentID(int cQCommentID) {
        this.cQCommentID = cQCommentID;
    }
    /**
     * 
     * @return 
     */
    public Date getDate() {
        return date;
    }
/**
 * 
 * @param date 
 */
    public void setDate(Date date) {
        this.date = date;
    }
/**
 * 
 * @return 
 */
    public String getCommentText() {
        return commentText;
    }
/**
 * 
 * @param commentText 
 */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
/**
 * 
 * @return 
 */
    public int getSeverityRating() {
        return severityRating;
    }
/**
 * 
 * @param severityRating 
 */
    public void setSeverityRating(int severityRating) {
        this.severityRating = severityRating;
    }
/**
 * 
 * @return 
 */
    public int getFaultTimeInSeconds() {
        return faultTimeInSeconds;
    }
/**
 * 
 * @param faultTimeInSeconds 
 */
    public void setFaultTimeInSeconds(int faultTimeInSeconds) {
        this.faultTimeInSeconds = faultTimeInSeconds;
    }
    /**
     * 
     * @return 
     */
     public int getQCReportID() {
        return QCReportID;
    }
/**
 * 
 * @param QCReportID 
 */
    public void setQCReportID(int QCReportID) {
        this.QCReportID = QCReportID;
    }
}
