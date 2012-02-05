package system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Store implements Cloneable{
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
	
	public void startDeliveryBatch()
	{
		currDelivery = new Delivery(this);
	}
	
	public void acceptDeliveryItem(Item accepted, int quantity, double pricePerUnit)
	{
		if(currDelivery == null)
		{
			throw new IllegalStateException("No active delivery batch.");
		}
		else
		{
			if(inventory.containsKey(accepted))
				inventory.put(accepted, inventory.get(accepted) + quantity);
			else
				inventory.put(accepted, quantity);
				
			currDelivery.addDeliveryItem(new DeliveryItem(accepted, quantity, pricePerUnit));
		}
	}
	
	public Delivery endDeliveryBatch()
	{
		totalCash -= currDelivery.getTotalPrice();
		Delivery toReturn = currDelivery;
		currDelivery = null;
		return toReturn;
	}
	
	public void addCashier(Cashier toAdd)
	{
		cashiers.add(toAdd);
		//cashPerCashier = totalCash / cashiers.size();
	}
	
	public void removeCashier(int index)
	{
		cashiers.remove(index);
		//cashPerCashier = totalCash / cashiers.size();
	}
	
	public Iterator<Transaction> transactionIterator()
	{
		return transactions.iterator();
	}
	
	public Iterator<Cashier> cashierIterator(){
		return cashiers.iterator();
	}
	public Cashier getCashier(int cashierIndex) {
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
	
	public void addTransaction(Transaction transaction)
	{
		transactions.add(transaction);
	}
	public void deductFromStock(Item item, int quantity){
		inventory.put(item, inventory.get(item)-quantity);
	}
	
	public double getTotalCash(){
		return totalCash;
	}
	public int getStoreID(){
		return id;
	}
	public Iterator<Map.Entry<Item, Integer>> inventoryIterator(){
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
		Store s = new Store(getStoreID(), totalCash, inventoryCopy, cashPerCashier, transactionsCopy, (Delivery)currDelivery.clone());
		for(Cashier c : cashiers)
		{
			s.addCashier(new Cashier(s, c.getCash(), c.getCurrentTransaction()));
		}
		return s;
	}
}
