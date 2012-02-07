package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
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

public class EndDayCard 
{
	private JPanel endday;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel getCard(Container con)
	{
		if(endday==null)
		{
			endday = new JPanel();
			this.con = con;
			init();
		}
		return endday;
	}
	
	public void init()
	{
		endday.setLayout(new FormLayout(
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
		endday.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		endday.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier Index:");
		endday.add(lblCashierIndex, "2, 4, right, default");
		
		textField_1 = new JTextField();
		endday.add(textField_1, "4, 4, left, default");
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
					JOptionPane.showMessageDialog(endday, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = null;
				try
				{
					s = SystemBox.getSystem().getStore(storeId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(endday, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(endday, "Specified Cashier Index is in an improper format.");
					return;
				}
				Cashier c = null;
				try
				{
					c = s.getCashier(cashierindex);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(endday, "Store " + storeId + " does not have a Cashier " + cashierindex + ".");
					return;
				}
				if(!c.isOnline())
				{
					JOptionPane.showMessageDialog(endday, "Store " + storeId + " Cashier " + cashierindex + " is not online.");
					return;
				}
				double cashBack = c.endDay();
				JOptionPane.showMessageDialog(endday, "Store " + storeId + " Cashier " + cashierindex + " is now offline. " + cashBack + " was returned to the store.");
				returnToPreviousScreen();
			}
		});
		endday.add(btnNewButton, "4, 8, left, default");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				returnToPreviousScreen();
			}
		});
		endday.add(btnCancel, "4, 10, left, default");
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
		cl.show(con, Card.CASHIER.getLabel());
	}
}
