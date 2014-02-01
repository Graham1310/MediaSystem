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

    /**
     *
     */
    public SetOfProjects(){
        super();
    }

    /**
     *
     * @param aProject
     */
    public void addProject(Project aProject){
        super.add(aProject);
    }

    /**
     *
     * @param aProject
     */
    public void removeProject(Project aProject){
        super.remove(aProject);
    }
}
