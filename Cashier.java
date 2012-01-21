import java.util.*;
import java.util.Map.Entry;


public class Cashier {

	public static final double POINTS_PER_PESO = 500;
	private Store store;
	private double cash;
	private Transaction currentTransaction;
	
	public Cashier(Store store)
	{
		this.store = store;
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
		return toReturn;
	}
	
	public double endDay()
	{
		double toReturn = cash;
		store.getCashFromCashier(cash);
		cash = 0;
		return toReturn;
	}
}

