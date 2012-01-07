
public class Unit {
	String unitName;
	double unitPrice;
	Item item;
	int itemQuantity;
	
	public Item getItem(){ return item; }
	public String getUnitName(){ return unitName; }
	public double getUnitPrice(){ return unitPrice; }
	public void setUnitPrice(double newPrice){
		unitPrice = newPrice;
	}
	public int getItemQuantity(){ return itemQuantity; }
}
