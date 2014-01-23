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
    private SetOfElements componentElements;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public SetOfElements getComponentElements() {
        return componentElements;
    }

    public void SetOfElements(SetOfElements componentElements) {
        this.componentElements = componentElements;
    }
}
