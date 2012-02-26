package system.entity;

import java.util.*;


public class Store implements Cloneable
{
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
	
	public void addCashier()
	{
		cashiers.add(new Cashier(this));
		//cashPerCashier = totalCash / cashiers.size();
	}
	
	public void removeCashier()
	{
		cashiers.remove(cashiers.size() - 1);
		//cashPerCashier = totalCash / cashiers.size();
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
		return cashiers.get(cashierIndex);
	}
	
	public double giveCashToCashier()
	{
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
		inventory.put(item, inventory.get(item)-quantity);
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
		Store s = new Store(getId(), totalCash, inventoryCopy, cashPerCashier, transactionsCopy, currDelivery == null ? null : (Delivery)currDelivery.clone());
		for(Cashier c : cashiers)
		{
			s.addCashier(new Cashier(s, c.getCash(), c.getCurrentTransaction(), c.isOnline()));
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

	public void setInventory(HashMap<Item, Integer> inventory) {
		this.inventory = inventory;
	}

	public double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}

	public double getCashPerCashier() {
		return cashPerCashier;
	}

	public void setCashPerCashier(double cashPerCashier) {
		this.cashPerCashier = cashPerCashier;
	}

	public List<Cashier> getCashiers() {
		return cashiers;
	}

	public void setCashiers(ArrayList<Cashier> cashiers) {
		this.cashiers = cashiers;
	}

	public List<TransactionE> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<TransactionE> transactions) {
		this.transactions = transactions;
	}

}
