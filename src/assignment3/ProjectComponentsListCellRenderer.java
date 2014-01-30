/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Neverborn
 */
public class ProjectComponentsListCellRenderer extends DefaultListCellRenderer{
            public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof ProjectComponent) {
            ProjectComponent projectComponent = (ProjectComponent)value;
            setText(projectComponent.getComponentName());
            // setIcon(ingredient.getIcon());
        }
        return this;
    }
}
