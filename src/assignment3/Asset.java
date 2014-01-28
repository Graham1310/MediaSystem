/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Asset {

    public Asset(int assetID, String name, String type) {
        this.assetID = assetID;
        this.name = name;
        this.type = type;
    }
    
    private int assetID;
    private String name;
    private String type;

    public Asset(int AssID, String Assname, String Asstype) {
        assetID = AssID;
        name = Assname;
        type = Asstype;        
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
