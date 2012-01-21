import java.sql.Timestamp;
import java.util.*;


public class Transaction {
	private Store store;
	private Cashier cashier;
	private HashMap<Item, Integer> itemsSold;
	private Timestamp dateTime;
	private Customer customer;
	private int pointsUsed;
	private double revenue;
	
	public Transaction(){
		dateTime = new Timestamp(System.currentTimeMillis());
		itemsSold = new HashMap<Item, Integer>();
	}
	public Cashier getCashier(){ return cashier; }
	public Iterator<Map.Entry<Item, Integer>> itemsSoldIterator(){
		return itemsSold.entrySet().iterator();
	}
	public double getRevenue(){
		return revenue;
	}
	public void setRevenue(double revenue)
	{
		this.revenue = revenue;
	}
	
	public int getPointsUsed()
	{
		return pointsUsed;
	}
	
	public void setPointsUsed(int pointsUsed)
	{
		this.pointsUsed = pointsUsed;
	}
	public Timestamp getDateTime(){ return dateTime; }
	public Customer getCustomer(){ return customer; }
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public void addItemSold(Item currentItem, int quantity){
		itemsSold.put(currentItem, quantity);
	}
}
