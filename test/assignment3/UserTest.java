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
public class UserTest {
    
    public UserTest() {
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
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        User instance = new User(1, "first name", "surname", "username", "password");
        int expResult = 1;
        int result = instance.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserID method, of class User.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        int userID = 111;
        User instance = new User(1, "first name", "surname", "username", "password");
        instance.setUserID(userID);
        int expResult = 111;
        int result = instance.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User(1, "first name", "surname", "username", "password");
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "different username";
        User instance = new User(1, "first name", "surname", "username", "password");
        instance.setUsername(username);
        String expResult = "different username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User(1, "first name", "surname", "username", "password");
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "different password";
        User instance = new User(1, "first name", "surname", "username", "password");
        instance.setPassword(password);
        String expResult = "different password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        User instance = new User(1, "first name", "surname", "username", "password");
        String expResult = "first name";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "different name";
        User instance = new User(1, "first name", "surname", "username", "password");
        instance.setFirstName(firstName);
        String expResult = "different name";
        String result = instance.getFirstName();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        User instance = new User(1, "first name", "surname", "username", "password");
        String expResult = "surname";
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurname method, of class User.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "different surname";
        User instance = new User(1, "first name", "surname", "username", "password");
        instance.setSurname(surname);
        String expResult = "different surname";
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }
}