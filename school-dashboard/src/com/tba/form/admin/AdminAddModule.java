/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tba.form.admin;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.tba.form.MainForm;
import loginpage.ConnectDatabase;
import com.tba.main.Main;
import java.sql.*;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

/**
 *
 * @author T_T
 */
public class AdminAddModule extends javax.swing.JFrame {

    AdminModules am = new AdminModules();

    java.sql.PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = ConnectDatabase.connectdb();

    public AdminAddModule() {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initComponents();
        txtSpec2.setVisible(false);
        getHours();
        hideHours();
    }
    
    //get the hours
    public void getHours() {
        int min = 0;
        int max = Integer.parseInt(txtCredit.getSelectedItem().toString());
        SpinnerModel value = new SpinnerNumberModel(0, min, max, 1);
        SpinnerModel valueTut = new SpinnerNumberModel(0, min, max, 1);
        SpinnerModel valueLab = new SpinnerNumberModel(0, min, max, 1);
        LecHour.setModel(value);
        TutHour.setModel(valueTut);
        LabHour.setModel(valueLab);
    }
    //hide hours for lecturer
    public void hideHours() {
        LabHour.setVisible(false);
        LecHour.setVisible(false);
        TutHour.setVisible(false);
        lecHoursLabel.setVisible(false);
        tutHoursLabel.setVisible(false);
        labHoursLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCredit = new javax.swing.JComboBox<>();
        codePlace = new javax.swing.JLabel();
        txtType = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMuet = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtSpec2 = new javax.swing.JComboBox<>();
        txtSpec1 = new javax.swing.JComboBox<>();
        exitBtn = new javax.swing.JLabel();
        lecHoursLabel = new javax.swing.JLabel();
        labHoursLabel = new javax.swing.JLabel();
        tutHoursLabel = new javax.swing.JLabel();
        LecHour = new javax.swing.JSpinner();
        TutHour = new javax.swing.JSpinner();
        LabHour = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel5.setText("Credit Hour (s)");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADD MODULE COURSE INFORMATION");

        txtCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        txtCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditActionPerformed(evt);
            }
        });

        codePlace.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        codePlace.setText("Course Code");

        txtType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LECTURE", "TUTORIAL", "LAB", "LECTURE, TUTORIAL, LAB", "LECTURE, TUTORIAL", "TUTORIAL, LAB" }));
        txtType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel8.setText("Select Activity");

        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        addButton.setText("Add New");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel9.setText("MUET Requirement");

        txtMuet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel10.setText("Specialization");

        txtSpec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpec2ActionPerformed(evt);
            }
        });

        txtSpec1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "COMPUTER SCIENCE", "INFORMATION TECHNOLOGY" }));
        txtSpec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpec1ActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("Readex Pro", 1, 12)); // NOI18N
        exitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitBtn.setText("X");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });

        lecHoursLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lecHoursLabel.setText("Lecture Hours");

        labHoursLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labHoursLabel.setText("Lab Hours");

        tutHoursLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        tutHoursLabel.setText("Tutorial Hours");

        LecHour.setModel(new javax.swing.SpinnerNumberModel());
        LecHour.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LecHourStateChanged(evt);
            }
        });

        TutHour.setModel(new javax.swing.SpinnerNumberModel());

        LabHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lecHoursLabel)
                                .addComponent(tutHoursLabel)
                                .addComponent(labHoursLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LabHour, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(TutHour))
                                .addComponent(LecHour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(codePlace, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(63, 63, 63)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCode)
                                .addComponent(txtCredit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMuet, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSpec2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSpec1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codePlace)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMuet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtSpec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSpec2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecHoursLabel)
                    .addComponent(LecHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tutHoursLabel)
                    .addComponent(TutHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labHoursLabel)
                    .addComponent(LabHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String coursecode = txtCode.getText().toUpperCase();
        String type = txtType.getSelectedItem().toString();
        String cd = txtCredit.getSelectedItem().toString();
        String mt = txtMuet.getSelectedItem().toString();
        int credit = Integer.parseInt(cd);
        int muet = Integer.parseInt(mt);
        int csit = -1;
        int stype = -1;
        int lechour = (Integer) LecHour.getValue();
        int tutohour = (Integer) TutHour.getValue();
        int labhour = (Integer) LabHour.getValue();

        String spc1 = txtSpec1.getSelectedItem().toString();
        String spc2 = "";
        //txtSpec2 would be null if module is for ALL
        if (Objects.isNull(txtSpec2.getSelectedItem())) {
            stype = 0;
        } else {
            spc2 = txtSpec2.getSelectedItem().toString();
        }

        if (spc1.equals("COMPUTER SCIENCE")) {
            csit = 1;
        } else if (spc1.equals("ALL")) {
            csit = 0;

            //if module is for both CS & IT , then it is for all specializations -> stype = 0
        } else {
            csit = 2;
        }

        if (spc2.equals("Artificial Intelligence")) {
            stype = 2;
        } else if (spc2.equals("Information Systems")) {
            stype = 3;
        } else if (spc2.equals("Computer System and Network")) {
            stype = 1;
        } else if (spc2.equals("Multimedia")) {
            stype = 6;
        } else if (spc2.equals("Data Science")) {
            stype = 5;
        } else if (spc2.equals("Software Engineering")) {
            stype = 4;
        } else {
            System.out.println("no stype");
        }

        //making sure that when csit = "General" then specialization SHOULD BE "General"
        //this is needed in case for instance admin selects CS degree and so stype is automatically defaulted to AI, 
        //but admin reverts back to ALL for degree. in this case csit = ALL, stype = AI which doesn't make sense 
        if (csit == 0) {
            stype = 0;
        }

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/userlogin", "app", "app");
            Statement st;
            st = con.createStatement();

            String strQuery3 = "SELECT COUNT(*) FROM app.valid_modules where module='" + coursecode + "'";
            ResultSet rs3 = st.executeQuery(strQuery3);
            rs3.next();
            String Countrow3 = rs3.getString(1);
            System.out.println(Countrow3);
            if (credit == (labhour + lechour + tutohour)) {
                if (Countrow3.equals("0")) {
                    int i = st.executeUpdate("INSERT INTO APP.VALID_MODULES(MODULE,ACTIVITY,CREDIT,MUET,CSIT,STUDENTTYPE,LECTUREHOUR,TUTORIALHOUR,LABHOUR) VALUES('" + coursecode + "','" + type + "'," + credit + "," + muet + "," + csit + "," + stype + "," + lechour + "," + tutohour + "," + labhour + ")");
                    JOptionPane.showMessageDialog(this, "Course Added");
                } else {
                    JOptionPane.showMessageDialog(this, "Added course already exists!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Activity Hours");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to Add Course" + credit + muet + csit + stype);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void txtSpec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpec2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpec2ActionPerformed

    private void txtSpec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpec1ActionPerformed
        
        if (txtSpec1.getSelectedItem().toString().equals("COMPUTER SCIENCE")) {
            txtSpec2.setVisible(true);
            txtSpec2.addItem("Artificial Intelligence");
            txtSpec2.addItem("Information Systems");
            txtSpec2.addItem("Software Engineering");
            txtSpec2.addItem("Computer System and Network");
            txtSpec2.addItem("Data Science");
            txtSpec2.removeItem("Multimedia");
        } else if (txtSpec1.getSelectedItem().toString().equals("INFORMATION TECHNOLOGY")) {
            txtSpec2.setVisible(true);
            txtSpec2.removeAllItems();
            txtSpec2.addItem("Multimedia");
        } else {
            txtSpec2.setVisible(false);
        }
    }//GEN-LAST:event_txtSpec1ActionPerformed

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        // TODO add your handling code here:
//        
        this.dispose();
    }//GEN-LAST:event_exitBtnMouseClicked

    private void LecHourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LecHourStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_LecHourStateChanged

    private void txtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeActionPerformed
        // TODO add your handling code here:
        int index = txtType.getSelectedIndex();
        if(index == 0){
            hideHours();
            LecHour.setVisible(true);
            lecHoursLabel.setVisible(true);
        } else if(index == 1){
            hideHours();
            TutHour.setVisible(true);
            tutHoursLabel.setVisible(true);
        } else if(index == 2){
            hideHours();
            LabHour.setVisible(true);
            labHoursLabel.setVisible(true);
        } else if(index == 3){
            hideHours();
            LecHour.setVisible(true);
            lecHoursLabel.setVisible(true);
            TutHour.setVisible(true);
            tutHoursLabel.setVisible(true);
            LabHour.setVisible(true);
            labHoursLabel.setVisible(true);
        } else if(index == 4){
            hideHours();
            LecHour.setVisible(true);
            lecHoursLabel.setVisible(true);
            TutHour.setVisible(true);
            tutHoursLabel.setVisible(true);
        } else if(index == 5){
            hideHours();
            LabHour.setVisible(true);
            labHoursLabel.setVisible(true);
            TutHour.setVisible(true);
            tutHoursLabel.setVisible(true);
        }
    }//GEN-LAST:event_txtTypeActionPerformed

    private void txtCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditActionPerformed
        // TODO add your handling code here:
        getHours();
    }//GEN-LAST:event_txtCreditActionPerformed

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
            java.util.logging.Logger.getLogger(AdminAddModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminAddModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminAddModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminAddModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminAddModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner LabHour;
    private javax.swing.JSpinner LecHour;
    private javax.swing.JSpinner TutHour;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel codePlace;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labHoursLabel;
    private javax.swing.JLabel lecHoursLabel;
    private javax.swing.JLabel tutHoursLabel;
    private javax.swing.JTextField txtCode;
    private javax.swing.JComboBox<String> txtCredit;
    private javax.swing.JComboBox<String> txtMuet;
    private javax.swing.JComboBox<String> txtSpec1;
    private javax.swing.JComboBox<String> txtSpec2;
    private javax.swing.JComboBox<String> txtType;
    // End of variables declaration//GEN-END:variables
}
