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
class Component {
    private Date date;
    private SetOfAssets setOfAssets;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void SetComponentAssets (SetOfAssets setOfAssets) {
        this.setOfAssets = setOfAssets;
    }
    
     public SetOfAssets getComponentAssets() {
        return setOfAssets;
    }
}
