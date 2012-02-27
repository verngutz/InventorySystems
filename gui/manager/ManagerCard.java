package gui.manager;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ManagerCard 
{
	private JPanel manager;
	private Container con;
	
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
		manager.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
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
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA21.getLabel());
			}
		});
		manager.add(btnRestock, "2, 4");
		
		JButton btnGetCustomerReport = new JButton("Get Customer Report");
		btnGetCustomerReport.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA3.getLabel());
			}
		});
		manager.add(btnGetCustomerReport, "2, 6");
		
		JButton btnCashPosition = new JButton("Get Cash Position");
		btnCashPosition.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA4.getLabel());
			}
		});
		manager.add(btnCashPosition, "2, 8");
		
		JButton btnGetItemSummary = new JButton("Get Item Summary");
		btnGetItemSummary.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA9.getLabel());
			}
		});
		manager.add(btnGetItemSummary, "2, 10");
		
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
		
		JButton btnChangeUnitPrice = new JButton("Change Unit Price");
		btnChangeUnitPrice.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MA6.getLabel());
			}
		});
		manager.add(btnChangeUnitPrice, "4, 4");
		
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
