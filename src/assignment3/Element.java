/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Element {

    /**
     *
     * @param elementID
     * @param name
     * @param setOfAssets
     * Creates constructor
     */
    public Element(int elementID, String name, SetOfAssets setOfAssets) {
        this.elementID = elementID;
        this.name = name;
        this.setOfAssets = setOfAssets;
    }
    
    /**
     * Defines variables required
     */
    private int elementID;
    private String name;
    private SetOfAssets setOfAssets;
    private boolean approved;

    /**
     *
     * @param ElName
     */
    public Element(String ElName) {
       name = ElName;
    }

    /**
     *
     * @param elID
     * @param ElName
     */
    public Element(int elID, String ElName) {
        this.elementID = elID;
        this.name = ElName; 
    }
    
        /**
     *
     * @param elID
     * @param ElName
     * @param approved
     */
    public Element(int elID, String ElName, boolean approved) {
        this.elementID = elID;
        this.name = ElName; 
        this.approved = approved;
    }

    /**
     *
     * @return
     */
    public int getElementID() {
        return elementID;
    }

    /**
     *
     * @param elementID
     */
    public void setElementID(int elementID) {
        this.elementID = elementID;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    public SetOfAssets getSetOfAssets() {
        return setOfAssets;
    }
    
    /**
     *
     * @param setOfAssets
     */
    public void SetOfAssets (SetOfAssets setOfAssets) {
        this.setOfAssets = setOfAssets;
    }
    
    /**
     *
     * @return
     */
    public boolean getApproved() {
        return approved;
    }

    /**
     *
     * @param approved
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }    
        
}
