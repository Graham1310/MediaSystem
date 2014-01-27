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
    private String commentText;
    private int severityRating;
    private int faultTimeInSeconds;
    private Date date;
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
}
