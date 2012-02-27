package system;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Delivery
{
	private int id;
	private Store store;
	private Timestamp dateTime;
	private List<TransactionItem> items;
	
	public Delivery() { }
	
	public Delivery(Store receiver)
	{
		store = receiver;
		dateTime = new Timestamp(System.currentTimeMillis());
		items = new ArrayList<TransactionItem>();
	}
	
	public int getId() { return id; }
	public Store getStore(){ return store; }
	public Timestamp getDateTime(){ return dateTime; }
	public List<TransactionItem> getItems() { return items; }
	
	public void setId(int id) { this.id = id; }
	public void setStore(Store store) { this.store = store; }
	public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
	public void setItems(List<TransactionItem> items) { this.items = items; }
	
	public double getTotalPrice()
	{ 
		double totalPrice = 0;
		for(TransactionItem ditem : getItems())
			totalPrice += ditem.getPrice()*ditem.getQuantity();
		
		return totalPrice; 
	}
	
	public void addTransactionItem(TransactionItem item)
	{
		items.add(item);
	}
}
