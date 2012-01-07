
public class Customer {
	private String firstName;
	private String lastName;
	private int id;
	private String address;
	private String gender;
	private int age;
	private int ptsEarned;
	private int ptsRedeemed;
	
	public Customer(String firstName, String lastName, int id, String address, String gender)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.address = address;
		this.gender = gender;
		this.age = age;
		this.ptsEarned = 0;
		this.ptsRedeemed = 0;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public int getPointsEarned()
	{
		return ptsEarned;
	}
	
	public void addPointsEarned(int points)
	{
		ptsEarned += points;
	}
	
	public int getPointsRedeemed()
	{
		return ptsRedeemed;
	}
	
	public void addPointsRedeemed(int points)
	{
		ptsRedeemed += points;
	}
	
	public int getUsablePoints()
	{
		return ptsEarned - ptsRedeemed; 
	}
}
