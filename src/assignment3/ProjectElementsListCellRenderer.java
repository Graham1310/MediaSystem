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
class ProjectElementsListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Element) {
            Element element = (Element)value;
            setText(element.getName());
            // setIcon(ingredient.getIcon());
        }
        return this;
    }    
}
