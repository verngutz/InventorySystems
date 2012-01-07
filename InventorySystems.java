import java.util.*;


public class InventorySystems {
	ArrayList<Store> stores;
	ArrayList<Customer> customers;
	ArrayList<Item> items;
	ArrayList<Delivery> deliveries;
	
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
		items.add(newItem);
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
		return items.iterator();
	}
	
	public Iteratore<Delivery> deliveryIterator()
	{
		return deliveries.iterator();
	}
}
