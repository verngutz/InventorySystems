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

public class ChangePriceCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
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
		}));
		
		JLabel lblItemCode = new JLabel("Item Code:");
		panel.add(lblItemCode, "2, 2, right, default");
		
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
					textField_1.setText(SystemBox.getSystem().getItem(textField.getText()).getItemName());
					textField_2.setText(SystemBox.getSystem().getItem(textField.getText()).getUnitPrice() + "");
				}
				else
				{
					JOptionPane.showMessageDialog(panel, "Item not found.");
				}
			}
		});
		panel.add(btnInquireCode, "2, 4");
		
		JLabel lblItemName = new JLabel("Item Name:");
		panel.add(lblItemName, "2, 6, right, default");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Price:");
		panel.add(lblNewLabel, "2, 8, right, default");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewPrice = new JLabel("New Price:");
		panel.add(lblNewPrice, "2, 12, right, default");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "4, 12, fill, default");
		textField_3.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
					return;
				}
				if(!SystemBox.getSystem().containsItem(textField.getText()))
				{
					JOptionPane.showMessageDialog(panel, "Item not found.");
					return;
				}
				double newPrice = 0;
				try
				{
					newPrice = Double.parseDouble(textField_3.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(panel, "Specified New Price is in an improper format.");
					return;
				}
				SystemBox.getSystem().getItem(textField.getText()).setUnitPrice(newPrice);
				JOptionPane.showMessageDialog(panel, "Price successfully changed.");
				returnToPreviousScreen();
			}
		});
		panel.add(btnOk, "2, 14");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				returnToPreviousScreen();
			}
		});
		panel.add(btnCancel, "2, 16");
	}
	
	public void resetFields()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
