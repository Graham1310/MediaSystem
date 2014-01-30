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

    public Staff(User staffUser, String role) {
        this.staffUser = staffUser;
        this.role = role;
    }
    private User staffUser;
    private String role;

    public User getStaffUser() {
        return staffUser;
    }

    public void setStaffUser(User staffUser) {
        this.staffUser = staffUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
