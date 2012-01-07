import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Store {
	HashMap<Item, Integer> inventory;
	double totalCash;
	double cashPerCashier;
	ArrayList<Cashier> cashiers;
	ArrayList<Transaction> transactions;
	Delivery currDelivery;
	
	public Store(double startingCash)
	{
		totalCash = startingCash;
	}
	
	public void startDeliveryBatch()
	{
		currDelivery = new Delivery(this);
	}
	
	public void acceptDeliveryItem(Item accepted, int quantity, double pricePerUnit)
	{
		if(inventory.containsKey(accepted))
			inventory.put(accepted, inventory.get(accepted) + quantity);
		else
			inventory.put(accepted, quantity);
			
		currDelivery.addDeliveryItem();
	}
	
	public Delivery endDeliveryBatch()
	{
		Delivery toReturn = currDelivery;
		currDelivery = null;
		return toReturn;
	}
	
	public void addCashier(Cashier toAdd)
	{
		cashiers.add(toAdd);
	}
	
	public void removeCashier(Cashier toRemove)
	{
		cashiers.remove(toRemove);
	}
	
	public Iterator<Transaction> transactionIterator()
	{
		return transactions.iterator();
	}
}
