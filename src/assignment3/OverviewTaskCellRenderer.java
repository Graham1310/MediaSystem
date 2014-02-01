/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment3;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
/**
 *
 * @author Graham
 */
public class OverviewTaskCellRenderer extends DefaultListCellRenderer{
                public Component getListCellRendererComponent(JList<?> list,
                                 Object value,
                                 int index,
                                 boolean isSelected,
                                 boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Task) {
            Task task = (Task)value;
            
             if (task.getResponsiblePerson() == null && task.getStatus().equals("Not Started"))
            {
              setText(task.getTaskName());                    
            }                              
            else if (task.getStatus().equals("Not Started") && task.getResponsiblePerson() != null)
            {
               setText(task.getTaskName() + "    " + task.getResponsiblePerson().getUsername());                  
            }                              
            else if (task.getStatus().equals("In Progress"))
            {
               setText(task.getTaskName());                         
            }                              
            else if (task.getStatus().equals("Completed"))
            {
                setText(task.getTaskName());                        
            }
           
         
                                   
                  
            
            
           
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
