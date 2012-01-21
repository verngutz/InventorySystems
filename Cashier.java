import java.util.*;
import java.util.Map.Entry;


public class Cashier implements Cloneable{

	public static final double POINTS_PER_PESO = 500;
	private Store store;
	private double cash;
	private Transaction currentTransaction;
	
	public Cashier(Store store)
	{
		this.store = store;
	}
	
	public Cashier(Store store, double cash, Transaction currentTransaction)
	{
		this.store = store;
		this.cash = cash;
		this.currentTransaction = currentTransaction;
	}
	
	public void startDay()
	{
		this.cash = store.giveCashToCashier();
	}
	
	public double getCash(){ return cash; }
	public void startTransaction(){
		currentTransaction = new Transaction();
	}
	public void sell(Item currentItem, int quantity){
		currentTransaction.addItemSold(currentItem, quantity);
	}
	
	public Transaction endTransaction(Customer loyalBuyer, int pointsUsed)
	{
		if(loyalBuyer != null && loyalBuyer.getUsablePoints() < pointsUsed)
		{
			throw new IllegalArgumentException("Customer does not have enough points to use.");
		}
		double cashDue = 0;
		Iterator<Entry<Item, Integer>> itemsSold = currentTransaction.itemsSoldIterator();
		while(itemsSold.hasNext())
		{
			Map.Entry<Item, Integer> entry = itemsSold.next();
			cashDue += entry.getKey().getUnitPrice() * entry.getValue();
			store.deductFromStock(entry.getKey(), entry.getValue());
		}
		if(loyalBuyer != null)
		{
			int realPointsUsed = (int)(cashDue - Math.min(cashDue - pointsUsed, -1));
			currentTransaction.setCustomer(loyalBuyer);
			currentTransaction.setPointsUsed(realPointsUsed);
			loyalBuyer.addPointsRedeemed(realPointsUsed);
			cashDue -= realPointsUsed;
			loyalBuyer.addPointsEarned((int)(cashDue / POINTS_PER_PESO));
		}
		currentTransaction.setRevenue(cashDue);
		cash += cashDue;
		Transaction toReturn = currentTransaction;
		currentTransaction = null;
		return toReturn;
	}
	
	public double endDay()
	{
		double toReturn = cash;
		store.addCash(cash);
		cash = 0;
		return toReturn;
	}
	
	public Cashier clone()
	{
		return new Cashier((Store)store.clone(), cash, (Transaction)currentTransaction.clone());
	}
}

