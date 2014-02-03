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
class UserListCellRenderer extends DefaultListCellRenderer{
            public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof User) {
            User user = (User)value;
            setText(user.getFirstName()+ " " + user.getSurname());
            // setIcon(ingredient.getIcon());
        }
        if (value instanceof Asset) {
            Asset asset = (Asset)value;
            setText(asset.getName());
            // setIcon(ingredient.getIcon());
        }
        
      if (value instanceof Project) {
            Project project = (Project)value;
            setText(project.getProjectName());
            // setIcon(ingredient.getIcon());
        }
      
          if (value instanceof Element) {
            Element element = (Element)value;
            setText(element.getName());
            // setIcon(ingredient.getIcon());
        }
        
        
        return this;
    
    }
}
