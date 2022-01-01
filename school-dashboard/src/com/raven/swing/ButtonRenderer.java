/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class ButtonRenderer extends Button implements TableCellRenderer{
    
    private Button button;
    
    public ButtonRenderer() {
    
    setOpaque(true);
   
      
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
      
     
    } else {
      setForeground(table.getForeground());
      setBackground(Color.WHITE);
    }
    setText((value == null) ? "..." : value.toString());
    setFont(new Font("Tahoma", Font.BOLD, 14));
      
   
    
    return this;
  }
}


  
  
 