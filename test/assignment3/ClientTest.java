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
public class ClientTest {
    
    public ClientTest() {
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
     * Test of getClientID method, of class Client.
     */
    @Test
    public void testGetClientID() {
        System.out.println("getClientID");
        Client instance = new Client(1, "organisation name", "organisation address");
        int expResult = 1;
        int result = instance.getClientID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClientID method, of class Client.
     */
    @Test
    public void testSetClientID() {
        System.out.println("setClientID");
        int clientID = 2;
        Client instance = new Client(1, "organisation name", "organisation address");
        instance.setClientID(clientID);
        int expResult = 2;
        int result = instance.getClientID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganisationName method, of class Client.
     */
    @Test
    public void testGetOrganisationName() {
        System.out.println("getOrganisationName");
        Client instance = new Client(1, "organisation name", "organisation address");
        String expResult = "organisation name";
        String result = instance.getOrganisationName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrganisationName method, of class Client.
     */
    @Test
    public void testSetOrganisationName() {
        System.out.println("setOrganisationName");
        String organisationName = "different organisation";
        Client instance = new Client(1, "organisation name", "organisation address");
        instance.setOrganisationName(organisationName);
        String result = instance.getOrganisationName();
        String expResult = "different organisation";
        assertEquals(expResult, result);        
    }

    /**
     * Test of getOrganisationAddress method, of class Client.
     */
    @Test
    public void testGetOrganisationAddress() {
        System.out.println("getOrganisationAddress");
        Client instance = new Client(1, "organisation name", "organisation address");
        String expResult = "organisation address";
        String result = instance.getOrganisationAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrganisationAddress method, of class Client.
     */
    @Test
    public void testSetOrganisationAddress() {
        System.out.println("setOrganisationAddress");
        String organisationAddress = "different address";
        Client instance = new Client(1, "organisation name", "organisation address");
        instance.setOrganisationAddress(organisationAddress);
        String expResult = "different address";
        String result = instance.getOrganisationAddress();
        assertEquals(expResult, result);
    }
}