import java.util.List;
import java.util.LinkedList;
public class Register {
	//attributes
	private double cash;
	private List<CheckLine> purchaseList = new LinkedList<CheckLine>();
	private static List<Item> storeItemList=new LinkedList<Item>();
	//constructors
	public Register(){

	}
	public Register(double cashStartingPoint){
		this.cash=cashStartingPoint;
	}

	//methods
	//add check line item
	public void addItemToPurchase(String itemName, double quantity){
		int index=existCheckline(itemName);
		if(index == -1)
		{
			CheckLine new_checkline=new CheckLine(storeItemList.get(getItemPlacement(itemName)),quantity);
			this.purchaseList.add(new_checkline);
		}
		else {
			this.purchaseList.get(index).updateCheckLine(quantity);

		}
	}
	private int existCheckline(String itemName){
		int place=-1;
		for(CheckLine checkline : this.purchaseList){
			if(checkline.getItem().getName().equals(itemName))
			{
				place=this.purchaseList.indexOf(checkline);
				break;
			}
		}
		return place;
	}
	//returns current price of purchase
	public double currenPurchaseCost() {
		double sum=0;
		for  (CheckLine i : this.purchaseList){
			sum+=i.getCost();
		}
		return sum;
	}
	//returns the amount of change give back to the customer
	public double change(double payment) {
		double returnedAmount=payment-currenPurchaseCost();
		updateCashInRegister(currenPurchaseCost());
		cleanPurchaseList();
		return returnedAmount;
	}
	private void updateCashInRegister(double cashFlow)
	{
		this.cash+=cashFlow;
	}
	private void cleanPurchaseList() {
		this.purchaseList=new LinkedList<CheckLine>();
	}
	//returns current cash in register
	public double currentCashInRegister() {
		return this.cash;
	}
	//prints current purchase list
	public String printPurchase() {
		String purchase="";
		for (CheckLine checkline: this.purchaseList) {
			purchase+=ChecklineOrganizer(checkline.getItem().getName(),checkline.getQuantity(),checkline.getCost());
		}	 
		return purchase;
	}
	private String ChecklineOrganizer(String name,double quantity, double cost) {
		return "item name: "+name+" quantity: "+quantity+" cost: "+cost+System.lineSeparator();
	}
	//items that will available in store to check code
	public static void uploadStoreItems(){
		storeItemList.add(new Item("shoes",200));
		storeItemList.add(new Item("shirt",50));
		storeItemList.add(new Item("pants",70));
		storeItemList.add(new Item("hat",20));
	}
	public static void printStoreItems() {
		for (Item item: storeItemList) {
			printItemDetails(item);
		}
	}
	private static void printItemDetails(Item item) {
		System.out.println(item.getName()+" price:"+item.getPrice());
	}
	private static int getItemPlacement(String itemName) {
		int itemPlace = -1;
		for (Item item: storeItemList) {
			if(item.getName().equals(itemName))
			{
				itemPlace = storeItemList.indexOf(item);
				break; 
			}
		}
		return itemPlace;
	}

}

