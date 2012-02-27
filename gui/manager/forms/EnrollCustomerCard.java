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

import system.Customer;
import system.InventorySystems;

public class EnrollCustomerCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldGender;
	private JTextField textFieldAge;
	private JTextField textFieldAddress;
	
	public JPanel getCard(Container con)
	{
		panel = new JPanel();
		this.con = con;
		init();
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
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblEnrollCustomer = new JLabel("Enroll Customer:");
		panel.add(lblEnrollCustomer, "2, 2, right, default");
		
		JLabel lblFirstName = new JLabel("First Name:");
		panel.add(lblFirstName, "2, 6, right, default");
		
		textFieldFirstName = new JTextField();
		panel.add(textFieldFirstName, "4, 6, fill, default");
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		panel.add(lblLastName, "2, 8, right, default");
		
		textFieldLastName = new JTextField();
		panel.add(textFieldLastName, "4, 8, fill, default");
		textFieldLastName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		panel.add(lblAddress, "2, 10, right, default");
		
		textFieldAddress = new JTextField();
		panel.add(textFieldAddress, "4, 10, fill, default");
		textFieldAddress.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		panel.add(lblGender, "2, 12, right, default");
		
		textFieldGender = new JTextField();
		panel.add(textFieldGender, "4, 12, fill, default");
		textFieldGender.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		panel.add(lblAge, "2, 14, right, default");
		
		textFieldAge = new JTextField();
		panel.add(textFieldAge, "4, 14, fill, default");
		textFieldAge.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				int id = InventorySystems.getSystem().nextCustomerId();
				int age = 0;
				if(!textFieldAge.getText().equals(""))
				{
					try
					{
						age = Integer.parseInt(textFieldAge.getText());
					}
					catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(panel), "Supplied Age is in an improper format.");
						return;
					}
				}
				InventorySystems.getSystem().addCustomer(new Customer(textFieldFirstName.getText(), textFieldLastName.getText(), id, textFieldAddress.getText(), textFieldGender.getText(), age));
				JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(panel), "Successfully enrolled customer with ID " + id + ".");
				returnToPreviousScreen();
			}
		});
		panel.add(btnOk, "2, 18");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				returnToPreviousScreen();
			}
		});
		panel.add(btnCancel, "2, 20");	
	}
	
	public void resetFields()
	{
		textFieldAddress.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
		textFieldGender.setText("");
		textFieldAge.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
