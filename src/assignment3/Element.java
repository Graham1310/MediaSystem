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
    private String name;
    private SetOfAssets setOfAssets;

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
