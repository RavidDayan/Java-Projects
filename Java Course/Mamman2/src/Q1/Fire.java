package Q1;

public class Fire extends Smoke {
	
	private boolean active;
	
	public Fire(String address,String operator) throws BadAlarm {
		super(address,operator);
		this.active=true;
	}
	public void action() {
		this.active=false;
		System.out.println("Address: "+this.address);
		System.out.println("Date & Time: "+this);
		System.out.println("Operator: "+this.operator);
	}
}
