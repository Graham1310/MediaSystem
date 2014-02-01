/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Client {

    /**
     *
     * @param clientID
     * @param organisationName
     * @param organisationAddress
     * Creates constructor
     */
    public Client(int clientID, String organisationName, String organisationAddress) {
        this.clientID = clientID;
        this.organisationName = organisationName;
        this.organisationAddress = organisationAddress;
    }
    
    /**
     * Declares variables required
     */
    private int clientID;
    private String organisationName;
    private String organisationAddress;

    /**
     *
     * @return
     */
    public int getClientID() {
        return clientID;
    }

    /**
     *
     * @param clientID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    /**
     *
     * @return
     */
    public String getOrganisationName() {
        return organisationName;
    }

    /**
     *
     * @param organisationName
     */
    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    /**
     *
     * @return
     */
    public String getOrganisationAddress() {
        return organisationAddress;
    }

    /**
     *
     * @param organisationAddress
     */
    public void setOrganisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
    }
}
