package system;

import system.dao.ItemDao;

public class Item
{
	private String itemCode;
	private String itemName;
	private String itemCategory;
	private String unitName;
	private double unitPrice;
	
	public Item() { }
	
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
	
	public void setItemCode(String itemCode) { this.itemCode = itemCode; }
	public void setItemName(String itemName) { this.itemName = itemName; }
	public void setItemCategory(String itemCategory) { this.itemCategory = itemCategory; }
	public void setUnitName(String unitName) { this.unitName = unitName; }
	public void setUnitPrice(double newPrice)
	{
		unitPrice = newPrice;
		ItemDao itemdao = new ItemDao();
		itemdao.save(this);
	}
	
	public int hashCode()
	{
		return itemCode.hashCode();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Item) && ((Item)o).getItemCode().equals(itemCode);
	}
}
