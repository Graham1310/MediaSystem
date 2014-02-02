/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Vector;

/**
 *
 * @author Tim Beale
 */
class SetOfTasks extends Vector<Task>{
    public SetOfTasks(){
        super();
    }
    /**
     * 
     * @param aTask 
     * Adds a task to the set of tasks
     */
    public void addTask(Task aTask){
        super.add(aTask);
    }
    /**
     * 
     * @param aTask 
     * Removes a task from the set of tasks
     */
    public void removeTask(Task aTask){
        super.remove(aTask);
    }
}
