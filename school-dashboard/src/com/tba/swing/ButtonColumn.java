/***************************************************************************************
*    Title: Table Button Column
*    Author: Rob Camick
*    Date: 2009
*    Code version: 1.0
*    Availability: https://tips4java.wordpress.com/2009/07/12/table-button-column/
*
***************************************************************************************/
package com.tba.swing;

import com.tba.model.ModelStudentType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.tba.swing.Button;



/**
 *
 * @author user
 */
public class ButtonColumn extends AbstractCellEditor
implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener{
    private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private Button renderButton;
	private Button editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;
        private Image img;
        private ModelStudentType type;

	/**
	 *  Create the ButtonColumn to be used as a renderer and editor. The
	 *  renderer and editor will automatically be installed on the TableColumn
	 *  of the specified column.
	 *
	 *  @param table the table containing the button renderer/editor
	 *  @param action the Action to be invoked when the button is invoked
	 *  @param column the column to which the button renderer/editor is added
	 */
	public ButtonColumn(JTable table, Action action,int column) 
	{
		this.table = table;
		this.action = action;
               

		renderButton = new Button();
		editButton = new Button();
		editButton.setFocusPainted( false );
		editButton.addActionListener( this );
		originalBorder = editButton.getBorder();
		//setFocusBorder( new LineBorder(Color.BLUE) );

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer( this );
		columnModel.getColumn(column).setCellEditor( this );
		table.addMouseListener( this );
	}


	/**
	 *  Get foreground color of the button when the cell has focus
	 *
	 *  @return the foreground color
	 */
	public Border getFocusBorder()
	{
		return focusBorder;
	}

	/**
	 *  The foreground color of the button when the cell has focus
	 *
	 *  @param focusBorder the foreground color
	 */
	public void setFocusBorder(Border focusBorder)
	{
		this.focusBorder = focusBorder;
		editButton.setBorder( focusBorder );
	}

	public int getMnemonic()
	{
		return mnemonic;
	}

	/**
	 *  The mnemonic to activate the button when the cell has focus
	 *
	 *  @param mnemonic the mnemonic
	 */
	public void setMnemonic(int mnemonic)
	{
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
	}

	@Override
	public Component getTableCellEditorComponent(
		JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (column==9)
		{
			editButton.setText( "Add" );
                        editButton.setForeground(new Color(0x4285F4));
			
		}
               
		else if (value instanceof Icon)
		{
			editButton.setText( "" );
			editButton.setIcon( (Icon)value );
		}
                else if (column==10 || column==4)
		{
			editButton.setText( "Delete" );
			editButton.setForeground(Color.red);
                        
		}
                else if (column==8)
		{
			editButton.setText( "Edit" );
                        editButton.setForeground(new Color(0x4285F4));
			
                        
		}
                
                       
		
		/*else
		{
			editButton.setText( value.toString() );
			editButton.setIcon( null );
		}*/

		this.editorValue = value;
		return editButton;
	}

	@Override
	public Object getCellEditorValue()
	{
		return editorValue;
	}

//
//  Implement TableCellRenderer interface
//
	public Component getTableCellRendererComponent(
		JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			renderButton.setForeground(Color.BLACK);
	 		renderButton.setBackground(Color.WHITE);
		}
		else
		{
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus)
		{
			//renderButton.setBorder( focusBorder );
		}
		else
		{
			//renderButton.setBorder( originalBorder );
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if ( column==9)
		{
			renderButton.setText( "Add" );
                        renderButton.setForeground(new Color(0x4285F4));
			
		}
                
		else if (value instanceof Icon)
		{
			renderButton.setText( "" );
			renderButton.setIcon( (Icon)value );
		}
                else if (column==10 || column==4)
		{
			renderButton.setText( "Delete" );
                        renderButton.setForeground(Color.red);
			//editButton.setIcon( (Icon)value );
                        
		}
                else if (column==8)
		{
			renderButton.setText( "Edit" );
                        renderButton.setForeground(new Color(0x4285F4));
			//editButton.setIcon( (Icon)value );
                        
		}
		/*else
		{
			renderButton.setText( value.toString() );
			renderButton.setIcon( null );
		}*/

		return renderButton;
	}
        
       

//
//  Implement ActionListener interface
//
	/*
	 *	The button has been pressed. Stop editing and invoke the custom Action
	 */
	public void actionPerformed(ActionEvent e)
	{
		int row = table.convertRowIndexToModel( table.getEditingRow() );
		fireEditingStopped();

		//  Invoke the Action

		ActionEvent event = new ActionEvent(
			table,
			ActionEvent.ACTION_PERFORMED,
			"" + row);
		action.actionPerformed(event);
	}
        
       
        
        

//
//  Implement MouseListener interface
//
	/*
	 *  When the mouse is pressed the editor is invoked. If you then then drag
	 *  the mouse to another cell before releasing it, the editor is still
	 *  active. Make sure editing is stopped when the mouse is released.
	 */
    public void mousePressed(MouseEvent e)
    {
    	if (table.isEditing()
		&&  table.getCellEditor() == this)
			isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e)
    {
    	if (isButtonColumnEditor
    	&&  table.isEditing())
    		table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
