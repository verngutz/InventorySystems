package system;

import java.util.List;

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
	
	public void backup()
	{
	}
	
	public void restore()
	{
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
