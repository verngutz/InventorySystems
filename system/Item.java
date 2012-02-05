package system;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class Item implements Cloneable{
	String itemCode;
	String itemName;
	String itemCategory;
	String unitName;
	double unitPrice;
	
	public Item(String itemCode, String itemName, String itemCategory, String unitName, double unitPrice)
	{
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.unitName = unitName;
		this.unitPrice = unitPrice;
	}
	
	public String getItemCode(){ return itemCode; }
	public String getItemName(){ return itemName; }
	public String getItemCategory(){ return itemCategory; }
	public String getUnitName(){ return unitName; }
	public double getUnitPrice(){ return unitPrice; }
	public void setUnitPrice(double newPrice){
		unitPrice = newPrice;
	}
	
	public int hashCode()
	{
		return itemCode.hashCode();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Item) && ((Item)o).itemCode.equals(itemCode);
	}
	
	public Item clone()
	{
		return new Item(itemCode, itemName, itemCategory, unitName, unitPrice);
	}
}
