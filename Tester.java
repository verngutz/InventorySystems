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
	}
}
