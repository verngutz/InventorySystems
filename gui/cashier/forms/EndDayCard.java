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
import system.InventorySystems;
import system.Store;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class EndDayCard 
{
	private JPanel endday;
	private Container con;
	
	private JTextField textFieldStoreId;
	private JTextField textFieldCashierIndex;
	
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
		
		textFieldStoreId = new JTextField();
		endday.add(textFieldStoreId, "4, 2, left, default");
		textFieldStoreId.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier Index:");
		endday.add(lblCashierIndex, "2, 4, right, default");
		
		textFieldCashierIndex = new JTextField();
		endday.add(textFieldCashierIndex, "4, 4, left, default");
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
					JOptionPane.showMessageDialog(endday, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = InventorySystems.getSystem().getStore(storeId);
				if(s == null)
				{
					JOptionPane.showMessageDialog(endday, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textFieldCashierIndex.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(endday, "Specified Cashier ID is in an improper format.");
					return;
				}
				Cashier c = s.getCashier(cashierindex);
				if(c == null)
				{
					JOptionPane.showMessageDialog(endday, "Store " + storeId + " does not have a Cashier " + cashierindex + ".");
					return;
				}
				if(!c.getOnline())
				{
					JOptionPane.showMessageDialog(endday, "Store " + storeId + " Cashier " + cashierindex + " is not online.");
					return;
				}
				double cashBack = c.endDay();
				JOptionPane.showMessageDialog(endday, "Store " + storeId + " Cashier " + cashierindex + " is now offline. " + cashBack + " was returned to the store.");
				returnToPreviousScreen();
			}
		});
		endday.add(btnOK, "4, 8, left, default");
		
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
