package system;
import java.util.*;

import system.dao.impl.CashierDaoImpl;


public class Store implements Cloneable
{
	public Store(int id, Map<Item, Integer> inventory, double totalCash,
			double cashPerCashier, List<Cashier> cashiers,
			List<TransactionE> transactions) {
		super();
		this.id = id;
		this.inventory = inventory;
		this.totalCash = totalCash;
		this.cashPerCashier = cashPerCashier;
		this.cashiers = cashiers;
		this.transactions = transactions;
	}

	public Store() {
		super();
	}

	int id;
	Map<Item, Integer> inventory;
	double totalCash;
	double cashPerCashier;
	List<Cashier> cashiers;
	List<TransactionE> transactions;
	Delivery currDelivery;
	
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
	
	public Store(int id, double startingCash, HashMap<Item, Integer> inventory, double cashPerCashier, ArrayList<TransactionE> transactions, Delivery currDelivery)
	{
		this.id = id;
		this.totalCash = startingCash;
		this.inventory = inventory;
		this.cashPerCashier = cashPerCashier;
		this.cashiers = new ArrayList<Cashier>();
		this.transactions = transactions;
		this.currDelivery = currDelivery;
	}
	
	public int checkInventory(Item i)
	{
		if(inventory.containsKey(i))
			return inventory.get(i);
		else
			return 0;
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
		CashierDaoImpl cashdao = new CashierDaoImpl();
		cashdao.save(toAdd);
	}
	
	public void addCashier(int cashierID)
	{
		CashierDaoImpl cashdao = new CashierDaoImpl();
		cashdao.save(new Cashier(this, cashierID));
	}
	
	public void removeCashier(int cashierID)
	{
		CashierDaoImpl cashdao = new CashierDaoImpl();
		Cashier c = cashdao.get(cashierID);
		if(c == null)
			throw new IllegalArgumentException();
		cashdao.delete(cashierID);
	}
	
	public int getNumCashiers()
	{
		return cashiers.size();
	}
	
	public Iterator<TransactionE> transactionIterator()
	{
		return transactions.iterator();
	}
	
	public Iterator<Cashier> cashierIterator()
	{
		return cashiers.iterator();
	}
	
	public Cashier getCashier(int cashierIndex) 
	{
		CashierDaoImpl cashdao = new CashierDaoImpl();
		return cashdao.get(cashierIndex);
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
	
	public void addTransaction(TransactionE transaction)
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
		ArrayList<TransactionE> transactionsCopy = new ArrayList<TransactionE>();
		for(TransactionE t : transactionsCopy)
		{
			transactionsCopy.add((TransactionE)t.clone());
		}
		Store s = new Store(getStoreID(), totalCash, inventoryCopy, cashPerCashier, transactionsCopy, currDelivery == null ? null : (Delivery)currDelivery.clone());
		for(Cashier c : cashiers)
		{
			s.addCashier(new Cashier(s, c.getIndex(), c.getCash(), c.getCurrentTransaction(), c.isOnline()));
		}
		return s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Item, Integer> getInventory() {
		return inventory;
	}

	public void setInventory(Map<Item, Integer> inventory) {
		this.inventory = inventory;
	}

	public List<Cashier> getCashiers() {
		return cashiers;
	}

	public void setCashiers(List<Cashier> cashiers) {
		this.cashiers = cashiers;
	}

	public List<TransactionE> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionE> transactions) {
		this.transactions = transactions;
	}

	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}

	public void setCashPerCashier(double cashPerCashier) {
		this.cashPerCashier = cashPerCashier;
	}
}
