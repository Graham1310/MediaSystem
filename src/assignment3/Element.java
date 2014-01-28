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
    private int elementID;
    private String name;
    private SetOfAssets setOfAssets;

    public Element(String ElName) {
       name = ElName;
    }

    public Element(int elID, String ElName) {
        elementID = elID;
        name = ElName;       
    }

    public int getElementID() {
        return elementID;
    }

    public void setElementID(int elementID) {
        this.elementID = elementID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public SetOfAssets getSetOfAssets() {
        return setOfAssets;
    }
    
    public void SetOfAssets (SetOfAssets setOfAssets) {
        this.setOfAssets = setOfAssets;
    }
        
}
