import java.io.File;
import java.util.Scanner;


public class Tester {
	public static void main(String args[]){
		new Tester();
	}
	
	public Tester(){
		InventorySystems system = new InventorySystems();
		Scanner in;
		try{
			in = new Scanner(new File("in.in"));
		}catch(Exception e){
			in = new Scanner(System.in);
		}
		out: while(true)
		{
			System.out.println("====== SELECT A MODE =====");
			System.out.println("1 = Manager");
			System.out.println("2 = Cashier");
			System.out.println("3 = Customer");
			System.out.println("4 = System Admin");
			System.out.println("5 = Quit");
			System.out.println("==========================");
			int user = in.nextInt();
			switch(user)
			{
				case 1:
					a: while(true)
					{
						System.out.println("====== MANAGER MODE ======");
						System.out.println("==== SELECT AN ACTION ====");
						System.out.println("1 = Enroll Customer");
						System.out.println("2 = Restock");
						System.out.println("3 = Get Customer Report");
						System.out.println("4 = Get Cash Position");
						System.out.println("5 = Add New Item");
						System.out.println("6 = Add New Unit");
						System.out.println("7 = Change Unit Price");
						System.out.println("8 = Add Cashier");
						System.out.println("9 = Remove Cashier");
						System.out.println("10 = Return to Main Menu");
						System.out.println("==========================");
						int action = in.nextInt();
						in.nextLine();
						switch(action)
						{
							case 1:
								System.out.println("Enter Customer's First Name:");
								String firstname = in.nextLine();
								System.out.println("Enter Customer's Last Name:");
								String lastname = in.nextLine();
								System.out.println("Enter Customer's Address:");
								String address = in.nextLine();
								System.out.println("Enter Customer's Gender:");
								String gender = in.nextLine();
								System.out.println("Enter Customer's Age:");
								int age = in.nextInt();
								system.addCustomer(new Customer(firstname, lastname, system.nextCustomerId(), address, gender, age));
								System.out.println("Customer successfully enrolled! Press the enter key to continue.");
								in.nextLine();
								break;
							case 2:
								System.out.println("Enter Store Id:");
								int id = in.nextInt();
								Store toRestock = system.getStore(id);
								toRestock.startDeliveryBatch();
								while(true)
								{
									System.out.println("Enter item code of item delivered, quantity delivered, and wholesale price (e.g. P101 10 100)");
									System.out.println("Enter \"q\" to quit");
									String itemCode = in.next();
									if(itemCode.equals("q")) break;
									int quantity = in.nextInt();
									double wholesalePrice = in.nextDouble();
									toRestock.acceptDeliveryItem(system.getItem(itemCode), quantity, wholesalePrice);
								}
								toRestock.endDeliveryBatch();
								break;
							case 3:
								System.out.println("This feature is not available yet. Press the enter key to continue.");
								in.nextLine();
								break;
							case 4:
								System.out.println("This feature is not available yet. Press the enter key to continue.");
								in.nextLine();
								break;
							case 5:
								String itemCode = "";
								do
								{
									System.out.println("Enter Item Code:");
									itemCode = in.nextLine();
									if(system.containsItem(itemCode))
									{
										System.out.println("That item code is already assigned to a different item.");
									}
									else break;
								}
								while(true);
								System.out.println("Enter Item Name:");
								String itemName = in.nextLine();
								System.out.println("Enter Item Category");
								String itemCatoegory = in.nextLine();
								System.out.println("Item successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 6:
								System.out.println("Enter Item Code:");
								itemCode = in.nextLine();
								System.out.println("Enter Unit Name:");
								String unitName = in.nextLine();
								System.out.println("Enter Item Quantity:");
								int quantity = in.nextInt();
								in.nextLine();
								System.out.println("Enter Unit Price:");
								double unitPrice = in.nextDouble();
								in.nextLine();
								system.getItem(itemCode).addUnit(new Unit(unitName, unitPrice, quantity));
								System.out.println("Unit successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 7:
								break;
							case 8:
								System.out.println("Enter Store Id:");
								id = in.nextInt();
								Store store = system.getStore(id);
								System.out.println("Enter Amount of Cash:");
								double cash = in.nextDouble();
								store.addCashier(new Cashier(store, cash));
								System.out.println("Cashier successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 9:
								
								break;
							case 10:
								break a;
						}
					}
					break;
				case 2:
				
					break;
				case 3:
				
					break;
				case 4:
				
					break;
				case 5:
					break out;
			}
		}
	}
}