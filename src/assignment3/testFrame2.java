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
    public testFrame2() {
        initComponents();
         FillCombo();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("submit and close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(UserCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(122, 122, 122))
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

            this.dispose();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FillCombo(){
        try{
                     ResultSet loginResults = null;
                     Statement statement;
                     
                     statement = connection.createStatement();
                     loginResults = statement.executeQuery( "SELECT * FROM User");                     
                     
                     //This gets stuff from the database and populates in to a combobox
                     while (loginResults.next())
                    {
                        String name = loginResults.getString("firstName");
                        UserCbo.addItem(name);
                    }
            
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
                //new testFrame2().setVisible(true);
                
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
    private javax.swing.JComboBox UserCbo;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}