/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author user
 */
public class ButtonEditor extends DefaultCellEditor {
    
    private String label;
    private Button button;
    
   
    
    public ButtonEditor(JCheckBox checkBox)
    {
        
      super(checkBox);
      
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
        
      label = (value == null) ? "..." : value.toString();
      button.setText(label);
      button.setFont(new Font("Tahoma", Font.BOLD, 14));
      return button;
    }
    public Object getCellEditorValue() 
    {
        
      return new String(label);
    }
    
}
