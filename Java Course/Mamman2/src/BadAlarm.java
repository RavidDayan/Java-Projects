
public class BadAlarm extends Exception {
	public BadAlarm() {
		System.err.println("\u001B[0m"+"A bad address has been entered, please enter a known address");
	}
}
