public class Elevator extends Alarm{
	private int floor;
	
	public Elevator(String address,int floor) {
		super(address);
		this.floor=floor;
	}
	
	public void action() {
		System.out.println("Address: "+this.address);
		System.out.println("Date & Time: "+this);
		System.out.println("Floor: "+this.floor);
	}
	public void reset() {
		this.floor=0;
	}
}
