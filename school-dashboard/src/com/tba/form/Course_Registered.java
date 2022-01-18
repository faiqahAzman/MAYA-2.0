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
import java.util.LinkedList;
import java.util.List;
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
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import loginpage.ConnectDatabase;
import loginpage.LoginForm1;

public class Course_Registered extends javax.swing.JPanel {

    static int credithour = 0;
    private Card card;
    Button button = new Button();
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel tblModel;
    private ModelStudentType type = new ModelStudentType();
    LoginForm1 lf = new LoginForm1();
    static String moduleCode = "";
    static String credit = "";
    static String act = "";
    static int hours = -1;
    int stype = lf.getStudent_type();

    public Course_Registered() {

        con = ConnectDatabase.connectdb();
        initComponents();
        setVisible(true);
        setOpaque(false);
        

        table1.fixTable(jScrollPane1);
        tblModel = (DefaultTableModel) table1.getModel();
        comboPicker();

        if (type.checkType() >= 1) {
            init();

        } else {
            initStaff();
            txtCred.setVisible(false);

        }

    }
    //features for students
    private void init() {

        showButton();
        getModules();
        setCredits();
        txtCred.setText("TOTAL CREDIT HOUR: "+(credithour));

    }
    
    //features for staff
    private void initStaff() {

        getModuleStaff();
        showButton();
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
    
    //feature for lecturer to edit capacity
    private void showButton() {

        Action edit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                
                JTable table = (JTable) e.getSource();
                int selectedRowIndex = table.getSelectedRow();
                String cap = table.getValueAt(selectedRowIndex, 6).toString();

                
                int scap = Integer.parseInt(cap);
                int newTG = Integer.parseInt(JOptionPane.showInputDialog(null, "Target Number", scap));
                boolean scap_limit = true;

                if (newTG < scap) {
                    scap_limit = false;
                    JOptionPane.showMessageDialog(null, "STUDENT LIMIT IS LOWER THAN REIGSTERED STUDENTS!");
                } else {
                    updateTG(newTG);
                    table.setValueAt(newTG, selectedRowIndex, 6);

                }

                
            }
        };

        if (type.checkType() == 0) {
            ButtonColumn buttonColumn2 = new ButtonColumn(table1, edit, 8);
        }

    }
    
    //feature to display modules based on user student
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

                String tbData[] = {MODULES, OCC, ACTIVITY, DAY, TS, TE};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //feature to display modules based on user staff
    private void getModuleStaff() {

        String q1 = "SELECT * FROM TIMETABLE_MODULES JOIN LOGINTABLE ON LOGINTABLE.matrix_number = '" + lf.getMatrixNo() + "' AND TIMETABLE_MODULES.lecturer=LOGINTABLE.FULLNAME";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULES");
                String OCC = rs.getString("OCCURENCE");
                String ACTIVITY = rs.getString("ACTIVITYTYPE");
                String DAY = rs.getString("DAY");
                String TS = rs.getString("TIMESTART");
                String TE = rs.getString("TIMEEND");
                String SC = rs.getString("STUDENTCAP");
                String AC = rs.getString("ACTUAL");

                String tbData[] = {MODULES, OCC, ACTIVITY, DAY, TS, TE, SC, AC};
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //method to update capacity
    private void updateTG(int TG) {

        try {
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            int rows = tb.getRowCount();
           
            String modules = tb.getValueAt(table1.getSelectedRow(), 0).toString();
            String occurence = tb.getValueAt(table1.getSelectedRow(), 1).toString();
            String act = tb.getValueAt(table1.getSelectedRow(), 2).toString();

           
            String query = "UPDATE TIMETABLE_MODULES" + " SET STUDENTCAP = " + TG + " WHERE MODULES ='" + modules + "'AND "
                    + "OCCURENCE =" + occurence + " AND ACTIVITYTYPE='" + act + "'";
            ps = con.prepareStatement(query);

           
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

  

    public static int getCredithour() {
        return credithour;
    }

    public static void setCredithour(int credithour) {
        Course_Registered.credithour = credithour;
    }
    
    
    
    //method to set total credit
    public void setCredits(){
        int totalcred = 0;
        try{
          
            String getCredit = "SELECT * FROM REGISTEREDMODULES WHERE USERNAME='"+lf.getMatrixNo()+"'";
            ps = con.prepareStatement(getCredit);
            rs = ps.executeQuery();
            
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
                
            }
            setCredithour(totalcred);
            } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //method to show modules in combo box
    private void comboPicker() {

        String q1 = "SELECT * FROM REGISTEREDMODULES WHERE REGISTEREDMODULES.username = '" + lf.getMatrixNo() + "'";

        try {

            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            while (rs.next()) {
                String MODULES = rs.getString("MODULE");

                if (MODULES.equals("WIA2001/WIB2001")) {
                    if (lf.getCsit() == 1) {
                        MODULES = "WIA2001";
                    } else if (lf.getCsit() == 2) {
                        MODULES = "WIB2001";
                    }
                }

                modulebox.addItem(MODULES);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    //method to filter out the jtable based on the combo box
    private void filter() {

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblModel);
        table1.setRowSorter(sorter);
        List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();

        modulebox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (filters.isEmpty()) {
                    filters.add(RowFilter.regexFilter(modulebox.getSelectedItem().toString()));
                } else {
                    filters.set(0, RowFilter.regexFilter(modulebox.getSelectedItem().toString()));
                }
                // Apply filters
                sorter.setRowFilter(RowFilter.andFilter(filters));
            }
        });

        occbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                if (filters.size() < 2) {
                    filters.add(RowFilter.regexFilter(occbox.getSelectedItem().toString(), 1));
                } else {
                    filters.set(1, RowFilter.regexFilter(occbox.getSelectedItem().toString(), 1));
                }
                
                // Apply filters

                sorter.setRowFilter(RowFilter.andFilter(filters));
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tba.swing.table.TableNew();
        txtCred = new javax.swing.JLabel();
        modulebox = new javax.swing.JComboBox<>();
        occbox = new javax.swing.JComboBox<>();

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("  My Modules");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modules", "Occurence", "Activity", "Day", "Time Start", "Time End", "Capacity", "Actual", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        txtCred.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtCred.setText("TOTAL CREDIT HOUR:");

        modulebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        modulebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                moduleboxItemStateChanged(evt);
            }
        });

        occbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtCred, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(occbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modulebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
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

    private void moduleboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_moduleboxItemStateChanged
        filter();
    }//GEN-LAST:event_moduleboxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> modulebox;
    private javax.swing.JComboBox<String> occbox;
    private com.tba.swing.table.TableNew table1;
    private javax.swing.JLabel txtCred;
    // End of variables declaration//GEN-END:variables
}
