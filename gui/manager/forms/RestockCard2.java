package gui.manager.forms;

import gui.Card;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import system.Delivery;
import system.Item;
import system.Store;
import system.InventorySystems;

import java.util.LinkedList;

public class RestockCard2 
{
	private JSplitPane restockpane;
	private Container con;
	private JTextField textFieldItemId;
	private JTextField textFieldQuantity;
	private JTextField textFieldAmountDue;
	private JTextField textFieldWholesalePrice;
	private JPanel panelTransactionDetails;
	
	private LinkedList<JTextField> transactionDetails;
	private int transactionDrawingPosition;
	
	private Store store;
	
	public void setStore(Store store)
	{
		this.store = store;
	}
	
	public JSplitPane getCard(Container con)
	{
		if(restockpane==null)
		{
			restockpane = new JSplitPane();
			this.con = con;
			init();
		}
		return restockpane;
	}
	
	public void init()
	{
		JScrollPane scrollPane = new JScrollPane();
		restockpane.setRightComponent(scrollPane);
		
		panelTransactionDetails = new JPanel();
		scrollPane.setViewportView(panelTransactionDetails);
		panelTransactionDetails.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.MIN_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("default:grow"),
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("max(39dlu;default):grow"),
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
		
		JLabel lblQuantity_1 = new JLabel("Quantity");
		panelTransactionDetails.add(lblQuantity_1, "2, 2");
		
		JLabel lblItem = new JLabel("Item");
		panelTransactionDetails.add(lblItem, "4, 2");
		
		JLabel lblPrice = new JLabel("Total Price");
		panelTransactionDetails.add(lblPrice, "6, 2");
		
		transactionDetails = new LinkedList<JTextField>();
		transactionDrawingPosition = 4;
		
		JPanel panel = new JPanel();
		restockpane.setLeftComponent(panel);
		panel.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
			ColumnSpec.decode("85px:grow"),
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("max(55dlu;default):grow"),
		},
		new RowSpec[] 
		{
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
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblItemId = new JLabel("Item Code:");
		panel.add(lblItemId, "2, 2, right, default");
		
		textFieldItemId = new JTextField();
		panel.add(textFieldItemId, "4, 2, fill, default");
		textFieldItemId.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		panel.add(lblQuantity, "2, 4, right, default");
		
		textFieldQuantity = new JTextField();
		panel.add(textFieldQuantity, "4, 4, fill, default");
		textFieldQuantity.setColumns(10);
		
		JLabel lblWholesalePrice = new JLabel("Wholesale Price:");
		panel.add(lblWholesalePrice, "2, 6, right, default");
		
		textFieldWholesalePrice = new JTextField();
		panel.add(textFieldWholesalePrice, "4, 6, fill, default");
		textFieldWholesalePrice.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(textFieldItemId.getText().equals(""))
				{
					JOptionPane.showMessageDialog(restockpane, "No Item Code specified.");
					return;
				}
				if(!InventorySystems.getSystem().containsItem(textFieldItemId.getText()))
				{
					JOptionPane.showMessageDialog(restockpane, "Item not found.");
					return;
				}
				int quantity = 0;
				try
				{
					quantity = Integer.parseInt(textFieldQuantity.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(restockpane, "Specified Quantity is in an improper format.");
					return;
				}
				double price = 0;
				try
				{
					price = Double.parseDouble(textFieldWholesalePrice.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(restockpane, "Specified Price is in an improper format.");
					return;
				}
				Item accepted = InventorySystems.getSystem().getItem(textFieldItemId.getText());
				double added = store.acceptDeliveryItem(accepted, quantity, price);
				
				textFieldItemId.setText("");
				textFieldQuantity.setText("");
				textFieldWholesalePrice.setText("");
				textFieldAmountDue.setText(Double.parseDouble(textFieldAmountDue.getText()) + added + "");
				
				JTextField tempQuantity = new JTextField();
				JTextField tempItem = new JTextField();
				JTextField tempTotalPrice = new JTextField();
				
				tempQuantity.setEditable(false);
				tempItem.setEditable(false);
				tempTotalPrice.setEditable(false);
				
				tempQuantity.setText(quantity + "");
				tempItem.setText(accepted.getItemName() + " at " + price + " per " + accepted.getUnitName());
				tempTotalPrice.setText(quantity * price + "");
				
				panelTransactionDetails.add(tempQuantity, "2, " + transactionDrawingPosition + ", fill, default");
				panelTransactionDetails.add(tempItem, "4, " + transactionDrawingPosition + ", fill, default");
				panelTransactionDetails.add(tempTotalPrice, "6, " + transactionDrawingPosition + ", fill, default");
				
				tempQuantity.setColumns(10);
				tempItem.setColumns(10);
				tempTotalPrice.setColumns(10);
				
				panelTransactionDetails.revalidate();
		
				transactionDetails.add(tempQuantity);
				transactionDetails.add(tempItem);
				transactionDetails.add(tempTotalPrice);
				
				transactionDrawingPosition += 2;
			}
		});
		panel.add(btnAdd, "4, 10, fill, top");
		
		JButton btnEnd = new JButton("End");
		btnEnd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				try
				{
					Delivery d = store.endDeliveryBatch();
					InventorySystems.getSystem().addDelivery(d);
					JOptionPane.showMessageDialog(restockpane, "Delivery ended. The total amount due is " + d.getTotalPrice() + ".");
					resetFields();
					CardLayout cl = (CardLayout) con.getLayout();
					cl.show(con, Card.MANAGER.getLabel());
				}
				catch(IllegalStateException ise)
				{
					JOptionPane.showMessageDialog(restockpane, ise.getMessage());
				}
			}
		});
		panel.add(btnEnd, "4, 12");
		
		JLabel lblAmountDue = new JLabel("Amount Due:");
		panel.add(lblAmountDue, "2, 14");
		
		textFieldAmountDue = new JTextField();
		textFieldAmountDue.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldAmountDue.setEditable(false);
		textFieldAmountDue.setText("0.0");
		panel.add(textFieldAmountDue, "2, 16, fill, default");
		textFieldAmountDue.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 18");
	}
	
	public void resetFields()
	{
		textFieldItemId.setText("");
		textFieldQuantity.setText("");
		textFieldAmountDue.setText("0.0");
		textFieldWholesalePrice.setText("");
		
		for(JTextField j : transactionDetails)
		{
			panelTransactionDetails.remove(j);
		}
		
		transactionDetails = new LinkedList<JTextField>();
		transactionDrawingPosition = 4;
	}
}
