/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tba.form.admin;

import com.tba.model.ModelStudentType;
import java.awt.HeadlessException;
import loginpage.ConnectDatabase;
import java.sql.*;
import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author T_T
 */
public class AdminOcc extends javax.swing.JFrame {

    AdminModules am = new AdminModules();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = ConnectDatabase.connectdb();
    private ModelStudentType type = new ModelStudentType();
    static int studentNumber = 0;
    static String activity2 = "", module2 = "", occurence2 = "";

    /**
     * Creates new form AdminAddOcc
     */
    public AdminOcc() {
        initComponents();
        setCode();
        setActivity();
        setLec();
        
    }

    public static void setStudentNumber(int studentNumber) {
        AdminOcc.studentNumber = studentNumber;
    }
    
       public String convert24hours(String time) {
        if (time.equals("01:00")) {
            time = "13:00";
        } else if (time.equals("02:00")) {
            time = "14:00";
        } else if (time.equals("03:00")) {
            time = "15:00";
        } else if (time.equals("04:00")) {
            time = "16:00";
        } else if (time.equals("05:00")) {
            time = "17:00";
        } else if (time.equals("06:00")) {
            time = "18:00";
        } else if (time.equals("07:00")) {
            time = "19:00";
        }
        return time;
    }
    
    
    //Check for time conflicts
    public boolean checkTime(String day, String time1, String time2, String username, int option, int occ) {
        String q1 = "";
        if (option == 0) {
            q1 = "SELECT * FROM APP.REGISTEREDMODULES WHERE USERNAME='" + username + "'";
        } else if (option == 1) {
            q1 = "SELECT * FROM APP.TIMETABLE_MODULES WHERE MODULES='" + am.getModuleCode() + "' AND OCCURENCE=" + occ + "";
        }
        System.out.println(q1);

        time1 = convert24hours(time1.substring(0, 5));
        time2 = convert24hours(time2.substring(0, 5));

        LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareEnd = LocalTime.parse(time2.substring(0, 5));

        System.out.println(compareStart + "\n" + compareEnd);

        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {

                String DAY2 = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART").substring(0, 5);
                String TIMEEND = rs.getString("TIMEEND").substring(0, 5);
                activity2 = rs.getString("ACTIVITYTYPE");
                if (option == 0) {
                    module2 = rs.getString("MODULE");
                    occurence2 = rs.getString("OCC");
                } else if (option == 1) {
                    module2 = rs.getString("MODULES");
                    occurence2 = rs.getString("OCCURENCE");
                }

                TIMESTART = convert24hours(TIMESTART);
                TIMEEND = convert24hours(TIMEEND);

                LocalTime targetStart = LocalTime.parse(TIMESTART);
                LocalTime targetEnd = LocalTime.parse(TIMEEND);

                System.out.println(targetStart + "\n" + targetEnd);
                System.out.println(day + "\n" + DAY2);

                if (day == null ? DAY2 == null : day.equals(DAY2)) {

                    boolean NoClashClassAfterEnd = (targetStart.isAfter(compareEnd) || targetStart.equals(compareEnd));
                    boolean NoClashClassBeforeStart = (targetEnd.isBefore(compareStart) || targetEnd.equals(compareStart));

                    if (NoClashClassAfterEnd || NoClashClassBeforeStart) {

                        continue;
                    } else {

                        return true;
                    }
                }
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public void setCode() {
        codePlace.setText(am.getModuleCode());
        if (type.checkType() == 0) {
            txtOcc.setEnabled(false);
            txtAct.setEnabled(false);
            txtDay.setEnabled(false);
            txtTime1.setEnabled(false);
            ampm1.setEnabled(false);
            txtLec.setEnabled(false);
            //addingOccurenceLabel.setText("INCREASE CAPACITY OF OCCURENCE");
        }
    }

    public void setLec() {
        try {
            String slc = "SELECT * FROM LOGINTABLE WHERE STDNT_TYPE=" + 0 + "";
            ps = con.prepareStatement(slc);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString("FULLNAME");
                txtLec.addItem(fullname);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public int setActual() {
        String getActual = "SELECT ACTUAL FROM TIMETABLE_MODULES WHERE MODULES='" + am.getModuleCode() + "' AND OCCURENCE=" + am.getEOcc() + " AND ACTIVITYTYPE='" + am.getEType() + "'";
        int actual = 0;
        try {
            ps = con.prepareStatement(getActual);
            rs = ps.executeQuery();
            rs.next();
            actual = rs.getInt("ACTUAL");
            actualStudentsPlaceholder.setText("" + actual);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return actual;
    }

    public void setActivity() {

        if (am.getAct().equals("LECTURE, TUTORIAL")) {
            txtAct.removeItem("LAB");
            txtAct.addItem("LECTURE");
            txtAct.addItem("TUTORIAL");
        } else if (am.getAct().equals("TUTORIAL")) {
            txtAct.removeItem("LECTURE");
            txtAct.removeItem("LAB");
            txtAct.addItem("TUTORIAL");
        } else if (am.getAct().equals("LECTURE")) {
            txtAct.removeItem("TUTORIAL");
            txtAct.removeItem("LAB");
            txtAct.addItem("LECTURE");

        } else if (am.getAct().equals("LECTURE, LAB")) {
            txtAct.addItem("LECTURE");
            txtAct.addItem("LAB");
            txtAct.removeItem("TUTORIAL");

        } else {
            txtAct.addItem("LECTURE");
            txtAct.addItem("TUTORIAL");
            txtAct.addItem("LAB");

        }
    }

    public void setTime(int tm1, int hours, int ap) {
        if (tm1 < 12 - hours) {
            String time2 = txtTime1.getItemAt(tm1 + hours).toString();
            txtTime2.setText(time2);
            String sfx2 = ampm1.getSelectedItem().toString();
            ampm2.setText(sfx2);
        } else {
            String time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
            txtTime2.setText(time2);
            String sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
            ampm2.setText(sfx2);
        }
    }

    public void setHour() {
        String time1 = txtTime1.getSelectedItem().toString();
        int tm1 = txtTime1.getSelectedIndex();
        int ap = ampm1.getSelectedIndex();
        int hours = -1;
        if (txtAct.getSelectedItem().toString().equals("LECTURE")) {
            //Getting Lecture Hour:
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LECTUREHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork1");
            }
            //Setting Time Start & Time End:
            setTime(tm1, hours, ap);

        } else if (txtAct.getSelectedItem().toString().equals("TUTORIAL")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("TUTORIALHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork2");
            }
            setTime(tm1, hours, ap);
        } else if (txtAct.getSelectedItem().toString().equals("LAB")) {
            String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
            try {
                ps = con.prepareStatement(link);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hours = rs.getInt("LABHOUR");
                }
            } catch (SQLException ex) {
                System.out.println("didntwork3");
            }
            setTime(tm1, hours, ap);
        }
    }

    public void editMod() {
        try {

            int oc = am.getEOcc();
            //if admin is ADDING OCC
//            if (am.edit == 0){
//                
//            txtOcc.setSelectedIndex((oc - 1));
//            txtAct.setSelectedItem(am.getEType());
//            
//            } else { //if admin is EDITING OCC

            title.setText("Edit Occurence");

            //make Occ count fixed if admin is JUST EDITING and NOT ADDING OCC
            int lastOcc = txtOcc.getItemCount();
            for (int i = 1; i <= lastOcc; i++) {
                if (i == oc) {
                    continue;
                }
//                    System.out.print(""+i+" ");       //outputs removed occurences
                txtOcc.removeItem("" + i + "");
            }
            //if admin is just EDITING, it will only show the selected Activity that was selected from occTable
            if (am.getEType().equals("LECTURE")) {
                txtAct.removeItem("LAB");
                txtAct.removeItem("TUTORIAL");
            } else if (am.getEType().equals("TUTORIAL")) {
                txtAct.removeItem("LAB");
                txtAct.removeItem("LECTURE");
            } else if (am.getEType().equals("LAB")) {
                txtAct.removeItem("LECTURE");
                txtAct.removeItem("TUTORIAL");
            } else {
                JOptionPane.showMessageDialog(null, "txtAct error");
            }

//            }
//            
            txtDay.setSelectedItem(am.getEDay());
            txtTime1.setSelectedItem(am.getETime());
//            txtLec.setSelectedItem(am.getELec());
            txtSCap.setValue(am.getECap());
            actualStudentsPlaceholder.setText("" + setActual());

        } catch (Exception e) {
            System.out.println("ops");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codePlace = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        txtAct = new javax.swing.JComboBox<>();
        txtDay = new javax.swing.JComboBox<>();
        txtLec = new javax.swing.JComboBox<>();
        txtOcc = new javax.swing.JComboBox<>();
        txtTime1 = new javax.swing.JComboBox<>();
        ampm1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtTime2 = new javax.swing.JLabel();
        ampm2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSCap = new javax.swing.JSpinner();
        exitFrame = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        actualStudentsPlaceholder = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        title.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ADD OCCURENCE ");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel3.setText("Course Code");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel4.setText("Occurrence");

        codePlace.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        codePlace.setText("Code placeholder");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel6.setText("Class Activity");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel7.setText("Day");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel8.setText("Assign Lecturer");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel9.setText("Timeslot");

        submitButton.setText("SUBMIT");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        txtAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActActionPerformed(evt);
            }
        });

        txtDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" }));

        txtLec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLecActionPerformed(evt);
            }
        });

        txtOcc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        txtOcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOccActionPerformed(evt);
            }
        });

        txtTime1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00" }));
        txtTime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTime1ActionPerformed(evt);
            }
        });

        ampm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        ampm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ampm1ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        txtTime2.setText("END TIME");

        ampm2.setText("AM/PM");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel10.setText("Target Students");

        txtSCap.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 5));

        exitFrame.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitFrame.setText("X");
        exitFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitFrameMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel2.setText("Actual Students");

        actualStudentsPlaceholder.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        actualStudentsPlaceholder.setText("Placeholder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(77, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(codePlace, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                        .addGap(43, 43, 43))
                                    .addComponent(txtAct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtOcc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ampm1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ampm2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(actualStudentsPlaceholder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(codePlace))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtOcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(actualStudentsPlaceholder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
          int edit = am.getEdit();
        String coursecode = am.getModuleCode();
        String oc = txtOcc.getSelectedItem().toString();
        String type = txtAct.getSelectedItem().toString();
        String day = txtDay.getSelectedItem().toString();
        String time1 = txtTime1.getSelectedItem().toString();
        String lec = txtLec.getSelectedItem().toString();
        String time2 = txtTime2.getText();
        String sfx2 = ampm2.getText();
        String sfx1 = ampm1.getSelectedItem().toString();
        String t1 = time1 + " " + sfx1;
        String t2 = time2 + " " + sfx2;
        String username = "";
        int occ = Integer.parseInt(oc);
        int scap = (Integer) txtSCap.getValue();
        boolean scap_limit = true;

        if (studentNumber < scap) {
            scap_limit = false;
        } else {
            JOptionPane.showMessageDialog(null, "STUDENT LIMIT IS LOWER THAN REGISTERED STUDENTS!");
            //reminder.setVisible(true);
        }

        if (scap_limit == false) {
            
            //Getting the username of the lecturer
            try {
                String selectUsername = "SELECT MATRIX_NUMBER FROM LOGINTABLE WHERE FULLNAME='" + lec + "'";
                ps = con.prepareStatement(selectUsername);
                rs = ps.executeQuery();
                rs.next();
                username = rs.getString("MATRIX_NUMBER");
            } catch (SQLException e) {
                System.out.println(e);
            }
            
            //Inserting edited/new modules
            try {
                
                String InsertModule = "INSERT INTO APP.timetable_modules(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP,ACTUAL)VALUES('" + am.getModuleCode() + "'," + am.getEOcc() + ",'" + am.getEDay() + "','" + am.getETime1() + "','" + am.getETime2() + "','" + am.getEType() + "','" + am.getELec() + "'," + am.getECap() + ","+ 0 +")";
                String InsertLecturer = "INSERT INTO REGISTEREDMODULES(USERNAME,MODULE,OCC,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND,TYPE)VALUES('" + username + "','" + coursecode + "'," + occ + ",'" + type + "','" + day + "','" + t1 + "','" + t2 + "'," + 0 + ")";
                String checkDuplicate = "SELECT COUNT(*) FROM app.timetable_modules where occurence=" + occ + " and activitytype='" + type + "' and modules='" + am.getModuleCode() + "'";
                String deleteModule = "DELETE FROM app.timetable_modules WHERE modules ='" + am.getModuleCode() + "' and occurence =" + am.getEOcc() + " and activitytype ='" + am.getEType() + "'";
                String deleteLecturer = "DELETE FROM app.REGISTEREDMODULES WHERE module ='" + am.getModuleCode() + "' and occ =" + am.getEOcc() + " and activitytype ='" + am.getEType() + "' and type=0";
                
                
                Statement st;
                st = con.createStatement();
                
                //Deleting the occurrence and lecturer from the module which is to be edited
                if (edit == 1) {
                    try {
                        PreparedStatement set1 = con.prepareStatement(deleteModule);
                        set1.executeUpdate();
                        PreparedStatement set2 = con.prepareStatement(deleteLecturer);
                        set2.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
                
                //Checking for time conflicts 
                boolean lecturerClash = checkTime(day, t1, t2, username, 0, occ);
                boolean occurrenceClash = checkTime(day, t1, t2, username, 1, occ);

                ResultSet rs1 = st.executeQuery(checkDuplicate);
                rs1.next();
                String duplicateRows = rs1.getString(1);
                
                //If time clashes with lecturer's timetable
                if (lecturerClash) {
                    JOptionPane.showMessageDialog(null, "CLASHES WITH " + lec + "'s CLASS: " + activity2 + " FOR " + module2 + " OCCURRENCE " + occurence2);
                    if (edit == 1) {
                        st.execute(InsertModule);
                        st.execute(InsertLecturer);
                    }
                }
                //If time clashes with another activity type of the same occurrence
                else if (occurrenceClash) {
                    JOptionPane.showMessageDialog(null, "CLASHES WITH THE " + activity2 + " FOR " + module2 + " OCCURRENCE " + occurence2);
                    if (edit == 1) {
                        st.execute(InsertModule);
                        st.execute(InsertLecturer);
                    }
                }
                //Checking for duplicate modules
                else if (duplicateRows.equals("0")) {
                    String registerLecturer = "INSERT INTO REGISTEREDMODULES(USERNAME,MODULE,OCC,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND,TYPE)VALUES('" + username + "','" + coursecode + "'," + occ + ",'" + type + "','" + day + "','" + t1 + "','" + t2 + "'," + 0 + ")";
                    String registerModule = "INSERT INTO APP.timetable_modules(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP,ACTUAL)VALUES('" + coursecode + "'," + occ + ",'" + day + "','" + t1 + "','" + t2 + "','" + type + "','" + lec + "'," + scap + ","+ 0+")";
                    
                    st.execute(registerLecturer);
                    st.execute(registerModule);

                    if (edit == 1) {
                        JOptionPane.showMessageDialog(this, "Module Updated");
                    } else {
                        JOptionPane.showMessageDialog(this, "Module Added");
                    }
                    
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Module already exists!");
                    //Insert the original module and lecturer if edit fails.
                    if (edit == 1) {
                        st.execute(InsertModule);
                        st.execute(InsertLecturer);
                    }
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to Add Course");
            }
        
        /*int edit = am.getEdit();
        String coursecode = am.getModuleCode();
        String oc = txtOcc.getSelectedItem().toString();
        String type = txtAct.getSelectedItem().toString();
        String day = txtDay.getSelectedItem().toString();
        String time1 = txtTime1.getSelectedItem().toString();
        String time2 = "";
        String sfx2 = "";
        String sfx1 = ampm1.getSelectedItem().toString();
        int tm1 = txtTime1.getSelectedIndex();
        int ap = ampm1.getSelectedIndex();
        int cred = Integer.parseInt(am.getCredit());
        int scap = (Integer) txtSCap.getValue();
        int hours = -1;
        boolean scap_limit = true;

        if (studentNumber < scap) {
            scap_limit = false;
        } else {
            JOptionPane.showMessageDialog(null, "STUDENT LIMIT IS LOWER THAN REGISTERED STUDENTS!");
        }

        if (scap_limit == false) {

            if (txtAct.getSelectedItem().toString().equals("LECTURE")) {
                //Getting Lecture Hour:
                String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
                try {
                    ps = con.prepareStatement(link);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        hours = rs.getInt("LECTUREHOUR");
                    }
                    if (tm1 < 12 - hours) {
                        time2 = txtTime1.getItemAt(tm1 + hours).toString();
                        sfx2 = ampm1.getSelectedItem().toString();
                    } else {
                        time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                        sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                    }
                } catch (SQLException ex) {
                    System.out.println("didntwork1");
                }
                //Setting Time Start & Time End:
                setTime(tm1, hours, ap);

            } else if (txtAct.getSelectedItem().toString().equals("TUTORIAL")) {
                String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
                try {
                    ps = con.prepareStatement(link);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        hours = rs.getInt("TUTORIALHOUR");
                    }
                    if (tm1 < 12 - hours) {
                        time2 = txtTime1.getItemAt(tm1 + hours).toString();
                        sfx2 = ampm1.getSelectedItem().toString();
                    } else {
                        time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                        sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                    }
                } catch (SQLException ex) {
                    System.out.println("didntwork2");
                }
                setTime(tm1, hours, ap);
            } else if (txtAct.getSelectedItem().toString().equals("LAB")) {
                String link = "SELECT * FROM APP.VALID_MODULES where MODULE = '" + am.getModuleCode() + "'";
                try {
                    ps = con.prepareStatement(link);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        hours = rs.getInt("LABHOUR");
                    }
                    if (tm1 < 12 - hours) {
                        time2 = txtTime1.getItemAt(tm1 + hours).toString();
                        sfx2 = ampm1.getSelectedItem().toString();
                    } else {
                        time2 = txtTime1.getItemAt(tm1 - (12 - hours)).toString();
                        sfx2 = ampm1.getItemAt(Math.abs(ap - 1)).toString();
                    }
                } catch (SQLException ex) {
                    System.out.println("didntwork3");
                }
                setTime(tm1, hours, ap);
            }

            String t1 = time1 + " " + sfx1;
            String t2 = time2 + " " + sfx2;
            //boolean lim = false;

            int occ = Integer.parseInt(oc);
            String lec = txtLec.getSelectedItem().toString();

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/userlogin", "app", "app");
                Statement st;
                st = con.createStatement();
                if (edit == 1) {
                    try {
                        PreparedStatement set = con.prepareStatement("DELETE FROM app.ALL_MODULES WHERE modules ='" + am.getModuleCode() + "' and occurence =" + am.getEOcc() + " and activitytype ='" + am.getEType() + "'");
                        set.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("480 am");
                    }
                }
                String strQuery3 = "SELECT COUNT(*) FROM app.ALL_modules where occurence=" + occ + " and activitytype='" + type + "' and modules='" + am.getModuleCode() + "'";
                ResultSet rs3 = st.executeQuery(strQuery3);
                rs3.next();
                String Countrow3 = rs3.getString(1);
                System.out.println(Countrow3);
                if (Countrow3.equals("0")) {
                    st.execute("INSERT INTO APP.VALID_MODULES(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP)VALUES('" + coursecode + "'," + occ + ",'" + day + "','" + t1 + "','" + t2 + "','" + type + "','" + lec + "'," + scap + ")");
                    
                    if (edit == 1) {
                        JOptionPane.showMessageDialog(this, "Occurence Updated");
                    } else {
                        JOptionPane.showMessageDialog(this, "Occurence Added");
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Occurence already exists!");
                    if (edit == 1) {
                        st.execute("INSERT INTO APP.VALID_MODULES(MODULES,OCCURENCE,DAY,TIMESTART,TIMEEND,ACTIVITYTYPE,LECTURER,STUDENTCAP)VALUES('" + am.getModuleCode() + "'," + am.getEOcc() + ",'" + am.getEDay() + "','" + am.getETime1() + "','" + am.getETime2() + "','" + am.getEType() + "','" + am.getELec() + "'," + am.getECap() + ")");

                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to Add Course");
            }
        }*/

        //update occTable?
//        new modulesDetail().setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void txtActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_txtActActionPerformed

    private void txtLecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLecActionPerformed

    private void txtOccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOccActionPerformed

    private void txtTime1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTime1ActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_txtTime1ActionPerformed

    private void ampm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ampm1ActionPerformed
        // TODO add your handling code here:
        setHour();
    }//GEN-LAST:event_ampm1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminOcc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminOcc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminOcc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminOcc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminOcc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actualStudentsPlaceholder;
    private javax.swing.JComboBox<String> ampm1;
    private javax.swing.JLabel ampm2;
    private javax.swing.JLabel codePlace;
    private javax.swing.JLabel exitFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel title;
    private javax.swing.JComboBox<String> txtAct;
    private javax.swing.JComboBox<String> txtDay;
    private javax.swing.JComboBox<String> txtLec;
    private javax.swing.JComboBox<String> txtOcc;
    private javax.swing.JSpinner txtSCap;
    private javax.swing.JComboBox<String> txtTime1;
    private javax.swing.JLabel txtTime2;
    // End of variables declaration//GEN-END:variables
}
