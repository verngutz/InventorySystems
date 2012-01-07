import java.util.*;


public class Cashier {
	private Store store;
	private double cash;
	private Transaction currentTransaction;
	
	public double getCash(){ return cash; }
	public void startTransaction(){
		currentTransaction = new Transaction();
	}
	public Item scanItem(String itemCode){
		return store.;
	}
	public Iterator<Unit> unitsIterator(Item toSell){
		return toSell.unitIterator();
	}
	public void sell(Unit tosell, int quantity){
		
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
