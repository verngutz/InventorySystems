import java.io.File;
import java.util.Scanner;


public class Tester {
	public static void main(String args[]){
		new Tester();
	}
	
	public Tester(){
		Scanner in;
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
			
				break;
			case 3:
			
				break;
			case 4:
			
				break;
		}
	}
}
