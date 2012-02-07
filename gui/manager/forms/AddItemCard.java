package gui.manager.forms;

import gui.Card;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import system.SystemBox;
import system.Item;

public class AddItemCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public JPanel getCard(Container con)
	{
		if(panel==null)
		{
			panel = new JPanel();
			this.con = con;
			init();
		}
		return panel;
	}
	
	public void init()
	{
		panel.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("default:grow"),
		},
		new RowSpec[] 
		{
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblNewItemCode = new JLabel("New Item Code:");
		panel.add(lblNewItemCode, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JButton btnInquireCode = new JButton("Inquire Code");
		btnInquireCode.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
				}
				else if(SystemBox.getSystem().containsItem(textField.getText()))
				{
					JOptionPane.showMessageDialog(panel, "An item with the specified Item Code already exists.");
				}
				else
				{
					JOptionPane.showMessageDialog(panel, "The specified Item Code is safe to use.");
				}
			}
		});
		panel.add(btnInquireCode, "2, 4");
		
		JLabel lblItemName = new JLabel("Item Name:");
		panel.add(lblItemName, "2, 6, right, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblItemCategory = new JLabel("Item Category:");
		panel.add(lblItemCategory, "2, 8, right, default");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblItemUnit = new JLabel("Item Unit:");
		panel.add(lblItemUnit, "2, 10, right, default");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "4, 10, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblUnitPrice = new JLabel("Unit Price:");
		panel.add(lblUnitPrice, "2, 12, right, default");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "4, 12, fill, default");
		textField_4.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
					return;
				}
				if(SystemBox.getSystem().containsItem(textField.getText()))
				{
					JOptionPane.showMessageDialog(panel, "An item with the specified Item Code already exists.");
					return;
				}
				double price = 0;
				try
				{
					price = Double.parseDouble(textField_4.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(panel, "Supplied Unit Price is in an improper format.");
					return;
				}
				SystemBox.getSystem().addItem(new Item(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), price));
				JOptionPane.showMessageDialog(panel, "Item successfully added.");
				returnToPreviousScreen();
			}
		});
		panel.add(btnAdd, "2, 16");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				returnToPreviousScreen();
			}
		});
		panel.add(btnCancel, "2, 18");
	}
	
	public void resetFields()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
