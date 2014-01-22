/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Tim Beale
 */
public class Element {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SetOfComponents getComponentElements() {
        return componentElements;
    }

    public void setComponentElements(SetOfComponents componentElements) {
        this.componentElements = componentElements;
    }
    private SetOfComponents componentElements;
}
