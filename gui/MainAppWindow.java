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

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.event.*;
import com.jgoodies.forms.layout.*;
import java.awt.*;
import java.io.File;

import com.jgoodies.forms.factories.FormFactory;

import system.Customer;
import system.Item;
import system.Tester;
import system.dao.CustomerDao;
import system.dao.ItemDao;
import system.dao.impl.CustomerDaoImpl;
import system.dao.impl.ItemDaoImpl;
import system.dao.impl.SessionFactorySingleton;

public class MainAppWindow 
{
	private static MainAppWindow maw;
	private JFrame frame;
	
	private CustomerCard customerCard;
	
	private SetupStoreCard setupStoreCard;
	
	private AddItemCard addItemCard;
	private EnrollCustomerCard enrollCustomerCard;
	private ChangePriceCard changePriceCard;
	private AddCashierCard addCashierCard;
	private RemoveCashierCard removeCashierCard;
	private RestockCard1 restockCard1;
	private RestockCard2 restockCard2;
	private ItemSummaryCard itemSummaryCard;
	private CashPositionCard cashPositionCard;
	private CustomerReportCard customerReportCard;
	
	private MakeSaleCard1 makeSaleCard1;
	private MakeSaleCard2 makeSaleCard2;
	private MakeSaleCard3 makeSaleCard3;
	
	private StartDayCard startDayCard;
	private EndDayCard endDayCard;
	
	private static SessionFactory factory;
	
	public static SessionFactory getSessionFactory()
	{
		return factory;
	}
	
	public static void main(String[] args) 
	{
		Configuration configuration = new Configuration();
        configuration.configure(new File("hibernate.cfg.xml"));
        factory = configuration.buildSessionFactory();
        
        // set the singleton do all the dao objects can have easy access to it
        SessionFactorySingleton.setSessionFactory(factory);
        
		args = new String[1];
		args[0] = "samplein.txt";
		if(args.length > 0)
		{
			new Tester(args[0]);
		}
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					maw = getMainAppWindow();
					maw.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

        SessionFactorySingleton.getSessionFactory().close();
	}
	
	public static MainAppWindow getMainAppWindow()
	{
		if(maw==null)
		{
			maw = new MainAppWindow();
		}
		return maw;
	}
	
	public JFrame getFrame()
	{
		if(frame==null)
		{
			frame = new JFrame();
		}
		return frame;
	}
	
	public MainAppWindow() 
	{
		initialize();
	}

