import java.util.Scanner;

public class Main {
	private static boolean isRegisterOpen(int userInput){
		if (userInput==6)
			return false;
		else
			return true;
	}
	private static void printUserMenu() {
		System.out.println("what would you like to do? please enter option number");
		System.out.println("1.add new item to purchase");
		System.out.println("2.checkout");
		System.out.println("3.check shopping cart");
		System.out.println("4.check total purchase cost");
		System.out.println("5.check current cash in register");	
		System.out.println("6.close Register");	
	}
	public static void main(String[] args) {
		//startup
		Register.uploadStoreItems();
		Register register=new Register();
		int navigator=0;
		String userInput;
		double quantity;
		double temp;
		Scanner sc=new Scanner(System.in);
		//working register
		while(isRegisterOpen(navigator)) {
			printUserMenu();
			navigator=sc.nextInt();
			switch(navigator) {
				case 1:
					System.out.println("please enter product name from list:");
					Register.printStoreItems();
					userInput=sc.next();
					System.out.println("please enter amount:");
					quantity=sc.nextDouble();
					register.addItemToPurchase(userInput, quantity);
					break;
				case 2:
					temp=register.currenPurchaseCost();
					System.out.println("Total price is: " + temp + ",please enter money amount you are paying");
					temp=register.change(sc.nextDouble());
					System.out.println("Thank you for your purchase, your change is: " + temp + " shekels");
					break;
				case 3:
					System.out.println(register.printPurchase());
					break;
				case 4:
					temp=register.currenPurchaseCost();
					System.out.println("Total cost is: " + temp);
					break;
				case 5:
					temp=register.currentCashInRegister();
					System.out.println("Register has " + temp + "shekel");
					break;
				case 6:
					System.out.println("Register is closed");	
					sc.close();
					break;
			}	
		}
	}
}
