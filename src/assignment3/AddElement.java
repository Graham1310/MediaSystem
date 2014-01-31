/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b0025885
 */
public class AddElement extends javax.swing.JFrame {

    public SetOfAssets NewAssets =  new SetOfAssets();
    public SetOfTasks AssetTasks = new SetOfTasks();
    private Element element;
    int tempElementID=0; //no need for deletion upon exit when 0 
    /**
     * Creates new form AddElement
     */
    public AddElement() {
        initComponents();
        insertTempElementIntoDataBase();
        setLastElementIDFromDataBase();      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        elementNameTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        assetList = new javax.swing.JList();
        AddAssetBtn = new javax.swing.JButton();
        createAssetBtn = new javax.swing.JButton();
        removeAssetBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList();
        addNewTask = new javax.swing.JButton();
        removeTaskBtn = new javax.swing.JButton();
        saveElementBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Element Name");

        jLabel2.setText("Assets on Element ");

        assetList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(assetList);

        AddAssetBtn.setText("Add Asset");
        AddAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAssetBtnActionPerformed(evt);
            }
        });

        createAssetBtn.setText("Create New Asset");
        createAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAssetBtnActionPerformed(evt);
            }
        });

        removeAssetBtn.setText("Remove Asset");
        removeAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAssetBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Tasks on Selected Asset");

        taskList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(taskList);

        addNewTask.setText("Add New Task");
        addNewTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTaskActionPerformed(evt);
            }
        });

        removeTaskBtn.setText("Remove Task");
        removeTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTaskBtnActionPerformed(evt);
            }
        });

        saveElementBtn.setText("Save Element");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("refresh assets");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveElementBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(elementNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddAssetBtn)
                                            .addComponent(createAssetBtn)
                                            .addComponent(removeAssetBtn)))
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addNewTask)
                                            .addComponent(removeTaskBtn)))))
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(elementNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddAssetBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(createAssetBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeAssetBtn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addNewTask)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeTaskBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveElementBtn)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertTempElementIntoDataBase(){
        try {
            
            Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate( "INSERT INTO Element(elementName) VALUES ('TempElement');");
        } catch (SQLException ex) {
            
        }
    };
    
    private void setLastElementIDFromDataBase(){
        try {
            ResultSet tempProjectIDResults= null;
            Statement statement;
            statement = connection.createStatement();
            tempProjectIDResults= statement.executeQuery( "SELECT LAST(elementID) FROM Element;"); // select last only supported in ms access
            
            while (tempProjectIDResults.next()){
                tempElementID = tempProjectIDResults.getInt(1);
            }
           
        } catch (SQLException ex) {
           
        }
     }   
    
    private void AddAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAssetBtnActionPerformed
        // LOADS UP ADD ASSET UI
        Project SelectedProject = null;
        new AddAssetToElement(tempElementID).setVisible(true);
    }//GEN-LAST:event_AddAssetBtnActionPerformed

    private void createAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAssetBtnActionPerformed
        // LOADS UP CREATE NEW ASSET UI
        new CreateAssetUI().setVisible(true);
    }//GEN-LAST:event_createAssetBtnActionPerformed

    private void removeAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAssetBtnActionPerformed
        // REMOVE ASSET FROM ELEMENT delete from database update the list
        
        
        
        
    }//GEN-LAST:event_removeAssetBtnActionPerformed

    private void addNewTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTaskActionPerformed
        // ADDS TASK TO SELECTED ASSET, LOADS UP UI.
        //NEEDS TO UPDATE THE LIST
        Asset SelectedAsset = (Asset) assetList.getSelectedValue();
         new AddTaskUI(SelectedAsset.getAssetID()).setVisible(true);
    }//GEN-LAST:event_addNewTaskActionPerformed

    private void removeTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTaskBtnActionPerformed
        // REMOVES TASK FROM SELECTED ASSET and DATABASE
    }//GEN-LAST:event_removeTaskBtnActionPerformed

    private void saveElementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveElementBtnActionPerformed
         
        element = new Element(elementNameTxt.getText());
        
        insertElementIntoDataBase(element);
        this.dispose();    // TODO add your handling code here:
    }//GEN-LAST:event_saveElementBtnActionPerformed

    private void assetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_assetListValueChanged
        Asset SelectedAsset = (Asset) assetList.getSelectedValue();
        taskList.setListData(SelectedAsset.getSetOfTasks());        
    }//GEN-LAST:event_assetListValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //remove the fake element
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Refresh assets
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

     private void insertElementIntoDataBase(Element element){
        try {
            
            Statement statement;
            statement = connection.createStatement();
//            statement.executeUpdate( "INSERT INTO Project(projectName, rootComponent, teamLeader, clientRep, priority) "
//                    + "VALUES ('" + project.getProjectName() + "', '" + project.getRootComponentID() + "','" + project.getTeamLeaderID() + "', '" + project.getClientRepID() + "', '" + project.getPriority() + "');"); // change to update
            statement.executeUpdate("UPDATE Element SET elementName='" + element.getName()+ " WHERE elementID = " + element.getElementID() +";");
        } catch (SQLException ex) {
            
        }
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddElement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddElement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddElement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddElement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddElement().setVisible(true);
            }
        });
        
      
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAssetBtn;
    private javax.swing.JButton addNewTask;
    private javax.swing.JList assetList;
    private javax.swing.JButton createAssetBtn;
    private javax.swing.JTextField elementNameTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeAssetBtn;
    private javax.swing.JButton removeTaskBtn;
    private javax.swing.JButton saveElementBtn;
    private javax.swing.JList taskList;
    // End of variables declaration//GEN-END:variables
}
