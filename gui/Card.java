package gui;

public enum Card 
{
	SYSADMIN("System Administrator"),
	SA1("Setup Store"),
	MANAGER("Manager"),
	MA1("Enroll Customer"),
	MA21("Restock1"),
	MA22("Restock2"),
	MA3("Get Customer Report"),
	MA4("Get Cash Position"),
	MA5("Add New Item"),
	MA6("Change Unit Price"),
	MA7("Add Cashier"),
	MA8("Remove Cashier"),
	MA9("Item Summary"),
	CASHIER("Cashier"),
	CA1("Make Sale"),
	CA2("Make Sale2"),
	CA3("Make Sale3"),
	CA4("Start Day"),
	CA5("End Day"),
	CUSTOMER("Customer"),
	COVER("Cover");
	
	private String label;
	private Card(String label) { this.label = label; }
	public String getLabel() { return label; }
}
