package gui.cashier.forms;

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
import system.Cashier;
import system.Store;

public class StartDayCard 
{
	private JPanel startday;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel getCard(Container con)
	{
		if(startday==null)
		{
			startday = new JPanel();
			this.con = con;
			init();
		}
		return startday;
	}
	
	public void init()
	{
		startday.setLayout(new FormLayout(
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
		startday.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		startday.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier ID:");
		startday.add(lblCashierIndex, "2, 4, right, default");
		
		textField_1 = new JTextField();
		startday.add(textField_1, "4, 4, left, default");
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
					JOptionPane.showMessageDialog(startday, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = null;
				try
				{
					s = SystemBox.getSystem().getStore(storeId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(startday, "Store not found.");
					return;
				}
				long cashierindex = 0;
				try
				{
					cashierindex = Long.parseLong(textField_1.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(startday, "Specified Cashier ID is in an improper format.");
					return;
				}
				Cashier c = null;
				try
				{
					c = s.getCashier(cashierindex);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(startday, "Store " + storeId + " does not have a Cashier " + cashierindex + ".");
					return;
				}
				if(c.isOnline())
				{
					JOptionPane.showMessageDialog(startday, "Store " + storeId + " Cashier " + cashierindex + " is already online.");
					return;
				}
				c.startDay();
				double cashGiven = s.getCashPerCashier();
				JOptionPane.showMessageDialog(startday, "Store " + storeId + " Cashier " + cashierindex + " is now online. " + cashGiven + " was transferred from the store balance to the cashier." );
				returnToPreviousScreen();
			}
		});
		startday.add(btnNewButton, "4, 8, left, default");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				returnToPreviousScreen();
			}
		});
		startday.add(btnCancel, "4, 10, left, default");
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
