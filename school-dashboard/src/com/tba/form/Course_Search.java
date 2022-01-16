
package com.tba.form;



import com.tba.model.ModelStudentType;
import com.tba.swing.Button;
import com.tba.swing.ButtonColumn;


import com.tba.swing.TableMover;
import com.tba.swing.scrollbar.ScrollBarCustom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import loginpage.ConnectDatabase;
import loginpage.LoginForm1;
import loginpage.WelcomePage;
import net.miginfocom.swing.MigLayout;


public class Course_Search extends javax.swing.JPanel {

    JCheckBox checkBox = new JCheckBox();
    LoginForm1 lf = new LoginForm1();
    Button button = new Button();
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel tblModel;
    private ModelStudentType type;
    static String  module2 , occurence2 , activity2;
    static String moduleCode = "";
    static String credit = "";
    static String activity = "";
    static int occurence;
    Course_Registered cr = new Course_Registered();
    
    
    
    public Course_Search()  {
        con = ConnectDatabase.connectdb();
        initComponents();
        setOpaque(false);
        tblModel = (DefaultTableModel) table1.getModel();
        table1.fixTable(jScrollPane1);
        type = new ModelStudentType();
        table2.fixTable(jScrollPane2);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        sp.setViewportBorder(BorderFactory.createLineBorder(new Color(244,247,252)));
        comboPicker();
        showData();
        if(type.checkType()>=1){
           showButton();
          
           txtCred.setVisible(false);
        }else {
            
            jLabel2.setVisible(false);
            jScrollPane2.setVisible(false);
            button1.setVisible(false);
            txtCred.setVisible(false);
            
         
            
            
            
        }
            
        
   
        
    }
    
   

    
    
    
    
    private void showButton(){
       TableMover mover = new TableMover(table1, table2);
        
         Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };
        
        Action add = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                //
              
                //mover.copy_row(); //the selected row will be copied from jTable1 to jTable2
               /* int index = table1.getSelectedRow();
                TableModel model = table1.getModel();

                int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
                String type = model.getValueAt(index, 2).toString();
                String modulecode = model.getValueAt(index, 0).toString();
                String day = model.getValueAt(index, 5).toString();
                String time1 = model.getValueAt(index, 6).toString();
                String time2 = model.getValueAt(index, 7).toString();
                String username = lf.getMatrixNo();
                String actual1 = "";
                
                //If the module code is either WIA2001 or WIB2001, register the module under the code = WIA2001/WIB2001.
                if (modulecode.equals("WIA2001") || modulecode.equals("WIB2001")) {
                    modulecode = "WIA2001/WIB2001";
                }
                try {
                    Statement st;
                    st = con.createStatement();
                    String checkCap = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and OCC=" + occ + " and ACTIVITYTYPE='" + type + "'";
                    ResultSet rs3 = st.executeQuery(checkCap);
                    rs3.next();
                    actual1 = rs3.getString(1);
                    System.out.println(actual1);

                } catch (SQLException a) {
                    System.out.println("failed md484");
                }

                try {
                    Statement st;
                    Statement st1;
                    st = con.createStatement();
                    String strQuery3 = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and USERNAME='" + username + "'";
                    ResultSet rs3 = st.executeQuery(strQuery3);
                    rs3.next();
                    String Countrow3 = rs3.getString(1);
                    System.out.println(Countrow3);

                    boolean Clash = checkTime();

                    if (Clash) {
                        JOptionPane.showMessageDialog(null, "CLASHES WITH " + activity2 + " FOR " + module2 + " OCCURENCE " + occurence2);
                    } else if (Countrow3.equals("0")) {
                      
                        mover.copy_row();
                        

                        
                    } else {
                        JOptionPane.showMessageDialog(null, "MODULE ALREADY REGISTERED");

                    }
                } catch (SQLException a) {
                    System.out.println("failed md401");
                    JOptionPane.showMessageDialog(null, e);
            }*/
                
