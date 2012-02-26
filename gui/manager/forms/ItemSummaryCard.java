package gui.manager.forms;

import gui.Card;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

import system.Cashier;
import system.Item;
import system.Store;
import system.SystemBox;
import system.TransactionE;
import system.TransactionItem;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class ItemSummaryCard {
	private JSplitPane reportpane;
	private Container con;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JPanel panel_1;
	
	private LinkedList<JTextField> itemDetails;
	
	public JSplitPane getCard(Container con){
		if(reportpane==null){
			reportpane = new JSplitPane();
			this.con = con;
			init();
		}
		return reportpane;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		reportpane = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane();
		reportpane.setRightComponent(scrollPane);
		itemDetails = new LinkedList<JTextField>();
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("min:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblItem = new JLabel("Quantity");
		panel_1.add(lblItem, "2, 2");
		
		JLabel lblPrice = new JLabel("Customer");
		panel_1.add(lblPrice, "4, 2");
		
		JLabel lblPrice_1 = new JLabel("Price");
		panel_1.add(lblPrice_1, "6, 2");
		
		
		JPanel panel = new JPanel();
		reportpane.setLeftComponent(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblItemId = new JLabel("Item ID:");
		panel.add(lblItemId, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel store = new JLabel("Store ID:");
		panel.add(store, "2, 4, right, default");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "4, 4, fill, default");
		textField_4.setColumns(10);
		
		JButton btnInquire = new JButton("Inquire");
		btnInquire.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String itemCode = textField.getText();
				Item i;
				try
				{
					i = SystemBox.getSystem().getItem(itemCode);
				}
				catch(NullPointerException npe)
				{
					JOptionPane.showMessageDialog(reportpane, "Item not found.");
					return;
				}
				textField_3.setText(i.getItemName());
				int quantitySold = 0;
				double amountMade = 0;
				
				for(JTextField j : itemDetails)
				{
					j.setVisible(false);
					panel_1.remove(j);
				}

				Store query = null;
				if(!textField_4.getText().equals(""))
				{
					int storeId = 0;
					try
					{
						storeId = Integer.parseInt(textField_4.getText());
					}
					catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(reportpane, "Specified Store ID is in an improper format.");
						return;
					}
					try
					{
						query = SystemBox.getSystem().getStore(storeId);
					}
					catch(IndexOutOfBoundsException ioobe)
					{
						JOptionPane.showMessageDialog(reportpane, "Store not found.");
						return;
					}
				}
				
				int y = 4;
				
				Iterator<Store> stores = SystemBox.getSystem().storeIterator();
				while(stores.hasNext())
				{
					Store s = stores.next();
					if(query != null && !query.equals(s))
					{
						continue;
					}
					Iterator<TransactionE> transactions = s.transactionIterator();
					while(transactions.hasNext())
					{
						TransactionE t = transactions.next();
						Iterator<TransactionItem> items = t.itemsSoldIterator();
						while(items.hasNext())
						{
							TransactionItem ti = items.next();
							if(ti.getItem().equals(i))
							{
								JTextField quantity = new JTextField();
								JTextField customer = new JTextField();
								JTextField price = new JTextField();
								
								quantity.setEditable(false);
								customer.setEditable(false);
								price.setEditable(false);
								
								quantity.setColumns(10);
								customer.setColumns(10);
								price.setColumns(10);
								
								quantity.setText(ti.getQuantity() + "");
								customer.setText(t.getCustomer().getId() + ": " + t.getCustomer().getFirstName() + " " + t.getCustomer().getLastName() + ", " + t.getCustomer().getAge() + ", " + t.getCustomer().getGender());
								price.setText(ti.getPrice() + "");

								panel_1.add(quantity, "2, " + y + ", fill, default");
								panel_1.add(customer, "4, " + y + ", fill, default");
								panel_1.add(price, "6, " + y + ", fill, default");
								
								itemDetails.add(quantity);
								itemDetails.add(customer);
								itemDetails.add(price);
								
								quantitySold += ti.getQuantity();
								amountMade += ti.getQuantity() * ti.getPrice();
								
								y+=2;
							}
						}
					}
				}
				
				panel_1.revalidate();
				textField_1.setText(quantitySold + "");
				textField_2.setText(amountMade + "");
				if(query != null)
					textField_5.setText(query.checkInventory(i) + "");
				else
					textField_5.setText("");
			}
		});
		panel.add(btnInquire, "2, 6");
		
		JLabel lblItemName = new JLabel("Item Name:");
		panel.add(lblItemName, "2, 8, right, default");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel.add(textField_3, "4, 8, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblCashOnStore = new JLabel("Quantity Sold:");
		panel.add(lblCashOnStore, "2, 10, right, default");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel.add(textField_1, "4, 10, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblTotalCash = new JLabel("Amount Made:");
		panel.add(lblTotalCash, "2, 12, right, default");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2, "4, 12, fill, default");
		textField_2.setColumns(10);
		
		JLabel stock = new JLabel("Available Stock in Store:");
		panel.add(stock, "2, 14, right, default");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		panel.add(textField_5, "4, 14, fill, default");
		textField_5.setColumns(10);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 16");
		
	}
	
	public void resetFields()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		for(JTextField j : itemDetails)
		{
			panel_1.remove(j);
		}
	}
}
