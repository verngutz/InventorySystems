import java.util.ArrayList;
import java.util.Iterator;


public class Item {
	String itemCode;
	String itemName;
	String itemCategory;
	ArrayList<Unit> units;
	
	public String getItemCode(){ return itemCode; }
	public String getItemName(){ return itemName; }
	public String getItemCategory(){ return itemCategory; }
	public Iterator<Unit> unitIterator(){ return units.iterator(); }
	
	public int hashCode()
	{
		return itemCode.hashCode();
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Item) && ((Item)o).itemCode.equals(itemCode);
	}
}
