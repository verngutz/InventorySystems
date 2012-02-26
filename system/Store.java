package system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Store implements Cloneable
{
	int id;
	HashMap<Item, Integer> inventory;
	double totalCash;
	double cashPerCashier;
	ArrayList<Cashier> cashiers;
	ArrayList<Transaction> transactions;
	Delivery currDelivery;
	
	public Store(int id, double startingCash, double cashPerCashier)
	{
		this.id = id;
		cashiers = new ArrayList<Cashier>();
		totalCash = startingCash;
		this.cashPerCashier = cashPerCashier;
		inventory = new HashMap<Item, Integer>();
		cashiers = new ArrayList<Cashier>();
		transactions = new ArrayList<Transaction>();
	}
	
	public Store(int id, double startingCash, HashMap<Item, Integer> inventory, double cashPerCashier, ArrayList<Transaction> transactions, Delivery currDelivery)
	{
		this.id = id;
		this.totalCash = startingCash;
		this.inventory = inventory;
		this.cashPerCashier = cashPerCashier;
		this.cashiers = new ArrayList<Cashier>();
		this.transactions = transactions;
		this.currDelivery = currDelivery;
	}
	
	public double getCashPerCashier()
	{
		return cashPerCashier;
	}
	
	public void startDeliveryBatch()
	{
		currDelivery = new Delivery(this);
	}
	
	public double acceptDeliveryItem(Item accepted, int quantity, double pricePerUnit)
	{
		if(currDelivery == null)
		{
			throw new IllegalStateException("No active delivery batch.");
		}
				
		currDelivery.addTransactionItem(new TransactionItem(accepted, quantity, pricePerUnit));
		return quantity * pricePerUnit;
	}
	
	public Delivery endDeliveryBatch()
	{
		if(currDelivery == null)
		{
			throw new IllegalStateException("No active delivery batch.");
		}
		if(currDelivery.getTotalPrice() > totalCash)
		{
			throw new IllegalStateException("Specified store does not have enough store balance to pay for this restock operation.");
		}
		Iterator<TransactionItem> iterator = currDelivery.itemIterator();
		while(iterator.hasNext())
		{
			TransactionItem i = iterator.next();
			Item item = i.getItem();
			int quantity = i.getQuantity();
			double price = i.getPrice();
			if(inventory.containsKey(item))
				inventory.put(item, inventory.get(item) + quantity);
			else
				inventory.put(item, quantity);
			totalCash -= quantity * price;
		}
		Delivery toReturn = currDelivery;
		currDelivery = null;
		return toReturn;
	}
	
	public void addCashier(Cashier toAdd)
	{
		cashiers.add(toAdd);
	}
	
	public void addCashier(Long cashierID)
	{
		cashiers.add(new Cashier(this, cashierID));
		//cashPerCashier = totalCash / cashiers.size();
	}
	
	public void removeCashier(Long cashierID)
	{
		for(int i = 0; i < cashiers.size(); i++)
		{
			if(cashiers.get(i).getIndex().equals(cashierID))
			{
				cashiers.remove(i);
				return;
			}
		}
		throw new IllegalArgumentException();
		//cashPerCashier = totalCash / cashiers.size();
	}
	
	public int getNumCashiers()
	{
		return cashiers.size();
	}
	
	public Iterator<Transaction> transactionIterator()
	{
		return transactions.iterator();
	}
	
	public Iterator<Cashier> cashierIterator()
	{
		return cashiers.iterator();
	}
	
	public Cashier getCashier(Long cashierIndex) 
	{
		for(int i = 0; i < cashiers.size(); i++)
			if(cashiers.get(i).getIndex().equals(cashierIndex))
				return cashiers.get(i);
		return null;
	}
	
	public double giveCashToCashier()
	{
		if(totalCash < cashPerCashier)
			throw new IllegalStateException("Specified store does not have enough store-wide balance to begin another cashier's operations.");
		totalCash -= cashPerCashier;
		return cashPerCashier;
	}
	
	public void addCash(double cash)
	{
		totalCash += cash;
	}
	
	public void addTransaction(Transaction transaction)
	{
		transactions.add(transaction);
	}
	
	public void deductFromStock(Item item, int quantity)
	{
		if(inventory.get(item) < quantity)
			throw new IllegalArgumentException("There aren't enough items with item code " + item.getItemCode() + " available in stock for this transaction.");
		inventory.put(item, inventory.get(item)-quantity);
	}
	
	public double getTotalCash()
	{
		return totalCash;
	}
	
	public int getStoreID()
	{
		return id;
	}
	
	public Iterator<Map.Entry<Item, Integer>> inventoryIterator()
	{
		return inventory.entrySet().iterator();
	}
	
	public Store clone()
	{
		HashMap<Item, Integer> inventoryCopy = new HashMap<Item, Integer>();
		for(Item i : inventory.keySet())
		{
			inventoryCopy.put((Item)i.clone(), inventory.get(i));
		}
		ArrayList<Transaction> transactionsCopy = new ArrayList<Transaction>();
		for(Transaction t : transactionsCopy)
		{
			transactionsCopy.add((Transaction)t.clone());
		}
		Store s = new Store(getStoreID(), totalCash, inventoryCopy, cashPerCashier, transactionsCopy, currDelivery == null ? null : (Delivery)currDelivery.clone());
		for(Cashier c : cashiers)
		{
			s.addCashier(new Cashier(s, c.getIndex(), c.getCash(), c.getCurrentTransaction(), c.isOnline()));
		}
		return s;
	}
}
