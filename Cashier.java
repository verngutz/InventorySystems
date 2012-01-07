import java.util.*;


public class Cashier {
	private Store store;
	private double cash;
	private Transaction currentTransaction;
	
	public Cashier(Store store, double cash)
	{
		this.store = store;
		this.cash = cash;
	}
	
	public double getCash(){ return cash; }
	public void startTransaction(){
		currentTransaction = new Transaction();
	}
	public Iterator<Unit> unitsIterator(Item toSell){
		return toSell.unitIterator();
	}
	public void sell(Unit tosell, int quantity){
		currentTransaction.addUnitsSold(tosell, quantity);
	}
	
	public Transaction endTransaction(Customer loyalBuyer, double cashReceived,int pointsUsed){
		Transaction toReturn = currentTransaction;
		currentTransaction = null;
		return toReturn;
	}
	public double endDay(){
		double toReturn = cash;
		cash = 0;
		return toReturn;
	}
}
