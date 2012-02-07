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
import system.Store;
import system.Cashier;

public class RemoveCashierCard 
{
	private JPanel removecashier;
	private Container con;
	
	private JTextField textField;
	
	public JPanel getCard(Container con)
	{
		if(removecashier==null)
		{
			removecashier = new JPanel();
			this.con = con;
			init();
		}
		return removecashier;
	}
	
	public void init()
	{
		removecashier.setLayout(new FormLayout(
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
		removecashier.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		removecashier.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
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
					JOptionPane.showMessageDialog(removecashier, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = null;
				try
				{
					s = SystemBox.getSystem().getStore(storeId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(removecashier, "Store not found.");
					return;
				}
				try
				{
					s.removeCashier();
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(removecashier, "There are no cashiers in Store " + storeId + ".");
					return;
				}
				JOptionPane.showMessageDialog(removecashier, "Cashier successfully removed. Store " + storeId + " now has " + s.getNumCashiers() + " cashier(s).");
				returnToPreviousScreen();
			}
		});
		removecashier.add(btnNewButton, "4, 8, left, default");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				returnToPreviousScreen();
			}
		});
		removecashier.add(btnCancel, "4, 10, left, default");
	}
	
	public void resetFields()
	{
		textField.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
