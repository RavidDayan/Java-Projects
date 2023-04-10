package Q1;
import java.util.ArrayList;

public class TestAlarms {
	
	public static void process(ArrayList<Alarm> alarmList) {
		int smokeCounter = 0;
		for (Alarm alarm : alarmList) {
			alarm.action();
			if(alarm instanceof Smoke) {
				smokeCounter+=1;
			}
			if(alarm instanceof Elevator) {
				((Elevator) alarm).reset();
			}
			System.out.print("\n");
		}
		System.out.println("the number of smoke alarms is: "+smokeCounter);
	}
	
	
}