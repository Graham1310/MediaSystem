/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class ClientRep {

    public ClientRep(User clientRepUser, Client client, String contactNo, String email) {
        this.clientRepUser = clientRepUser;
        this.client = client;
        this.contactNo = contactNo;
        this.email = email;
    }
    
    private User clientRepUser;
    private Client client;
    private String contactNo;
    private String email;

    public User getClientRepUser() {
        return clientRepUser;
    }

    public void setClientRepUser(User clientRepUser) {
        this.clientRepUser = clientRepUser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
