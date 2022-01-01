/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import com.raven.dialog.EditMessage;
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

/**
 *
 * @author user
 */
public class RowPopUp extends JPopupMenu {
    
     private JTable table;
    
   

    public RowPopUp(JTable table) {
        
       //create popup items
       JMenuItem edit = new JMenuItem("Edit");
       JMenuItem delete = new JMenuItem("Delete");
       
       //when edit clicked
       edit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              EditMessage msg = new EditMessage(null,true);
              msg.showAlert();
           }
       });
       
       //when delete clicked
       delete.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
               int modelRow = Integer.valueOf( e.getActionCommand() );
               ((DefaultTableModel)table.getModel()).removeRow(modelRow);
           }
       });
      
       add(edit);
       add(new JSeparator());
       add(delete);
    }
    
  
        
    
    
}
