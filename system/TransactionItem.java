package system;

public class TransactionItem
{
	int id;
	Item item;
	int quantity;
	double price;
	
	public TransactionItem() { }
	
	public TransactionItem(Item item, int quantity, double price)
	{
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() { return id; }
	public Item getItem() { return item; }
	public int getQuantity(){ return quantity; }
	public double getPrice(){ return price; }
	
	public void setId(int id) { this.id = id; }
	public void setItem(Item item) { this.item = item; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public void setPrice(double price) { this.price = price; }
}
