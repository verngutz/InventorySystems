package gui.manager.forms;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import system.Cashier;
import system.InventorySystems;
import system.Store;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CashPositionCard 
{
	private JSplitPane reportpane;
	private Container con;
	private JTextField textFieldStoreId;
	private JTextField textFieldCashOnStore;
	private JTextField textFieldTotalCash;
	private JPanel panelCashPosition;
	
	private LinkedList<JTextField> cashierDetails;
	
	public JSplitPane getCard(Container con)
	{
		if(reportpane==null)
		{
			reportpane = new JSplitPane();
			this.con = con;
			init();
		}
		return reportpane;
	}
	
	public void init()
	{
		JScrollPane scrollPane = new JScrollPane();
		reportpane.setRightComponent(scrollPane);
		
		panelCashPosition = new JPanel();
		scrollPane.setViewportView(panelCashPosition);
		panelCashPosition.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.MIN_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("min:grow"),
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
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblCashierId = new JLabel("Cashier ID");
		panelCashPosition.add(lblCashierId, "2, 2");
		
		JLabel lblCash = new JLabel("Cash");
		panelCashPosition.add(lblCash, "4, 2");
		
		cashierDetails = new LinkedList<JTextField>();
		
		JPanel panel = new JPanel();
		reportpane.setLeftComponent(panel);
		panel.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
			FormFactory.MIN_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("max(55dlu;default):grow"),
		},
		new RowSpec[] 
		{
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
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JLabel lblStoreId = new JLabel("Store ID:");
		panel.add(lblStoreId, "2, 2, right, default");
		
		textFieldStoreId = new JTextField();
		panel.add(textFieldStoreId, "4, 2, fill, default");
		textFieldStoreId.setColumns(10);
		
		JButton btnInquire = new JButton("Inquire");
		btnInquire.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				int storeId = 0;
				try
				{
					storeId = Integer.parseInt(textFieldStoreId.getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(reportpane, "Specified Store ID is in an improper format.");
					return;
				}
				Store s = InventorySystems.getSystem().getStore(storeId);
				if(s == null)
				{
					JOptionPane.showMessageDialog(reportpane, "Store not found.");
					return;
				}
				double cash = s.getTotalCash();
				textFieldCashOnStore.setText(cash + "");
				
				for(JTextField j : cashierDetails)
				{
					panelCashPosition.remove(j);
				}
				cashierDetails = new LinkedList<JTextField>();
				
				int y = 4;
				for(Cashier c : s.getCashiers())
				{
					JTextField tempCashier = new JTextField();
					tempCashier.setText(c.getIndex() + "");
					tempCashier.setEditable(false);
					panelCashPosition.add(tempCashier, "2, " + y + ", fill, default");
					tempCashier.setColumns(10);
					cashierDetails.add(tempCashier);
					
					JTextField tempCash = new JTextField();
					double currCash = c.getCash();
					cash += currCash;
					tempCash.setText(currCash + "");
					tempCash.setEditable(false);
					panelCashPosition.add(tempCash, "4, " + y + ", fill, default");
					tempCash.setColumns(10);
					cashierDetails.add(tempCash);
					
					y+=2;
				}
				
				panelCashPosition.revalidate();
				textFieldTotalCash.setText(cash + "");
			}
		});
		panel.add(btnInquire, "2, 4");
		
		JLabel lblCashOnStore = new JLabel("Cash on Store:");
		panel.add(lblCashOnStore, "2, 6, right, default");
		
		textFieldCashOnStore = new JTextField();
		textFieldCashOnStore.setEditable(false);
		panel.add(textFieldCashOnStore, "4, 6, fill, default");
		textFieldCashOnStore.setColumns(10);
		
		JLabel lblTotalCash = new JLabel("Total Cash:");
		panel.add(lblTotalCash, "2, 8, right, default");
		
		textFieldTotalCash = new JTextField();
		textFieldTotalCash.setEditable(false);
		panel.add(textFieldTotalCash, "4, 8, fill, default");
		textFieldTotalCash.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				resetFields();
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.MANAGER.getLabel());
			}
		});
		panel.add(btnCancel, "2, 12");
	}
	
	public void resetFields()
	{
		textFieldStoreId.setText("");
		textFieldCashOnStore.setText("");
		textFieldTotalCash.setText("");
		
		for(JTextField j : cashierDetails)
		{
			panelCashPosition.remove(j);
		}
		
		cashierDetails = new LinkedList<JTextField>();
	}
}
