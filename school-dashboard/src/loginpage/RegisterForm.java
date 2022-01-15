/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package loginpage;

import java.awt.CardLayout;
import java.sql.*;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author T_T
 */
public class RegisterForm extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CardLayout cardLayout2;

    public RegisterForm() {
        initComponents();
        emptyFieldLabel.setVisible(false);
        registerSuccessLabel.setVisible(false);
        duplicateMatrixLabel.setVisible(false);

        cardLayout2 = (CardLayout) (specialPanel.getLayout());

        setVisible(true);
        con = ConnectDatabase.connectdb();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StudentRegisterCard = new javax.swing.JPanel();
        registerPanel = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        passwordRegisterField = new javax.swing.JPasswordField();
        degreeLabel = new javax.swing.JLabel();
        matrixNoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        specializationLabel = new javax.swing.JLabel();
        muetBandLabel = new javax.swing.JLabel();
        registerStudBttn = new javax.swing.JButton();
        degreeMenu = new javax.swing.JComboBox<>();
        muetBandMenu = new javax.swing.JComboBox<>();
        specialPanel = new javax.swing.JPanel();
        CSPanel = new javax.swing.JPanel();
        specialCSMenu = new javax.swing.JComboBox<>();
        ITPanel = new javax.swing.JPanel();
        specialITMenu = new javax.swing.JComboBox<>();
        messagePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emptyFieldLabel = new javax.swing.JLabel();
        registerSuccessLabel = new javax.swing.JLabel();
        returnToLoginLabel = new javax.swing.JLabel();
        duplicateMatrixLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(392, 392));
        setUndecorated(true);

        StudentRegisterCard.setPreferredSize(new java.awt.Dimension(1366, 783));
        StudentRegisterCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registerPanel.setBackground(new java.awt.Color(58, 83, 155));
        registerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 247, 252), 2));
        registerPanel.setPreferredSize(new java.awt.Dimension(420, 540));

        exit.setBackground(new java.awt.Color(80, 101, 177));
        exit.setFont(new java.awt.Font("Readex Pro Medium", 1, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(244, 247, 252));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(80, 101, 177));
        jLabel1.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(244, 247, 252));
        jLabel1.setText("FULL NAME :");

        fullNameField.setBackground(new java.awt.Color(58, 83, 155));
        fullNameField.setFont(new java.awt.Font("Readex Pro Light", 0, 18)); // NOI18N
        fullNameField.setForeground(new java.awt.Color(244, 247, 252));
        fullNameField.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(244, 247, 252));

        jLabel2.setBackground(new java.awt.Color(80, 101, 177));
        jLabel2.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(244, 247, 252));
        jLabel2.setText("PASSWORD :");

        jSeparator2.setForeground(new java.awt.Color(244, 247, 252));

        passwordRegisterField.setBackground(new java.awt.Color(58, 83, 155));
        passwordRegisterField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passwordRegisterField.setForeground(new java.awt.Color(244, 247, 252));
        passwordRegisterField.setBorder(null);

        degreeLabel.setBackground(new java.awt.Color(80, 101, 177));
        degreeLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        degreeLabel.setForeground(new java.awt.Color(244, 247, 252));
        degreeLabel.setText("BACHELOR'S DEGREE :");
        degreeLabel.setToolTipText("");

        matrixNoField.setBackground(new java.awt.Color(58, 83, 155));
        matrixNoField.setFont(new java.awt.Font("Readex Pro Light", 0, 18)); // NOI18N
        matrixNoField.setForeground(new java.awt.Color(244, 247, 252));
        matrixNoField.setBorder(null);

        jLabel3.setBackground(new java.awt.Color(80, 101, 177));
        jLabel3.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(244, 247, 252));
        jLabel3.setText("MATRIX NO :");

        jSeparator4.setForeground(new java.awt.Color(244, 247, 252));

        specializationLabel.setBackground(new java.awt.Color(80, 101, 177));
        specializationLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        specializationLabel.setForeground(new java.awt.Color(244, 247, 252));
        specializationLabel.setText("SPECIALIZATION :");
        specializationLabel.setToolTipText("");

        muetBandLabel.setBackground(new java.awt.Color(80, 101, 177));
        muetBandLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        muetBandLabel.setForeground(new java.awt.Color(244, 247, 252));
        muetBandLabel.setText("MUET BAND :");
        muetBandLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                muetBandLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                muetBandLabelMouseExited(evt);
            }
        });

        registerStudBttn.setBackground(new java.awt.Color(80, 101, 177));
        registerStudBttn.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        registerStudBttn.setForeground(new java.awt.Color(244, 247, 252));
        registerStudBttn.setText("REGISTER");
        registerStudBttn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 247, 252)));
        registerStudBttn.setContentAreaFilled(false);
        registerStudBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerStudBttn.setFocusPainted(false);
        registerStudBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerStudBttnMouseClicked(evt);
            }
        });

        degreeMenu.setBackground(new java.awt.Color(244, 247, 252));
        degreeMenu.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        degreeMenu.setForeground(new java.awt.Color(58, 83, 155));
        degreeMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer Science", "Information Technology" }));
        degreeMenu.setBorder(null);
        degreeMenu.setName("box"); // NOI18N
        degreeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                degreeMenuActionPerformed(evt);
            }
        });

        muetBandMenu.setBackground(new java.awt.Color(244, 247, 252));
        muetBandMenu.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        muetBandMenu.setForeground(new java.awt.Color(58, 83, 155));
        muetBandMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        muetBandMenu.setBorder(null);
        muetBandMenu.setName("box"); // NOI18N

        specialPanel.setLayout(new java.awt.CardLayout());

        CSPanel.setBackground(new java.awt.Color(58, 83, 155));

        specialCSMenu.setBackground(new java.awt.Color(244, 247, 252));
        specialCSMenu.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        specialCSMenu.setForeground(new java.awt.Color(58, 83, 155));
        specialCSMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSN", "AI", "SE", "IS", "DS" }));
        specialCSMenu.setBorder(null);
        specialCSMenu.setName("box"); // NOI18N
        specialCSMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialCSMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CSPanelLayout = new javax.swing.GroupLayout(CSPanel);
        CSPanel.setLayout(CSPanelLayout);
        CSPanelLayout.setHorizontalGroup(
            CSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(specialCSMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        CSPanelLayout.setVerticalGroup(
            CSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CSPanelLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(specialCSMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        specialPanel.add(CSPanel, "CSPanel");

        ITPanel.setBackground(new java.awt.Color(58, 83, 155));

        specialITMenu.setBackground(new java.awt.Color(244, 247, 252));
        specialITMenu.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        specialITMenu.setForeground(new java.awt.Color(58, 83, 155));
        specialITMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MM" }));
        specialITMenu.setBorder(null);
        specialITMenu.setName("box"); // NOI18N
        specialITMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialITMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ITPanelLayout = new javax.swing.GroupLayout(ITPanel);
        ITPanel.setLayout(ITPanelLayout);
        ITPanelLayout.setHorizontalGroup(
            ITPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ITPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(specialITMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        ITPanelLayout.setVerticalGroup(
            ITPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ITPanelLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(specialITMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        specialPanel.add(ITPanel, "ITPanel");

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator4)
                                .addGroup(registerPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordRegisterField)
                                        .addComponent(matrixNoField)
                                        .addGroup(registerPanelLayout.createSequentialGroup()
                                            .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(registerPanelLayout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addComponent(degreeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addComponent(muetBandLabel)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(degreeLabel)
                                .addComponent(specializationLabel)
                                .addComponent(specialPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(registerStudBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(muetBandMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit)
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(fullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(matrixNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(passwordRegisterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(degreeLabel)
                .addGap(18, 18, 18)
                .addComponent(degreeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(specializationLabel)
                .addGap(18, 18, 18)
                .addComponent(specialPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(muetBandLabel)
                .addGap(28, 28, 28)
                .addComponent(muetBandMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(registerStudBttn)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        StudentRegisterCard.add(registerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 690, 783));

        messagePanel.setBackground(new java.awt.Color(244, 247, 252));
        messagePanel.setPreferredSize(new java.awt.Dimension(420, 540));

        jLabel5.setBackground(new java.awt.Color(96, 111, 137));
        jLabel5.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(96, 111, 137));
        jLabel5.setText("for more information");

        jLabel6.setBackground(new java.awt.Color(80, 101, 177));
        jLabel6.setFont(new java.awt.Font("Readex Pro Medium", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(58, 83, 155));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText(" Register Your Details");

        jLabel7.setBackground(new java.awt.Color(96, 111, 137));
        jLabel7.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(96, 111, 137));
        jLabel7.setText("Tip: Some labels can be hovered over");

        emptyFieldLabel.setBackground(new java.awt.Color(96, 111, 137));
        emptyFieldLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        emptyFieldLabel.setForeground(new java.awt.Color(145, 38, 67));
        emptyFieldLabel.setText("All fields must be filled for registration");

        registerSuccessLabel.setBackground(new java.awt.Color(96, 111, 137));
        registerSuccessLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        registerSuccessLabel.setForeground(new java.awt.Color(58, 83, 155));
        registerSuccessLabel.setText("You've successfully registered!");

        returnToLoginLabel.setBackground(new java.awt.Color(96, 111, 137));
        returnToLoginLabel.setFont(new java.awt.Font("Readex Pro Medium", 2, 14)); // NOI18N
        returnToLoginLabel.setForeground(new java.awt.Color(96, 111, 137));
        returnToLoginLabel.setText("<-- Login Page");
        returnToLoginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnToLoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnToLoginLabelMouseClicked(evt);
            }
        });

        duplicateMatrixLabel.setBackground(new java.awt.Color(96, 111, 137));
        duplicateMatrixLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        duplicateMatrixLabel.setForeground(new java.awt.Color(145, 38, 67));
        duplicateMatrixLabel.setText("The entered matrix no. has already been registered");

        jSeparator3.setForeground(new java.awt.Color(58, 83, 155));

        javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(messagePanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel5))
                            .addComponent(jLabel7)
                            .addComponent(registerSuccessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(duplicateMatrixLabel)
                            .addComponent(emptyFieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(messagePanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(returnToLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(121, 121, 121)
                .addComponent(emptyFieldLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(duplicateMatrixLabel)
                .addGap(18, 18, 18)
                .addComponent(registerSuccessLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addComponent(returnToLoginLabel)
                .addGap(49, 49, 49))
        );

        StudentRegisterCard.add(messagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 683, 783));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(StudentRegisterCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(StudentRegisterCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void registerStudBttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerStudBttnMouseClicked

        try {
            //check if entered matrix no. is already registered or not
            // it's not placed in the else if block cuz i want to pass it in the else if condition
            String q1 = "SELECT matrix_number FROM logintable where matrix_number = '" + matrixNoField.getText().toLowerCase() + "'";
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();

            //checking if any of the fields are empty
            if (fullNameField.getText().trim().length() == 0
                    || String.valueOf(passwordRegisterField.getPassword()).trim().length() == 0
                    || matrixNoField.getText().trim().length() == 0) {

                emptyFieldLabel.setVisible(true);

                //see if there are any rows (duplicate matrix no.) in rs
            } else if (rs.next()) {
                emptyFieldLabel.setVisible(false);
                duplicateMatrixLabel.setVisible(true);

            } else {

                String q2 = "INSERT INTO logintable values(?,?,?,?,?,?)";

                String fullName = fullNameField.getText();
                String password = String.valueOf(passwordRegisterField.getPassword());
                String matrixNo = matrixNoField.getText();
                String degree = degreeMenu.getSelectedItem().toString(); //converting Object -> String 

                String special = ""; // so that switch (special) dosn't throw error;
                //declaring special
                if (degreeMenu.getSelectedItem().toString().equals("Computer Science")) {
                    special = specialCSMenu.getSelectedItem().toString();
                } else if (degreeMenu.getSelectedItem().toString().equals("Information Technology")) {
                    special = specialITMenu.getSelectedItem().toString();
                } else {
                    special = "error";
                }

                int muetBand = Integer.parseInt(muetBandMenu.getSelectedItem().toString());

                int degreeNum;
                switch (degree) {
                    case "Computer Science": 
                        degreeNum = 1;
                        break;
                    case "Information Technology":
                        degreeNum = 2;
                        break;
                    default:
                        degreeNum = 0;
                };

                int specialNum; 
                switch (special) {
                    case "CSN":
                        specialNum= 1;
                        break;
                    case "AI":
                        specialNum=2;
                        break;
                    case "IS" :
                        specialNum=3;
                        break;
                    case "SE":
                        specialNum=4;
                        break;
                    case "DS":
                        specialNum=5;
                        break;
                    case "MM":
                        specialNum=6;
                        break;
                    default:
                        specialNum=0;
                };

                ps = con.prepareStatement(q2);
                ps.setString(1, matrixNo);
                ps.setString(2, password);
                ps.setInt(3, specialNum);
                ps.setInt(4, muetBand);
                ps.setString(5, fullName);
                ps.setInt(6, degreeNum);
                ps.executeUpdate();

                emptyFieldLabel.setVisible(false);
                duplicateMatrixLabel.setVisible(false);
                registerSuccessLabel.setVisible(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registerStudBttnMouseClicked

    private void returnToLoginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnToLoginLabelMouseClicked
        // TODO add your handling code here:

        LoginForm1 lf = new LoginForm1();
        lf.setVisible(true);
        dispose();
    }//GEN-LAST:event_returnToLoginLabelMouseClicked

    private void muetBandLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muetBandLabelMouseEntered
        // TODO add your handling code here:
        muetBandLabel.setForeground(new java.awt.Color(228, 88, 98));

        muetBandLabel.setToolTipText("<html>"
                + "Select the band of 1 - 6"
                + "<br> 1 = lowest </br>"
                + "<br> 6 = highest </br>"
                + "</html>");
        UIManager.put("ToolTip.font", new Font("Readex Pro Light", 0, 11));
    }//GEN-LAST:event_muetBandLabelMouseEntered

    private void muetBandLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muetBandLabelMouseExited
        // TODO add your handling code here:
        muetBandLabel.setForeground(new java.awt.Color(244, 247, 252));
    }//GEN-LAST:event_muetBandLabelMouseExited

    private void specialCSMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialCSMenuActionPerformed
        // TODO add your handling code here:
        //Information Technology

    }//GEN-LAST:event_specialCSMenuActionPerformed

    private void specialITMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialITMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specialITMenuActionPerformed

    private void degreeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_degreeMenuActionPerformed
        // TODO add your handling code here:
        if (degreeMenu.getSelectedItem().toString().equals("Computer Science")) {
            cardLayout2.show(specialPanel, "CSPanel");
        }
        if (degreeMenu.getSelectedItem().toString().equals("Information Technology")) {
            cardLayout2.show(specialPanel, "ITPanel");
        }
    }//GEN-LAST:event_degreeMenuActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CSPanel;
    private javax.swing.JPanel ITPanel;
    private javax.swing.JPanel StudentRegisterCard;
    private javax.swing.JLabel degreeLabel;
    private javax.swing.JComboBox<String> degreeMenu;
    private javax.swing.JLabel duplicateMatrixLabel;
    private javax.swing.JLabel emptyFieldLabel;
    private javax.swing.JLabel exit;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField matrixNoField;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JLabel muetBandLabel;
    private javax.swing.JComboBox<String> muetBandMenu;
    private javax.swing.JPasswordField passwordRegisterField;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JButton registerStudBttn;
    private javax.swing.JLabel registerSuccessLabel;
    private javax.swing.JLabel returnToLoginLabel;
    private javax.swing.JComboBox<String> specialCSMenu;
    private javax.swing.JComboBox<String> specialITMenu;
    private javax.swing.JPanel specialPanel;
    private javax.swing.JLabel specializationLabel;
    // End of variables declaration//GEN-END:variables
}
