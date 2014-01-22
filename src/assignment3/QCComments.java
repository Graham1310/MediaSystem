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
    private Date date;

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
    private String commentText;
    private int severityRating;
    private int faultTimeInSeconds;
}
