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

    private int assetID;
    private String name;
    private String type;
    private SetOfTasks setOfTasks;

    public Asset(int AssID, String Assname, String Asstype, SetOfTasks setOfTasks) {
        assetID = AssID;
        name = Assname;
        type = Asstype;        
    }

     public SetOfTasks getSetOfTasks() {
        return setOfTasks;
    }

    public void setSetOfTasks(SetOfTasks setOfTasks) {
        this.setOfTasks = setOfTasks;
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
