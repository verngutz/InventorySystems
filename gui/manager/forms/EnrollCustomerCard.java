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

import system.SystemBox;
import system.Customer;

public class EnrollCustomerCard 
{
	private JPanel panel;
	private Container con;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	
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
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		panel.add(lblLastName, "2, 8, right, default");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		panel.add(lblAddress, "2, 10, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 10, fill, default");
		textField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		panel.add(lblGender, "2, 12, right, default");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "4, 12, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblAgel = new JLabel("Age:");
		panel.add(lblAgel, "2, 14, right, default");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "4, 14, fill, default");
		textField_4.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				int id = SystemBox.getSystem().nextCustomerId();
				int age = 0;
				try
				{
					age = Integer.parseInt(textField_4.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(panel), "Supplied Age is in an improper format.");
					return;
				}
				SystemBox.getSystem().addCustomer(new Customer(textField_1.getText(), textField_2.getText(), id, textField.getText(), textField_3.getText(), age));
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
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
	}
	
	public void returnToPreviousScreen()
	{
		resetFields();
		CardLayout cl = (CardLayout) con.getLayout();
		cl.show(con, Card.MANAGER.getLabel());
	}
}
