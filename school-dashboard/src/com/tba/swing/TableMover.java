/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tba.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author user
 */
public class TableMover {
    
    /**
     * Using DefaultTableModel to create the model for destination JTable
     */
    private DefaultTableModel model;
    
    /**
     * Both JTable below is the origin and destination table. Data is moved from table_origin to table_destination
     */
    private JTable table_origin;
    private JTable table_destination;
    
    /**
     * This is the data that will be inserted in the table_destination
     */
    Object data_move[];
    
    /**
     * This object is needed to create the column for table_destination as the table is empty at first
     */
    Object column[];
    
    /**
     * Unusual empty row, used with column to create a tablemodel
     */
    Object empty_row[][];
    
    /** Creates a new instance of TableDataMover */
    public TableMover() {
    }
    
    /**
     * Creates a new instance of TableDataMover with table_origin and table_destination directly initialized
     */
    public TableMover(JTable table_origin, JTable table_destination) {
        this.setTable_origin(table_origin);
        this.setTable_destination(table_destination);
        
        /**
         * Creating column
         */
        
        String header[] = {"1","2","3","4","5"};
        column = new Object[table_origin.getColumnCount()+1];
        for (int i=0;i<table_origin.getColumnCount();i++) {
            //column[i] = table_origin.getColumnName(i);
            
            column[i] = table_origin.getColumnName(i);
            
           
        }
        
       
        
        //String lastCol = (String) column[column.length-1];
        //System.out.println(column[column.length-1]);
        
        
        
       
        
        /**
         * Set the empty table_destination
         */
        model = new DefaultTableModel(empty_row,column);
        table_destination.setModel(model);
    }
    
    /**
     * Copy one row without uniqueness check
     */
    public  void copy_row() {
        /**
         * Count the number of column
         */
        //int column_count = getTable_origin().getSelectedColumn();
        int column_count = getTable_origin().getColumnCount();
       
        
        
        
        /**
         * Initialize the row
         */
        data_move = new Object[column_count];
        
        /**
         * Copy values from table_origin to the data_move
         */
        //maybe use two arrays
        for (int i=0;i<column_count;i++) {
            
            data_move[i] = getTable_origin().getValueAt(getTable_origin().getSelectedRow(),i); 
            System.out.println(data_move[i]);
            
            
            
            
        }
        
        
        
        
        /**
         * Add it to the DefaultTableModel
         */
        
        model.addRow(data_move);
        
           
   
        
        /**
         * Show it! and Done!
         */
        getTable_destination().setModel(model);
    }
    
    /**
     * Creates a new instance of TableDataMover with table_origin and table_destination directly initialized
     */
    public void copy_row_unique() {
        if (cek_unique()) {
            copy_row();
        }
        
        
    }
    
    public void remove_row() {
        model.removeRow(getTable_destination().getSelectedRow());
    }
    
    /**
     * This method check the uniqueness
     */
    private boolean cek_unique() {
        /**
         * Creates a new instance of TableDataMover with table_origin and table_destination directly initialized
         */
        boolean is_unique = true;
        
        int table_destination_length = model.getRowCount();
        
        /**
         * This is the string that will be checked for uniqueness
         */
        String key = getTable_origin().getValueAt(getTable_origin().getSelectedRow(),getTable_origin().getSelectedColumn()).toString();
        
        /**
         * Checking....
         */
        
        String key2 = getTable_destination().getValueAt(getTable_destination().getSelectedRow(),getTable_destination().getSelectedColumn()).toString();
          if (key2.equals(key)) {
                is_unique = false;
                //break;
                JOptionPane.showMessageDialog(table_origin, null);
                
            }  
        /*for (int i=0;i<table_destination_length;i++) {
            String key2 = getTable_destination().getValueAt(i,getTable_origin().getSelectedColumn()).toString();
            if (key2.equals(key)) {
                is_unique = false;
                //break;
                JOptionPane.showMessageDialog(table_origin, null);
                
            }
            
        }*/
        return is_unique;
    }
    
    public JTable getTable_origin() {
        return table_origin;
    }
    
    public void setTable_origin(JTable table_origin) {
        this.table_origin = table_origin;
    }
    
    public JTable getTable_destination() {
        return table_destination;
    }
    
    public void setTable_destination(JTable table_destination) {
        this.table_destination = table_destination;
    }
    
}
