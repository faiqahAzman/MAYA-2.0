package com.tba.form;



import com.tba.component.Card;


import com.tba.form.admin.AdminModules;
import com.tba.form.admin.AdminOcc;
import com.tba.main.Main;

import com.tba.model.ModelStudentType;
import com.tba.swing.Button;
import com.tba.swing.ButtonColumn;

import com.tba.swing.table.TableNew;



import com.tba.swing.scrollbar.ScrollBarCustom;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.ButtonModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import loginpage.ConnectDatabase;
import loginpage.LoginForm1;


public class Course_Registered extends javax.swing.JPanel {

   
    private Card card;
    Button button = new Button();
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel tblModel;
    private ModelStudentType type = new ModelStudentType();
    LoginForm1 lf = new LoginForm1();
    
    public Course_Registered() {
        
        con = ConnectDatabase.connectdb();
        initComponents();
        setVisible(true);
        setOpaque(false);
        
        table1.fixTable(jScrollPane1);
        tblModel = (DefaultTableModel) table1.getModel();
        
        if (type.checkType()>=1) {
           init(); 
           
           
        }
        else{
            initStaff();
            
        }
        
        
      
  
       
    }
    
    private void init() {
        
        showButton();
        getModules();
        setCredits(); 
    }
    
    private void initStaff(){
        
        getModuleStaff();
        showButton();
    }
    
    private void showButton() {
        
        Action edit = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                
               
                /*SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
                JSpinner spinner = new JSpinner(sModel);*/
                
                JTable table = (JTable)e.getSource();
                int selectedRowIndex = table.getSelectedRow();
                
                
                
                
                //int target_num = Integer.parseInt(table.getValueAt(selectedRowIndex, 3));
                //int target_num = Integer.parseInt((int) table.getValueAt(selectedRowIndex, 3));
               int newTG = Integer.parseInt(JOptionPane.showInputDialog(null,"Target Number", 0)); 
              
                
                //table.setValueAt(newTG, selectedRowIndex, 3);
                
                   
                       int scap = Integer.parseInt((String) table.getValueAt(selectedRowIndex, 4));
                       boolean scap_limit = true;
                       
                       if (newTG <scap ) {
                           scap_limit = false;
                          JOptionPane.showMessageDialog(null, "STUDENT LIMIT IS LOWER THAN REIGSTERED STUDENTS!");
                       }else {
                            updateTG(newTG);
                           table.setValueAt(newTG, selectedRowIndex, 4);
                       
        }
                        
                   
                   
                   //updateTG(newTG);
                   //table.setValueAt(newTG, selectedRowIndex, 4);
                
                
                
                 //as.setVisible(tr);
                 
                 

        
       
             
                
                
               
                
              
               
            }
        };
       
        if(type.checkType()== 0){
        ButtonColumn buttonColumn2 = new ButtonColumn(table1, edit, 6);
        }
        
    }
    
    private void getModules() {
        
        
        String q1 = "SELECT * FROM REGISTEREDMODULES WHERE username = '" + lf.getMatrixNo() + "'";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String OCC = rs.getString("OCC");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String DAY = rs.getString("DAY");
                String TS = rs.getString("TIMESTART");
                String TE = rs.getString("TIMEEND");

                
                String tbData[] = {MODULES, OCC, ACTIVITY,DAY, TS,TE};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void getModuleStaff() {
        
        
        String q1 = "SELECT * FROM TIMETABLE_MODULES JOIN LOGINTABLE ON TIMETABLE_MODULES.lecturer = '" + lf.getMatrixNo() + "' AND TIMETABLE_MODULES.lecturer=LOGINTABLE.FULLNAME" ;
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String OCC = rs.getString("OCCURENCE");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String DAY = rs.getString("DAY");
                String SC = rs.getString("STUDENTCAP");
                String AC = rs.getString("ACTUAL");

                
                String tbData[] = {MODULES, OCC, ACTIVITY,DAY, SC,AC};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void updateTG(int TG) {
        
        
        try {
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            int rows = tb.getRowCount();
       //for (int i = 0; i < rows; i++) {
           String modules = tb.getValueAt(table1.getSelectedRow(), 0).toString();
           String occurence = tb.getValueAt(table1.getSelectedRow(), 1).toString();
           String act = tb.getValueAt(table1.getSelectedRow(), 2).toString();

           //String query = "UPDATE TIMETABLE_MODULES SET CAPACITY = '"+TG+"' WHERE MODULES ='"+a+"'";
           String query = "UPDATE TIMETABLE_MODULES"+" SET STUDENTCAP = "+TG+" WHERE MODULES ='"+modules+"'AND "
                   + "OCCURENCE ="+occurence+" AND ACTIVITYTYPE='"+act+"'"  ;
           ps = con.prepareStatement(query);

           //ps.setInt(7, TG);
           ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
          public void setCredits(){
        
        try{
            //Gets all details from REGISTEREDMODULES database with the user's username
            String getCredit = "SELECT * FROM REGISTEREDMODULES WHERE USERNAME='"+lf.getMatrixNo()+"'";
            ps = con.prepareStatement(getCredit);
            rs = ps.executeQuery();
            int totalcred = 0;
            //Gets the TIMESTART and TIMEEND of the modules
            while(rs.next()){
                int time1 = Integer.parseInt(rs.getString("TIMESTART").substring(0,2));
                int time2 = Integer.parseInt(rs.getString("TIMEEND").substring(0,2));
                int time = time2-time1;
                if(time<0){
                    time = time+12;
                }
                //Getting the cummulative time duration (credit hour) of the modules registered to the student
                totalcred = totalcred+time;

                txtCred.setText("TOTAL CREDIT HOUR: "+String.valueOf(totalcred));
            }
            } catch(SQLException e){
            
        }
    }
    
   
    
    
     
   
     
     
  

  
  
   
   
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tba.swing.table.TableNew();
        searchbar = new javax.swing.JTextField();
        txtCred = new javax.swing.JLabel();

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("  My Modules");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modules", "Occurence", "Activity", "Day", "Time Start", "Time End", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        searchbar.setBackground(new java.awt.Color(218, 225, 236));
        searchbar.setForeground(new java.awt.Color(46, 59, 82));
        searchbar.setText("   Search");
        searchbar.setBorder(null);
        searchbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbarActionPerformed(evt);
            }
        });
        searchbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbarKeyReleased(evt);
            }
        });

        txtCred.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtCred.setText("TOTAL CREDIT HOUR:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(txtCred, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCred)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbarActionPerformed

    private void searchbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyReleased
          tblModel.setRowCount(0);
        String typedText = searchbar.getText();
        String q1 = "SELECT * FROM REGISTEREDMODULES WHERE username = '" + lf.getMatrixNo() + "' AND MODULE LIKE ?";
        try {
            ps = con.prepareStatement(q1);
            ps.setString(1, "%" + typedText.toUpperCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String OCC = rs.getString("OCC");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String DAY = rs.getString("DAY");
                String TS = rs.getString("TIMESTART");
                String TE = rs.getString("TIMEEND");

                
                String tbData[] = {MODULES, OCC, ACTIVITY,DAY, TS,TE};
                

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (searchbar.equals("")) {
            getModules();
        }
    }//GEN-LAST:event_searchbarKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchbar;
    private com.tba.swing.table.TableNew table1;
    private javax.swing.JLabel txtCred;
    // End of variables declaration//GEN-END:variables
}
