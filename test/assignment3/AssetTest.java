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
public class AssetTest {
    
    public AssetTest() {
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
     * Test of getSetOfTasks method, of class Asset.
     */
    @Test
    public void testGetSetOfTasks() {
        System.out.println("getSetOfTasks");
        SetOfTasks tasks = new SetOfTasks();
        Asset instance = new Asset(1, "asset name", "asset type", tasks);
        Object result = instance.getSetOfTasks();
        assertThat(result,instanceOf(SetOfTasks.class));
    }

    /**
     * Test of setSetOfTasks method, of class Asset.
     */
    @Test
    public void testSetSetOfTasks() {
        System.out.println("setSetOfTasks");
        SetOfTasks tasks = new SetOfTasks();
        Task task = new Task(1,1,"test task",1,"",1,1,"");
        tasks.add(task);
        Asset instance = new Asset(1, "asset name", "asset type", tasks);
        instance.setSetOfTasks(tasks);
        Object result = instance.getSetOfTasks();
        assertEquals(tasks, result);
    }

    /**
     * Test of getAssetID method, of class Asset.
     */
    @Test
    public void testGetAssetID() {
        System.out.println("getAssetID");
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());
        int expResult = 1;
        int result = instance.getAssetID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAssetID method, of class Asset.
     */
    @Test
    public void testSetAssetID() {
        System.out.println("setAssetID");
        int assetID = 2;
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());
        instance.setAssetID(assetID);
        int result = instance.getAssetID();
        int expResult = 2;
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Asset.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());
        String expResult = "asset name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Asset.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "different name";
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());
        instance.setName(name);
        String expResult = "different name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Asset.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());
        String expResult = "asset type";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Asset.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "different type";
        Asset instance = new Asset(1, "asset name", "asset type", new SetOfTasks());;
        instance.setType(type);
        String expResult = "different type";
        String result = instance.getType();
        assertEquals(expResult, result);
    }
}