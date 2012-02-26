package gui.manager.forms;

import gui.Card;

import java.awt.Container;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import system.Store;
import system.SystemBox;

public class RestockCard1 
{
	private JPanel restockpanel;
	private Container con;
	
	private JTextField textField;
	
	private RestockCard2 restockCard2;
	
	public RestockCard1(RestockCard2 restockCard2)
	{
		this.restockCard2 = restockCard2;
	}
	
	public JPanel getCard(Container con)
	{
		if(restockpanel==null)
		{
			restockpanel = new JPanel();
			this.con = con;
			init();
		}
		return restockpanel;
	}
	
	public void init()
	{
		restockpanel.setLayout(new FormLayout(
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
		restockpanel.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		restockpanel.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() 
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
					JOptionPane.showMessageDialog(restockpanel, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = null;
				try
				{
					s = SystemBox.getSystem().getStore(storeId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(restockpanel, "Store not found.");
					return;
				}
				restockCard2.setStore(s);
				s.startDeliveryBatch();
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA22.getLabel());
			}
		});
		restockpanel.add(btnOk, "2, 8");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		restockpanel.add(btnCancel, "2, 10");
	}
	
	public void resetFields()
	{
		textField.setText("");
	}
}
