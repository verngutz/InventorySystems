import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class Item {
	String itemCode;
	String itemName;
	String itemCategory;
	HashMap<String, Unit> units;
	
	public Item(String itemCode, String itemName, String itemCategory)
	{
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		units = new HashMap<String, Unit>();
	}
	
	public String getItemCode(){ return itemCode; }
	public String getItemName(){ return itemName; }
	public String getItemCategory(){ return itemCategory; }
	public Iterator<Unit> unitIterator(){ return units.values().iterator(); }
	
	public void addUnit(Unit unit)
	{
		units.put(unit.getUnitName(), unit);
	}
	
	public Unit getUnit(String unitName)
	{
		return units.get(unitName);
	}
	
	public int hashCode()
	{
		return itemCode.hashCode();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Item) && ((Item)o).itemCode.equals(itemCode);
	}
}
