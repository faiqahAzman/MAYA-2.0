/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class Grids extends JTable{
    
     // stores cell renderers
    private final ArrayList<ArrayList<DefaultTableCellRenderer>> renderers;
    private final Object[] columns;
    private final ArrayList<Object[]> data;
    private GridModel model;
    
    public Grids(Object... columns) {
        super(new GridModel(new Object[0][0], columns));
        this.renderers = new ArrayList<ArrayList<DefaultTableCellRenderer>>();
        this.columns = columns;
        this.data = new ArrayList<Object[]>();
        this.model = (GridModel) this.getModel();
        
        // initialize renderers
        for(int i = 0; i < 1; i++) {
            renderers.add(new ArrayList<DefaultTableCellRenderer>());
            for(int k = 0; k < columns.length; k++)
                renderers.get(i).add(new DefaultTableCellRenderer());
        }
        
        // set cell height to fill grid
        this.setRowHeight(25);
    }
    
    public int rowCount() {
        return data.size();
    }
    
    public int columnCount() {
        return columns.length;
    }
    
    public synchronized void updateCell(int row, int column, Color color) {
        if(!checkBounds(row, column))
            return;
        
        renderers.get(row).get(column).setBackground(color);
        this.repaint();
    }
    
    public synchronized void updateCell(int row, int column, String text) {
        if(!checkBounds(row, column))
            return;
        
        model.setValueAt(text, row, column);
        this.repaint();
    }
    
    public synchronized void addRow(Object... data) {
        this.data.add(data);
        addRowRenderer();
        model.addRow(data);
    }
    
    private synchronized void addRowRenderer() {
        renderers.add(new ArrayList<DefaultTableCellRenderer>());
        for(int i = 0; i < columns.length; i++)
            renderers.get(renderers.size() - 1).add(new DefaultTableCellRenderer());
    }
    
    public synchronized void updateRow(int row, Object... data) {
        this.data.set(row, data);
        model.insertRow(row, data);
    }
    
    public boolean checkBounds(int row, int col) {
        // non-positive bounds
        if(row < 0 || col < 0)
            return false;
        
        // greater than current bounds
        if(row >= model.getRowCount() || col >= model.getColumnCount())
            return false;
        
        return true;
    }
    
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        try {
            return renderers.get(row).get(column);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}

class GridModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public GridModel(Object[][] data, Object[] columns) {
        super(data, columns);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }    
    
}
