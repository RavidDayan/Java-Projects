package Q2;

public class CheckLine {
	//attributes
	private MenuItem item;
	private double quantity;
	private double cost;
	//constructors
	CheckLine(MenuItem item,double quantity){
		this.item=item;
		this.quantity=quantity;
		this.cost=item.getPrice() * quantity;
	}
	//methods
	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}	
	public void updateCheckLine(double addedQuantity) {
		this.cost+=addedQuantity*this.item.getPrice();
		this.quantity+=addedQuantity;
	}
}
