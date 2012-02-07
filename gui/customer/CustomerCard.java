package gui.customer;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import system.SystemBox;
import system.Customer;

public class CustomerCard
{
	private JPanel customer;
	
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	private Container con;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel getCard(Container con)
	{
		if(customer==null)
		{
			customer=new JPanel();
			this.con = con;
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
		
		textField_2 = new JTextField();
		customer.add(textField_2, "4, 2, left, default");
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Inquire");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				String customerIdString = textField_2.getText();
				int customerId = 0;
				try
				{
					customerId = Integer.parseInt(customerIdString);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(customer), "Specified Customer ID is in an improper format.");
					return;
				}
				Customer c = null;
				try
				{
					c = SystemBox.getSystem().getCustomer(customerId);
				}
				catch(IndexOutOfBoundsException ioobe)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(customer), "Customer not found.");
					return;
				}
				textField_3.setText(c.getFirstName() + " " + c.getLastName());
				textField_4.setText(c.getPointsEarned() + "");
				textField_5.setText(c.getPointsRedeemed() + "");
				textField_6.setText(c.getUsablePoints() + "");
			}
		});
		customer.add(btnNewButton, "4, 4, left, default");
		
		JLabel lblNewLabel = new JLabel("Customer Name:");
		customer.add(lblNewLabel, "2, 6, right, default");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		customer.add(textField_3, "4, 6, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblPoints = new JLabel("Points Earned:");
		customer.add(lblPoints, "2, 8, right, default");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		customer.add(textField_4, "4, 8, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblPointsRedeamed = new JLabel("Points Redeemed:");
		customer.add(lblPointsRedeamed, "2, 10, right, default");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		customer.add(textField_5, "4, 10, fill, default");
		textField_5.setColumns(10);
		
		JLabel lblUsablePoints = new JLabel("Usable Points:");
		customer.add(lblUsablePoints, "2, 12, right, default");
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		customer.add(textField_6, "4, 12, fill, default");
		textField_6.setColumns(10);
	}
	
	public void resetFields()
	{
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
	}
}
