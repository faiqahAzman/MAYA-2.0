/***************************************************************************************
*    Title: raven-project
*    Author: DJ-Raven
*    Date: 2021
*    Code version: 1.0
*    Availability: https://github.com/DJ-Raven/raven-project
*
***************************************************************************************/
package com.tba.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;

/**
 *
 * @author user
 */
public class TextField extends JTextField{
    
    private final Animator animator;
    private boolean animateHintText = true;
    private float location;
    private boolean show;
    private boolean mouseOver = false;
    private String labelText = "Label";

    public TextField() {
        
        
        setBackground(new Color(0xF4F7FC));
        super.setBorder(new EmptyBorder(10,3,3,3));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver= false;
                repaint();
            }
            
            
        });
        animator = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (mouseOver) {
            g2.setColor(new Color(0x5065B1));
        }else   {
            g2.setColor(new Color(150,150,150,90));
        }
        g2.fillRect(2, height - 1, width-4, 1);
        
        g2.dispose();
    }
    
    
    
    
    
   
        
    

    
    
    
    

    
    }
        
       
    
    
  
