package Q1;

public class BadAlarm extends Exception {
	public BadAlarm() {
		super("A bad address has been entered, please enter a known address");
	}
}
