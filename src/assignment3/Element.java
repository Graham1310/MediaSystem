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
     */
    public Element(int elementID, String name, SetOfAssets setOfAssets) {
        this.elementID = elementID;
        this.name = name;
        this.setOfAssets = setOfAssets;
    }
    private int elementID;
    private String name;
    private SetOfAssets setOfAssets;

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
        elementID = elID;
        name = ElName;       
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
        
}
