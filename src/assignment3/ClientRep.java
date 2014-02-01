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

    /**
     *
     * @param clientRepUser
     * @param client
     * @param contactNo
     * @param email
     */
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

    /**
     *
     * @return
     */
    public User getClientRepUser() {
        return clientRepUser;
    }

    /**
     *
     * @param clientRepUser
     */
    public void setClientRepUser(User clientRepUser) {
        this.clientRepUser = clientRepUser;
    }

    /**
     *
     * @return
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     *
     * @param contactNo
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
