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
    public void addTask(Task aTask){
        super.add(aTask);
    }
    public void removeTask(Task aTask){
        super.remove(aTask);
    }
}
