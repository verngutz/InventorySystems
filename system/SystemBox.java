package system;
import java.util.LinkedList;

public class SystemBox
{
	private static LinkedList<ISMemento> saveStack = new LinkedList<ISMemento>();
	private static InventorySystems system = new InventorySystems();
	
	public static InventorySystems getSystem()
	{
		return system;
	}
	
	public static void backup()
	{
		saveStack.push(system.saveToMemento());
	}
	
	public static void restore()
	{
		system.restoreFromMemento(saveStack.pop());
	}
}