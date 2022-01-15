package loginpage;

import com.tba.main.Main;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Faris Faiz
 */
public class LoginForm1 extends javax.swing.JFrame {

    Connection con = ConnectDatabase.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    static String matrixNo = "PLACEHOLDER";
    static int student_type;
    static String matrixNumber;
    static int muet_band;
    static int csit;

    public static int getCsit() {
        return csit;
    }

    public static void setCsit(int csit) {
        LoginForm1.csit = csit;
    }

    public static int getMuet_band() {
        return muet_band;
    }

    public static void setMuet_band(int muet_band) {
        LoginForm1.muet_band = muet_band;
    }
    
  

    public static int getStudent_type() {
        return student_type;
    }

    public static void setStudent_type(int student_type) {
        LoginForm1.student_type = student_type;
    }

    //UI stuff
    public void setMatrixNo(String inputtedMatrixNo) {
        this.matrixNo = inputtedMatrixNo;
    }

    public String getMatrixNo() {
        return matrixNo;
    }

    /**
     * Creates new form LoginForm1
     */
    public LoginForm1() {
        initComponents();
        LoginFailedLabel.setVisible(false);
        setLocationRelativeTo(null);

        ConnectDatabase.connectdb();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightLoginPanel = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        matrixNoField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        resetBttn = new javax.swing.JButton();
        loginBttn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        LoginFailedLabel = new javax.swing.JLabel();
        LeftLoginPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        registerLink = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rightLoginPanel.setBackground(new java.awt.Color(80, 101, 177));
        rightLoginPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 247, 252), 2));
        rightLoginPanel.setForeground(new java.awt.Color(244, 247, 252));

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
        jLabel1.setText("MATRIX NO / STAFF ID :");

        matrixNoField.setBackground(new java.awt.Color(80, 101, 177));
        matrixNoField.setFont(new java.awt.Font("Readex Pro Light", 0, 18)); // NOI18N
        matrixNoField.setForeground(new java.awt.Color(244, 247, 252));
        matrixNoField.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(244, 247, 252));

        jLabel3.setBackground(new java.awt.Color(80, 101, 177));
        jLabel3.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(244, 247, 252));
        jLabel3.setText("PASSWORD :");

        jSeparator2.setForeground(new java.awt.Color(244, 247, 252));

        resetBttn.setBackground(new java.awt.Color(80, 101, 177));
        resetBttn.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        resetBttn.setForeground(new java.awt.Color(244, 247, 252));
        resetBttn.setText("RESET");
        resetBttn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 247, 252)));
        resetBttn.setContentAreaFilled(false);
        resetBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBttn.setFocusPainted(false);
        resetBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBttnMouseClicked(evt);
            }
        });

        loginBttn.setBackground(new java.awt.Color(80, 101, 177));
        loginBttn.setFont(new java.awt.Font("Readex Pro Medium", 0, 14)); // NOI18N
        loginBttn.setForeground(new java.awt.Color(244, 247, 252));
        loginBttn.setText("LOGIN");
        loginBttn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 247, 252)));
        loginBttn.setContentAreaFilled(false);
        loginBttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBttn.setFocusPainted(false);
        loginBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBttnMouseClicked(evt);
            }
        });

        passwordField.setBackground(new java.awt.Color(80, 101, 177));
        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(244, 247, 252));
        passwordField.setBorder(null);

        LoginFailedLabel.setBackground(new java.awt.Color(80, 101, 177));
        LoginFailedLabel.setFont(new java.awt.Font("Readex Pro Medium", 0, 18)); // NOI18N
        LoginFailedLabel.setForeground(new java.awt.Color(114, 30, 53));
        LoginFailedLabel.setText("Wrong Password");

        javax.swing.GroupLayout rightLoginPanelLayout = new javax.swing.GroupLayout(rightLoginPanel);
        rightLoginPanel.setLayout(rightLoginPanelLayout);
        rightLoginPanelLayout.setHorizontalGroup(
            rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightLoginPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLoginPanelLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rightLoginPanelLayout.createSequentialGroup()
                        .addComponent(LoginFailedLabel)
                        .addGap(18, 18, 18)
                        .addComponent(loginBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addComponent(jSeparator2)))
                .addGap(137, 137, 137))
            .addGroup(rightLoginPanelLayout.createSequentialGroup()
                .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightLoginPanelLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)))
                    .addGroup(rightLoginPanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(matrixNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rightLoginPanelLayout.setVerticalGroup(
            rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightLoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit)
                .addGap(179, 179, 179)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(matrixNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rightLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoginFailedLabel))
                .addContainerGap(271, Short.MAX_VALUE))
        );

        getContentPane().add(rightLoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 683, 783));

        LeftLoginPanel.setBackground(new java.awt.Color(244, 247, 252));
        LeftLoginPanel.setForeground(new java.awt.Color(244, 247, 252));

        jLabel4.setBackground(new java.awt.Color(80, 101, 177));
        jLabel4.setFont(new java.awt.Font("Readex Pro Medium", 0, 70)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(80, 101, 177));
        jLabel4.setText("MAYA 2.0");

        jLabel5.setBackground(new java.awt.Color(80, 101, 177));
        jLabel5.setFont(new java.awt.Font("Readex Pro Medium", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(80, 101, 177));
        jLabel5.setText("LOGIN PAGE");

        registerLink.setBackground(new java.awt.Color(80, 101, 177));
        registerLink.setFont(new java.awt.Font("Readex Pro Medium", 2, 16)); // NOI18N
        registerLink.setForeground(new java.awt.Color(96, 111, 137));
        registerLink.setText("Click here if you have not registered as a user ");
        registerLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLinkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLinkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLinkMouseExited(evt);
            }
        });

        javax.swing.GroupLayout LeftLoginPanelLayout = new javax.swing.GroupLayout(LeftLoginPanel);
        LeftLoginPanel.setLayout(LeftLoginPanelLayout);
        LeftLoginPanelLayout.setHorizontalGroup(
            LeftLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLoginPanelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(LeftLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(LeftLoginPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addComponent(registerLink, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        LeftLoginPanelLayout.setVerticalGroup(
            LeftLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLoginPanelLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addComponent(registerLink)
                .addGap(89, 89, 89))
        );

        getContentPane().add(LeftLoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 683, 783));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void resetBttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBttnMouseClicked
        // TODO add your handling code here:
        matrixNoField.setText("");
        passwordField.setText("");
    }//GEN-LAST:event_resetBttnMouseClicked

    private void loginBttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBttnMouseClicked
        LoginFailedLabel.setVisible(false);
        //haven't figured out a way to check for staff id and staff password
        String login = "SELECT * FROM logintable WHERE matrix_number = ? AND password = ?";
        try {
            
            ps = con.prepareStatement(login);
            ps.setString(1, matrixNoField.getText());
            ps.setString(2, String.valueOf(passwordField.getPassword()));
            rs = ps.executeQuery();
            if (rs.next()) {
                
                student_type = rs.getInt("STDNT_TYPE");
                setStudent_type(student_type);
                muet_band = rs.getInt("MUET_BAND");
                setMuet_band(muet_band);
                csit = rs.getInt("CSIT_LOGIN");
                setCsit(csit);
//                JOptionPane.showMessageDialog(null, "Login Successful!");
                String inputtedMatrixNo = matrixNoField.getText();
                setMatrixNo(inputtedMatrixNo);
                dispose();
                //new WelcomePage().setVisible(true);
                new Main().setVisible(true);
            }
            else {
                LoginFailedLabel.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_loginBttnMouseClicked

    private void registerLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLinkMouseClicked
        // TODO add your handling code here:

//        RegisterUserPage rup = new RegisterUserPage();
        RegisterForm rf = new RegisterForm();
        dispose();
    }//GEN-LAST:event_registerLinkMouseClicked

    private void registerLinkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLinkMouseEntered
        // TODO add your handling code here:
        registerLink.setForeground(new java.awt.Color(80, 101, 177));
    }//GEN-LAST:event_registerLinkMouseEntered

    private void registerLinkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLinkMouseExited
        // TODO add your handling code here:

        registerLink.setForeground(new java.awt.Color(96, 111, 137));
    }//GEN-LAST:event_registerLinkMouseExited

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
            java.util.logging.Logger.getLogger(LoginForm1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftLoginPanel;
    private javax.swing.JLabel LoginFailedLabel;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton loginBttn;
    private javax.swing.JTextField matrixNoField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel registerLink;
    private javax.swing.JButton resetBttn;
    private javax.swing.JPanel rightLoginPanel;
    // End of variables declaration//GEN-END:variables
}
