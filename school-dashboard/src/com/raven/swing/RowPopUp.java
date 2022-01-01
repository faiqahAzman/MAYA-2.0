/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import com.raven.dialog.EditMessage;
import com.raven.form.MainForm;
import com.raven.main.Main;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.*;
import loginpage.LoginForm1;

/**
 *
 * @author user
 */
public class RowPopUp extends JPopupMenu {
    
     private JTable table;
     private Main main;
     private JMenuItem logout = new JMenuItem("Logout");
   
  

    public RowPopUp() {
        
        
        
       
       //create popup items
       String username = new String("Full Name"); 
       String email = new String("email"); 
       JMenuItem logout = new JMenuItem("Logout");
       
       //when edit clicked
       
      /*logout.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              
           }
       });*/
       
       //when delete clicked
       
      
       add(username);
       add(email);
       add(new JSeparator());
       add(logout);
       
      
       
       
    }
    
    public void logOut(ActionListener event){
        
        String username = new String("Full Name"); 
       String email = new String("email"); 
       JMenuItem logout = new JMenuItem("Logout");
       
       //when edit clicked
       
      logout.addActionListener(event);
       
       //when delete clicked
       
      
       add(username);
       add(email);
       add(new JSeparator());
       add(logout);
        
        
        
    }
    
    
  
        
    
    
}
