package system;
import java.util.*;


public class InventorySystems {
	ArrayList<Store> stores;
	ArrayList<Customer> customers;
	HashMap<String, Item> items;
	ArrayList<Delivery> deliveries;
	
	public InventorySystems()
	{
		stores = new ArrayList<Store>();
		customers = new ArrayList<Customer>();
		items = new HashMap<String, Item>();
		deliveries = new ArrayList<Delivery>();
	}
	
	public void addStore(Store newStore)
	{
		for(int i = 0; i < stores.size(); i++)
		{
			if(stores.get(i).getStoreID() == newStore.getStoreID())
			{
				stores.set(i, newStore);
				return;
			}
		}
		stores.add(newStore);
	}
	
	public void addCustomer(Customer newCustomer)
	{
		customers.add(newCustomer);
	}
	
	public void addItem(Item newItem)
	{
		items.put(newItem.getItemCode(), newItem);
	}
	
	public void addDelivery(Delivery newDelivery)
	{
		deliveries.add(newDelivery);
	}
	
	public Iterator<Store> storeIterator()
	{
		return stores.iterator();
	}
	
	public Iterator<Customer> customerIterator()
	{
		return customers.iterator();
	}
	
	public Iterator<Item> itemIterator()
	{
		return items.values().iterator();
	}
	
	public Iterator<Delivery> deliveryIterator()
	{
		return deliveries.iterator();
	}
	
	public int nextCustomerId()
	{
		return customers.size();
	}
	
	public int nextStoreId()
	{
		return stores.size();
	}
	
	public Store getStore(int id)
	{
		for(int i = 0; i < stores.size(); i++)
		{
			if(stores.get(i).getStoreID() == id)
				return stores.get(i);
		}
		throw new IndexOutOfBoundsException();
	}
	
	public Customer getCustomer(int id)
	{
		return customers.get(id);
	}
	
	public boolean containsItem(String itemCode)
	{
		return items.containsKey(itemCode);
	}
	
	public Item getItem(String itemCode)
	{
		return items.get(itemCode);
	}
	
	public ISMemento saveToMemento()
	{
		ArrayList<Store> storesCopy = new ArrayList<Store>();
		for(Store s : stores)
		{
			storesCopy.add((Store)s.clone());
		}
		ArrayList<Customer> customersCopy = new ArrayList<Customer>();
		for(Customer c : customers)
		{
			customersCopy.add((Customer)c.clone());
		}
		HashMap<String, Item> itemsCopy = new HashMap<String, Item>();
		for(String key : items.keySet())
		{
			itemsCopy.put(key, (Item)items.get(key).clone());
		}
		ArrayList<Delivery> deliveriesCopy = new ArrayList<Delivery>();
		for(Delivery d : deliveries)
		{
			deliveriesCopy.add((Delivery)d.clone());
		}
		return new ISMemento(storesCopy, customersCopy, itemsCopy, deliveriesCopy);
	}
	
	public void restoreFromMemento(ISMemento memento)
	{
		stores = memento.getStores();
		customers = memento.getCustomers();
		items = memento.getItems();
		deliveries = memento.getDeliveries();
	}
}
