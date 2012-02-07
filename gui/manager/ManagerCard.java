package gui.manager;

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

public class ManagerCard 
{
	private JPanel manager;
	private Container con;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public JPanel getCard(Container con)
	{
		if(manager==null)
		{
			manager = new JPanel();
			this.con = con;
			init();
		}
		return manager;
	}
	
	public void init()
	{
		manager.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.MIN_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.MIN_COLSPEC,
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
		}));
		
		JButton btnEnrollCustomer = new JButton("Enroll Customer");
		btnEnrollCustomer.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA1.getLabel());
			}
		});
		manager.add(btnEnrollCustomer, "2, 2");
		
		JButton btnAddNewItem = new JButton("Add New Item");
		btnAddNewItem.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA5.getLabel());
			}
		});
		manager.add(btnAddNewItem, "4, 2");
		
		JButton btnNewButton = new JButton("Restock");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA21.getLabel());
			}
		});
		manager.add(btnNewButton, "2, 4");
		
		JButton btnNewButton_3 = new JButton("Change Unit Price");
		btnNewButton_3.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA6.getLabel());
			}
		});
		manager.add(btnNewButton_3, "4, 4");
		
		JButton btnNewButton_1 = new JButton("Get Customer Report");
		btnNewButton_1.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA3.getLabel());
			}
		});
		manager.add(btnNewButton_1, "2, 6");
		
		JButton btnAddcashier = new JButton("Add Cashier");
		btnAddcashier.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA7.getLabel());
			}
		});
		manager.add(btnAddcashier, "4, 6");
		
		JButton btnNewButton_2 = new JButton("Get Cash Position");
		btnNewButton_2.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA4.getLabel());
			}
		});
		manager.add(btnNewButton_2, "2, 8");
		
		JButton btnRemoveCashier = new JButton("Remove Cashier");
		btnRemoveCashier.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA8.getLabel());
			}
		});
		manager.add(btnRemoveCashier, "4, 8");
	}
}
