package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.Cashier;
import system.Customer;
import system.InventorySystems;
import system.TransactionE;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MakeSaleCard3 
{
	private JPanel makesale;
	private Container con;
	
	private JTextField textFieldCustomerId;
	private JTextField textFieldUsablePoints;
	private JTextField textFieldPointsToUse;
	private JTextField textFieldAmountDue;
	
	private Cashier cashier;
	
	public void setCashier(Cashier c)
	{
		cashier = c;
		textFieldAmountDue.setText(c.getRawCashDue() + "");
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
		
		textFieldCustomerId = new JTextField();
		makesale.add(textFieldCustomerId, "4, 2, left, default");
		textFieldCustomerId.setColumns(10);
		
		JButton btnNewButton = new JButton("Fetch Customer Info");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				String customerIdString = textFieldCustomerId.getText();
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
				Customer c = InventorySystems.getSystem().getCustomer(customerId);
				if(c == null)
				{
					JOptionPane.showMessageDialog(makesale, "Customer not found.");
					return;
				}
				textFieldUsablePoints.setText(c.getUsablePoints() + "");
			}
		});
		makesale.add(btnNewButton, "4, 4, left, default");
		
		JLabel lblNewLabel = new JLabel("Usable Points:");
		makesale.add(lblNewLabel, "2, 6, right, default");
		
		textFieldUsablePoints = new JTextField();
		textFieldUsablePoints.setEditable(false);
		makesale.add(textFieldUsablePoints, "4, 6, left, default");
		textFieldUsablePoints.setColumns(10);
		
		JLabel lblPointsToUse = new JLabel("Points to Use:");
		makesale.add(lblPointsToUse, "2, 8, right, default");
		
		textFieldPointsToUse = new JTextField();
		makesale.add(textFieldPointsToUse, "4, 8, left, default");
		textFieldPointsToUse.setColumns(10);
		
		JLabel lblAmountDuel = new JLabel("Amount Due:");
		makesale.add(lblAmountDuel, "2, 12, right, default");
		
		textFieldAmountDue = new JTextField();
		textFieldAmountDue.setEditable(false);
		makesale.add(textFieldAmountDue, "4, 12, left, default");
		textFieldAmountDue.setColumns(10);
		
		JButton btnEndTransaction = new JButton("End Transaction");
		btnEndTransaction.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				TransactionE t;
				String customerIdString = textFieldCustomerId.getText();
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
					c = InventorySystems.getSystem().getCustomer(customerId);
					if(c == null)
					{
						JOptionPane.showMessageDialog(makesale, "Customer not found.");
						return;
					}
					if(!textFieldPointsToUse.getText().equals(""))
					{
						try
						{
							pointsUsed = Integer.parseInt(textFieldPointsToUse.getText());
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
					if(pointsUsed > Double.parseDouble(textFieldAmountDue.getText()))
					{
						JOptionPane.showMessageDialog(makesale, "Too many points specified.");
						return;
					}
				}
				try
				{
					t = cashier.endTransaction(c, pointsUsed);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(makesale, ex.getMessage());
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
		textFieldCustomerId.setText("");
		textFieldUsablePoints.setText("");
		textFieldPointsToUse.setText("");
		textFieldAmountDue.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.CASHIER.getLabel());
	}
}
