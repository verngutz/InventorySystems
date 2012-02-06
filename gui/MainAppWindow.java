package gui;

import gui.cashier.CashierCard;
import gui.cashier.forms.*;
import gui.customer.CustomerCard;
import gui.sysadmin.SystemAdminCard;
import gui.sysadmin.forms.SetupStoreCard;
import gui.manager.*;
import gui.manager.forms.*;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.*;
import com.jgoodies.forms.layout.*;
import java.awt.*;
import com.jgoodies.forms.factories.FormFactory;


public class MainAppWindow {

	
	
	private static MainAppWindow maw;
	private JFrame frame;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maw = getMainAppWindow();
					maw.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static MainAppWindow getMainAppWindow(){
		if(maw==null){
			maw = new MainAppWindow();
		}
		return maw;
	}
	public JFrame getFrame(){
		if(frame==null){
			frame = new JFrame();
			
			
		}
		return frame;
	}
	/**
	 * Create the application.
	 */
	public MainAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = getFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("System");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});

		ButtonGroup bgview = new ButtonGroup();

		JRadioButtonMenuItem rdbtnmntmSystemAdmin = new JRadioButtonMenuItem(
				"System Admin");
		rdbtnmntmSystemAdmin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					// display sysadmin view
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.SYSADMIN.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmSystemAdmin);
		bgview.add(rdbtnmntmSystemAdmin);

		JRadioButtonMenuItem rdbtnmntmManager = new JRadioButtonMenuItem(
				"Manager");
		rdbtnmntmManager.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					// display manager view
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.MANAGER.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmManager);
		bgview.add(rdbtnmntmManager);

		JRadioButtonMenuItem rdbtnmntmCashier = new JRadioButtonMenuItem(
				"Cashier");
		rdbtnmntmCashier.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					// display cashier view
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.CASHIER.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmCashier);
		bgview.add(rdbtnmntmCashier);

		JRadioButtonMenuItem rdbtnmntmCustomer = new JRadioButtonMenuItem(
				"Customer");
		rdbtnmntmCustomer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.CUSTOMER.getLabel());
				}
			}
		});
		mnFile.add(rdbtnmntmCustomer);
		bgview.add(rdbtnmntmCustomer);
		mnFile.add(mntmExit);
		
		//START OF DECK
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		//cover card
		JPanel cover = new JPanel();
		frame.getContentPane().add(cover, Card.COVER.getLabel());
		//cover end
		
		//customer start
		JPanel customer = (new CustomerCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(customer, Card.CUSTOMER.getLabel());
		
		//customer end
		
		//sysadmin_setup start
		JPanel sysadmin_setup = (new SetupStoreCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(sysadmin_setup, Card.SA1.getLabel());
		//sysadmin_setup end
		
		// sysadmin view start
		JPanel sysadmin = (new SystemAdminCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(sysadmin, Card.SYSADMIN.getLabel());
		//sysadmin view end
		
		JPanel cashier = (new CashierCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(cashier, Card.CASHIER.getLabel());
		
		JPanel panel = (new MakeSaleCard1()).getCard(frame.getContentPane());
		frame.getContentPane().add(panel, Card.CA1.getLabel());
		
		JSplitPane splitPane = (new MakeSaleCard2()).getCard(frame.getContentPane());
		frame.getContentPane().add(splitPane, Card.CA2.getLabel());
		
		JPanel makesale = (new MakeSaleCard3()).getCard(frame.getContentPane());
		frame.getContentPane().add(makesale, Card.CA3.getLabel());
		
		JPanel startday = (new StartDayCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(startday, Card.CA4.getLabel());
		
		JPanel endday = (new EndDayCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(endday, Card.CA5.getLabel());
		
		JPanel manager = (new ManagerCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(manager, Card.MANAGER.getLabel());
		
		JPanel ma1 = (new EnrollCustomerCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma1, Card.MA1.getLabel());
		
		JPanel ma21 = (new RestockCard1()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma21, Card.MA21.getLabel());
		
		JSplitPane ma22 = (new RestockCard2()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma22, Card.MA22.getLabel());
		
		JPanel ma3 = (new CustomerReportCard()).getCard(frame.getContentPane());
		//frame.getContentPane().add(ma3, Card.MA3.getLabel());
		
		JPanel ma4 = (new CashPositionCard()).getCard(frame.getContentPane());
		//frame.getContentPane().add(ma4, Card.MA4.getLabel());
		
		JPanel ma5 = (new AddItemCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma5, Card.MA5.getLabel());
		
		JPanel ma6 = (new ChangePriceCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma6, Card.MA6.getLabel());
		
		JPanel ma7 = (new AddCashierCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma7, Card.MA7.getLabel());
		
		JPanel ma8 = (new RemoveCashierCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(ma8, Card.MA8.getLabel());
		
	}
}
