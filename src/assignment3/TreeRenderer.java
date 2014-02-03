 
package assignment3;


import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Graham
 */
public class TreeRenderer extends DefaultTreeCellRenderer  {
   public Component getTreeCellRendererComponent(
              JTree tree,Object value, boolean selected, boolean expanded,
              boolean leaf, int row, boolean hasFocus) {
  // Allow the original renderer to set up the label
   Component c = super.getTreeCellRendererComponent(
                  tree, value, selected,
                  expanded, leaf, row,
                  hasFocus); 
   
  DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
   
     if (node.getUserObject() instanceof Asset) {
            Asset task = (Asset)node.getUserObject();
            setText(task.getName());           
       }
     
     if (node.getUserObject() instanceof Task) {
            Task task = (Task)node.getUserObject();
            setText(task.getTaskName());           
        }
          
     if (node.getUserObject() instanceof Element) {
            Element task = (Element)node.getUserObject();
            setText(task.getName());           
        }
     
          if (node.getUserObject() instanceof Project) {
            Project project = (Project)node.getUserObject();
            setText(project.getProjectName());           
        }
   
   return this;
   
    }
    
}
