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
    private int componentID;
    private String componentName;
    private Date date;
    private SetOfElements componentElements;

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
    
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
