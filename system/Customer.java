package system;

import system.dao.impl.CustomerDaoImpl;

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
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public void addPointsEarned(int points)
	{
		ptsEarned += points;
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
	//getters setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPtsEarned() {
		return ptsEarned;
	}

	public void setPtsEarned(int ptsEarned) {
		this.ptsEarned = ptsEarned;
	}

	public int getPtsRedeemed() {
		return ptsRedeemed;
	}

	public void setPtsRedeemed(int ptsRedeemed) {
		this.ptsRedeemed = ptsRedeemed;
	}
}
