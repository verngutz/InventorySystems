package system;

public class Customer implements Cloneable{
	private String firstName;
	private String lastName;
	private int id;
	private String address;
	private String gender;
	private int age;
	private int ptsEarned;
	private int ptsRedeemed;
	
	public Customer(String firstName, String lastName, int id, String address, String gender, int age)
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
	
	public Customer(String firstName, String lastName, int id, String address, String gender, int age, int ptsEarned, int ptsRedeemed)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.address = address;
		this.gender = gender;
		this.age = age;
		this.ptsEarned = ptsEarned;
		this.ptsRedeemed = ptsRedeemed;
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
	
	public int getAge()
	{
		return age;
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
	
	public Customer clone()
	{
		return new Customer(firstName, lastName, id, address, gender, age, ptsEarned, ptsRedeemed);
	}
}
