import java.io.File;
import java.util.*;


public class Tester {
	public static void main(String args[]){
		new Tester();
	}
	
	public Tester(){
		LinkedList<ISMemento> saveStack = new LinkedList<ISMemento>();
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
			System.out.println("5 = Debug Mode");
			System.out.println("6 = Quit");
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
						System.out.println("6 = Change Unit Price");
						System.out.println("7 = Add Cashier");
						System.out.println("8 = Remove Cashier");
						System.out.println("9 = Return to Main Menu");
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
								System.out.println("Enter Item Category:");
								String itemCategory = in.nextLine();
								System.out.println("Enter Item Unit:");
								String itemUnit = in.nextLine();
								System.out.println("Enter Unit Price:");
								double unitPrice = in.nextDouble();
								system.addItem(new Item(itemCode, itemName, itemCategory, itemUnit, unitPrice));
								System.out.println("Item successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 6:
								System.out.println("Enter Item Code:");
								itemCode = in.nextLine();
								System.out.println("Enter New Price:");
								unitPrice = in.nextDouble();
								in.nextLine();
								system.getItem(itemCode).setUnitPrice(unitPrice);
								System.out.println("Unit successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 7:
								System.out.println("Enter Store Id:");
								id = in.nextInt();
								in.nextLine();
								Store store = system.getStore(id);
								store.addCashier(new Cashier(store));
								System.out.println("Cashier successfully added! Press the enter key to continue.");
								in.nextLine();
								break;
							case 8:
								System.out.println("Enter Store Id:");
								id = in.nextInt();
								in.nextLine();
								store = system.getStore(id);
								System.out.println("Enter cashier index");
								int cashierIndex = in.nextInt();in.nextLine();
								store.removeCashier(cashierIndex);
								System.out.println("Cashier successfully removed! Press the enter key to continue.");
								in.nextLine();
								break;
							case 9:
								break a;
						}
					}
					break;
				case 2:
					System.out.println("====== CASHIER MODE ======");
					System.out.println("==== SELECT AN ACTION ====");
					System.out.println("1 = Start Day");
					System.out.println("2 = Make Sale");
					System.out.println("3 = End Day");
					System.out.println("==========================");
					int conf = in.nextInt();
					switch(conf)
					{
						case 1:
							System.out.println("Enter store id");
							int storeid = in.nextInt();
							Store store = system.getStore(storeid);
							System.out.println("Enter cashier index");
							int cashierIndex = in.nextInt();
							Cashier cashier = store.getCashier(cashierIndex);
							cashier.startDay();
							System.out.println("Welcome!");
							break;
						case 2:
							System.out.println("Enter store id");
							storeid = in.nextInt();
							store = system.getStore(storeid);
							System.out.println("Enter cashier index");
							cashierIndex = in.nextInt();
							cashier = store.getCashier(cashierIndex);
							System.out.println("Starting transaction...");
							cashier.startTransaction();
							HashMap<Item, Integer> itemsToCheckout = new HashMap<Item, Integer>();
							String itemid;
							a: while(true){
								System.out.println("Enter item id (0 to end transaction)");
								itemid = in.next();
								if(itemid.equals("0")) break a;
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
								/*
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
								*/
								System.out.println("Enter number of items");
								int quantity = in.nextInt();
								cashier.sell(currentItem, quantity);
								System.out.println("Item added: "+quantity+" "+currentItem.getUnitName()+" "+currentItem.getItemName());
								//currentUnit.deductFromStock(quantity);
								itemsToCheckout.put(currentItem, quantity);
							}
							Customer buyer = null;
							int pointsUsed = 0;
							System.out.println("Is customer enrolled in loyalty program? (Y/N)");
							String answer = in.next();
							if(answer.equalsIgnoreCase("y"))
							{
								System.out.println("Enter customer id: ");
								int customerId = in.nextInt();
								buyer = system.getCustomer(customerId);
								System.out.println("Usable points: "+buyer.getUsablePoints());
								System.out.println("Enter points used: ");
								pointsUsed = in.nextInt();
							}
							Transaction result = cashier.endTransaction(buyer, pointsUsed);
							System.out.println("Amount due: " + result.getRevenue());
							//deduct from stock
							/*Iterator<Map.Entry<Item, Integer>> it = itemsToCheckout.entrySet().iterator();
							while(it.hasNext()){
								Map.Entry<Item, Integer> pair = it.next();
								pair.getKey().deductFromStock(pair.getValue());
							}
							*/
							if(buyer != null)
							{
								store.addTransaction(result);
							}
							break;
						case 3:
							System.out.println("Enter store id");
							storeid = in.nextInt();
							store = system.getStore(storeid);
							System.out.println("Enter cashier index");
							cashierIndex = in.nextInt();
							cashier = store.getCashier(cashierIndex);
							cashier.endDay();
							System.out.println("Goodbye!");
							break;
					}
					break;
				case 3:
					System.out.println("===== CUSTOMER MODE ======");
					System.out.println("==== SELECT AN ACTION ====");
					System.out.println("1 = Inquire Points");
					System.out.println("==========================");
					conf = in.nextInt();
					if(conf!=1) break;
					a: while(true){
						System.out.println("Enter customer id");
						int id = in.nextInt();
						System.out.println("Fetching your info...");
						Customer current = null;
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
					System.out.println("====== SYSADMIN MODE =====");
					System.out.println("==== SELECT AN ACTION ====");
					System.out.println("1 = Setup Store");
					System.out.println("2 = Backup System");
					System.out.println("3 = Restore System");
					System.out.println("==========================");
					int choice = in.nextInt();
					switch(choice){
					case 1:
						System.out.println("Enter starting cash.");
						double cash = in.nextDouble();
						int tempid = system.nextStoreId();
						System.out.println("Enter cash per cashier");
						double cpc = in.nextDouble();
						system.addStore(new Store(tempid, cash, cpc));
						System.out.println("Store added. StoreID: "+tempid);
						break;
					case 2:
						saveStack.push(system.saveToMemento());
						System.out.println("System successfully backed up.");
						break;
					case 3:
						if(saveStack.size() > 0)
						{
							system.restoreFromMemento(saveStack.pop());
							System.out.println("System successfully restored to previous restore point.");
						}
						else
						{
							System.out.println("No restore points available.");
						}
						break;
					}
					break;
				case 5:
					System.out.println("1 = View Customer List");
					System.out.println("2 = View Inventory List");
					System.out.println("3 = View Store List");
					choice = in.nextInt();
					switch(choice){
					case 1:
						Iterator<Customer> cust = system.customerIterator();
						while(cust.hasNext()){
							Customer curr = cust.next();
							System.out.println(curr.getId()+": "+curr.getLastName()+", "+curr.getFirstName()+
							" PTS EARNED: "+curr.getPointsEarned()+" REDEEMED PTS: "+curr.getPointsRedeemed()+" USABLE PTS: "+curr.getUsablePoints());
							
						}
						break;
					case 2:
						Iterator<Item> items = system.itemIterator();
						while(items.hasNext()){
							Item curr = items.next();
							System.out.println(curr.getItemCode()+": "+curr.getItemCategory()+" "+curr.getItemName()+" "+curr.getUnitName()+" "+curr.getUnitPrice());
						}
						break;
						
					case 3:
						Iterator<Store> stores = system.storeIterator();
						while(stores.hasNext()){
							Store store = stores.next();
							System.out.println("Store ID: "+store.getStoreID()+" Cash: "+store.getTotalCash());
							Iterator<Cashier> cashiers = store.cashierIterator();
							int counter=0;
							while(cashiers.hasNext()){
								System.out.println("Cashier index: "+counter+" Cash: "+cashiers.next().getCash());
								counter++;
							}
							Iterator<Map.Entry<Item, Integer>> inventory = store.inventoryIterator();
							System.out.println("Drawing Inventory table...");
							while(inventory.hasNext()){
								Map.Entry<Item, Integer> curr = inventory.next();
								System.out.println("Item: "+curr.getKey().getItemCode()+" "+curr.getKey().getItemName()+" Quantity left: "+curr.getValue());
							}
						}
						break;
					default:
						break;
					}
					break;
				case 6:
					break out;
			}
		}
	}
}
