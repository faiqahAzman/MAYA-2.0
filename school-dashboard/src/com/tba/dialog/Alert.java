
package com.tba.dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;


public class Alert extends JDialog {
    
     private Background content;
    private Animator animator;
    
   
    private boolean show;

    public Alert(Frame fram, boolean modal) {
        super(fram, modal);
        init();
    }

    private void init() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        content = new Background();
        content.setBackground(Color.WHITE);
        setContentPane(content);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void end() {
                if (show == false) {
                    //  Close dialog
                    dispose();
                }
            }
            
             

        
       
    };
        animator = new Animator(500, target);
        animator.setResolution(0);
        
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                closeAlert();
            }
        });

        
    }
    
    

    public void showAlert() {
       
        if (animator.isRunning()) {
            animator.stop();
        }
        show = true;
        animator.setDuration(500);
        animator.start();
        
        //center the dialog
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void closeAlert() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.setDuration(400);
        animator.start();
    }

    
    private class Background extends JComponent {

        @Override
        public void paint(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paint(grphcs);
        }
    }
    
    
}
