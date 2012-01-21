//used for return of endTransaction() in Cashier class
public class Tuple<Transaction, Double>{
	private Transaction trans;
	private double amount;
	
	public Tuple(Transaction trans, double amount){
		this.trans = trans;
		this.amount = amount;
	}
	public Transaction getTrans(){ return trans; }
	public double getAmount(){ return amount; }
}