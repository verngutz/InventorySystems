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
		StoreDaoImpl storedao = new StoreDaoImpl();
		return storedao.get(id);
	}
	
	public Customer getCustomer(int id)
	{
		CustomerDaoImpl cusdao = new CustomerDaoImpl();
		return cusdao.get(id);
	}
	
	public boolean containsItem(String itemCode)
	{
		ItemDaoImpl itemdao = new ItemDaoImpl();
	}
	
	public Item getItem(String itemCode)
	{
	}
}
