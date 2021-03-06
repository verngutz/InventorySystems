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
import system.InventorySystems;

public class RemoveCashierCard 
{
	private JPanel removecashier;
	private Container con;
	
	private JTextField textFieldStoreId;
	private JTextField textFieldCashierIndex;
	
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
		
		textFieldStoreId = new JTextField();
		removecashier.add(textFieldStoreId, "4, 2, left, default");
		textFieldStoreId.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier ID:");
		removecashier.add(lblCashierIndex, "2, 4, right, default");
		
		textFieldCashierIndex = new JTextField();
		removecashier.add(textFieldCashierIndex, "4, 4, left, default");
		textFieldCashierIndex.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() 
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
					JOptionPane.showMessageDialog(removecashier, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = InventorySystems.getSystem().getStore(storeId);
				if(s == null)
				{
					JOptionPane.showMessageDialog(removecashier, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textFieldCashierIndex.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(removecashier, "Specified Cashier ID is in an improper format.");
					return;
				}
				Cashier c = s.getCashier(cashierindex);
				if(c == null)
				{
					JOptionPane.showMessageDialog(removecashier, "Store " + storeId + " does not have a Cashier " + cashierindex + ".");
					return;
				}
				if(c.getOnline())
				{
					JOptionPane.showMessageDialog(removecashier, "Store " + storeId + " Cashier " + cashierindex + " is still online and cannot be removed.");
					return;
				}
				s.removeCashier(cashierindex);
				JOptionPane.showMessageDialog(removecashier, "Cashier " + cashierindex + " successfully removed from. Store " + storeId + " now has " + s.getNumCashiers() + " cashier(s).");
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
		textFieldStoreId.setText("");
		textFieldCashierIndex.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
