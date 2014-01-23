/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Vector;

/**
 *
 * @author Tim Beale
 */
class SetOfAssets extends Vector<Asset>{
    
    public SetOfAssets(){
        super();
    }
    public void addAsset(Asset aAsset){
        super.add(aAsset);
    }
    public void removeAsset(Asset aAsset){
        super.remove(aAsset);
    }
}