	private void initialize() 
	{
		frame = getFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("System");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				System.exit(0);
			}
		});

		ButtonGroup bgview = new ButtonGroup();

		JRadioButtonMenuItem rdbtnmntmSystemAdmin = new JRadioButtonMenuItem("System Admin");
		rdbtnmntmSystemAdmin.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					setupStoreCard.resetFields();
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.SYSADMIN.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmSystemAdmin);
		bgview.add(rdbtnmntmSystemAdmin);

		JRadioButtonMenuItem rdbtnmntmManager = new JRadioButtonMenuItem("Manager");
		rdbtnmntmManager.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					addItemCard.resetFields();
					enrollCustomerCard.resetFields();
					changePriceCard.resetFields();
					addCashierCard.resetFields();
					removeCashierCard.resetFields();
					restockCard1.resetFields();
					restockCard2.resetFields();
					cashPositionCard.resetFields();
					customerReportCard.resetFields();
					itemSummaryCard.resetFields();
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.MANAGER.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmManager);
		bgview.add(rdbtnmntmManager);

		JRadioButtonMenuItem rdbtnmntmCashier = new JRadioButtonMenuItem("Cashier");
		rdbtnmntmCashier.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					makeSaleCard1.resetFields();
					makeSaleCard2.resetFields();
					makeSaleCard3.resetFields();
					startDayCard.resetFields();
					endDayCard.resetFields();
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.CASHIER.getLabel());
				} 
			}
		});
		mnFile.add(rdbtnmntmCashier);
		bgview.add(rdbtnmntmCashier);

		JRadioButtonMenuItem rdbtnmntmCustomer = new JRadioButtonMenuItem("Customer");
		rdbtnmntmCustomer.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					customerCard.resetFields();
					CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
					cl.show(frame.getContentPane(), Card.CUSTOMER.getLabel());
				}
			}
		});
		mnFile.add(rdbtnmntmCustomer);
		bgview.add(rdbtnmntmCustomer);
		mnFile.add(mntmExit);
		
		// START OF DECK
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		// cover card
		JPanel cover = new JPanel();
		frame.getContentPane().add(cover, Card.COVER.getLabel());
		// cover end
		
		// customer start
		customerCard = new CustomerCard();
		JPanel customer = customerCard.getCard(frame.getContentPane());
		frame.getContentPane().add(customer, Card.CUSTOMER.getLabel());
		
		// customer end
		
		// sysadmin view start
		setupStoreCard = new SetupStoreCard();
		JPanel sysadmin_setup = setupStoreCard.getCard(frame.getContentPane());
		frame.getContentPane().add(sysadmin_setup, Card.SA1.getLabel());

		JPanel sysadmin = (new SystemAdminCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(sysadmin, Card.SYSADMIN.getLabel());
		// sysadmin view end
		
		// cashier view
		JPanel cashier = (new CashierCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(cashier, Card.CASHIER.getLabel());
		
		makeSaleCard3 = new MakeSaleCard3();
		JPanel makesale = makeSaleCard3.getCard(frame.getContentPane());
		frame.getContentPane().add(makesale, Card.CA3.getLabel());
		
		makeSaleCard2 = new MakeSaleCard2(makeSaleCard3);
		JSplitPane splitPane = makeSaleCard2.getCard(frame.getContentPane());
		frame.getContentPane().add(splitPane, Card.CA2.getLabel());
		
		makeSaleCard1 = new MakeSaleCard1(makeSaleCard2);
		JPanel panel = makeSaleCard1.getCard(frame.getContentPane());
		frame.getContentPane().add(panel, Card.CA1.getLabel());

		startDayCard = new StartDayCard();
		JPanel startday = startDayCard.getCard(frame.getContentPane());
		frame.getContentPane().add(startday, Card.CA4.getLabel());
		
		endDayCard = new EndDayCard();
		JPanel endday = endDayCard.getCard(frame.getContentPane());
		frame.getContentPane().add(endday, Card.CA5.getLabel());
		// cashier view end
		
		// manager view
		JPanel manager = (new ManagerCard()).getCard(frame.getContentPane());
		frame.getContentPane().add(manager, Card.MANAGER.getLabel());
		
		enrollCustomerCard = new EnrollCustomerCard();
		JPanel ma1 = enrollCustomerCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma1, Card.MA1.getLabel());
		
		restockCard2 = new RestockCard2();
		JSplitPane ma22 = restockCard2.getCard(frame.getContentPane());
		frame.getContentPane().add(ma22, Card.MA22.getLabel());
		
		restockCard1 = new RestockCard1(restockCard2);
		JPanel ma21 = restockCard1.getCard(frame.getContentPane());
		frame.getContentPane().add(ma21, Card.MA21.getLabel());
		
		customerReportCard = new CustomerReportCard();
		JSplitPane ma3 = customerReportCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma3, Card.MA3.getLabel());
		
		cashPositionCard = new CashPositionCard();
		JSplitPane ma4 = cashPositionCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma4, Card.MA4.getLabel());
		
		addItemCard = new AddItemCard();
		JPanel ma5 = addItemCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma5, Card.MA5.getLabel());
		
		changePriceCard = new ChangePriceCard();
		JPanel ma6 = changePriceCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma6, Card.MA6.getLabel());
		
		addCashierCard = new AddCashierCard();
		JPanel ma7 = addCashierCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma7, Card.MA7.getLabel());
		
		removeCashierCard = new RemoveCashierCard();
		JPanel ma8 = removeCashierCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma8, Card.MA8.getLabel());
		
		itemSummaryCard = new ItemSummaryCard();
		JSplitPane ma9 = itemSummaryCard.getCard(frame.getContentPane());
		frame.getContentPane().add(ma9, Card.MA9.getLabel());
		//manager view end
	}
}