                checkTime();
                
              

            }
        };
        
         

       ButtonColumn buttonColumn = new ButtonColumn(table1, add, 10);
       ButtonColumn buttonColumn2 = new ButtonColumn(table2, delete, 11);
       
       
        
    }
    
   
    
    
    private void showData() {
        
        
        
        

        try {
            retrieveData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
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
    
     private void retrieveData() {
        String q1 = "SELECT * FROM TIMETABLE_MODULES JOIN VALID_MODULES ON TIMETABLE_MODULES.MODULES = VALID_MODULES.MODULE";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String DAY = rs.getString("DAY");
                String OCC = rs.getString("OCCURENCE");
                String AC = rs.getString("ACTIVITYTYPE");
                String TE = rs.getString("TIMEEND");
                 String TS = rs.getString("TIMESTART");
                 String L = rs.getString("LECTURER");
                String CAP = rs.getString("STUDENTCAP");
                 String ACT = rs.getString("ACTUAL");
                 String CREDIT = rs.getString("CREDIT");

                String tbData[] = {MODULES, OCC,AC,CREDIT,DAY, TS,TE,L,CAP,ACT};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    
     
      private void comboPicker() {
          
            
        int muet = lf.getMuet_band();
        if(lf.getMuet_band()==6){
            muet = 5;
        }
          String q1 = "SELECT * FROM VALID_MODULES WHERE STUDENTTYPE="+lf.getStudent_type()+" OR STUDENTTYPE=0 AND MUET=0 AND CSIT=0 OR MUET="+muet+" OR CSIT="+lf.getCsit()+"ORDER BY MODULE";
         
          try {
              
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                
                if(MODULES.equals("WIA2001/WIB2001")){
                  if(lf.getCsit()==1){
                      MODULES = "WIA2001";
                  } else if(lf.getCsit()==2){
                      MODULES = "WIB2001";
                  }
                }

                modulebox.addItem(MODULES);
            }
              
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
          
        
    }
      
      private void filter(){
          
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblModel);
          table1.setRowSorter(sorter);
         List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
         
         

        modulebox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(filters.isEmpty())
                    filters.add(RowFilter.regexFilter(modulebox.getSelectedItem().toString()));
                else
                    filters.set(0, RowFilter.regexFilter(modulebox.getSelectedItem().toString()));
                // Apply filters
                sorter.setRowFilter(RowFilter.andFilter(filters));
            }
        });

        
        
        
        occurencebox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                
                if(filters.size() < 2)
                   filters.add(RowFilter.regexFilter(occurencebox.getSelectedItem().toString(),1));
                    
                   
                else
                  filters.set(1,RowFilter.regexFilter(occurencebox.getSelectedItem().toString(),1));
                    //filters.set(1,RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,occurencebox.getSelectedIndex()));
                // Apply filters*/
                
                sorter.setRowFilter(RowFilter.andFilter(filters));           
            }
        });
        
     
      }
      
      /*public boolean checkTime() {
        String q1 = "SELECT * FROM APP.REGISTEREDMODULES WHERE USERNAME='" + lf.getMatrixNo() + "'";

        int index = table1.getSelectedRow();
        TableModel model = table1.getModel();

        String day = model.getValueAt(index, 4).toString();
        String time1 = model.getValueAt(index, 5).toString();
        String time2 = model.getValueAt(index, 6).toString();

        //LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareEnd = LocalTime.parse(time2.substring(0, 5));

        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {

                String DAY2 = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                activity2 = rs.getString("ACTIVITYTYPE");
                module2 = rs.getString("MODULE");
                occurence2 = rs.getString("OCC");

                LocalTime targetStart = LocalTime.parse(TIMESTART.substring(0, 5));
                LocalTime targetEnd = LocalTime.parse(TIMEEND.substring(0, 5));

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
    }*/
      
       public boolean checkTime() {
        //SQL command to get all details from REGISTEREDMODULES database with the user's username/matrix number.
       
        String q1 = "SELECT * FROM APP.REGISTEREDMODULES WHERE USERNAME='" + lf.getMatrixNo() + "'";

        TableModel model = table1.getModel();
        int index = table1.getSelectedRow();
        int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
        String modulecode = (String) model.getValueAt(index, 0);
        String time1 = "";
        String time2 = "";
        String day = "";
        
        try {
            //Get TIMESTART and TIMEEND of the module from TIMETABLE_MODULES 
            //String occtimes = "SELECT * FROM TIMETABLE_MODULES  WHERE MODULES='" + modulecode + "' AND OCCURENCE=" + occ + "";
            String occtimes = "SELECT * FROM TIMETABLE_MODULES WHERE MODULES='" + modulecode + "' AND OCCURENCE=" + occ + "";
            ps = con.prepareStatement(occtimes);
            rs = ps.executeQuery();
            while (rs.next()) {
                String modules = rs.getString("MODULES");
                time1 = rs.getString("TIMESTART").substring(0, 5);
                time2 = rs.getString("TIMEEND").substring(0, 5);
                time1 = convert24hours(time1.substring(0, 5));
                time2 = convert24hours(time2.substring(0, 5));
                day = rs.getString("DAY");

                System.out.println(modules+" "+day + " " + time1 + " " + time2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareEnd = LocalTime.parse(time2.substring(0, 5));

        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {

                String DAY2 = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                activity2 = rs.getString("ACTIVITYTYPE");
                module2 = rs.getString("MODULE");
                occurence2 = rs.getString("OCC");

                TIMESTART = convert24hours(TIMESTART.substring(0, 5));
                TIMEEND = convert24hours(TIMEEND.substring(0, 5));

                LocalTime targetStart = LocalTime.parse(TIMESTART.substring(0, 5));
                LocalTime targetEnd = LocalTime.parse(TIMEEND.substring(0, 5));

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
      
       //Method to check for the module's time conflicts with the user's timetable
   /* public boolean checkTime() {
        //SQL command to get all details from REGISTEREDMODULES database with the user's username/matrix number.
        String q1 = "SELECT * FROM APP.REGISTEREDMODULES WHERE USERNAME='" + lf.getMatrixNo() + "'";

        TableModel model = table1.getModel();
        int index = table1.getSelectedRow();
        int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
        String time1 = "";
        String time2 = "";
        String day = "";
        
        try {
            //Get TIMESTART and TIMEEND of the module from TIMETABLE_MODULES 
            String occtimes = "SELECT * FROM TIMETABLE_MODULES WHERE MODULES='" + cr.getModuleCode() + "' AND OCCURENCE=" + occ + "";
            ps = con.prepareStatement(occtimes);
            rs = ps.executeQuery();
            while (rs.next()) {
                time1 = rs.getString("TIMESTART").substring(0, 5);
                time2 = rs.getString("TIMEEND").substring(0, 5);
                time1 = convert24hours(time1.substring(0, 5));
                time2 = convert24hours(time2.substring(0, 5));
                day = rs.getString("DAY");

                System.out.println(day + " " + time1 + " " + time2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        LocalTime compareStart = LocalTime.parse(time1.substring(0, 5));
        LocalTime compareEnd = LocalTime.parse(time2.substring(0, 5));

        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {

                String DAY2 = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                activity2 = rs.getString("ACTIVITYTYPE");
                module2 = rs.getString("MODULE");
                occurence2 = rs.getString("OCC");

                TIMESTART = convert24hours(TIMESTART.substring(0, 5));
                TIMEEND = convert24hours(TIMEEND.substring(0, 5));

                LocalTime targetStart = LocalTime.parse(TIMESTART.substring(0, 5));
                LocalTime targetEnd = LocalTime.parse(TIMEEND.substring(0, 5));

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
    }*/
       
  
       

     
 
        
    
    
  
    
   
    
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tba.swing.table.TableNew();
        modulebox = new javax.swing.JComboBox<>();
        occurencebox = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.tba.swing.table.TableNew();
        button1 = new com.tba.swing.Button();
        txtCred = new javax.swing.JLabel();

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1043, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        sp.setBackground(new java.awt.Color(244, 247, 252));
        sp.setBorder(null);
        sp.setForeground(new java.awt.Color(255, 255, 255));
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setOpaque(false);

        panel.setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("  Search Modules");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module", "Occ", "Activity ", "Credit", "Day", "Time Start", "Time End", "Lecturer", "Capacity", "Actual", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(1).setPreferredWidth(20);
            table1.getColumnModel().getColumn(3).setPreferredWidth(20);
            table1.getColumnModel().getColumn(6).setPreferredWidth(80);
            table1.getColumnModel().getColumn(7).setPreferredWidth(120);
            table1.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        modulebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                moduleboxItemStateChanged(evt);
            }
        });
        modulebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleboxActionPerformed(evt);
            }
        });

        occurencebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));

        txtSearch.setText("jTextField1");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(occurencebox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occurencebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setText("  Selected Modules");

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table2);

        button1.setBackground(new java.awt.Color(66, 133, 244));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("   Confirm  ");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        txtCred.setText("Total Credit:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(txtCred, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCred)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 459, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 459, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMenuActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Register the selected modules?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            
            int index = table1.getSelectedRow();
        TableModel model = table1.getModel();

        int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
        String type = model.getValueAt(index, 2).toString();
        String modulecode = model.getValueAt(index, 0).toString();
        String day = model.getValueAt(index, 4).toString();
        String time1 = model.getValueAt(index, 5).toString();
        String time2 = model.getValueAt(index, 6).toString();
        String username = lf.getMatrixNo();
        String actual1 = "";

        //If the module code is either WIA2001 or WIB2001, register the module under the code = WIA2001/WIB2001.
        if (modulecode.equals("WIA2001") || modulecode.equals("WIB2001")) {
            modulecode = "WIA2001/WIB2001";
        }

        try {
            Statement st;
            st = con.createStatement();
            
            //Check the amount of students already registered to the selected module
            String checkCap = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and OCC=" + occ + " and ACTIVITYTYPE='" + type + "' and TYPE=1";
            ResultSet rs3 = st.executeQuery(checkCap);
            rs3.next();
            actual1 = rs3.getString(1);
            System.out.println(actual1);

        } catch (SQLException e) {
            System.out.println("failed md484 " + e);
        }

        try {
            Statement st;
            st = con.createStatement();
            
            //SQL command to find number of rows with the same module.
            String checkDuplicates = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and USERNAME='" + username + "' and type=1";
            ResultSet rs3 = st.executeQuery(checkDuplicates);
            rs3.next();
            String duplicatedRows = rs3.getString(1);
            System.out.println(duplicatedRows);

            boolean Clash = checkTime();

            //Check for duplicates modules
            if (duplicatedRows.equals("0")) {
                //Check for time conflicts with student's timetable
                if (Clash) {
                    JOptionPane.showMessageDialog(null, "CLASHES WITH " + activity2 + " FOR " + module2 + " OCCURENCE " + occurence2);
                } 
                //Registers the module to the student's timetable.
                else {
                    int actual = Integer.parseInt(actual1) + 1;
                    
                    String registerModule = "INSERT INTO APP.REGISTEREDMODULES(USERNAME,MODULE,OCC,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND,TYPE) SELECT '" + username + "',MODULES,OCCURENCE,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND,1 FROM APP.TIMETABLE_MODULES WHERE OCCURENCE=" + occ + " AND  MODULES='" + modulecode + "'";
                    String setActual = "UPDATE APP.TIMETABLE_MODULES SET ACTUAL=" + actual + " WHERE MODULES='" + modulecode + "' AND OCCURENCE=" + occ + "";

                    st.execute(registerModule);
                    st.execute(setActual);

                    JOptionPane.showMessageDialog(null, "MODULE REGISTERED");
                }
            } else {
                JOptionPane.showMessageDialog(null, "MODULE ALREADY REGISTERED");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
               /*int index = table1.getSelectedRow();
                TableModel model = table1.getModel();
              
                int stdnt = lf.getStudent_type();
                int occ = Integer.parseInt(model.getValueAt(index, 1).toString());
                String type = model.getValueAt(index, 2).toString();
                String modulecode = model.getValueAt(index, 0).toString();
                String day = model.getValueAt(index, 5).toString();
                String time1 = model.getValueAt(index, 6).toString();
                String time2 = model.getValueAt(index, 7).toString();
                String username = lf.getMatrixNo();
                String actual1 = "";
                
                if (modulecode.equals("WIA2001") || modulecode.equals("WIB2001")) {
                    modulecode = "WIA2001/WIB2001";
                }
                try {
                    Statement st;
                    st = con.createStatement();
                    
                    //check amount of students 
                    String checkCap = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and OCC=" + occ + " and ACTIVITYTYPE='" + type + "'";
                    ResultSet rs3 = st.executeQuery(checkCap);
                    rs3.next();
                    actual1 = rs3.getString(1);
                    System.out.println(actual1);

                } catch (SQLException a) {
                    System.out.println("failed md484");
                }

                try {
                    Statement st;
                    Statement st1;
                    st = con.createStatement();
                    String strQuery3 = "SELECT COUNT(*) FROM app.REGISTEREDMODULES where module='" + modulecode + "' and USERNAME='" + username + "'";
                    ResultSet rs3 = st.executeQuery(strQuery3);
                    rs3.next();
                    String Countrow3 = rs3.getString(1);
                    System.out.println(Countrow3);

                    boolean Clash = checkTime();

                    if (Clash) {
                        JOptionPane.showMessageDialog(null, "CLASHES WITH " + activity2 + " FOR " + module2 + " OCCURENCE " + occurence2);
                    } else if (Countrow3.equals("0")) {
                        String reg = "INSERT INTO APP.REGISTEREDMODULES(USERNAME,MODULE,OCC,ACTIVITYTYPE,DAY,TIMESTART,TIMEEND,TYPE) VALUES ('" + username + "','" + modulecode + "'," + occ + ",'" + type + "','" + day + "','" + time1 + "','" + time2 + "'," + stdnt + ")";
                        st = con.createStatement();
                        st.execute(reg);

                        int actual = Integer.parseInt(actual1) + 1;

                        String setActual = "UPDATE APP.TIMETABLE_MODULES SET ACTUAL=" + actual + " WHERE MODULES='" + modulecode + "' AND OCCURENCE=" + occ + "";
                        st1 = con.createStatement();
                        st1.execute(setActual);
                        
                        //the selected row will be copied from jTable1 to jTable2

                        JOptionPane.showMessageDialog(null, "MODULE REGISTERED");
                    } else {
                        JOptionPane.showMessageDialog(null, "MODULE ALREADY REGISTERED");

                    }
                } catch (SQLException a) {
                    System.out.println("failed md401");
                    JOptionPane.showMessageDialog(null, a);
            }
                   
        } else {*/
    // no option
}
    
    }//GEN-LAST:event_button1ActionPerformed

    private void moduleboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleboxActionPerformed

    private void moduleboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_moduleboxItemStateChanged
        String query = modulebox.getSelectedItem().toString();
        
       // filter();
    }//GEN-LAST:event_moduleboxItemStateChanged

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        DefaultTableModel table = (DefaultTableModel)table1.getModel();
        String search = txtSearch.getText().toUpperCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tba.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> modulebox;
    private javax.swing.JComboBox<String> occurencebox;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    private com.tba.swing.table.TableNew table1;
    private com.tba.swing.table.TableNew table2;
    private javax.swing.JLabel txtCred;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

