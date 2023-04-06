
public class Smoke extends Alarm {
	protected String operator;
	
	public Smoke(String address,String operator) throws BadAlarm {
		super(address);
		this.operator=operator;
	}
	public void action() {
		System.out.println("Address: "+this.address);
		System.out.println("Date & Time: "+this);
		System.out.println("Operator: "+this.operator);
	}
}
