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
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 *
 * @author Neverborn
 */
public class ElementTest {
    
    public ElementTest() {
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
     * Test of getElementID method, of class Element.
     */
    @Test
    public void testGetElementID() {
        System.out.println("getElementID");
        Element instance = new Element(1, "element name", new SetOfAssets());
        int expResult = 1;
        int result = instance.getElementID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElementID method, of class Element.
     */
    @Test
    public void testSetElementID() {
        System.out.println("setElementID");
        int elementID = 2;
        Element instance = new Element(1, "element name", new SetOfAssets());
        instance.setElementID(elementID);
        int expResult = 2;
        int result = instance.getElementID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Element.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Element instance = new Element(1, "element name", new SetOfAssets());
        String expResult = "element name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Element.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "different name";
        Element instance = new Element(1, "element name", new SetOfAssets());
        instance.setName(name);
        String expResult = "different name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetOfAssets method, of class Element.
     */
    @Test
    public void testGetSetOfAssets() {
        System.out.println("getSetOfAssets");
        Element instance = new Element(1, "element name", new SetOfAssets());
        Object result = instance.getSetOfAssets();
        assertThat(result,instanceOf(SetOfAssets.class));
    }

    /**
     * Test of SetOfAssets method, of class Element.
     */
    @Test
    public void testSetOfAssets() {
        System.out.println("SetOfAssets");
        SetOfAssets assets = new SetOfAssets();
        Asset asset = new Asset(1, "asset name", "asset type", new SetOfTasks());
        assets.add(asset);
        Element instance = new Element(1, "element name", new SetOfAssets());
        instance.SetOfAssets(assets);
        Object result = instance.getSetOfAssets();
        assertEquals(assets, result);       
    }
    
     /**
     * Test of testGetName method, of class Element.
     */
    @Test
    public void testGetApproved() {
        System.out.println("getApproved");
        Element instance = new Element(1, "element name", true);
        boolean expResult = true;
        boolean result = instance.getApproved();
        assertEquals(expResult, result);
    }

    /**
     * Test of testSetName method, of class Element.
     */
    @Test
    public void testSetApproved() {
        System.out.println("setApproved");
        boolean approved = false;
        Element instance = new Element(1, "element name", true);
        instance.setApproved(approved);
        boolean expResult = true;
        boolean result = instance.getApproved();
        assertEquals(expResult, result);
    }    
    
}