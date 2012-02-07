package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class MakeSaleCard3 {
	private JPanel makesale;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public JPanel getCard(Container con){
		if(makesale==null){
			makesale = new JPanel();
			this.con = con;
			init();
		}
		return makesale;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		makesale.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("90dlu:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblLoyalCustomerId = new JLabel("Loyal Customer ID:");
		makesale.add(lblLoyalCustomerId, "2, 2, right, default");
		
		textField = new JTextField();
		makesale.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Fetch Customer Info");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//fetch info
			}
		});
		makesale.add(btnNewButton, "4, 4, left, default");
		
		JLabel lblNewLabel = new JLabel("Usable Points:");
		makesale.add(lblNewLabel, "2, 6, right, default");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		makesale.add(textField_1, "4, 6, left, default");
		textField_1.setColumns(10);
		
		JLabel lblPointsToUse = new JLabel("Points to Use:");
		makesale.add(lblPointsToUse, "2, 8, right, default");
		
		textField_2 = new JTextField();
		makesale.add(textField_2, "4, 8, left, default");
		textField_2.setColumns(10);
		
		JLabel lblAmountDuel = new JLabel("Amount Due:");
		makesale.add(lblAmountDuel, "2, 12, right, default");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		makesale.add(textField_3, "4, 12, left, default");
		textField_3.setColumns(10);
		
		JButton btnEndTransaction = new JButton("End Transaction");
		btnEndTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		makesale.add(btnCancel, "2, 16");
		makesale.add(btnEndTransaction, "4, 16, left, default");
	}
}
