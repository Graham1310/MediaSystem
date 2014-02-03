/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Neverborn
 */
public class StaffTest {
    
    public StaffTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStaffUser method, of class Staff.
     */
    @Test
    public void testGetStaffUser() {
        System.out.println("getStaffUser");
        User user = new User(1, "name", "surname");
        Staff instance = new Staff(user, "role");
        User expResult = user;
        User result = instance.getStaffUser();
        assertEquals(expResult, result);
     }

    /**
     * Test of setStaffUser method, of class Staff.
     */
    @Test
    public void testSetStaffUser() {
        System.out.println("setStaffUser");
        User user = new User(1, "name", "surname");        
        User staffUser = new User(2, "name2", "surname2"); 
        Staff instance = new Staff(user, "role");
        instance.setStaffUser(staffUser);
        User expResult = staffUser;
        User result = instance.getStaffUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole method, of class Staff.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        User user = new User(1, "name", "surname");        
        Staff instance = new Staff(user, "role");
        String expResult = "role";
        String result = instance.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRole method, of class Staff.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        User user = new User(1, "name", "surname");   
        String role = "different role";
        Staff instance = new Staff(user, "role");
        instance.setRole(role);
        String expResult = "different role";
        String result = instance.getRole();
        assertEquals(expResult, result);
    }
}