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

public class EnrollCustomerCard {
	private JPanel panel;
	private Container con;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_5;
	
	public JPanel getCard(Container con){
		panel = new JPanel();
		this.con = con;
		init();
		return panel;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEnrollCustomer = new JLabel("Enroll Customer:");
		panel.add(lblEnrollCustomer, "2, 2, right, default");
		
		JLabel lblCustomerId = new JLabel("Customer ID:");
		panel.add(lblCustomerId, "2, 4, right, default");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		panel.add(textField_5, "4, 4, fill, default");
		textField_5.setColumns(10);
		
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
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//submit form
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnOk, "2, 18");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 20");
		
		
	}
}
