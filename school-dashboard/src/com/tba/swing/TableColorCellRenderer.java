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
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class TableColorCellRenderer implements TableCellRenderer {
    
    private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = RENDERER.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, column);
        String versionVal = table.getValueAt(row, column).toString();
        
        if (versionVal.equalsIgnoreCase("WIX1001")) {
            c.setBackground(Color.red);
        }
        else{
            c.setBackground(Color.black);
        }
         return c;   
        }
        
    }
    

