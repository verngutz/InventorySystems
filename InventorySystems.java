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
		return stores.get(id);
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
}
