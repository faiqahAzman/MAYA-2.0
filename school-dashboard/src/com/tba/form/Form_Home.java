/***************************************************************************************
*    Title: java-swing-school-management-dashboard
*    Author: DJ-Raven
*    Date: 2021
*    Code version: 1.0
*    Availability: https://github.com/DJ-Raven/java-swing-school-management-dashboard.git 
*
***************************************************************************************/

package com.tba.form;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.tba.main.Main;
import com.tba.model.ModelCard;


import com.tba.swing.TableColorCellRenderer;
import com.tba.swing.icon.GoogleMaterialDesignIcons;
import com.tba.swing.icon.IconFontSwing;
import com.tba.noticeboard.ModelNoticeBoard;


import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import loginpage.ConnectDatabase;
import loginpage.LoginForm1;
import loginpage.WelcomePage;


public class Form_Home extends javax.swing.JPanel {

    TableColorCellRenderer renderer = new TableColorCellRenderer();
    
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    LoginForm1 lf = new LoginForm1();
    String output;
  
    
    
    
    public Form_Home() {
        initComponents();
        
        //stable1.fixTable(jScrollPane1);
       // table1.setDefaultRenderer(String.class, renderer);
        //table1.updateCell(1, 1, Color.YELLOW);
       
        
        setOpaque(false);
        initData();
        //ReadFile();
        showData();
       
        
    }

    private void initData() {
        //initCardData();
        initNoticeBoard();
       
        //initTableData();
    }
    
