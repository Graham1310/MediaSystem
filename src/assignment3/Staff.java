/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Staff {

    /**
     *
     * @param staffUser
     * @param role
     * Creates constructor
     */
    public Staff(User staffUser, String role) {
        this.staffUser = staffUser;
        this.role = role;
    }
    /**
     * Declares variables required
     */
    private User staffUser;
    private String role;

    /**
     *
     * @return
     */
    public User getStaffUser() {
        return staffUser;
    }

    /**
     *
     * @param staffUser
     */
    public void setStaffUser(User staffUser) {
        this.staffUser = staffUser;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
