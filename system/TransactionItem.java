package system;

public class TransactionItem
{
	Item item;
	int quantity;
	double price;
	
	public TransactionItem(Item item, int quantity, double price)
	{
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Item getItem() { return item; }
	public int getQuantity(){ return quantity; }
	public double getPrice(){ return price; }
}
