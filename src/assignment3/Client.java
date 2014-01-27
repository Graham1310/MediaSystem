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
    
    private int clientID;
    private String organisationName;
    private String organisationAddress;

     public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationAddress() {
        return organisationAddress;
    }

    public void setOrganisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
    }
}
