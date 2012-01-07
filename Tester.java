import java.io.File;
import java.util.*;


public class Tester {
	public static void main(String args[]){
		new Tester();
	}
	
	public Tester(){
		Scanner in;
		InventorySystems system = new InventorySystems();
		try{
			in = new Scanner(new File("in.in"));
		}catch(Exception e){
			in = new Scanner(System.in);
		}
		
		System.out.println("1 = Manager, 2 = Cashier, 3 = Customer, 4 = System Admin");
		int user = in.nextInt();
		switch(user)
		{
			case 1:
				System.out.println("======== MANAGER MODE ===========");
				System.out.println("1 = Enroll Customer, 2 = Restock, 3 = Get Customer Report, "
									+ "4 = Get Cash Position, 5 = Add New Item, 6 = Add New Unit, "
									+ "7 = Change Unit Price, 8 = Add Cashier, 9 = Remove Cashier");
				break;
			case 2:
				System.out.println("======== CASHIER MODE ===========");
				System.out.println("1 = Make Sale");
				int conf = in.nextInt();
				if(conf==1){
					System.out.println("Enter store id");
					int storeid = in.nextInt();
					Store store = system.getStore(storeid);
					System.out.println("Enter cashier index");
					int cashierIndex = in.nextInt();
					Cashier cashier = store.getCashier(cashierIndex);
					System.out.println("Starting transaction...");
					cashier.startTransaction();
					HashMap<Unit, Integer> itemsToCheckout = new HashMap<Unit, Integer>();
					String itemid;
					a: while(true){
						System.out.println("Enter item id (0 to end transaction)");
						itemid = in.next();
						if(itemid.equals("0")) break a;
						Unit currentUnit;
						Unit tempUnit;
						Item currentItem = null;
						Item tempItem;
						Iterator<Item> itemsList = system.itemIterator();
						while(itemsList.hasNext()){
							tempItem = itemsList.next();
							if(tempItem.getItemCode().equals(itemid)){
								currentItem = tempItem;
								break;
							}
						}
						Iterator<Unit> unitList = currentItem.unitIterator();
						while(unitList.hasNext()){
							tempUnit = unitList.next();
							System.out.println(tempUnit.getUnitName()+" at "+tempUnit.getUnitPrice());
						}
						System.out.println("Enter desired unit of item and quantity separated by a space (e.g., DOZEN 1)");
						String unitname = in.next();
						int quantity = in.nextInt();
						unitList = currentItem.unitIterator();
						while(unitList.hasNext()){
							tempUnit = unitList.next();
							if(tempUnit.getUnitName().equalsIgnoreCase(unitname)){
								currentUnit = tempUnit;
								break;
							}
						}
						cashier.sell(currentUnit, quantity);
						System.out.println("Item added: "+quantity+" "+currentUnit+" "+currentItem);
					}
				}
				break;
			case 3:
				System.out.println("======== CUSTOMER MODE ===========");
				System.out.println("1 = Inquire Points");
				conf = in.nextInt();
				if(conf!=1) break;
				a: while(true){
					System.out.println("Enter customer id");
					int id = in.nextInt();
					System.out.println("Fetching your info...");
					Customer current;
					Customer temp;
					Iterator<Customer> customers = system.customerIterator();
					while(customers.hasNext()){
						temp = customers.next();
						if(temp.getId()==id)
							current = temp;
					}
					System.out.println("Customer: "+current.getLastName()+", "+current.getFirstName());
					System.out.println("Points Earned: "+current.getPointsEarned());
					System.out.println("Points Redeemed: "+current.getPointsRedeemed());
					System.out.println("Usable Points: "+current.getUsablePoints());
					break a;
				}
				break;
			case 4:
				System.out.println("======== SYSADMIN MODE ===========");
				System.out.println("1 = Setup Store");
				System.out.println("2 = Backup System");
				System.out.println("3 = Restore System");
				int choice = in.nextInt();
				switch(choice){
				case 1:
					System.out.println("Enter starting cash.");
					double cash = in.nextDouble();
					system.addStore(new Store(cash));
					System.out.println("Store added.");
					break;
				case 2:
					System.out.println("FEATURE NOT AVAILABLE");
					break;
				case 3:
					System.out.println("FEATURE NOT AVAILABLE");
					break;
				}
				break;
		}
	}
	
	
}
