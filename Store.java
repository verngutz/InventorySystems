import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Store {
	HashMap<Item, Integer> inventory;
	double totalCash;
	double cashPerCashier;
	ArrayList<Cashier> cashiers;
	ArrayList<Transaction> transactions;
	
	public void startDeliveryBatch(){
		d
	}
	public void acceptDeliveryItem(Item accepted, int quantity, double pricePerUnit){
		inventory.put(accepted, quantity);
		
	}
	public Delivery endDeliveryBatch(){
		return null;
	}
	public void addCashier(Cashier toAdd){
		cashiers.add(toAdd);
	}
	public void removeCashier(Cashier toRemove){
		cashiers.remove(toRemove);
	}
	public Iterator<Transaction> transactionIterator(){
		return transactions.iterator();
	}
	
}
