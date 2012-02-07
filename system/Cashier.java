package system;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;


public class Cashier 
{

	public static final double POINTS_PER_PESO = 500;
	private Store store;
	private double cash;
	private Transaction currentTransaction;
	private boolean online;
	private double rawCashDue;
	
	public Cashier(Store store)
	{
		this.store = store;
		online = false;
	}
	
	public Cashier(Store store, double cash, Transaction currentTransaction, boolean online)
	{
		this.store = store;
		this.cash = cash;
		this.currentTransaction = currentTransaction;
		this.online = online;
	}
	
	public Store getStore()
	{
		return store;
	}
	
	public boolean isOnline()
	{
		return online;
	}
	
	public double getRawCashDue()
	{
		return rawCashDue;
	}
	
	public void startDay()
	{
		online = true;
		this.cash = store.giveCashToCashier();
	}
	
	public Transaction getCurrentTransaction()
	{
		return currentTransaction;
	}
	
	public double getCash(){ return cash; }
	public void startTransaction()
	{
		if(!online)
		{
			throw new IllegalStateException("Cashier is not online.");
		}
		currentTransaction = new Transaction();
		rawCashDue = 0;
	}
	
	public void sell(Item currentItem, int quantity)
	{
		if(!online)
		{
			throw new IllegalStateException("Cashier is not online.");
		}
		if(currentTransaction == null)
		{
			throw new IllegalStateException("No active transaction.");
		}
		currentTransaction.addItemSold(currentItem, quantity, currentItem.getUnitPrice());
		rawCashDue += quantity * currentItem.getUnitPrice();
	}
	
	public Transaction endTransaction(Customer loyalBuyer, int pointsUsed)
	{
		if(!online)
		{
			throw new IllegalStateException("Cashier is not online.");
		}
		if(currentTransaction == null)
		{
			throw new IllegalStateException("No active transaction.");
		}
		if(loyalBuyer != null && loyalBuyer.getUsablePoints() < pointsUsed)
		{
			throw new IllegalArgumentException("Customer does not have enough points to use.");
		}
		double cashDue = rawCashDue;
		Iterator<TransactionItem> itemsSold = currentTransaction.itemsSoldIterator();
		while(itemsSold.hasNext())
		{
			TransactionItem entry = itemsSold.next();
			store.deductFromStock(entry.getItem(), entry.getQuantity());
		}
		if(loyalBuyer != null)
		{
			currentTransaction.setCustomer(loyalBuyer);
			currentTransaction.setPointsUsed(pointsUsed);
			loyalBuyer.addPointsRedeemed(pointsUsed);
			cashDue -= pointsUsed;
			loyalBuyer.addPointsEarned((int)(cashDue / POINTS_PER_PESO));
		}
		currentTransaction.setRevenue(cashDue);
		cash += cashDue;
		Transaction toReturn = currentTransaction;
		currentTransaction = null;
		rawCashDue = 0;
		return toReturn;
	}
	
	public double endDay()
	{
		if(!online)
		{
			throw new IllegalStateException("Cashier is not online.");
		}
		online = false;
		double toReturn = cash;
		store.addCash(cash);
		cash = 0;
		return toReturn;
	}
}

