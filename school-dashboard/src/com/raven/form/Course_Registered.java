package com.raven.form;



import com.raven.component.Card;

import com.raven.dialog.EditMessage;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.model.ModelStudentType;
import com.raven.swing.Button;
import com.raven.swing.ButtonColumn;

import com.raven.swing.table.TableNew;


import com.raven.swing.ButtonRenderer;

import com.raven.swing.RowPopUp;
import com.raven.swing.scrollbar.ScrollBarCustom;
import com.raven.swing.table.EventAction;
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


public class Course_Registered extends javax.swing.JPanel {

   
    private Card card;
    Button button = new Button();
   Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
     DefaultTableModel tblModel;
      private ModelStudentType type = new ModelStudentType();

    public Course_Registered() {
        
         con = ConnectDatabase.connectdb();
        initComponents();
        setVisible(true);
        setOpaque(false);
        table1.fixTable(jScrollPane1);
        tblModel = (DefaultTableModel) table1.getModel();
        init();
        
      
  
       
    }
    
    private void init() {
        
        showButton();
        getModules();
    }
    
    private void showButton() {
        
        Action edit = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                
               
                SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
                JSpinner spinner = new JSpinner(sModel);
                
                JTable table = (JTable)e.getSource();
                int selectedRowIndex = table.getSelectedRow();
                
                try{
                
                
                //int target_num = Integer.parseInt(table.getValueAt(selectedRowIndex, 3));
                //int target_num = Integer.parseInt((int) table.getValueAt(selectedRowIndex, 3));
                int newTG = Integer.parseInt(JOptionPane.showInputDialog(null, "Target Number", 0)); 
                
                //table.setValueAt(newTG, selectedRowIndex, 3);
                
                   try {
                       
                        updateTG(newTG);
                        
                        table.setValueAt(newTG, selectedRowIndex, 3);
                    } catch (Exception a) {
                        JOptionPane.showMessageDialog(null, a.getMessage());
                    }
             
                
                
                } catch (Exception ae) {
                    JOptionPane.showMessageDialog(null, "No Row Selected");
                }
                
                /*int option = JOptionPane.showOptionDialog(null, spinner, "Enter new target number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (option == JOptionPane.CANCEL_OPTION)
                {
                    // user hit cancel
                } else if (option == JOptionPane.OK_OPTION)
                {
                    table.setValueAt(option, selectedRowIndex, 3);
                    updateTG(option);
                }   
                    
                }
                catch(Exception ae) {
                     JOptionPane.showMessageDialog(null, "No Row Selected");   
                        }*/
               
            }
        };
       
        if(type.checkType()== 0){
        ButtonColumn buttonColumn2 = new ButtonColumn(table1, edit, 5);
        }
        
    }
    
    private void getModules() {
        
        
        String q1 = "SELECT * FROM TIMETABLE_MODULES";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String OCC = rs.getString("OCCURENCE");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String TG = rs.getString("CAPACITY");

                
                String tbData[] = {MODULES, OCC, ACTIVITY,TG};
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
           String query = "UPDATE TIMETABLE_MODULES"+" SET CAPACITY = "+TG+" WHERE MODULES ='"+modules+"'AND "
                   + "OCCURENCE ="+occurence+" AND ACTIVITYTYPE='"+act+"'"  ;
           ps = con.prepareStatement(query);

           //ps.setInt(7, TG);
           ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
   
    
    
     
   
     
     
  

  
  
   
   
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.raven.swing.table.TableNew();
        searchbar = new javax.swing.JTextField();

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("  My Modules");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modules", "Occurence", "Activity", "Capacity", "", ""
            }
        ));
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbarActionPerformed

    private void searchbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyReleased
          tblModel.setRowCount(0);
        String typedText = searchbar.getText();
        String q1 = "SELECT * FROM TIMETABLE_MODULES WHERE MODULES LIKE ?";
        try {
            ps = con.prepareStatement(q1);
            ps.setString(1, "%" + typedText.toUpperCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                 String MODULES = rs.getString("MODULES");
                String OCC = rs.getString("OCCURENCE");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String TG = rs.getString("CAPACITY");

                
                String tbData[] = {MODULES, OCC, ACTIVITY,TG};
                

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
    private com.raven.swing.table.TableNew table1;
    // End of variables declaration//GEN-END:variables
}
