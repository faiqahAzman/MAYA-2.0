

package com.tba.form.admin;



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


public class ViewRegisteredStudents extends javax.swing.JPanel {

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
    
    
    
    public ViewRegisteredStudents()  {
        con = ConnectDatabase.connectdb();
        initComponents();
        setOpaque(false);
        tblModel = (DefaultTableModel) table1.getModel();
        table1.fixTable(jScrollPane1);
        type = new ModelStudentType();
       
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        sp.setViewportBorder(BorderFactory.createLineBorder(new Color(244,247,252)));
        
        if(type.checkType()==0){
           comboPickerLecturer();
           showData();
           
        }else if(type.checkType()<0) {
            
            
            //adminControl();
            showButton();
            comboPicker();
            showData();
            
            
        }
            
        
   
        
    }
    
   

   
    private void showButton(){
       //TableMover mover = new TableMover(table1, table2);
        
         Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    JTable table = (JTable)e.getSource();
                    int selectedRowIndex = table.getSelectedRow();
                    String name = table.getValueAt(selectedRowIndex, 0).toString();
                    String module = table.getValueAt(selectedRowIndex, 1).toString();
                    int occ = Integer.parseInt(table.getValueAt(selectedRowIndex, 2).toString());
                    String type = table.getValueAt(selectedRowIndex, 3).toString();
                    
                    PreparedStatement st = con.prepareStatement("DELETE FROM REGISTEREDMODULES WHERE username ='" + name + "' and occ =" + occ + " and activitytype ='"+type+"'");
                    st.executeUpdate();
                    
                    int modelRow = Integer.valueOf( e.getActionCommand() );
                    ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                } catch (Exception a) {
                    Logger.getLogger(ViewRegisteredStudents.class.getName()).log(Level.SEVERE, null, a);
                }
            }
        };
        
      
       ButtonColumn buttonColumn2 = new ButtonColumn(table1, delete, 4);
       
       
        
    }
    
   
    
    
    private void showData() {
        
        
        
        

        try {
            retrieveData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
    
     private void retrieveData() {
        String q1 = "SELECT * FROM REGISTEREDMODULES WHERE TYPE >=1";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULE = rs.getString("MODULE");
                String USER = rs.getString("USERNAME");
                String OCC = rs.getString("OCC");
                String AC = rs.getString("ACTIVITYTYPE");
                
                String tbData[] = {USER,MODULE, OCC,AC};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    
     
      private void comboPicker() {
          String q1 = "SELECT * FROM VALID_MODULES ";
         
          try {
              
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                
                

                modulebox.addItem(MODULES);
            }
              
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          
          
        
    }
      
       private void comboPickerLecturer() {
          String q1 = "SELECT * FROM TIMETABLE_MODULES JOIN LOGINTABLE ON LOGINTABLE.matrix_number = '" + lf.getMatrixNo() + "' AND TIMETABLE_MODULES.lecturer=LOGINTABLE.FULLNAME" ;
         
          try {
              
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                
                

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
        
        activitybox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                
                if(filters.size() < 3)
                    filters.add(RowFilter.regexFilter(activitybox.getSelectedItem().toString()));
                   
                else
                   
                    filters.set(2,RowFilter.regexFilter(activitybox.getSelectedItem().toString()));
                // Apply filters
                
                sorter.setRowFilter(RowFilter.andFilter(filters));           
            }
        });
      }
      
     

     
 
        
    
    
  
    
   
    
    
    
    

    
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
        activitybox = new javax.swing.JComboBox<>();

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
        jLabel1.setText("  View Registered Students");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Module", "Occ", "Activity", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(1).setPreferredWidth(20);
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

        activitybox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LECTURE", "TUTORIAL", "LAB" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(occurencebox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(activitybox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occurencebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activitybox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(477, 477, 477))
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

    private void moduleboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleboxActionPerformed

    private void moduleboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_moduleboxItemStateChanged
        String query = modulebox.getSelectedItem().toString();
        
        filter();
    }//GEN-LAST:event_moduleboxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> activitybox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> modulebox;
    private javax.swing.JComboBox<String> occurencebox;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    private com.tba.swing.table.TableNew table1;
    // End of variables declaration//GEN-END:variables
}

