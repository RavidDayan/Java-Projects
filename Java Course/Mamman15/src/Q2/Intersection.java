package Q2;

public class Intersection implements Runnable {
    int carGreenTime;
    int pedestriansGreenTime;
    TrafficLight top;
    TrafficLight bottom;
    TrafficLight left;
    TrafficLight right;
    Timer trafficTimer;

    public Intersection(TrafficLight top, TrafficLight bottom, TrafficLight left, TrafficLight right, int carGreenTime, int pedestriansGreenTime) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.carGreenTime = carGreenTime;
        this.pedestriansGreenTime = pedestriansGreenTime;
        this.trafficTimer = new Timer(carGreenTime, pedestriansGreenTime);
    }

    private void topBottomCarGreen() {
        this.left.carsRedLight();
        this.right.carsRedLight();
        this.top.carsGreenLight();
        this.bottom.carsGreenLight();
        while (trafficTimer.getStatus()) {
            left.run();
            right.run();
        }
    }

    private void sidesCarGreen() {
        this.top.carsRedLight();
        this.bottom.carsRedLight();
        this.left.carsGreenLight();
        this.right.carsGreenLight();
        while (!trafficTimer.getStatus()) {
            top.run();
            bottom.run();
        }

    }

    @Override
    public void run() {
        Thread timer = new Thread(trafficTimer);
        timer.start();
        while (true) {
            topBottomCarGreen();
            sidesCarGreen();
        }
    }
}
