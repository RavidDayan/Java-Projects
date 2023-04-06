import java.time.LocalDateTime;

public abstract class Alarm {

	protected LocalDateTime alarmActivationDate;
	protected String address;

	public Alarm(String address) {
		this.alarmActivationDate = LocalDateTime.now();
		this.address = address;
	}

	public String toString() {
		String DateTime = this.alarmActivationDate.toString();
		String DateTimeFormatted;
		DateTimeFormatted = DateTime.substring(0, 10) + " " + DateTime.substring(11, 16);
		return DateTimeFormatted;
	}

	public abstract void action();

}
