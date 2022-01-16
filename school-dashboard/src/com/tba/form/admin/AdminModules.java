/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.tba.form.admin;

import com.formdev.flatlaf.FlatIntelliJLaf;
import loginpage.ConnectDatabase;
import com.tba.main.Main;
import static com.tba.main.Main.main;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author T_T
 */
public class AdminModules extends javax.swing.JPanel {

    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    static String moduleCode = "";
    static String credit = "";
    static String act = "";
    static int hours = -1;
    //e for edit
    static int occ = -1, ecap = -1, edit = 0, actual = 0;
    static String type=""; static String eday =""; 
    static String etime =""; static String elec =""; 
    static String etime1 = ""; static String etime2="";

    public AdminModules() {

        //Changes Look & Feel so that it doesn't look that ugly
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initComponents();
        displayCourses();
        
//        occPanel.setVisible(false);

        setVisible(true);
    }

    public void displayCourses() {
        String q1 = "SELECT * FROM VALID_MODULES";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String CREDIT = rs.getString("CREDIT");
                String ACTIVITY = rs.getString("ACTIVITY");
                String MUET = rs.getString("MUET");
                int csitNum = rs.getInt("CSIT");
                int stypeNum = rs.getInt("STUDENTTYPE");

                String CSIT = "";
                if (csitNum == 0 ){
                    CSIT = "General";    
                }else if ( csitNum == 1){
                    CSIT = "CS";    
                }else if ( csitNum == 2){
                    CSIT = "IT";    
                }else{
                    CSIT = "error";    
                }
                
                String STYPE = "";
                if (stypeNum == 0 ){
                    STYPE = "General";    
                }else if ( stypeNum == 1){
                    STYPE = "CSN";    
                }else if ( stypeNum == 2){
                    STYPE = "AI";    
                }else if ( stypeNum == 3){
                    STYPE = "IS";    
                }else if ( stypeNum == 4){
                    STYPE = "SE";    
                }else if ( stypeNum == 5){
                    STYPE = "DS";    
                }else if ( stypeNum == 6){
                    STYPE = "MM";    
                }else{
                    STYPE = "error";    
                }
                
                //switch case was giving errors so used if-else instead
//                String CSIT = switch (csitNum) {
//                    case 0 ->
//                       "General";
//                    case 1 ->
//                        "CS";
//                    case 2 ->
//                        "IT";
//                    default ->
//                        "null";
//                };
//                String STYPE = switch (stypeNum) {
//                    case 0 ->
//                        "General";
//                    case 1 ->
//                        "CSN";
//                    case 2 ->
//                        "AI";
//                    case 3 ->
//                        "IS";
//                    case 4 ->
//                        "SE";
//                    case 5 ->
//                        "DS";
//                    case 6 ->
//                        "MM";
//                    default ->
//                        "null";
//                };


                
                String tbData[] = {MODULES, CREDIT, ACTIVITY, MUET, CSIT, STYPE};
                DefaultTableModel tblModel = (DefaultTableModel) allModulesTable.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCredit() {
        return credit;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getAct() {
        return act;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        occPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        occTable = new javax.swing.JTable();
        courseCredit = new javax.swing.JLabel();
        courseCode = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        deleteOccBtn = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        VIEWBUTTON = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        allModulesTable = new javax.swing.JTable();
        addNewBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1055, 748));

        occPanel.setBackground(new java.awt.Color(255, 255, 255));
        occPanel.setOpaque(false);

        occTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULES", "OCC", "DAY", "TIMESTART", "TIMEEND", "ACTIVITY", "LECTURER", "TARGET", "ACTUAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        occTable.setColumnSelectionAllowed(true);
        occTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                occTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(occTable);
        occTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        courseCredit.setFont(new java.awt.Font("Readex Pro", 1, 12)); // NOI18N
        courseCredit.setText("CREDIT    :");

        courseCode.setFont(new java.awt.Font("Readex Pro", 1, 12)); // NOI18N
        courseCode.setText("COURSE  : ");

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteOccBtn.setText("Delete");
        deleteOccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOccBtnActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout occPanelLayout = new javax.swing.GroupLayout(occPanel);
        occPanel.setLayout(occPanelLayout);
        occPanelLayout.setHorizontalGroup(
            occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, occPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, occPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(occPanelLayout.createSequentialGroup()
                                .addComponent(courseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(occPanelLayout.createSequentialGroup()
                                .addComponent(courseCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteOccBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editButton)
                                .addGap(18, 18, 18)
                                .addComponent(addButton))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        occPanelLayout.setVerticalGroup(
            occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(occPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(courseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(occPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(deleteOccBtn)
                        .addComponent(editButton))
                    .addComponent(courseCredit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        VIEWBUTTON.setText("VIEW");
        VIEWBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEWBUTTONActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Readex Pro", 0, 14)); // NOI18N
        jLabel2.setText("SEARCH : ");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        allModulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE CODE", "CREDIT", "ACTIVITY", "MUET", "CSIT", "TYPE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allModulesTable.setColumnSelectionAllowed(true);
        allModulesTable.getTableHeader().setReorderingAllowed(false);
        allModulesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allModulesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(allModulesTable);
        allModulesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        addNewBtn.setText("ADD NEW MODULE");
        addNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VIEWBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addComponent(addNewBtn))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VIEWBUTTON)
                    .addComponent(deleteButton)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(occPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void allModulesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allModulesTableMouseClicked
        // TODO add your handling code here:

        /*
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String code = model.getValueAt(index, 0).toString();
        setModuleCode(code);
        String credit = model.getValueAt(index, 1).toString();
        setCredit(credit);
        new modulesDetail().setVisible(true);
        this.setVisible(false);
         */
    }//GEN-LAST:event_allModulesTableMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) allModulesTable.getModel();
        String search = txtSearch.getText().toUpperCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        allModulesTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void VIEWBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIEWBUTTONActionPerformed
        // TODO add your handling code here:
        try {

            int index = allModulesTable.getSelectedRow();
            TableModel model = allModulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            setModuleCode(code);
            String credit = model.getValueAt(index, 1).toString();
            setCredit(credit);
            String act = model.getValueAt(index, 2).toString();
            setAct(act);

            retrieveOcc();
            courseCode.setText("Classes for: " + getModuleCode());
            courseCredit.setText("Credit hours: " + getCredit());

//            occPanel.setVisible(true);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
        }
    }//GEN-LAST:event_VIEWBUTTONActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        
        
        
        DefaultTableModel dtm = (DefaultTableModel) allModulesTable.getModel();
        try {
            /*int SelectedRowIndex = jTable1.getSelectedRow();
            model.removeRow(SelectedRowIndex);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.valid_modules WHERE modules = " + name + "");
            st.executeUpdate();*/
            
            int index = allModulesTable.getSelectedRow();
            TableModel model = allModulesTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            
            int confirmDelete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete module code "+code+"?");
            if (confirmDelete == 0){
                setModuleCode(code);
                //System.out.println(code);
                PreparedStatement st = con.prepareStatement("DELETE FROM app.valid_modules WHERE module ='" + code + "'");
                st.executeUpdate();
                dtm.removeRow(index);
            } 
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void occTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_occTableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_occTableMouseClicked

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        AdminOcc as = new AdminOcc();
        if (occTable.getSelectedRowCount() == 1){
            try{
                int index = occTable.getSelectedRow();
                TableModel model = occTable.getModel();
                int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
                int scap = Integer.parseInt(model.getValueAt(index, 7).toString());
                int edit = 1;
                //int actual = Integer.parseInt(model.getValueAt(index, 8).toString());
                String typ = model.getValueAt(index, 5).toString();
                String day = model.getValueAt(index, 2).toString();
                String time1 = model.getValueAt(index, 3).toString();
                String time2 = model.getValueAt(index, 4).toString();
                String time = time1.substring(0,5);
                
                //String lec = model.getValueAt(index, 6).toString();
                //String lec = model.getValueAt(index, 6).toString();
                setEdit(occ, scap, typ, day, time, edit, time1, time2);
                as.editMod(); //sends details on selected module to addModules Form
                as.setVisible(true);

//                JOptionPane.showMessageDialog(null,"no addModules Form yet");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else{
        JOptionPane.showMessageDialog(null,"no row is selected");
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteOccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOccBtnActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) occTable.getModel();
        //Show a yes or no panel to confirm deletion of the selected module
        JFrame frame = new JFrame("Delete");
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this module? ",
            " ",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            try {
                int index = occTable.getSelectedRow();
                TableModel model = occTable.getModel();
                String code = model.getValueAt(index, 0).toString();
                System.out.println(code);
                String type = model.getValueAt(index, 5).toString();
                int occ = Integer.parseInt(model.getValueAt(index, 1).toString());

                PreparedStatement st1 = con.prepareStatement("DELETE FROM app.TIMETABLE_MODULES WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='" + type + "'");
                PreparedStatement st2 = con.prepareStatement("DELETE FROM APP.REGISTEREDMODULES WHERE MODULE='" + code + "' and occ =" + occ + " and activitytype ='" + type + "'");

                st1.executeUpdate();
                st2.executeUpdate();
                dtm.removeRow(index);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "NO COURSE SELECTED");
            }
        }
       /* DefaultTableModel dtm = (DefaultTableModel) occTable.getModel();
        try{
            /*int SelectedRowIndex = jTable1.getSelectedRow();
            model.removeRow(SelectedRowIndex);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.valid_modules WHERE modules = " + name + "");
            st.executeUpdate();
            int index = occTable.getSelectedRow();
            TableModel model = occTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String type = model.getValueAt(index, 5).toString();
            int occ = Integer.parseInt(model.getValueAt(index, 1).toString());

//            System.out.println(code);
            PreparedStatement st = con.prepareStatement("DELETE FROM app.ALL_MODULES WHERE modules ='" + code + "' and occurence =" + occ + " and activitytype ='"+type+"'");
            st.executeUpdate();
            dtm.removeRow(index);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"NO COURSE SELECTED");
        }*/
    }//GEN-LAST:event_deleteOccBtnActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        this.edit = 0;
        
        new AdminOcc().setVisible(true);
//        JOptionPane.showMessageDialog(null,"no addModules Form yet");

        
    }//GEN-LAST:event_addButtonActionPerformed

    private void addNewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewBtnActionPerformed
        // TODO add your handling code here:
        new AdminAddModule().setVisible(true);
        
    }//GEN-LAST:event_addNewBtnActionPerformed

    private void retrieveOcc() {

        String q1 = "SELECT * FROM TIMETABLE_MODULES JOIN ALL_MODULES ON TIMETABLE_MODULES.MODULES = ALL_MODULES.MODULE WHERE MODULES = '" + getModuleCode() + "'";

        DefaultTableModel tblModel = (DefaultTableModel) occTable.getModel();
        tblModel.setRowCount(0);


        String dm = "SELECT * FROM TIMETABLE_MODULES where modules='"+getModuleCode()+"' ORDER BY OCCURENCE";
        try {
            ps = con.prepareStatement(dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String OCCURENCE = rs.getString("OCCURENCE");
                String DAY = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String LECTURER = rs.getString("LECTURER");
                String TARGET = rs.getString("STUDENTCAP");
                //String ACT = rs.getString("ACTUAL");
                
                
//                DateFormat df = new SimpleDateFormat("HH:mm");
//                
//                if (Objects.isNull(rs.getTime("TIMESTART"))) {
//
//                    TIMESTART = "00:00";
//                   TIMEEND = "00:00";
//
//                } else {
//                    TIMESTART = df.format(rs.getTime("TIMESTART"));
//                    TIMEEND = df.format(rs.getTime("TIMEEND"));
//                }
                

                String tbData[] = {MODULES, OCCURENCE, DAY, TIMESTART, TIMEEND, ACTIVITY, LECTURER,TARGET};

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
   public void setEdit(int occ,int scap,String type,String day,String time,int edit,String time1,String time2){
        this.occ = occ;
        this.type = type;
        this.eday = day;    //e for edit
        this.etime = time;
        //this.elec = lec;
        this.ecap = scap;
        this.edit = edit;
        this.etime1 = time1;
        this.etime2 = time2;
    }
    public int getEOcc(){
        return occ;
    }
    public String getEType(){
        return type;
    }
    public String getEDay(){
        return eday;
    }
    public String getETime(){
        return etime;
    }
    public String getETime1(){
        return etime1;
    }
    public String getETime2(){
        return etime2;
    }
    public String getELec(){
        return elec;
    }
    public int getECap(){
        return ecap;
    }
    public int getEdit(){
        return edit;
    }
    
     //Method to set the values of the data with details from the module
   /* public void setEdit(int occ, int scap, String type, String day, String time, int edit, String time1, String time2, String lec, int actual) {
        this.occ = occ;
        this.type = type;
        this.eday = day;
        this.etime = time;
        this.ecap = scap;
        this.edit = edit;
        this.etime1 = time1;
        this.etime2 = time2;
        this.elec = lec;
        this.actual = actual;
    }

    public int getEOcc() {
        return occ;
    }

    public int getEActual() {
        return actual;
    }

    public String getEType() {
        return type;
    }

    public String getEDay() {
        return eday;
    }

    public String getETime() {
        return etime;
    }

    public String getETime1() {
        return etime1;
    }

    public String getETime2() {
        return etime2;
    }

    public String getELec() {
        return elec;
    }

    public int getECap() {
        return ecap;
    }

    public int getEdit() {
        return edit;
    }*/


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VIEWBUTTON;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addNewBtn;
    private javax.swing.JTable allModulesTable;
    private javax.swing.JLabel courseCode;
    private javax.swing.JLabel courseCredit;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteOccBtn;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel occPanel;
    private javax.swing.JTable occTable;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
