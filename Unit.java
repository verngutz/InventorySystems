
public class Unit {
	String unitName;
	double unitPrice;
	int itemQuantity;
	
	public Unit(String unitName, double unitPrice, int itemQuantity)
	{
		this.unitName = unitName;
		this.unitPrice = unitPrice;
		this.itemQuantity = itemQuantity;
	}
	
	public String getUnitName(){ return unitName; }
	public double getUnitPrice(){ return unitPrice; }
	public void setUnitPrice(double newPrice){
		unitPrice = newPrice;
	}
	public int getItemQuantity(){ return itemQuantity; }
}
