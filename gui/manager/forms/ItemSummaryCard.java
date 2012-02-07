package gui.manager.forms;

import gui.Card;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class ItemSummaryCard {
	private JSplitPane reportpane;
	private Container con;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_5;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField textField_8;
	
	public JSplitPane getCard(Container con){
		if(reportpane==null){
			reportpane = new JSplitPane();
			this.con = con;
			init();
		}
		return reportpane;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(){
		reportpane = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane();
		reportpane.setRightComponent(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("min:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblItem = new JLabel("Quantity");
		panel_1.add(lblItem, "2, 2");
		
		JLabel lblPrice = new JLabel("Customer");
		panel_1.add(lblPrice, "4, 2");
		
		JLabel lblPrice_1 = new JLabel("Price");
		panel_1.add(lblPrice_1, "6, 2");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel_1.add(textField_4, "2, 4, fill, default");
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		panel_1.add(textField_5, "4, 4, fill, default");
		textField_5.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		panel_1.add(textField_7, "6, 4, fill, default");
		textField_7.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		panel_1.add(textField_6, "2, 6, fill, default");
		textField_6.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel_1.add(textField_3, "4, 6, fill, default");
		textField_3.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		panel_1.add(textField_8, "6, 6, fill, default");
		textField_8.setColumns(10);
		
		JPanel panel = new JPanel();
		reportpane.setLeftComponent(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
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
		
		JButton btnInquire = new JButton("Inquire");
		btnInquire.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//do your shit.
			}
		});
		panel.add(btnInquire, "2, 4");
		
		JLabel lblCashOnStore = new JLabel("Quantity Sold:");
		panel.add(lblCashOnStore, "2, 6, right, default");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblTotalCash = new JLabel("Amount Made:");
		panel.add(lblTotalCash, "2, 8, right, default");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 12");
		
	}
}
