import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		TestAlarms.process(alarmListGenerator());
	}

	public static ArrayList<Alarm> alarmListGenerator() {
		ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
		alarmList.add(new Fire("address 1", "Operator 1"));
		alarmList.add(new Smoke("address 2", "Operator 2"));
		alarmList.add(new Elevator("address 3", 3));
		alarmList.add(new Fire("address 4", "Operator 4"));
		alarmList.add(new Smoke("address 5", "Operator 5"));
		alarmList.add(new Elevator("address 6", 6));
		alarmList.add(new Fire("address 7", "Operator 7"));
		alarmList.add(new Smoke("address 8", "Operator 8"));
		alarmList.add(new Elevator("address 9", 9));
		alarmList.add(new Fire("address 10", "Operator 10"));
		alarmList.add(new Smoke("address 11", "Operator 11"));
		alarmList.add(new Elevator("address 12", 12));
		return alarmList;
	}

}
