package gui.sysadmin.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import system.SystemBox;
import system.Store;

public class SetupStoreCard {
	private JPanel sysadmin_setup;
	Container con;
	
	private JTextField textField;
	private JTextField textField_1;
	
	public JPanel getCard(Container con){
		if(sysadmin_setup==null){
			sysadmin_setup = new JPanel();
			this.con = con;
			init();
		}
		return sysadmin_setup;
	}
	public void init(){
		sysadmin_setup.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewStore = new JLabel("New Store:");
		sysadmin_setup.add(lblNewStore, "2, 2");
		
		JLabel lblStartingCash = new JLabel("Starting Cash:");
		lblStartingCash.setHorizontalAlignment(SwingConstants.RIGHT);
		sysadmin_setup.add(lblStartingCash, "2, 4, right, default");
		
		textField = new JTextField();
		sysadmin_setup.add(textField, "4, 4, fill, default");
		textField.setColumns(10);
		
		JLabel lblCashPerCashier = new JLabel("Cash per Cashier:");
		lblCashPerCashier.setHorizontalAlignment(SwingConstants.RIGHT);
		sysadmin_setup.add(lblCashPerCashier, "2, 6, right, default");
		
		textField_1 = new JTextField();
		sysadmin_setup.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//submit and parse the form then go back to sysadmin
				double cash = 0;
				try
				{
					cash = Double.parseDouble(textField.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin_setup), "Supplied Starting Cash is in an improper format.");
					return;
				}
				double cashPerCashier = 0;
				try
				{
					cashPerCashier = Double.parseDouble(textField_1.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin_setup), "Supplied Cash per Cashier is in an improper format.");
					return;
				}
				int storeId = SystemBox.getSystem().nextStoreId();
				SystemBox.getSystem().addStore(new Store(storeId, cash, cashPerCashier));
				JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin_setup), "Successfully set up store with ID " + storeId + ".");
				textField.setText("");
				textField_1.setText("");
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.SYSADMIN.getLabel());
			}
		});
		sysadmin_setup.add(btnAdd, "2, 12");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.SYSADMIN.getLabel());
			}
		});
		sysadmin_setup.add(btnCancel, "2, 14");
		sysadmin_setup.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, btnAdd, btnCancel, lblStartingCash, lblCashPerCashier, lblNewStore}));
		
	}
}