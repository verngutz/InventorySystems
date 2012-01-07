import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;


public class Delivery {
	Store store;
	Timestamp dateTime;
	ArrayList<DeliveryItem> items;
	
	public Delivery(Store receiver)
	{
		store = receiver;
		dateTime = new Timestamp(System.currentTimeMillis());
	}
	
	public Store getStore(){ return store; }
	public Timestamp getDateTime(){ return dateTime; }
	public Iterator<DeliveryItem> itemIterator(){ return items.iterator(); }
	public double getTotalPrice(){ 
		Iterator<DeliveryItem> iter = itemIterator();
		double totalPrice = 0;
		while(iter.hasNext()){
			totalPrice += iter.next().getWholeSalePrice();
		}
		return totalPrice; 
	}
	public void addDeliveryItem(DeliveryItem item){
		items.add(item);
	}
}
