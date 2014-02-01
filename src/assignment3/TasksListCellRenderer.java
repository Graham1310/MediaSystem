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
public class TasksListCellRenderer extends DefaultListCellRenderer{
                public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Task) {
            Task task = (Task)value;
            
           
         
                                   setText(task.getTaskName() + "   Status:" + task.getStatus());
                  
            
            
           
            // setIcon(ingredient.getIcon());
        }
        
         if (value instanceof Asset) {
            Asset task = (Asset)value;
            setText(task.getName());
            // setIcon(ingredient.getIcon());
        }
        
        return this;
    }    
}
