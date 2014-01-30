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
    
    private int cQCommentID;

    public QCComments(int cQCommentID, int QCReportID, Date date, String commentText, int severityRating, int faultTimeInSeconds, int assetID, Asset asset) {
        this.cQCommentID = cQCommentID;
        this.QCReportID = QCReportID;
        this.date = date;
        this.commentText = commentText;
        this.severityRating = severityRating;
        this.faultTimeInSeconds = faultTimeInSeconds;
        this.assetID = assetID;
        this.asset = asset;
    }
    private int QCReportID;
    private Date date;
    private String commentText;
    private int severityRating;
    private int faultTimeInSeconds;
    private int assetID;
    
    private Asset asset;

    public int getcQCommentID() {
        return cQCommentID;
    }

    public void setcQCommentID(int cQCommentID) {
        this.cQCommentID = cQCommentID;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getSeverityRating() {
        return severityRating;
    }

    public void setSeverityRating(int severityRating) {
        this.severityRating = severityRating;
    }

    public int getFaultTimeInSeconds() {
        return faultTimeInSeconds;
    }

    public void setFaultTimeInSeconds(int faultTimeInSeconds) {
        this.faultTimeInSeconds = faultTimeInSeconds;
    }
    
     public int getQCReportID() {
        return QCReportID;
    }

    public void setQCReportID(int QCReportID) {
        this.QCReportID = QCReportID;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
}
