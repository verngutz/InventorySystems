package system;
import gui.MainAppWindow;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import system.dao.*;
import system.dao.impl.*;


public class InventorySystems {
	
	public void addStore(Store newStore)
	{
		StoreDao storedao = new StoreDaoImpl();
		storedao.save(newStore);
	}
	
	public void addCustomer(Customer newCustomer)
	{
		CustomerDao cusdao = new CustomerDaoImpl();
		cusdao.save(newCustomer);
	}
	
	public void addItem(Item newItem)
	{
		ItemDao itedao = new ItemDaoImpl();
		itedao.save(newItem);
	}
	
	public void addDelivery(Delivery newDelivery)
	{
		DeliveryDao deldao = new DeliveryDaoImpl();
		deldao.save(newDelivery);
	}
	
	public Store getStore(int id)
	{
		StoreDao storedao = new StoreDaoImpl();
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
