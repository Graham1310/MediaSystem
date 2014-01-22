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
public class SetOfProjects extends Vector<Project>{
    public SetOfProjects(){
        super();
    }

    public void addUser(Project aProject){
        super.add(aProject);
    }

    public void removeUser(Project aProject){
        super.remove(aProject);
    }
}
