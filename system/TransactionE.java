package system;
import java.sql.Timestamp;
import java.util.*;


public class TransactionE implements Cloneable
{
	private int id;
	private Store store;
	private Cashier cashier;
	private List<TransactionItem> itemsSold;
	private Timestamp dateTime;
	private Customer customer;
	private int pointsUsed;
	private double revenue;
	
	public TransactionE()
	{
		dateTime = new Timestamp(System.currentTimeMillis());
		itemsSold = new ArrayList<TransactionItem>();
	}
	
	public TransactionE(Store store, Cashier cashier, ArrayList<TransactionItem> itemsSold, Timestamp dateTime, Customer customer, int pointsUsed, double revenue)
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
	
	public Iterator<TransactionItem> itemsSoldIterator()
	{
		return itemsSold.iterator();
	}
	
	public double getRevenue()
	{
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
	
	public void addItemSold(Item currentItem, int quantity, double price)
	{
		itemsSold.add(new TransactionItem(currentItem, quantity, price));
	}
	
	public TransactionE clone()
	{
		ArrayList<TransactionItem> itemsSoldCopy = new ArrayList<TransactionItem>();
		for(TransactionItem i : itemsSold)
		{
			itemsSoldCopy.add(new TransactionItem((Item)i.getItem().clone(), i.getQuantity(), i.getPrice()));
		}
		Store s = (Store)store.clone();
		return new TransactionE(s, new Cashier(s, cashier.getIndex(), cashier.getCash(), cashier.getCurrentTransaction(), cashier.isOnline()), itemsSoldCopy, dateTime, (Customer)customer.clone(), pointsUsed, revenue);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<TransactionItem> getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(List<TransactionItem> itemsSold) {
		this.itemsSold = itemsSold;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
}