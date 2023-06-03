package Q2;

//class that times the cycle of pedstrians walking and cars driving
public class Timer implements Runnable {
    private boolean status = true;//status of the time true=top and bottom cars moving, false=side cars are moving
    private int timeTop;//time for the green light of cars moving top and bottom
    private int timeSide;//time for the green light of cars moving sideways

    //constructor
    public Timer(int timeTop, int timeSide) {
        this.timeSide = timeSide;
        this.timeTop = timeTop;
    }

    //returns the value of status
    public boolean getStatus() {
        return status;
    }

    //the Thread that runs the timer sleeps acording to the time for green light of each pair of traffic lights.run non-stop
    @Override
    public void run() {
        while (true) {
            if (status) {
                try {
                    Thread.sleep(timeTop);
                    status = !status;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Thread.sleep(timeSide);
                    status = !status;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
