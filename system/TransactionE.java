package system;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionE
{
	private int id;
	private Store store;
	private Cashier cashier;
	private List<TransactionItem> itemsSold;
	private Timestamp dateTime;
	private Customer customer;
	private int pointsUsed;
	private double revenue;
	
	public TransactionE() { }
	
	public TransactionE(Store store, Cashier cashier)
	{
		this.store = store;
		this.cashier = cashier;
		dateTime = new Timestamp(System.currentTimeMillis());
		itemsSold = new ArrayList<TransactionItem>();
	}
	
	public int getId() { return id; }
	public Store getStore() { return store; }
	public Cashier getCashier(){ return cashier; }
	public List<TransactionItem> getItemsSold() { return itemsSold; }
	public Timestamp getDateTime(){ return dateTime; }
	public Customer getCustomer(){ return customer; }
	public int getPointsUsed() { return pointsUsed; }
	public double getRevenue() { return revenue; }
	
	public void setId(int id) { this.id = id; }
	public void setStore(Store store) { this.store = store; }
	public void setCashier(Cashier cashier) { this.cashier = cashier; }
	public void setItemsSold(List<TransactionItem> itemsSold) { this.itemsSold = itemsSold; }
	public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public void setPointsUsed(int pointsUsed) { this.pointsUsed = pointsUsed; }	
	public void setRevenue(double revenue) { this.revenue = revenue; }

	public void addItemSold(Item currentItem, int quantity, double price)
	{
		itemsSold.add(new TransactionItem(currentItem, quantity, price));
	}
}