   private void showData() {
        
        con = ConnectDatabase.connectdb();
        
        

        try {
            setUserName();
            setDegree();
            displayModules();
            //showTT();
        } catch (SQLException ex) {
            Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    

    /*private void initCardData() {
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("New Student", 5100, 20, icon1));
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card2.setData(new ModelCard("Income", 2000, 60, icon2));
        Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card3.setData(new ModelCard("Expense", 3000, 80, icon3));
        Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUSINESS_CENTER, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card4.setData(new ModelCard("Other Income", 550, 95, icon4));
    }*/

    //add data into NoticeBoard
    private void initNoticeBoard() {
        noticeBoard.addDate("04/10/2021");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(94, 49, 238), "Hidemode", "Now", "Sets the hide mode for the component. If the hide mode has been specified in the This hide mode can be overridden by the component constraint."));
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(218, 49, 238), "Tag", "2h ago", "Tags the component with metadata name that can be used by the layout engine. The tag can be used to explain for the layout manager what the components is showing, such as an OK or Cancel button."));
        noticeBoard.addDate("03/10/2021");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(32, 171, 43), "Further Reading", "12:30 PM", "There are more information to digest regarding MigLayout. The resources are all available at www.migcomponents.com"));
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(50, 93, 215), "Span", "10:30 AM", "Spans the current cell (merges) over a number of cells. Practically this means that this cell and the count number of cells will be treated as one cell and the component can use the space that all these cells have."));
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(27, 188, 204), "Skip ", "9:00 AM", "Skips a number of cells in the flow. This is used to jump over a number of cells before the next free cell is looked for. The skipping is done before this component is put in a cell and thus this cells is affected by it. \"count\" defaults to 1 if not specified."));
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(238, 46, 57), "Push", "7:15 AM", "Makes the row and/or column that the component is residing in grow with \"weight\". This can be used instead of having a \"grow\" keyword in the column/row constraints."));
        noticeBoard.scrollToTop();
    }
    
   
    
    
    
    public void setUserName() throws SQLException {
        String query = "SELECT fullname FROM logintable WHERE matrix_number = '" + lf.getMatrixNo() + "'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("fullname");
            username.setText(name + " ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
   
     
     private void setDegree() throws SQLException {
        String query = "SELECT stdnt_type FROM logintable WHERE matrix_number = '" + lf.getMatrixNo() + "'";
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            
            int STDNT_TYPE = rs.getInt("STDNT_TYPE");
            //String STDNT_TYPE = rs.getString("stdnt_type");
   
           
           switch(STDNT_TYPE) {
               
                   
                case 1:
                    output = "Bachelor of Computer Science (Computer Systems Networking)";
                    break;
                case 2:
                    output = "Bachelor of Computer Science (Artificial Intelligence)";
                    break;
                case 3:
                    output = "Bachelor of Computer Science (Information Systems)";
                    break;
                case 4:
                    output = "Bachelor of Computer Science (Software Engineering)";
                    break;
                case 5:
                    output = "Bachelor of Computer Science (Data Science)";
                    break;
                case 6:
                    output = "Bachelor of Computer Science (Multimedia)";
                    break;
                   
                
                default:
                    output= "";
               
            }
           
           
           
           id.setText(output);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    public void displayModules(){
        String dm = "SELECT * FROM REGISTEREDMODULES where USERNAME='"+lf.getMatrixNo()+"'";   
        
        try {
            ps = con.prepareStatement(dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");
                String OCC = rs.getString("OCC");
                String DAY = rs.getString("DAY");
                String TIMESTART = rs.getString("TIMESTART");
                String TIMEEND = rs.getString("TIMEEND");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String ELEMENT =MODULES+"\n("+ACTIVITY.substring(0, 3)+")";
                int day = 0;
                
                if(DAY.equals("MONDAY")){
                    day =  1;
                } else if(DAY.equals("TUESDAY")){
                    day =  2;
                } else if(DAY.equals("WEDNESDAY")){
                    day =  3;
                } else if(DAY.equals("THURSDAY")){
                    day =  4;
                } else if(DAY.equals("FRIDAY")){
                    day =  5;
                }
                
                int time1 = Integer.parseInt(TIMESTART.substring(0,2));
                int time2 = Integer.parseInt(TIMEEND.substring(0,2));
                int time = 0;
                int duration = time2 - time1;
                if(duration<0){
                    duration =  duration + 12;
                }
                if(time1 >=8){
                    time = Math.abs(time1-7);
                } else if(time1 <=7){
                    time =Math.abs(time1+5);
                } 
                
                if(duration==1){
                    table1.setValueAt((Object)ELEMENT, time-1, day);
                    
                } else if(duration==2){
                    table1.setValueAt((Object)ELEMENT, time, day);
                    table1.setValueAt((Object)ELEMENT, time-1, day);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
     }
    
    
    
       

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        noticeBoard = new com.tba.noticeboard.NoticeBoard();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new com.tba.swing.table.TableNew(){

            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){

                Component c = super.prepareRenderer(renderer, rowIndex, columnIndex);

                if(columnIndex==0){
                    c.setBackground(new Color(0xEFF3F9));
                }

                return c;
            }

        }
        ;
        jPanel3 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        imageAvatar2 = new com.tba.swing.ImageAvatar();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setText("Notice Board");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 105, 105));
        jLabel3.setText("Simple Miglayout API Doc");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 257, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(noticeBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(noticeBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setText("My Timetable");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8:00 - 9:00", "", null, null, null, null},
                {"9:00 - 10:00", null, null, null, null, null},
                {"10:00 - 11:00", null, null, null, null, null},
                {"11:00 - 12:00", null, null, null, null, null},
                {"12:00 -1:00", null, null, null, null, null},
                {"1:00 - 2:00", null, null, null, null, null},
                {"2:00 - 3:00", null, null, null, null, null},
                {"3:00 - 4:00", null, null, null, null, null},
                {"4:00 - 5:00", null, null, null, null, null},
                {"5:00 - 6:00", null, null, null, null, null},
                {"6:00 - 7:00", null, null, null, null, null},
                {"7:00 - 8:00", null, null, null, null, null}
            },
            new String [] {
                "", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jPanel3.setBackground(new java.awt.Color(58, 83, 155));

        username.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Student Name");

        id.setBackground(new java.awt.Color(244, 247, 252));
        id.setForeground(new java.awt.Color(244, 247, 252));
        id.setText("Email");

        imageAvatar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tba/icon/profileidnewnew.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username)
                    .addComponent(id))
                .addContainerGap(732, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id;
    private com.tba.swing.ImageAvatar imageAvatar2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private com.tba.noticeboard.NoticeBoard noticeBoard;
    private com.tba.swing.table.TableNew table1;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
