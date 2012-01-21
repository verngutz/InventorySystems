import java.util.*;

public class ISMemento
{
	private ArrayList<Store> stores;
	private ArrayList<Customer> customers;
	private HashMap<String, Item> items;
	private ArrayList<Delivery> deliveries;
	
	public ISMemento(ArrayList<Store> stores, ArrayList<Customer> customers, HashMap<String, Item> items, ArrayList<Delivery> deliveries)
	{
		this.stores = stores;
		this.customers = customers;
		this.items = items;
		this.deliveries = deliveries;
	}
	
	public ArrayList<Store> getStores()
	{
		return stores;
	}
	
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
	public HashMap<String, Item> getItems()
	{
		return items;
	}
	
	public ArrayList<Delivery> getDeliveries()
	{
		return deliveries;
	}
}