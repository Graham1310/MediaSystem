/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import static assignment3.LogInUI2.connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author Neverborn
 */
public class testFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form testFrame2
     */
    
    SetOfAssets AllAssets = new SetOfAssets();
    SetOfElements AllElements = new SetOfElements();
    
    public testFrame2() {
         
        
        initComponents();
         FillCombo();
         FillLists();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        UserCbo = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ElList = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        AssList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("submit and close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        UserCbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserCboActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jButton2.setText("Save updated User");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(ElList);

        jButton3.setText("assign Assets to elemnt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        AssList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(AssList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(UserCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(48, 48, 48))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(42, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(112, 112, 112))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //int userID;  //don't include userID as it will automatically add as it is an autonumber
            String firstName = "testfirstname001";
            String surname = "testsurname001";
            String username = "testusername001";
            String password = "testpassword001";
            Statement statement;
            statement = connection.createStatement();
            //Checks and this add things in to the database. When doing on forms the variables can come from selected items, text boxes etc.
            statement.executeUpdate( "INSERT INTO User(firstName,surname, username, password) "
                    + "VALUES ('" + firstName + "', '" + surname + "','" + username + "', '" + password + "');");

            FillCombo();
            this.dispose();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void UserCboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserCboActionPerformed
     User selectedUser = (User) UserCbo.getSelectedItem();
        jTextField1.setText(selectedUser.getFirstName());        
        jTextField2.setText(selectedUser.getSurname());
        jTextField3.setText(selectedUser.getUsername());
    }//GEN-LAST:event_UserCboActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //UPDATE THE USER HERE
          try {
            User selectedUser = (User) UserCbo.getSelectedItem();
        
            String firstName =  jTextField1.getText();
            String surname = jTextField2.getText();
            String username = jTextField3.getText();
            Statement statement;
            
            statement = connection.createStatement();
            //UPDATE Customers
//SET ContactName='Alfred Schmidt', City='Hamburg'
//WHERE CustomerName='Alfreds Futterkiste';
            
            
    
            //Checks and this add things in to the database. When doing on forms the variables can come from selected items, text boxes etc.
            statement.executeUpdate( "UPDATE  User SET [firstName] ='" + firstName + "', [surname] ='" + surname + "', [username] ='" + username + "'"
                    + " WHERE [userID] =" + selectedUser.getUserID() + "") ;

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }               
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        Element selectedElement = (Element) ElList.getSelectedValue();
        SetOfAssets elementAssets = new SetOfAssets();
      
        for (int i=0; i<AssList.getSelectedValuesList().size(); i++){
                        
                        elementAssets.add((Asset) AssList.getModel().getElementAt(i));
                    }
                        
        //selectedElement.SetOfAssets((SetOfAssets) AssList.getSelectedValuesList());
                   
          try {
                    //connection.close();
                    
                    //elementAssets = (SetOfAssets) AssList.getSelectedValuesList();
                    //requires code to put each asset selected in GUI to be put into "elementAssets"
                    Statement statement;
                    statement = connection.createStatement();
                    for (int i=0; i<elementAssets.size(); i++){
                        
                        //statement.executeUpdate( "INSERT INTO User(firstName,surname, username, password) "
                    //+ "VALUES ('" + firstName + "', '" + surname + "','" + username + "', '" + password + "');");
                        
                        int assid = elementAssets.get(i).getAssetID();
                        int eleid = selectedElement.getElementID();
                        
                        //statement.executeUpdate("INSERT INTO SetOFAssets(elementID, assetID)"
                                //+ "VALUES (" + eleid + "," + assid + ");" );
                        
                        statement.executeUpdate("INSERT INTO SetOFAssets(elementID, assetID)"
                                + "VALUES (1,3);" );
                    }
                } catch (SQLException ex) {
                     System.out.println("ERROR: " + ex);
                }
        
        
        
                    ElList.setListData(AllElements);
                    AssList.setListData(AllAssets);
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    
    
    
    private void FillCombo(){
        try{
                     ResultSet loginResults = null;
                     Statement statement;
                     
                     statement = connection.createStatement();
                     loginResults = statement.executeQuery( "SELECT * FROM User");                     
                     UserCbo.removeAllItems();
                     //This gets stuff from the database and populates in to a combobox
                     while (loginResults.next())
                    {
                        User UserLoggedIn;
                        UserLoggedIn = new User(loginResults.getInt("userID"), loginResults.getString("firstName"), loginResults.getString("surname"), loginResults.getString("username"), loginResults.getString("password"), loginResults.getString("role"));

                        //String name = loginResults.getString("firstName");
                        UserCbo.addItem(UserLoggedIn);
                        //jListUser.addElement(UserLoggedIn.toString());
                        
                    }
                     
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
            
    }
    
    private void FillLists(){
          try{
                     ResultSet AssetResults = null;
                     ResultSet ElementResults = null;
                     
                     Statement GetAssets;
                     Statement GetElements;
                     
                    
                     
                     GetElements = connection.createStatement();
                     ElementResults = GetElements.executeQuery( "SELECT * FROM Element");                     
                    
                     
                     //This gets stuff from the database and populates in to a combobox
                     while (ElementResults.next())
                    {
                       
                       Element AddElement;
                       
                       AddElement = new Element(ElementResults.getInt("elementID"), ElementResults.getString("elementName"));
                       //UserLoggedIn = new User(loginResults.getInt("userID"), loginResults.getString("firstName"), loginResults.getString("surname"), loginResults.getString("username"), loginResults.getString("password"));

                       AllElements.addElement(AddElement);
                        
                    }
                                                                                                     
                     GetAssets = connection.createStatement();
                     AssetResults = GetAssets.executeQuery( "SELECT * FROM Asset");  
                      
                    while (AssetResults.next())
                    {
                        
                        Asset AddAsset;
                        
                        AddAsset = new Asset(AssetResults.getInt("ID"),AssetResults.getString("assetName"), AssetResults.getString("assetType"));    
                        
                        AllAssets.addAsset(AddAsset);                    
                    }
                    
                    ElList.setListData(AllElements);
                    AssList.setListData(AllAssets);
                    //ElList.setCellRenderer()
                    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
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
            java.util.logging.Logger.getLogger(testFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testFrame2().setVisible(true);
                
            }
        });
        
         //Connection String for Tim
        //String fileName = "C:\\Users\\Tim Beale\\Documents\\Uni Work\\Year 3 again\\Case Studies\\Assignment3\\CSSD.mdb";
        //Connection String for Tim on Uni PC
        //String fileName = "F:\\MyWork\\Year 3 again\\CSSD\\Assignment 3 - Code\\CSSD.mdb";
        //Connection String for Marcin
        //String fileName = "C:\\Users\\Neverborn\\Documents\\NetBeansProjects\\MediaSystem\\CSSD.accdb";
        /*Connction String for Graham */
        String fileName = "F:\\MyWork\\NetBeansProjects\\JavaApplication1\\CSSD.mdb"; 
        String dbString ="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + fileName + ";"; //Change back to *mdb for 32bit access  		
                
    	try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			connection = DriverManager.getConnection(dbString,"","");
			System.out.println("Server Connected To Database");
		}
		
		catch(ClassNotFoundException | SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
  
       
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList AssList;
    private javax.swing.JList ElList;
    private javax.swing.JComboBox UserCbo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
