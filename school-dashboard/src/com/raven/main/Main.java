package com.raven.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.form.Admin;
import com.raven.form.Course_Registered;
import com.raven.form.Course_Search;
import com.raven.form.Enquiry;
import com.raven.form.FAQ;

import com.raven.form.Form_Home;
import com.raven.form.MainForm;
import com.raven.form.staff.Staff_Registered;
import com.raven.model.ModelStudentType;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.RowPopUp;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import loginpage.LoginForm1;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private FAQ faq;
    private RowPopUp pop;
    private ModelStudentType type;
    
    public Main() {
        initComponents();
        init();
        
        
        
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        pop = new RowPopUp();
        type = new ModelStudentType();
       
        
        
        header.logOut(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new LoginForm1().setVisible(true);
            }
        });
       
        
        
         header.minimizeEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                setExtendedState(Main.ICONIFIED);
                
            }
        });
         
        header.maximizeEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                if(getExtendedState()!= Main.MAXIMIZED_BOTH) {
                    setExtendedState(Main.MAXIMIZED_BOTH);
                }else {
                    setExtendedState(Main.NORMAL);
                }
            }
        });
        
        
         
        
        
       
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                
                if (menuIndex == 0) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Home());
                    } else if (subMenuIndex == 1) {
                       // main.showForm(new Form1());
                    }
                    
                    
                }
                
                if (menuIndex == 1) {
                    
                    if (type.checkType()==-1) {
                       if (subMenuIndex == 0) 
                        main.showForm(new Course_Search());
                    
                    }else{
                        if (subMenuIndex == 0) {
                        main.showForm(new Course_Registered());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new Course_Search());
                    }  
                        
                    }
                    
                    
                    
                    
                }
                
                if (menuIndex == 2) {
                    if (type.checkType()==-1) {
                        if (subMenuIndex == 0) {
                        main.showForm(new Admin());
                    } 
                    else if (subMenuIndex == 1) {
                        main.showForm(new Staff_Registered());
                    }
                    else if (subMenuIndex == 2) {
                        main.showForm(new Staff_Registered());
                    }
                    } else{
                    if (subMenuIndex == 0) {
                        main.showForm(new Enquiry());
                    } else if (subMenuIndex == 1) {
                       // main.showForm(new Course_Search());
                    } 
                    }
                    
                    
                }
                
                
                if (menuIndex == 3) {
                    if (subMenuIndex == 0) {
                        main.showForm(new FAQ());
                    } 
                }
                
              
                
                
            }

            @Override
            public boolean menuPressed(MenuItem aThis, boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        if(type.checkType()== -1){
            menu.initMenuItemAdmin();
        } else
            menu.initMenuItem();
        
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
                
            }
        });
       
        
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new Form_Home());
        
      
        
        
        
    }
    
    private void showPopUp() {
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(244, 247, 252));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm1().setVisible(true);
                //new Main().setVisible(true);
                
                
                
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
