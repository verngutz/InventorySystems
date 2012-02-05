package system;
import java.sql.Timestamp;
import java.util.*;


public class Transaction implements Cloneable{
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
	
	public Transaction(Store store, Cashier cashier, HashMap<Item, Integer> itemsSold, Timestamp dateTime, Customer customer, int pointsUsed, double revenue)
	{
		this.store = store;
		this.cashier = cashier;
		this.itemsSold = itemsSold;
		this.dateTime = dateTime;
		this.customer = customer;
		this.pointsUsed = pointsUsed;
		this.revenue = revenue;
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
	
	public Transaction clone()
	{
		HashMap<Item, Integer> itemsSoldCopy = new HashMap<Item, Integer>();
		for(Item i : itemsSoldCopy.keySet())
		{
			itemsSoldCopy.put((Item)i.clone(), itemsSold.get(i));
		}
		Store s = (Store)store.clone();
		return new Transaction(s, new Cashier(s, cashier.getCash(), cashier.getCurrentTransaction()), itemsSoldCopy, dateTime, (Customer)customer.clone(), pointsUsed, revenue);
	}
}
