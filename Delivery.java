import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;


public class Delivery implements Cloneable{
	Store store;
	Timestamp dateTime;
	ArrayList<DeliveryItem> items;
	
	public Delivery(Store receiver)
	{
		store = receiver;
		dateTime = new Timestamp(System.currentTimeMillis());
		items = new ArrayList<DeliveryItem>();
	}
	
	public Delivery(Store receiver, Timestamp dateTime, ArrayList<DeliveryItem> items)
	{
		store = receiver;
		this.dateTime = dateTime;
		this.items = items;
	}
	
	public Store getStore(){ return store; }
	public Timestamp getDateTime(){ return dateTime; }
	public Iterator<DeliveryItem> itemIterator(){ return items.iterator(); }
	public double getTotalPrice(){ 
		Iterator<DeliveryItem> iter = itemIterator();
		double totalPrice = 0;
		while(iter.hasNext()){
			DeliveryItem ditem = iter.next();
			totalPrice += ditem.getWholeSalePrice()*ditem.getQuantity();
		}
		return totalPrice; 
	}
	public void addDeliveryItem(DeliveryItem item){
		items.add(item);
	}
	
	public Delivery clone()
	{
		ArrayList<DeliveryItem> itemsCopy = new ArrayList<DeliveryItem>();
		for(DeliveryItem d : itemsCopy)
		{
			itemsCopy.add((DeliveryItem)d.clone());
		}
		return new Delivery(store, dateTime, itemsCopy);
	}
}
