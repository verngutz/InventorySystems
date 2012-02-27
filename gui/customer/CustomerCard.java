package gui.customer;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.Customer;
import system.InventorySystems;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CustomerCard
{
	private JPanel customer;
	
	private JTextField textFieldCustomerId;
	private JTextField textFieldCustomerName;
	private JTextField textFieldPointsEarned;
	private JTextField textFieldPointsRedeemed;
	private JTextField textFieldUsablePoints;
	
	public JPanel getCard(Container con)
	{
		if(customer==null)
		{
			customer=new JPanel();
			init();
		}
		return customer;
	}
	
	public void init()
	{
		customer.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("max(119dlu;default):grow"),
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
		
		JLabel lblCustomerId = new JLabel("Customer ID:");
		customer.add(lblCustomerId, "2, 2, right, default");
		
		textFieldCustomerId = new JTextField();
		customer.add(textFieldCustomerId, "4, 2, left, default");
		textFieldCustomerId.setColumns(10);
		
		JButton btnNewButton = new JButton("Inquire");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				String customerIdString = textFieldCustomerId.getText();
				int customerId = 0;
				try
				{
					customerId = Integer.parseInt(customerIdString);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(customer, "Specified Customer ID is in an improper format.");
					return;
				}
				Customer c = InventorySystems.getSystem().getCustomer(customerId);
				if(c == null)
				{
					JOptionPane.showMessageDialog(customer, "Customer not found.");
					return;
				}
				textFieldCustomerName.setText(c.getFirstName() + " " + c.getLastName());
				textFieldPointsEarned.setText(c.getPtsEarned() + "");
				textFieldPointsRedeemed.setText(c.getPtsRedeemed() + "");
				textFieldUsablePoints.setText(c.getUsablePoints() + "");
			}
		});
		customer.add(btnNewButton, "4, 4, left, default");
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		customer.add(lblCustomerName, "2, 6, right, default");
		
		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setEditable(false);
		customer.add(textFieldCustomerName, "4, 6, fill, default");
		textFieldCustomerName.setColumns(10);
		
		JLabel lblPointsEarned = new JLabel("Points Earned:");
		customer.add(lblPointsEarned, "2, 8, right, default");
		
		textFieldPointsEarned = new JTextField();
		textFieldPointsEarned.setEditable(false);
		customer.add(textFieldPointsEarned, "4, 8, fill, default");
		textFieldPointsEarned.setColumns(10);
		
		JLabel lblPointsRedeemed = new JLabel("Points Redeemed:");
		customer.add(lblPointsRedeemed, "2, 10, right, default");
		
		textFieldPointsRedeemed = new JTextField();
		textFieldPointsRedeemed.setEditable(false);
		customer.add(textFieldPointsRedeemed, "4, 10, fill, default");
		textFieldPointsRedeemed.setColumns(10);
		
		JLabel lblUsablePoints = new JLabel("Usable Points:");
		customer.add(lblUsablePoints, "2, 12, right, default");
		
		textFieldUsablePoints = new JTextField();
		textFieldUsablePoints.setEditable(false);
		customer.add(textFieldUsablePoints, "4, 12, fill, default");
		textFieldUsablePoints.setColumns(10);
	}
	
	public void resetFields()
	{
		textFieldCustomerId.setText("");
		textFieldCustomerName.setText("");
		textFieldPointsEarned.setText("");
		textFieldPointsRedeemed.setText("");
		textFieldUsablePoints.setText("");
	}
}
