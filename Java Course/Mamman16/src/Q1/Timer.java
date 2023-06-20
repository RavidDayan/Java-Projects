package Q1;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Timer extends Thread {//timer from each question's countdown
    private Label timeMeter;//label that shows the current timer second
    private int time;//how many seconds to countdown
    private boolean interupted;//statues if timer is interrupted from the outside

    public void interupt() {
        interupted = true;
    }

    public Timer(Label timeMeter, int time) {
        this.timeMeter = timeMeter;
        this.time = time;
        interupted = false;
    }//constructor

    @Override
    public void run() {
        int countdown = time;
        String stringedCountdown;
        while (countdown > 0 && !interupted) {
            try {
                sleep(1000);
                countdown--;
                stringedCountdown = String.valueOf(countdown);
                String finalStringedCountdown = stringedCountdown;
                Platform.runLater(() -> {
                    timeMeter.setText(finalStringedCountdown);
                });//updates label as a javafx from outside the main javafx thread
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }//runs as long as countdown is above 0 and not interrupted

    }
}
