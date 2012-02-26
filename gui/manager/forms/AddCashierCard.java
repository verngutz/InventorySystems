package gui.manager.forms;

import gui.Card;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import system.Cashier;
import system.Store;
import system.SystemBox;

public class AddCashierCard 
{
	private JPanel addcashier;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel getCard(Container con)
	{
		if(addcashier==null)
		{
			addcashier = new JPanel();
			this.con = con;
			init();
		}
		return addcashier;
	}
	
	public void init()
	{
		addcashier.setLayout(new FormLayout(
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
		}));
		
		JLabel lblStoreId = new JLabel("Store ID:");
		addcashier.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		addcashier.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier ID:");
		addcashier.add(lblCashierIndex, "2, 4, right, default");
		
		textField_1 = new JTextField();
		addcashier.add(textField_1, "4, 4, left, default");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int storeId = 0;
				try
				{
					storeId = Integer.parseInt(textField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(addcashier, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = null;
				try
				{
					s = SystemBox.getSystem().getStore(storeId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(addcashier, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(addcashier, "Specified Cashier ID is in an improper format.");
					return;
				}
				if(s.getCashier(cashierindex) != null)
				{
					JOptionPane.showMessageDialog(addcashier, "Store " + storeId + " already has a Cashier " + cashierindex + ". Please use another Cashier ID.");
					return;
				}
				s.addCashier(cashierindex);
				JOptionPane.showMessageDialog(addcashier, "Cashier successfully added. Store " + storeId + " now has " + s.getNumCashiers() + " cashier(s).");
				returnToPreviousScreen();
			}
		});
		addcashier.add(btnNewButton, "4, 8, left, default");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				returnToPreviousScreen();
			}
		});
		addcashier.add(btnCancel, "4, 10, left, default");
	}
	
	public void resetFields()
	{
		textField.setText("");
		textField_1.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
