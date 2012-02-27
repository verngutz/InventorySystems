package gui.manager.forms;

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

import system.InventorySystems;
import system.Store;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AddCashierCard 
{
	private JPanel addcashier;
	private Container con;
	
	private JTextField textFieldStoreId;
	private JTextField textFieldCashierIndex;
	
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
		
		textFieldStoreId = new JTextField();
		addcashier.add(textFieldStoreId, "4, 2, left, default");
		textFieldStoreId.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier ID:");
		addcashier.add(lblCashierIndex, "2, 4, right, default");
		
		textFieldCashierIndex = new JTextField();
		addcashier.add(textFieldCashierIndex, "4, 4, left, default");
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
					JOptionPane.showMessageDialog(addcashier, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = InventorySystems.getSystem().getStore(storeId);
				if(s == null)
				{
					JOptionPane.showMessageDialog(addcashier, "Store not found.");
					return;
				}
				int cashierindex = 0;
				try
				{
					cashierindex = Integer.parseInt(textFieldCashierIndex.getText());
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
		addcashier.add(btnOK, "4, 8, left, default");
		
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
