package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.dao.CashierDao;
import system.dao.StoreDao;
import system.dao.TransactionDao;

public class Store
{
	private int id;
	private Map<Item, Integer> inventory = new HashMap<Item, Integer>();
	private double totalCash;
	private double cashPerCashier;
	private List<Cashier> cashiers;
	private List<TransactionE> transactions;
	private Delivery currDelivery;
	
	public Store() { }
	
	public Store(int id, double startingCash, double cashPerCashier)
	{
		this.id = id;
		cashiers = new ArrayList<Cashier>();
		totalCash = startingCash;
		this.cashPerCashier = cashPerCashier;
		inventory = new HashMap<Item, Integer>();
		cashiers = new ArrayList<Cashier>();
		transactions = new ArrayList<TransactionE>();
	}
	
	public int getId() { return id; }
	public Map<Item, Integer> getInventory() { return inventory; }
	public double getTotalCash() { return totalCash; }
	public double getCashPerCashier() { return cashPerCashier; }
	public List<Cashier> getCashiers() { return cashiers; }
	public List<TransactionE> getTransactions() { return transactions; }
	
	public void setId(int id) { this.id = id; }
	public void setInventory(Map<Item, Integer> inventory) { this.inventory = inventory; }
	public void setTotalCash(double totalCash) { this.totalCash = totalCash; }
	public void setCashPerCashier(double cashPerCashier) { this.cashPerCashier = cashPerCashier; }
	public void setCashiers(List<Cashier> cashiers) { this.cashiers = cashiers; }
	public void setTransactions(List<TransactionE> transactions) { this.transactions = transactions; }
	
	public int checkInventory(Item i)
	{
		if(inventory.containsKey(i))
			return inventory.get(i);
		else
			return 0;
	}
	
	public void addCashier(Cashier toAdd)
	{
		cashiers.add(toAdd);
		CashierDao cashdao = new CashierDao();
		cashdao.save(toAdd);
	}
	
	public void addCashier(int cashierID)
	{	
		CashierDao cashdao = new CashierDao();
		
		if(cashdao.get(cashierID, this) != null)
			throw new IndexOutOfBoundsException();		
		
		Cashier c = new Cashier(this, cashierID);
		cashiers.add(c);
		cashdao.save(c);
	}
	
	public Cashier getCashier(int cashierIndex) 
	{
		CashierDao cashdao = new CashierDao();
		return cashdao.get(cashierIndex, this);
	}
	
	public void removeCashier(int cashierID)
	{
		CashierDao cashdao = new CashierDao();
		Cashier c = cashdao.get(cashierID, this);
		
		if(c == null)
			throw new IllegalArgumentException();
		
		cashiers.remove(c);
		cashdao.delete(cashierID);
	}
	
	public int getNumCashiers()
	{
		return cashiers.size();
	}
	
	public double giveCashToCashier()
	{
		if(totalCash < cashPerCashier)
			throw new IllegalStateException("Specified store does not have enough store-wide balance to begin another cashier's operations.");
		totalCash -= cashPerCashier;
		StoreDao storedao = new StoreDao();
		storedao.save(this);
		return cashPerCashier;
	}
	
	public void addCash(double cash)
	{
		totalCash += cash;
		StoreDao storedao = new StoreDao();
		storedao.save(this);
	}

	public void addTransaction(TransactionE transaction)
	{
		transactions.add(transaction);
		TransactionDao transdao = new TransactionDao();
		transdao.save(transaction);
	}
	
	public void deductFromStock(Item item, int quantity)
	{
		try
		{
			if(inventory.get(item) < quantity)
				throw new IllegalArgumentException("There aren't enough items with item code " + item.getItemCode() + " available in stock for this transaction.");
		}
		catch(NullPointerException npe)
		{
			throw new IllegalArgumentException("There aren't enough items with item code " + item.getItemCode() + " available in stock for this transaction.");
		}
		inventory.put(item, inventory.get(item)-quantity);
		StoreDao storedao = new StoreDao();
		storedao.save(this);
	}
	
	public void startDeliveryBatch()
	{
		currDelivery = new Delivery(this);
	}
	
	public double acceptDeliveryItem(Item accepted, int quantity, double pricePerUnit)
	{
		if(currDelivery == null)
			throw new IllegalStateException("No active delivery batch.");
				
		currDelivery.addTransactionItem(new TransactionItem(accepted, quantity, pricePerUnit));
		return quantity * pricePerUnit;
	}
	
	public Delivery endDeliveryBatch()
	{
		if(currDelivery == null)
			throw new IllegalStateException("No active delivery batch.");
		
		if(currDelivery.getTotalPrice() > totalCash)
			throw new IllegalStateException("Specified store does not have enough store balance to pay for this restock operation.");
		
		if(currDelivery.getItems().size() == 0)
			throw new IllegalStateException("No items received!");
		
		for(TransactionItem i : currDelivery.getItems())
		{
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
		StoreDao storedao = new StoreDao();
		storedao.save(this);
		return toReturn;
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Store) && id == ((Store)o).getId();
	}
}
