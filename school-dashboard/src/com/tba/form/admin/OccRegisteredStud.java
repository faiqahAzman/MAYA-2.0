/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tba.form.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import loginpage.ConnectDatabase;

/**
 *
 * @author T_T
 */
public class OccRegisteredStud extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AdminModules am;

    /**
     * Creates new form OccRegisteredStud
     */
    public OccRegisteredStud() {
        
        con = ConnectDatabase.connectdb();
        initComponents();
        try {
            retrieveData();
        } catch (Exception e) {
        }
        
    }
    
    
    public void setLabels(String module, int occ, String lect,int actual){
    // havent set actualStudLabel -> count registered stud in an occ in database
        moduleLabel.setText("Module Code: "+module);
        occLabel.setText("Occurence: "+String.valueOf(occ));
        lecLabel.setText("Tutor: "+lect);
        actualStudLabel.setText("Actual Students: "+actual);
    }
    
    private void retrieveData() {
        String q1 = "SELECT * FROM REGISTEREDMODULES where module='" + am.getModuleCode() + "' ORDER BY OCCURENCE"; 
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULE = rs.getString("MODULE");
                String USER = rs.getString("USERNAME");
                String OCC = rs.getString("OCC");
                String AC = rs.getString("ACTIVITYTYPE");
                
                String tbData[] = {USER,MODULE, OCC,AC};
                DefaultTableModel tblModel = (DefaultTableModel) RegisteredStudTable.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        RegisteredStudTable = new javax.swing.JTable();
        moduleLabel = new javax.swing.JLabel();
        occLabel = new javax.swing.JLabel();
        lecLabel = new javax.swing.JLabel();
        actualStudLabel = new javax.swing.JLabel();
        exitFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        RegisteredStudTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Name", "CSIT", "STYPE", "MUET"
            }
        ));
        jScrollPane1.setViewportView(RegisteredStudTable);

        moduleLabel.setFont(new java.awt.Font("Readex Pro", 1, 14)); // NOI18N
        moduleLabel.setText("module placeholder");

        occLabel.setFont(new java.awt.Font("Readex Pro", 1, 14)); // NOI18N
        occLabel.setText("occ placeholder");

        lecLabel.setFont(new java.awt.Font("Readex Pro", 1, 14)); // NOI18N
        lecLabel.setText("lect placeholder");

        actualStudLabel.setFont(new java.awt.Font("Readex Pro", 1, 14)); // NOI18N
        actualStudLabel.setText("actual stud placeholder");

        exitFrame.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitFrame.setText("X");
        exitFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitFrameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actualStudLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lecLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitFrame)
                .addGap(5, 5, 5)
                .addComponent(moduleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(occLabel)
                .addGap(11, 11, 11)
                .addComponent(lecLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actualStudLabel)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitFrameMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_exitFrameMouseClicked

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
            java.util.logging.Logger.getLogger(OccRegisteredStud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OccRegisteredStud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OccRegisteredStud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OccRegisteredStud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OccRegisteredStud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RegisteredStudTable;
    private javax.swing.JLabel actualStudLabel;
    private javax.swing.JLabel exitFrame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lecLabel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JLabel occLabel;
    // End of variables declaration//GEN-END:variables
}