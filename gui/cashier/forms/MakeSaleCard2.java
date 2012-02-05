package gui.cashier.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class MakeSaleCard2 {
	private JSplitPane makesale;
	private Container con;
	
	private JTextField txtItem;
	private JTextField txtQuantity;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public JSplitPane getCard(Container con){
		if(makesale==null){
			makesale = new JSplitPane();
			this.con = con;
			init();
		}
		return makesale;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		makesale = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane();
		makesale.setRightComponent(scrollPane);
		
		txtItem = new JTextField();
		txtItem.setEditable(false);
		txtItem.setText("Item");
		scrollPane.setViewportView(txtItem);
		txtItem.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setEditable(false);
		txtQuantity.setText("Quantity");
		scrollPane.setRowHeaderView(txtQuantity);
		txtQuantity.setColumns(10);
		
		JPanel panel = new JPanel();
		makesale.setLeftComponent(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default):grow"),},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblItemId = new JLabel("Item ID:");
		panel.add(lblItemId, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		panel.add(lblQuantity, "2, 4, right, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//add to receipt and append txtItem and txtQuantity
			}
		});
		panel.add(btnAdd, "4, 8, fill, top");
		
		JButton btnEnd = new JButton("End");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//go to payment page
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA3.getLabel());
			}
		});
		panel.add(btnEnd, "4, 10");
		
		JLabel lblAmountDue = new JLabel("Amount Due:");
		panel.add(lblAmountDue, "2, 14");
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setText("0.0");
		panel.add(textField_2, "2, 16, fill, default");
		textField_2.setColumns(10);
		
	}
}
