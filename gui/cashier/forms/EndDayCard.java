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

public class EndDayCard {
	private JPanel endday;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public JPanel getCard(Container con){
		if(endday==null){
			endday = new JPanel();
			this.con = con;
			init();
		}
		return endday;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		endday.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblStoreId = new JLabel("Store ID:");
		endday.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		endday.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier Index:");
		endday.add(lblCashierIndex, "2, 4, right, default");
		
		textField_1 = new JTextField();
		endday.add(textField_1, "4, 4, left, default");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		endday.add(btnNewButton, "4, 8, left, default");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		endday.add(btnCancel, "4, 10, left, default");
		
	}
}
