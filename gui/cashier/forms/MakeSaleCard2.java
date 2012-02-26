package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import system.Cashier;
import system.Item;
import system.Store;
import system.SystemBox;

import java.util.LinkedList;

public class MakeSaleCard2 {
	private JSplitPane makesale;
	private Container con;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel_1;
	
	private LinkedList<JTextField> transactionDetails;
	private int transactionDrawingPosition;
	
	private MakeSaleCard3 makeSaleCard3;
	private Cashier cashier;
	
	public MakeSaleCard2(MakeSaleCard3 makeSaleCard3)
	{
		this.makeSaleCard3 = makeSaleCard3;
	}
	
	public void setCashier(Cashier c)
	{
		cashier = c;
	}
	
	public JSplitPane getCard(Container con)
	{
		if(makesale==null){
			makesale = new JSplitPane();
			this.con = con;
			init();
		}
		return makesale;
	}
	
	public void init()
	{
		JScrollPane scrollPane = new JScrollPane();
		makesale.setRightComponent(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FormLayout(
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
		panel_1.add(lblQuantity_1, "2, 2");
		
		JLabel lblItem = new JLabel("Item");
		panel_1.add(lblItem, "4, 2");
		
		JLabel lblPrice = new JLabel("Price");
		panel_1.add(lblPrice, "6, 2");
		
		transactionDetails = new LinkedList<JTextField>();
		transactionDrawingPosition = 4;
		
		JPanel panel = new JPanel();
		makesale.setLeftComponent(panel);
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
		
		JLabel lblItemId = new JLabel("Item ID:");
		panel.add(lblItemId, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		panel.add(lblQuantity, "2, 4, right, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(makesale, "No Item Code specified.");
					return;
				}
				if(!SystemBox.getSystem().containsItem(textField.getText()))
				{
					JOptionPane.showMessageDialog(makesale, "Item not found.");
					return;
				}
				int quantity = 0;
				try
				{
					quantity = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(makesale, "Specified Quantity is in an improper format.");
					return;
				}
				if(quantity <= 0)
				{
					JOptionPane.showMessageDialog(makesale, "Specified Quantity must be greater than 0.");
					return;
				}
				Item toSell = SystemBox.getSystem().getItem(textField.getText());
				cashier.sell(toSell, quantity);
				double added = quantity * toSell.getUnitPrice();
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText(Double.parseDouble(textField_2.getText()) + added + "");
				
				JTextField tempQuantity = new JTextField();
				JTextField tempItem = new JTextField();
				JTextField tempTotalPrice = new JTextField();
				
				tempQuantity.setEditable(false);
				tempItem.setEditable(false);
				tempTotalPrice.setEditable(false);
				
				tempQuantity.setText(quantity + "");
				tempItem.setText(toSell.getItemName() + " at " + toSell.getUnitPrice() + " per " + toSell.getUnitName());
				tempTotalPrice.setText(added + "");
				
				panel_1.add(tempQuantity, "2, " + transactionDrawingPosition + ", fill, default");
				panel_1.add(tempItem, "4, " + transactionDrawingPosition + ", fill, default");
				panel_1.add(tempTotalPrice, "6, " + transactionDrawingPosition + ", fill, default");
				
				tempQuantity.setColumns(10);
				tempItem.setColumns(10);
				tempTotalPrice.setColumns(10);
				
				panel_1.revalidate();
		
				transactionDetails.add(tempQuantity);
				transactionDetails.add(tempItem);
				transactionDetails.add(tempTotalPrice);
				
				transactionDrawingPosition += 2;
			}
		});
		panel.add(btnAdd, "4, 8, fill, top");
		
		JButton btnEnd = new JButton("End");
		btnEnd.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				resetFields();
				makeSaleCard3.setCashier(cashier);
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA3.getLabel());
			}
		});
		
		panel.add(btnEnd, "4, 10");
		
		JLabel lblAmountDue = new JLabel("Amount Due:");
		panel.add(lblAmountDue, "2, 14");
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setText("0.0");
		panel.add(textField_2, "2, 16, fill, default");
		textField_2.setColumns(10);
		
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
		panel.add(btnCancel, "2, 18");
	}
	
	public void resetFields()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("0.0");
		
		for(JTextField j : transactionDetails)
		{
			panel_1.remove(j);
		}
		
		transactionDetails = new LinkedList<JTextField>();
		transactionDrawingPosition = 4;
	}
}
