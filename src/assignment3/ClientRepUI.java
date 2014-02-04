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
import javax.swing.JOptionPane;

/**
 *
 * @author Neverborn
 */
public class ClientRepUI extends javax.swing.JFrame {

    private int userLoggedInID;
    private SetOfProjects listOfUserProjects = new SetOfProjects();
    private SetOfElements listOfElementsToApprove = new SetOfElements();
    private SetOfElements listOfElementsApproved = new SetOfElements();
    private Project selectedProject;
    private Element selectedElement;
    /**
     * Creates new form ClientRepUI
     */
    public ClientRepUI(int userID) {
        this.userLoggedInID = userID;
        initComponents();
        fillInProjectsList(userLoggedInID);
    }

       private void fillInProjectsList(int userID){
        try{
            ResultSet projectsResultSet = null;
            listOfUserProjects.clear();
            Statement statement;
            
            statement = connection.createStatement();
            projectsResultSet = statement.executeQuery("SELECT User.userID, Project.projectID, Project.projectName, "
                  + "Project.teamLeader, Project.clientRep, Project.priority FROM User INNER JOIN (Staff INNER JOIN (Project INNER JOIN "
                          + "StaffOnProjects ON Project.projectID = StaffOnProjects.projectID) ON Staff.staffID = StaffOnProjects.staffID)"                     
                          + " ON User.userID = Staff.staffID WHERE Project.clientRep=" + userID + ";");
 
            Project project;
            int projectID;
            String projectName;
            User teamLeader;
            User clientRep;
            int priority;
            SetOfElements elementCollection;
            SetOfQCReports reports;
            SetOfStaff setOfStaff;
            SetOfTasks projectTasks;
            

            while(projectsResultSet.next())
            {
                boolean exists = false;
                projectID = projectsResultSet.getInt("projectId");
                for (Project aProject: listOfUserProjects){ 
                        if(aProject.getProjectID() == projectID)
                            exists = true;
                    }

                    if(!exists){
                        projectName = projectsResultSet.getString("ProjectName");
                        priority = projectsResultSet.getInt("priority");
                        int clientRepId = projectsResultSet.getInt("clientRep");
                        clientRep =  getClientRep(clientRepId);
                        project = new Project(projectID,projectName,priority,clientRep);
                        listOfUserProjects.add(project);
                        listUserProjectsList.setListData(listOfUserProjects);
                        ProjectListCellRenderer renderer = new ProjectListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                        listUserProjectsList.setCellRenderer(renderer);
                    }
            }
        }catch(SQLException err)
		{
                    System.out.println("ERROR: " + err);
			JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
			System.exit(1);
		}
    }
       
    private User getClientRep(int clientRepID){
       User clientRep=null;
       String clientRepFirstName;
       String clientRepSurname;
       try{
            ResultSet clientRepresultSet = null;
            Statement statement;
            statement = connection.createStatement();
            clientRepresultSet = statement.executeQuery("SELECT firstName, surname FROM User WHERE userID="+ clientRepID +";");
                
            while(clientRepresultSet.next())
            {
                clientRepFirstName = clientRepresultSet.getString("firstName");
                String clientrepSurname = clientRepresultSet.getString("surname");
                clientRep = new User(clientRepID,clientRepFirstName,clientrepSurname);
                return clientRep;
            } 
         }catch(SQLException err)
                 {
                     System.out.println("ERROR: " + err);
                         JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                         System.exit(1);
                 }
        return clientRep;      
   }
    
    private void fillInElementsLists(int projectID){
        listOfElementsToApprove.clear();
        listOfElementsApproved.clear();
        if (projectID >=1){
            try{
                ResultSet elementsOnProjectListResultSet = null;
                Statement statement;
                statement = connection.createStatement();
                elementsOnProjectListResultSet = statement.executeQuery("SELECT Element.elementID, Element.elementName, Element.approved, SetOFElements.ProjectID FROM Element INNER JOIN SetOFElements ON Element.[elementID] = SetOFElements.[elementID] WHERE ProjectID="+ projectID +";"); 
                int elementID;
                String elementName;
                boolean approved;

                while(elementsOnProjectListResultSet.next())
                {
                    elementID = elementsOnProjectListResultSet.getInt("elementID");
                    elementName = elementsOnProjectListResultSet.getString("elementName");
                    approved = elementsOnProjectListResultSet.getBoolean("approved");
                    Element element = new Element(elementID,elementName,approved);
                    if (approved == false){
                        listOfElementsToApprove.add(element);
                        listElementsToApproveList.setListData(listOfElementsToApprove);
                    }else{
                        listOfElementsApproved.add(element);
                        listElementsAprovedList.setListData(listOfElementsApproved);
                    }
                    ProjectElementsListCellRenderer renderer = new ProjectElementsListCellRenderer(); //custom cell renderer to display property rather than useless object.toString()
                    listElementsToApproveList.setCellRenderer(renderer);
                    listElementsAprovedList.setCellRenderer(renderer);
                }
            }catch(SQLException err)
                    {
                        System.out.println("ERROR: " + err);
                        JOptionPane.showMessageDialog(null,"* Cannot connect to database! *");
                        System.exit(1);
                    }
        }else{
            listOfElementsToApprove.clear();
            listElementsToApproveList.setListData(listOfElementsToApprove);
            listElementsToApproveList.repaint();
            
            listOfElementsApproved.clear();
            listElementsAprovedList.setListData(listOfElementsApproved);
            listElementsAprovedList.repaint();
        }
        if (listOfElementsToApprove.isEmpty())
            btnApprove.setEnabled(false);
        else
            btnApprove.setEnabled(true);
    } 
    
    private void approveElement(int elementID){
        Statement statement;
        boolean approved = true;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Element SET approved="+approved+" WHERE ElementID="+elementID+";");
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUserProjectsList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listElementsToApproveList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listElementsAprovedList = new javax.swing.JList();
        btnApprove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("List of your Projects:");

        listUserProjectsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUserProjectsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listUserProjectsList);

        jLabel2.setText("Elements to approve:");

        jLabel3.setText("Approved Elements:");

        jScrollPane2.setViewportView(listElementsToApproveList);

        jScrollPane3.setViewportView(listElementsAprovedList);

        btnApprove.setText("Approve Element");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnApprove, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApprove)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listUserProjectsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUserProjectsListValueChanged
        selectedProject = (Project) listUserProjectsList.getSelectedValue();
        if(selectedProject != null)
            fillInElementsLists(selectedProject.getProjectID());
    }//GEN-LAST:event_listUserProjectsListValueChanged

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        selectedElement = (Element) listElementsToApproveList.getSelectedValue();
        if(selectedElement != null && selectedProject != null ){
            approveElement(selectedElement.getElementID());
            fillInElementsLists(selectedProject.getProjectID());
        }
    }//GEN-LAST:event_btnApproveActionPerformed

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
            java.util.logging.Logger.getLogger(ClientRepUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientRepUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientRepUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientRepUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ClientRepUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listElementsAprovedList;
    private javax.swing.JList listElementsToApproveList;
    private javax.swing.JList listUserProjectsList;
    // End of variables declaration//GEN-END:variables
}
