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
public class TaskTest {
    
    public TaskTest() {
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
     * Test of getType method, of class Task.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        String expResult = "task type";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Task.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "different type";
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setType(type);
        String expResult = "different type";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsset method, of class Task.
     */
    @Test
    public void testGetAsset() {
        System.out.println("getAsset");
        Asset asset = new Asset(1, "asset name", "asset type", new SetOfTasks());
        User user = new User(1, "name", "surname");
        Task instance = new Task(1, user ,"task name", 3 ,"task status", 4, asset ,"task type");
        Asset expResult = asset;
        Asset result = instance.getAsset();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssetID method, of class Task.
     */
    @Test
    public void testGetAssetID() {
        System.out.println("getAssetID");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        int expResult = 5;
        int result = instance.getAssetID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAsset method, of class Task.
     */
    @Test
    public void testSetAsset() {
        System.out.println("setAsset");
        Asset asset = new Asset(1, "asset name", "asset type", new SetOfTasks());
        Asset asset2 = new Asset(2, "asset name2", "asset type2", new SetOfTasks());
        User user = new User(1, "name", "surname");
        Task instance = new Task(1, user ,"task name", 3 ,"task status", 4, asset ,"task type");
        instance.setAsset(asset2);
        Asset expResult = asset2;
        Asset result = instance.getAsset();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProjectID method, of class Task.
     */
    @Test
    public void testGetProjectID() {
        System.out.println("getProjectID");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        int expResult = 4;
        int result = instance.getProjectID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProjectID method, of class Task.
     */
    @Test
    public void testSetProjectID() {
        System.out.println("setProjectID");
        int projectID = 111;
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setProjectID(projectID);
        int expResult = 111;
        int result = instance.getProjectID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaskID method, of class Task.
     */
    @Test
    public void testGetTaskID() {
        System.out.println("getTaskID");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        int expResult = 1;
        int result = instance.getTaskID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaskID method, of class Task.
     */
    @Test
    public void testSetTaskID() {
        System.out.println("setTaskID");
        int taskID = 111;
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setTaskID(taskID);
        int expResult = 111;
        int result = instance.getTaskID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaskName method, of class Task.
     */
    @Test
    public void testGetTaskName() {
        System.out.println("getTaskName");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        String expResult = "task name";
        String result = instance.getTaskName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTaskName method, of class Task.
     */
    @Test
    public void testSetTaskName() {
        System.out.println("setTaskName");
        String taskName = "different task name";
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setTaskName(taskName);
        String expResult = "different task name";
        String result = instance.getTaskName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getResponsiblePerson method, of class Task.
     */
    @Test
    public void testGetResponsiblePerson() {
        System.out.println("getResponsiblePerson");
        Asset asset = new Asset(1, "asset name", "asset type", new SetOfTasks());
        User user = new User(1, "name", "surname");
        Task instance = new Task(1, user ,"task name", 3 ,"task status", 4, asset ,"task type");
        User expResult = user;
        User result = instance.getResponsiblePerson();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResponsiblePerson method, of class Task.
     */
    @Test
    public void testSetResponsiblePerson() {
        System.out.println("setResponsiblePerson");
        Asset asset = new Asset(1, "asset name", "asset type", new SetOfTasks());
        User user = new User(1, "name", "surname");        
        User responsiblePerson = new User(2, "name2", "surname2");
        Task instance = new Task(1, user ,"task name", 3 ,"task status", 4, asset ,"task type");
        User expResult = responsiblePerson;
        instance.setResponsiblePerson(responsiblePerson);
        User result = instance.getResponsiblePerson();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Task.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        int expResult = 3;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriority method, of class Task.
     */
    @Test
    public void testSetPriority() {
        System.out.println("setPriority");
        int priority = 1;
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setPriority(priority);
        int expResult = 1;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class Task.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        String expResult = "task status";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Task.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "different status";
        Task instance = new Task(1,2,"task name",3,"task status",4,5,"task type");
        instance.setStatus(status);
        String expResult = "different status";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }
}