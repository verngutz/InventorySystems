package system;
import java.util.LinkedList;

public class SystemBox
{
	private static InventorySystems system = new InventorySystems();
	
	public static InventorySystems getSystem()
	{
		return system;
	}
	
	public static void backup()
	{
	}
	
	public static void restore()
	{
	}
}