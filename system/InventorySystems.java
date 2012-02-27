package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import system.dao.BackupDao;
import system.dao.CustomerDao;
import system.dao.DeliveryDao;
import system.dao.ItemDao;
import system.dao.StoreDao;
import system.dao.TransactionDao;
import system.dao.TransactionItemDao;

public class InventorySystems 
{
	//
	// Singleton
	//
	private static InventorySystems system = new InventorySystems();	
	public static InventorySystems getSystem() { return system; }
	private InventorySystems() { }
	
	public void backup() throws IOException
	{
		BackupDao backupdao = new BackupDao();
		Backup b = new Backup();
		b.setId(backupdao.getBackups().size());
		backupdao.save(b);
		
		Process backup = Runtime.getRuntime().exec("mysqldump -u root -proot testdb cashier customer delivery item store store_inventory transaction transaction_item");
		InputStream istrm = backup.getInputStream();
		InputStreamReader istrmrdr = new InputStreamReader(istrm);
		BufferedReader buffrdr = new BufferedReader(istrmrdr);
		File dump = new File("dump_" + b.getId());
		FileWriter fw = new FileWriter(dump);
		String data;
		while ((data = buffrdr.readLine()) != null) 
		{
			fw.write(data + "\n");
			fw.flush();
		}
		fw.close();
		buffrdr.close();
		istrmrdr.close();
		istrm.close();
	}
	
	public void restore() throws IOException
	{
		BackupDao backupdao = new BackupDao();
		List<Backup> backups = backupdao.getBackups();
		if (backups.size() == 0)
			throw new NoSuchElementException();
		else
		{
			Process restore = Runtime.getRuntime().exec("mysql -u root -proot testdb");
			OutputStream ostrm = restore.getOutputStream();
			OutputStreamWriter ostrmwrtr = new OutputStreamWriter(ostrm);
			BufferedWriter buffwrtr = new BufferedWriter(ostrmwrtr);
			File dump = new File("dump_" + backups.get(backups.size() - 1).getId());
			Scanner in = new Scanner(dump);
			String data;
			while(in.hasNextLine())
			{
				data = in.nextLine();
				buffwrtr.write(data + "\n");
				buffwrtr.flush();
			}
			buffwrtr.write("quit\n");
			buffwrtr.flush();
			in.close();
			buffwrtr.close();
			ostrmwrtr.close();
			ostrm.close();
			dump.delete();
		}
		backupdao.delete(backups.get(backups.size() - 1));
	}
	
	public void addStore(Store newStore)
	{
		StoreDao storedao = new StoreDao();
		storedao.save(newStore);
	}
	
	public void addCustomer(Customer newCustomer)
	{
		CustomerDao cusdao = new CustomerDao();
		cusdao.save(newCustomer);
	}
	
	public void addItem(Item newItem)
	{
		ItemDao itedao = new ItemDao();
		itedao.save(newItem);
	}
	
	public void addDelivery(Delivery newDelivery)
	{
		DeliveryDao deldao = new DeliveryDao();
		deldao.save(newDelivery);
	}
	
	public Store getStore(int id)
	{
		StoreDao storedao = new StoreDao();
		return storedao.get(id);
	}
	
	public Customer getCustomer(int id)
	{
		CustomerDao cusdao = new CustomerDao();
		return cusdao.get(id);
	}
	
	public boolean containsItem(String itemCode)
	{
		ItemDao itemdao = new ItemDao();
		return itemdao.get(itemCode) != null;
	}
	
	public Item getItem(String itemCode)
	{
		ItemDao itemdao = new ItemDao();
		return itemdao.get(itemCode);
	}
	
	public List<Item> getItemList()
	{
		ItemDao itemdao = new ItemDao();
		return itemdao.getItems();
	}

	public List<Store> getStoreList()
	{
		StoreDao storedao = new StoreDao();
		return storedao.getStores();
	}
	
	public int nextCustomerId()
	{
		CustomerDao cusdao = new CustomerDao();
		return cusdao.getCustomers().size();
	}
	
	public int nextDeliveryId()
	{
		DeliveryDao deldao = new DeliveryDao();
		return deldao.getDeliveries().size();
	}
	
	public int nextTransactionId()
	{
		TransactionDao transdao = new TransactionDao();
		return transdao.getTransactions().size();
	}
	
	public int nextTransactionItemId()
	{
		TransactionItemDao transitemdao = new TransactionItemDao();
		return transitemdao.getTransactionItems().size();
	}
}
