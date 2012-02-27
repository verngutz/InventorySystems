package system;

public class Backup 
{
	private int id;
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public Backup() { }
	
	public boolean equals(Object o)
	{
		return (o instanceof Backup) && id == ((Backup)o).getId();
	}
}
