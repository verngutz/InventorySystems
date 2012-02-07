package system;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;


public class Delivery implements Cloneable{
	Store store;
	Timestamp dateTime;
	ArrayList<TransactionItem> items;
	
	public Delivery(Store receiver)
	{
		store = receiver;
		dateTime = new Timestamp(System.currentTimeMillis());
		items = new ArrayList<TransactionItem>();
	}
	
	public Delivery(Store receiver, Timestamp dateTime, ArrayList<TransactionItem> items)
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
}
