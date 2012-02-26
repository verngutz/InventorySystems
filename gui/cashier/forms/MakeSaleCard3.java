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

import system.Cashier;
import system.Customer;
import system.SystemBox;
import system.Transaction;

public class MakeSaleCard3 
{
	private JPanel makesale;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private Cashier cashier;
	
	public void setCashier(Cashier c)
	{
		cashier = c;
		textField_3.setText(c.getRawCashDue() + "");
	}
	
	public JPanel getCard(Container con)
	{
		if(makesale==null)
		{
			makesale = new JPanel();
			this.con = con;
			init();
		}
		return makesale;
	}
	
	public void init()
	{
		makesale.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("90dlu:grow"),
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
		
		JLabel lblLoyalCustomerId = new JLabel("Loyal Customer ID:");
		makesale.add(lblLoyalCustomerId, "2, 2, right, default");
		
		textField = new JTextField();
		makesale.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Fetch Customer Info");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				String customerIdString = textField.getText();
				int customerId = 0;
				try
				{
					customerId = Integer.parseInt(customerIdString);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(makesale, "Specified Customer ID is in an improper format.");
					return;
				}
				Customer c = null;
				try
				{
					c = SystemBox.getSystem().getCustomer(customerId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(makesale, "Customer not found.");
					return;
				}
				textField_1.setText(c.getUsablePoints() + "");
			}
		});
		makesale.add(btnNewButton, "4, 4, left, default");
		
		JLabel lblNewLabel = new JLabel("Usable Points:");
		makesale.add(lblNewLabel, "2, 6, right, default");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		makesale.add(textField_1, "4, 6, left, default");
		textField_1.setColumns(10);
		
		JLabel lblPointsToUse = new JLabel("Points to Use:");
		makesale.add(lblPointsToUse, "2, 8, right, default");
		
		textField_2 = new JTextField();
		makesale.add(textField_2, "4, 8, left, default");
		textField_2.setColumns(10);
		
		JLabel lblAmountDuel = new JLabel("Amount Due:");
		makesale.add(lblAmountDuel, "2, 12, right, default");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		makesale.add(textField_3, "4, 12, left, default");
		textField_3.setColumns(10);
		
		JButton btnEndTransaction = new JButton("End Transaction");
		btnEndTransaction.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				Transaction t;
				String customerIdString = textField.getText();
				Customer c = null;
				int pointsUsed = 0;
				if(!customerIdString.equals(""))
				{
					int customerId = 0;
					try
					{
						customerId = Integer.parseInt(customerIdString);
					}
					catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(makesale, "Specified Customer ID is in an improper format.");
						return;
					}
					try
					{
						c = SystemBox.getSystem().getCustomer(customerId);
					}
					catch(IndexOutOfBoundsException ioobe)
					{
						JOptionPane.showMessageDialog(makesale, "Customer not found.");
						return;
					}
					if(!textField_2.getText().equals(""))
					{
						try
						{
							pointsUsed = Integer.parseInt(textField_2.getText());
						}
						catch(NumberFormatException nfe)
						{
							JOptionPane.showMessageDialog(makesale, "Specified Points to Use is in an improper format.");
							return;
						}
					}
					if(pointsUsed > c.getUsablePoints())
					{
						JOptionPane.showMessageDialog(makesale, "Customer " + c.getFirstName() + " " + c.getLastName() + " does not have enough points.");
						return;
					}
					if(pointsUsed > Double.parseDouble(textField_3.getText()))
					{
						JOptionPane.showMessageDialog(makesale, "Too many points specified.");
						return;
					}
				}
				try
				{
					t = cashier.endTransaction(c, pointsUsed);
				}
				catch(IllegalArgumentException iae)
				{
					JOptionPane.showMessageDialog(makesale, iae.getMessage());
					return;
				}
				if(c != null)
				{
					cashier.getStore().addTransaction(t);
				}
				JOptionPane.showMessageDialog(makesale, "Transaction ended. Total amount due: " + t.getRevenue());
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		makesale.add(btnCancel, "2, 16");
		makesale.add(btnEndTransaction, "4, 16, left, default");
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
		cl.show(con, Card.CASHIER.getLabel());
	}
}
