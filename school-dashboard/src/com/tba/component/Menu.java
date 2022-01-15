
/***************************************************************************************
*    Title: java-swing-school-management-dashboard
*    Author: DJ-Raven
*    Date: 2021
*    Code version: 1.0
*    Availability: https://github.com/DJ-Raven/java-swing-school-management-dashboard.git 
*
***************************************************************************************/

package com.tba.component;

import com.tba.event.EventMenu;
import com.tba.event.EventMenuSelected;
import com.tba.event.EventShowPopupMenu;
import com.tba.model.ModelMenu;
import com.tba.model.ModelStudentType;
import com.tba.swing.MenuAnimation;
import com.tba.swing.MenuItem;
import com.tba.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    private ModelStudentType type = new ModelStudentType();

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        sp.setViewportBorder(BorderFactory.createLineBorder(new Color(80, 101, 177)));
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/1.png")), "Dashboard","Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/6.png")), "Courses", "Registered", "Search"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/3.png")), "Enquiries","Form"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/4.png")), "Help","Frequently Asked Question"));
        
        
        
        
        
        
    }
    
    public void initMenuItemAdmin(){
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/1.png")), "Dashboard","Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/6.png")), "Courses", "Search"));
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/5.png")), "Admin Tools","Modify Module","Register Staff","View Registered Students"));
        
    }
    public void initMenuItemStaff(){
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/1.png")), "Dashboard","Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/6.png")), "Courses", "Registered", "Search","View Registered Students"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/3.png")), "Enquiries","Form"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/tba/icon/4.png")), "Help","Frequently Asked Question"));
        
    }
    

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                    
                }
                return false;
                
            } 
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        GradientPaint gra = new GradientPaint(0, 0, new Color(80, 101, 177), getWidth(), 0, new Color(80, 101, 177));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
