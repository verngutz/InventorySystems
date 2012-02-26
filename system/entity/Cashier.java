package system.entity;

import java.util.*;

public class Cashier 
{

	public static final double POINTS_PER_PESO = 500;
	private int id;
	private Store store;
	private double cash;
	private TransactionE currentTransaction;
	private boolean online;
	private double rawCashDue;
	
	public Cashier(Store store)
	{
		this.store = store;
		online = false;
	}
	
	public Cashier(Store store, double cash, TransactionE currentTransaction, boolean online)
	{
		this.store = store;
		this.cash = cash;
		this.currentTransaction = currentTransaction;
		this.online = online;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
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
	
	public TransactionE getCurrentTransaction()
	{
		return currentTransaction;
	}
	
	public void startTransaction()
	{
		if(!online)
		{
			throw new IllegalStateException("Cashier is not online.");
		}
		currentTransaction = new TransactionE();
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
	
	public TransactionE endTransaction(Customer loyalBuyer, int pointsUsed)
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
		TransactionE toReturn = currentTransaction;
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

