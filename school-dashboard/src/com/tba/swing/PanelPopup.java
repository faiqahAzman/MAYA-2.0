/***************************************************************************************
*    Title: java-swing-school-management-dashboard
*    Author: DJ-Raven
*    Date: 2021
*    Code version: 1.0
*    Availability: https://github.com/DJ-Raven/java-swing-school-management-dashboard.git 
*
***************************************************************************************/
package com.tba.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPopup extends JPanel {

    public PanelPopup() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(46, 59, 82));
        g2.fillRect(8, 0, getSize().width - 8, getSize().height);
        int x[] = {0, 10, 10};
        int y[] = {20, 13, 27};
        g2.fillPolygon(x, y, x.length);
        super.paintComponent(grphcs);
    }
}
