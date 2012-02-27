package gui.sysadmin;

import gui.Card;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.InventorySystems;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SystemAdminCard 
{
	private JPanel sysadmin;
	
	private Container con;
	public JPanel getCard(Container con)
	{
		if(sysadmin==null)
		{
			sysadmin=new JPanel();
			this.con = con;
			init();
		}
		return sysadmin;
	}
	
	public void init()
	{
		sysadmin.setLayout(new FormLayout(
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

		JButton btnSetupStore = new JButton("Setup Store");
		btnSetupStore.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				CardLayout cl = (CardLayout) con.getLayout();
				cl.show(con, Card.SA1.getLabel());
			}
		});
		sysadmin.add(btnSetupStore, "2, 2");

		JButton btnBackupSystem = new JButton("Backup System");
		btnBackupSystem.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				InventorySystems.getSystem().backup();
				JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin), "System successfully backed up. A new restore point was created.");
			}
		});
		
		sysadmin.add(btnBackupSystem, "2, 4");

		JButton btnRestoreSystem = new JButton("Restore System");
		btnRestoreSystem.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				try
				{
					InventorySystems.getSystem().restore();
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin), "System successfully reverted to the last restore point.");
				}
				catch(NoSuchElementException nsee)
				{
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(sysadmin), "There are no available restore points.");
				}
			}
		});
		sysadmin.add(btnRestoreSystem, "2, 6");
	}
}
