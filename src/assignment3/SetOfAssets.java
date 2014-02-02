/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Vector;

/**
 *
 * @author Tim Beale
 * Stores list of set of assets
 */
class SetOfAssets extends Vector<Asset>{
    
    public SetOfAssets(){
        super();
    }
    /**
     * 
     * @param aAsset 
     * Adds passed in asset to the list
     */
    public void addAsset(Asset aAsset){
        super.add(aAsset);
    }
    /**
     * 
     * @param aAsset 
     * Removes passed in asset from the list
     */
    public void removeAsset(Asset aAsset){
        super.remove(aAsset);
    }
}
