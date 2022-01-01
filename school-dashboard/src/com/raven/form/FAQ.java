
package com.raven.form;

import com.raven.model.ModelCard;
import com.raven.model.ModelStudentType;




public class FAQ extends javax.swing.JPanel {
    
   
    private ModelStudentType type = new ModelStudentType();
    
    public FAQ() {
        initComponents();
        if (type.checkType()<=0)
            initCardStaff();
        else 
            initCardStudent();
        
        setOpaque(false);
       
        
        
    }
    
    private void initCardStudent() {
        //Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        
        //Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIoZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("How do I register my modules?",   "1. Please search your designated modules in the Search tab under Courses."
                + "\n2.Add the necessary modules for your course. \n3.Press Submit to confirm your registration" 
                                     , 60));
        card2.setData(new ModelCard("I accidentally registered the wrong module",   "Please report your problem to the admin through the Enquiry tab. " 
                                     + "\nAny issues pertaining to registration must be consulted with the admin", 60));
        card3.setData(new ModelCard("How do I view my registered modules?",   "Head over to the Registered tab under Courses to see your registered modules " 
                                     , 60));
        //card1.setData(new ModelCard("Who made this cool app", "a team of cool people from FSKTM", 60));
        //Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        //card3.setData(new ModelCard("Expense", 3000, 80, icon3));
        //Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUSINESS_CENTER, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        //card4.setData(new ModelCard("Other Income", 550, 95, icon4));
    }
    
     private void initCardStaff() {
        
        card3.setData(new ModelCard("How do I edit my modules?",   "Lecturers are only allowed to increase capacity for occurence of each module taught." 
                                     + "\nIf you would like to modify your module tutor, change day and time of lecture and etc., \nplease report to the admin using"
                                     + " Enquiry tab", 60));
        card2.setData(new ModelCard("I would like to add a module into my teaching schedule",   "Please report your problem to the admin through the Enquiry tab. " 
                                     + "\nAny issues pertaining to modules must be consulted with the admin", 60));
        card1.setData(new ModelCard("How do I view my modules?",   "Head over to the Registered tab under Courses to see your registered modules " 
                                     , 60));
       
    }
   

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1058, 748));

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setText("Frequently Asked Questions");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("bg");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
