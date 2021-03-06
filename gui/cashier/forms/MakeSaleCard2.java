package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import system.Cashier;
import system.InventorySystems;
import system.Item;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MakeSaleCard2 
{
	private JSplitPane makesale;
	private Container con;
	private JTextField textFieldItemId;
	private JTextField textFieldQuantity;
	private JTextField textFieldAmountDue;
	private JPanel panelTransactionDetails;
	
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
		if(makesale==null)
		{
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
		
		JLabel lblPrice = new JLabel("Price");
		panelTransactionDetails.add(lblPrice, "6, 2");
		
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
		
		textFieldItemId = new JTextField();
		panel.add(textFieldItemId, "4, 2, fill, default");
		textFieldItemId.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		panel.add(lblQuantity, "2, 4, right, default");
		
		textFieldQuantity = new JTextField();
		panel.add(textFieldQuantity, "4, 4, fill, default");
		textFieldQuantity.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(textFieldItemId.getText().equals(""))
				{
					JOptionPane.showMessageDialog(makesale, "No Item Code specified.");
					return;
				}
				if(!InventorySystems.getSystem().containsItem(textFieldItemId.getText()))
				{
					JOptionPane.showMessageDialog(makesale, "Item not found.");
					return;
				}
				int quantity = 0;
				try
				{
					quantity = Integer.parseInt(textFieldQuantity.getText());
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
				Item toSell = InventorySystems.getSystem().getItem(textFieldItemId.getText());
				cashier.sell(toSell, quantity);
				double added = quantity * toSell.getUnitPrice();
				
				textFieldItemId.setText("");
				textFieldQuantity.setText("");
				textFieldAmountDue.setText(Double.parseDouble(textFieldAmountDue.getText()) + added + "");
				
				JTextField tempQuantity = new JTextField();
				JTextField tempItem = new JTextField();
				JTextField tempTotalPrice = new JTextField();
				
				tempQuantity.setEditable(false);
				tempItem.setEditable(false);
				tempTotalPrice.setEditable(false);
				
				tempQuantity.setText(quantity + "");
				tempItem.setText(toSell.getItemName() + " at " + toSell.getUnitPrice() + " per " + toSell.getUnitName());
				tempTotalPrice.setText(added + "");
				
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
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 18");
	}
	
	public void resetFields()
	{
		textFieldItemId.setText("");
		textFieldQuantity.setText("");
		textFieldAmountDue.setText("0.0");
		
		for(JTextField j : transactionDetails)
		{
			panelTransactionDetails.remove(j);
		}
		
		transactionDetails = new LinkedList<JTextField>();
		transactionDrawingPosition = 4;
	}
}
