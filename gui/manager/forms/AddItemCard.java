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

import system.Item;
import system.InventorySystems;

public class AddItemCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textFieldNewItemCode;
	private JTextField textFieldItemName;
	private JTextField textFieldItemCategory;
	private JTextField textFieldItemUnit;
	private JTextField textFieldUnitPrice;
	
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
		
		textFieldNewItemCode = new JTextField();
		panel.add(textFieldNewItemCode, "4, 2, fill, default");
		textFieldNewItemCode.setColumns(10);
		
		JButton btnInquireCode = new JButton("Inquire Code");
		btnInquireCode.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textFieldNewItemCode.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
				}
				else if(InventorySystems.getSystem().containsItem(textFieldNewItemCode.getText()))
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
		
		textFieldItemName = new JTextField();
		panel.add(textFieldItemName, "4, 6, fill, default");
		textFieldItemName.setColumns(10);
		
		JLabel lblItemCategory = new JLabel("Item Category:");
		panel.add(lblItemCategory, "2, 8, right, default");
		
		textFieldItemCategory = new JTextField();
		panel.add(textFieldItemCategory, "4, 8, fill, default");
		textFieldItemCategory.setColumns(10);
		
		JLabel lblItemUnit = new JLabel("Item Unit:");
		panel.add(lblItemUnit, "2, 10, right, default");
		
		textFieldItemUnit = new JTextField();
		panel.add(textFieldItemUnit, "4, 10, fill, default");
		textFieldItemUnit.setColumns(10);
		
		JLabel lblUnitPrice = new JLabel("Unit Price:");
		panel.add(lblUnitPrice, "2, 12, right, default");
		
		textFieldUnitPrice = new JTextField();
		panel.add(textFieldUnitPrice, "4, 12, fill, default");
		textFieldUnitPrice.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textFieldNewItemCode.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
					return;
				}
				if(InventorySystems.getSystem().containsItem(textFieldNewItemCode.getText()))
				{
					JOptionPane.showMessageDialog(panel, "An item with the specified Item Code already exists.");
					return;
				}
				if(textFieldItemName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Name specified.");
					return;
				}
				if(textFieldItemCategory.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Category specified.");
					return;
				}

				if(textFieldItemUnit.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Unit Name specified.");
					return;
				}
				
				if(textFieldUnitPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Unit Price specified.");
					return;
				}
				double price = 0;
				try
				{
					price = Double.parseDouble(textFieldUnitPrice.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(panel, "Supplied Unit Price is in an improper format.");
					return;
				}
				InventorySystems.getSystem().addItem(new Item(textFieldNewItemCode.getText(), textFieldItemName.getText(), textFieldItemCategory.getText(), textFieldItemUnit.getText(), price));
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
		textFieldNewItemCode.setText("");
		textFieldItemName.setText("");
		textFieldItemCategory.setText("");
		textFieldItemUnit.setText("");
		textFieldUnitPrice.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
