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
class ProjectComponent {
    private int componentID;
    private String componentName;
    private Date componentDate;
    private SetOfElements componentElements;
    
    public ProjectComponent(int componentID, String componentName, Date componentDate, SetOfElements componentElements){
        this.componentID = componentID;
        this.componentName = componentName;
        this.componentDate = componentDate;
        this.componentElements = componentElements;
    }

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
    
    public Date getComponentDate() {
        return componentDate;
    }

    public void setComponentDate(Date componentDate) {
        this.componentDate = componentDate;
    }
    
    public SetOfElements getComponentElements() {
        return componentElements;
    }

    public void SetOfElements(SetOfElements componentElements) {
        this.componentElements = componentElements;
    }
}
