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

import system.Cashier;
import system.Store;
import system.InventorySystems;

public class StartDayCard 
{
	private JPanel startday;
	private Container con;
	
	private JTextField textFieldStoreId;
	private JTextField textFieldCashierIndex;
	
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
		
		textFieldStoreId = new JTextField();
		startday.add(textFieldStoreId, "4, 2, left, default");
		textFieldStoreId.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier ID:");
		startday.add(lblCashierIndex, "2, 4, right, default");
		
		textFieldCashierIndex = new JTextField();
		startday.add(textFieldCashierIndex, "4, 4, left, default");
		textFieldCashierIndex.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int storeId = 0;
				try
				{
					storeId = Integer.parseInt(textFieldStoreId.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(startday, "Specified Store ID is in an improper format.");
					return;
				}
				Store s  = InventorySystems.getSystem().getStore(storeId);
				if(s == null)
				{
					JOptionPane.showMessageDialog(startday, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textFieldCashierIndex.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(startday, "Specified Cashier ID is in an improper format.");
					return;
				}
				Cashier c = s.getCashier(cashierindex);
				if(c == null)
				{
					JOptionPane.showMessageDialog(startday, "Store " + storeId + " does not have a Cashier " + cashierindex + ".");
					return;
				}
				if(c.getOnline())
				{
					JOptionPane.showMessageDialog(startday, "Store " + storeId + " Cashier " + cashierindex + " is already online.");
					return;
				}
				try
				{
					c.startDay();
				}
				catch(IllegalStateException ise)
				{
					JOptionPane.showMessageDialog(startday, ise.getMessage());
					return;
				}
				double cashGiven = s.getCashPerCashier();
				JOptionPane.showMessageDialog(startday, "Store " + storeId + " Cashier " + cashierindex + " is now online. " + cashGiven + " was transferred from the store balance to the cashier." );
				returnToPreviousScreen();
			}
		});
		startday.add(btnOK, "4, 8, left, default");
		
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
		textFieldStoreId.setText("");
		textFieldCashierIndex.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.CASHIER.getLabel());
	}
}
