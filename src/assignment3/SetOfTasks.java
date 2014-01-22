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
    private void addTask(Task aTask){
        super.add(aTask);
    }
    private void removeTask(Task aTask){
        super.remove(aTask);
    }
}
