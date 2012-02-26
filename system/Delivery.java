package system;
import java.sql.Timestamp;
import java.util.*;
import java.util.Iterator;


public class Delivery implements Cloneable{
	int id;
	Store store;
	Timestamp dateTime;
	List<TransactionItem> items;
	
	public Delivery(Store receiver)
	{
		store = receiver;
		dateTime = new Timestamp(System.currentTimeMillis());
		items = new ArrayList<TransactionItem>();
	}
	
	public Delivery(Store receiver, Timestamp dateTime, List<TransactionItem> items)
	{
		store = receiver;
		this.dateTime = dateTime;
		this.items = items;
	}
	
	public Store getStore(){ return store; }
	public Timestamp getDateTime(){ return dateTime; }
	public Iterator<TransactionItem> itemIterator(){ return items.iterator(); }
	public double getTotalPrice(){ 
		Iterator<TransactionItem> iter = itemIterator();
		double totalPrice = 0;
		while(iter.hasNext()){
			TransactionItem ditem = iter.next();
			totalPrice += ditem.getPrice()*ditem.getQuantity();
		}
		return totalPrice; 
	}
	public void addTransactionItem(TransactionItem item){
		items.add(item);
	}
	
	public Delivery clone()
	{
		ArrayList<TransactionItem> itemsCopy = new ArrayList<TransactionItem>();
		for(TransactionItem d : itemsCopy)
		{
			itemsCopy.add(new TransactionItem(d.getItem(), d.getQuantity(), d.getPrice()));
		}
		return new Delivery(store, dateTime, itemsCopy);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TransactionItem> getItems() {
		return items;
	}

	public void setItems(List<TransactionItem> items) {
		this.items = items;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
}
