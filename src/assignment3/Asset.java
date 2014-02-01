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

    /**
     *
     * @param AssID
     * @param Assname
     * @param Asstype
     * @param setOfTasks
     */
    public Asset(int AssID, String Assname, String Asstype, SetOfTasks setOfTasks) {
        assetID = AssID;
        name = Assname;
        type = Asstype; 
        setOfTasks = setOfTasks;
    }

    /**
     *
     * @return
     */
    public SetOfTasks getSetOfTasks() {
        return setOfTasks;
    }

    /**
     *
     * @param setOfTasks
     */
    public void setSetOfTasks(SetOfTasks setOfTasks) {
        this.setOfTasks = setOfTasks;
    }
    
    /**
     *
     * @return
     */
    public int getAssetID() {
        return assetID;
    }

    /**
     *
     * @param assetID
     */
    public void setAssetID(int assetID) {
        this.assetID = assetID;
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
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
