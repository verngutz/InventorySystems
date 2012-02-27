package system;

import system.dao.CustomerDao;

public class Customer
{
	private String firstName;
	private String lastName;
	private int id;
	private String address;
	private String gender;
	private int age;
	private int ptsEarned;
	private int ptsRedeemed;

	public Customer() { }
	
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
	
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getId() { return id; }
	public String getAddress() { return address; }
	public String getGender() { return gender; }
	public int getAge() { return age; }
	public int getPtsEarned() { return ptsEarned; }
	public int getPtsRedeemed() { return ptsRedeemed; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setId(int id) { this.id = id; }
	public void setAddress(String address) { this.address = address; }
	public void setGender(String gender) { this.gender = gender; }
	public void setAge(int age) { this.age = age; }
	public void setPtsEarned(int ptsEarned) { this.ptsEarned = ptsEarned; }
	public void setPtsRedeemed(int ptsRedeemed) { this.ptsRedeemed = ptsRedeemed; }

	public void addPointsEarned(int points)
	{
		ptsEarned += points;
		CustomerDao cusdao = new CustomerDao();
		cusdao.save(this);
	}
	
	public void addPointsRedeemed(int points)
	{
		ptsRedeemed += points;
		CustomerDao cusdao = new CustomerDao();
		cusdao.save(this);
	}
	
	public int getUsablePoints()
	{
		return ptsEarned - ptsRedeemed; 
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Customer) && id == ((Customer)o).getId();
	}
}
