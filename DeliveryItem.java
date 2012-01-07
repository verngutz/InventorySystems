
public class DeliveryItem
{
	Item delivered;
	int quantity;
	double wholeSalePrice;
	
	public DeliveryItem(Item delivered, int quantity, double wholeSalePrice)
	{
		this.delivered = delivered;
		this.quantity = quantity;
		this.wholeSalePrice = wholeSalePrice;
	}
	
	public int getQuantity(){ return quantity; }
	public double getWholeSalePrice(){ return wholeSalePrice; }
}
