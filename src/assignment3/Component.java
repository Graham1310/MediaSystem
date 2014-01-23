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
    private SetOfComponents componentElements;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public SetOfComponents getComponentElements() {
        return componentElements;
    }

    public void setComponentElements(SetOfComponents componentElements) {
        this.componentElements = componentElements;
    }
}
