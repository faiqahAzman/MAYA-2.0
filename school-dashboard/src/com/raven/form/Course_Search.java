
package com.raven.form;



import com.raven.model.ModelStudentType;
import com.raven.swing.Button;
import com.raven.swing.ButtonColumn;
import com.raven.swing.ButtonRenderer;
import com.raven.swing.RowPopUp;
import com.raven.swing.TableMover;
import com.raven.swing.scrollbar.ScrollBarCustom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import loginpage.ConnectDatabase;
import loginpage.WelcomePage;
import net.miginfocom.swing.MigLayout;


public class Course_Search extends javax.swing.JPanel {

    JCheckBox checkBox = new JCheckBox();
    Button button = new Button();
     Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
     DefaultTableModel tblModel;
      private ModelStudentType type;
    
    
    public Course_Search()  {
        initComponents();
        setOpaque(false);
        tblModel = (DefaultTableModel) table1.getModel();
        table1.fixTable(jScrollPane1);
        type = new ModelStudentType();
        table2.fixTable(jScrollPane2);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        sp.setViewportBorder(BorderFactory.createLineBorder(new Color(244,247,252)));
        
        showData();
        if(type.checkType()>=1){
           showButton();
        }else {
            
            jLabel2.setVisible(false);
            jScrollPane2.setVisible(false);
            button1.setVisible(false);
            
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
               
                String data[] = new String[100];
                
                mover.copy_row(); //the selected row will be copied from jTable1 to jTable2
                //get selectedrow
                //table1.getSelectedRow();
                
                /*int column_count = table1.getColumnCount();
                for (int i=0;i<column_count-1;i++) {
                    //selected modules in array
                data[i] = (String) table1.getValueAt(table1.getSelectedRow(),i); 
                System.out.println(data[i]);
                v.add(data[i]);
                
                
                }*/
                
                
             
                
                
                //mover.copy_row_unique(); //same, but with uniqueness check
              

            }
        };
        
         

       ButtonColumn buttonColumn = new ButtonColumn(table1, add, 3);
       ButtonColumn buttonColumn2 = new ButtonColumn(table2, delete, 4);
        
    }
    
    private void showData() {
        
        con = ConnectDatabase.connectdb();
        
        

        try {
            retrieveData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
    
     private void retrieveData() {
        String q1 = "SELECT * FROM TIMETABLE_MODULES";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String DAY = rs.getString("DAY");
                String TS = rs.getString("TIMESTART");

                String tbData[] = {MODULES, DAY, TS};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     public class ClashCheck {
         
         String q1 = "SELECT * FROM VALID_MODULES";

   

        

        //fop lecture
        /*LocalTime compareStart = LocalTime.of(10, 00);
        LocalTime compareEnd = LocalTime.of(12, 00);

        while (true) {
            System.out.println(" >> Press X to exit");
            //module yg nak pilih
            System.out.print("Please enter a time to start (HH:MM) : ");
            String start = s.nextLine(); // 

            if (start.equalsIgnoreCase("x")) {
                System.out.println("\n********************* you wished to exit *********************");
                break;
            }
            //module yg nak pilih
            LocalTime targetStart = LocalTime.parse(start);

            System.out.println("");
            System.out.print("Please enter a time to end (HH:MM) : ");
            String end = s.nextLine();
            LocalTime targetEnd = LocalTime.parse(end);
            
            //case 1
            //s1.equals(s2)
            boolean startSame = (targetStart.equals(compareStart));
            boolean endSame = (targetEnd.equals(compareEnd));

            //case 2
            // 4 < x< 20 -> 4<x && x<20
            boolean startInBTW = (targetStart.isAfter(compareStart)
                    //isAfter = >
                    //ts > cs
                    && targetStart.isBefore(compareEnd) 
                    //isBefore = <
                    // ts < ce
                    );

            boolean endInBTW = (targetEnd.isAfter(compareStart)
                    && targetEnd.isBefore(compareEnd));

            //case 3 FOP
            boolean compareStartInBTW = (compareStart.isAfter(targetStart)
                    && compareStart.isBefore(targetEnd));

            boolean compareEndInBTW = (compareEnd.isAfter(targetStart)
                    && compareEnd.isBefore(targetEnd));

            if (startSame || endSame) { //case 1
                System.out.println("the class starts OR ends AT THE SAME TIME as fop class!!");
            } else if (startInBTW || endInBTW) {    //case 2
                System.out.println("the class starts OR ends DURING fop class!");
            } else if (compareStartInBTW || compareEndInBTW) {  //case 3
                System.out.println("fop class is held during this entered class!!!");
            } else {
                System.out.println("no clashes?");
            }

        }*/
    
}
 
        
    
    
  
    
   
    
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchbar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.raven.swing.table.TableNew();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.raven.swing.table.TableNew();
        button1 = new com.raven.swing.Button();

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

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module", "Credit", "Activity", ""
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
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
                    .addGap(0, 456, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 456, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMenuActionPerformed

    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbarActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Register the selected modules?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
        } else {
    // no option
}
    
    }//GEN-LAST:event_button1ActionPerformed

    private void searchbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyReleased
         tblModel.setRowCount(0);
        String typedText = searchbar.getText();
        String q1 = "SELECT * FROM VALID_MODULES WHERE MODULE LIKE ?";
        try {
            ps = con.prepareStatement(q1);
            ps.setString(1, "%" + typedText.toUpperCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String CREDIT = rs.getString("CREDIT");
                String ACTIVITY = rs.getString("ACTIVITY");
                String tbData[] = {MODULES, CREDIT, ACTIVITY};

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (searchbar.equals("")) {
            retrieveData();
        }
    }//GEN-LAST:event_searchbarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField searchbar;
    private javax.swing.JScrollPane sp;
    private com.raven.swing.table.TableNew table1;
    private com.raven.swing.table.TableNew table2;
    // End of variables declaration//GEN-END:variables
}

