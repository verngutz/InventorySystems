package gui.cashier.forms;

import gui.Card;

import java.awt.Container;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class MakeSaleCard1 {
	private JPanel makesale;
	private Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel getCard(Container con){
		if(makesale==null){
			makesale = new JPanel();
			this.con = con;
			init();
		}
		return makesale;
	}
	
	public void init(){
		makesale.setLayout(new FormLayout(new ColumnSpec[] {
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
		makesale.add(lblStoreId, "2, 2, right, default");
		
		textField = new JTextField();
		makesale.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblCashierIndex = new JLabel("Cashier Index:");
		makesale.add(lblCashierIndex, "2, 4, right, default");
		
		textField_1 = new JTextField();
		makesale.add(textField_1, "4, 4, left, default");
		textField_1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA2.getLabel());
			}
		});
		makesale.add(btnOk, "2, 8");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CASHIER.getLabel());
			}
		});
		makesale.add(btnCancel, "2, 10");
		
	}
}
