package gui.cashier;

import gui.Card;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class CashierCard 
{
	private JPanel cashier;
	private Container con;
	
	public JPanel getCard(Container con)
	{
		if(cashier==null)
		{
			cashier = new JPanel();
			this.con = con;
			init();
		}
		return cashier;
	}
	
	public void init()
	{
		cashier.setLayout(new FormLayout(
		new ColumnSpec[] 
		{
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
		},
		new RowSpec[] 
		{
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
		}));
		
		JButton btnStartDay = new JButton("Start Day");
		btnStartDay.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA4.getLabel());
			}
		});
		cashier.add(btnStartDay, "2, 2");
		
		JButton btnMakeSale = new JButton("Make Sale");
		btnMakeSale.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA1.getLabel());
			}
		});
		cashier.add(btnMakeSale, "2, 4");
		
		JButton btnEndDay = new JButton("End Day");
		btnEndDay.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.CA5.getLabel());
			}
		});
		cashier.add(btnEndDay, "2, 6");
	}
}
