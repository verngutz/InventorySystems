package system.entity;

public class Item implements Cloneable{
	private String itemCode;
	private String itemName;
	private String itemCategory;
	private String unitName;
	private double unitPrice;
	
	public Item(){
		
	}
	public Item(String itemCode, String itemName, String itemCategory,
			String unitName, double unitPrice) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.unitName = unitName;
		this.unitPrice = unitPrice;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
