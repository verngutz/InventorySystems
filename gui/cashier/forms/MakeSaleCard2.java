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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_5;
	private JTextField textField_8;
	
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
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;default):grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblQuantity_1 = new JLabel("Quantity");
		panel_1.add(lblQuantity_1, "2, 2");
		
		JLabel lblItem = new JLabel("Item");
		panel_1.add(lblItem, "4, 2");
		
		JLabel lblPrice = new JLabel("Price");
		panel_1.add(lblPrice, "6, 2");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel_1.add(textField_3, "2, 4, fill, default");
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel_1.add(textField_4, "4, 4, fill, default");
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		panel_1.add(textField_5, "6, 4, fill, default");
		textField_5.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		panel_1.add(textField_7, "2, 6, fill, default");
		textField_7.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		panel_1.add(textField_6, "4, 6, fill, default");
		textField_6.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		panel_1.add(textField_8, "6, 6, fill, default");
		textField_8.setColumns(10);
		
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
				//add to receipt and append Quantity and Item in the ScrollPane
				//my suggestion is to loop it, and keep adding JTextField()'s to 'panel_1'
				//the increments are by 2's. an example is textfield_3,4,7,6
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
