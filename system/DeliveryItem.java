package system;

public class DeliveryItem implements Cloneable
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
	
	public DeliveryItem clone()
	{
		return new DeliveryItem((Item)delivered.clone(), quantity, wholeSalePrice);
	}
}
