
public class CheckLine {
	//attributes
	private Item item;
	private double quantity;
	private double cost;
	//constructors
	CheckLine(Item item,double quantity){
		this.item=item;
		this.quantity=quantity;
		this.cost=item.getPrice() * quantity;
	}
	//methods
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
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
