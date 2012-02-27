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

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ChangePriceCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textFieldItemCode;
	private JTextField textFieldItemName;
	private JTextField textFieldPrice;
	private JTextField textFieldNewPrice;
	
	public JPanel getCard(Container con)
	{
		if(panel==null)
		{
			panel = new JPanel();
			this.con = con;
			init();
		}
		return panel;
	}
	
	public void init()
	{
		panel.setLayout(new FormLayout(
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
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblItemCode = new JLabel("Item Code:");
		panel.add(lblItemCode, "2, 2, right, default");
		
		textFieldItemCode = new JTextField();
		panel.add(textFieldItemCode, "4, 2, fill, default");
		textFieldItemCode.setColumns(10);
		
		JButton btnInquireCode = new JButton("Inquire Code");
		btnInquireCode.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textFieldItemCode.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
				}
				else if(InventorySystems.getSystem().containsItem(textFieldItemCode.getText()))
				{
					textFieldItemName.setText(InventorySystems.getSystem().getItem(textFieldItemCode.getText()).getItemName());
					textFieldPrice.setText(InventorySystems.getSystem().getItem(textFieldItemCode.getText()).getUnitPrice() + "");
				}
				else
				{
					JOptionPane.showMessageDialog(panel, "Item not found.");
				}
			}
		});
		panel.add(btnInquireCode, "2, 4");
		
		JLabel lblItemName = new JLabel("Item Name:");
		panel.add(lblItemName, "2, 6, right, default");
		
		textFieldItemName = new JTextField();
		textFieldItemName.setEditable(false);
		panel.add(textFieldItemName, "4, 6, fill, default");
		textFieldItemName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		panel.add(lblPrice, "2, 8, right, default");
		
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		panel.add(textFieldPrice, "4, 8, fill, default");
		textFieldPrice.setColumns(10);
		
		JLabel lblNewPrice = new JLabel("New Price:");
		panel.add(lblNewPrice, "2, 12, right, default");
		
		textFieldNewPrice = new JTextField();
		panel.add(textFieldNewPrice, "4, 12, fill, default");
		textFieldNewPrice.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(textFieldItemCode.getText().equals(""))
				{
					JOptionPane.showMessageDialog(panel, "No Item Code specified.");
					return;
				}
				if(!InventorySystems.getSystem().containsItem(textFieldItemCode.getText()))
				{
					JOptionPane.showMessageDialog(panel, "Item not found.");
					return;
				}
				double newPrice = 0;
				try
				{
					newPrice = Double.parseDouble(textFieldNewPrice.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(panel, "Specified New Price is in an improper format.");
					return;
				}
				InventorySystems.getSystem().getItem(textFieldItemCode.getText()).setUnitPrice(newPrice);
				JOptionPane.showMessageDialog(panel, "Price successfully changed.");
				returnToPreviousScreen();
			}
		});
		panel.add(btnOk, "2, 14");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				returnToPreviousScreen();
			}
		});
		panel.add(btnCancel, "2, 16");
	}
	
	public void resetFields()
	{
		textFieldItemCode.setText("");
		textFieldItemName.setText("");
		textFieldPrice.setText("");
		textFieldNewPrice.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
