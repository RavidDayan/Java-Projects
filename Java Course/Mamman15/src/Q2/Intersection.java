package Q2;

//represent an intersection of 4 traffic lights
public class Intersection implements Runnable {
    int topBottomGreenLight;//time for top and bottom green light
    int topBottomRedLight;//time for top and bottom red light
    TrafficLight top;//top traffic light
    TrafficLight bottom;//bottom traffic light
    TrafficLight left;//left traffic light
    TrafficLight right;//right traffic light
    Timer trafficTimer;//light timer of the intersection

    //constructor
    public Intersection(TrafficLight top, TrafficLight bottom, TrafficLight left, TrafficLight right, int topBottomGreenLight, int topBottomRedLight) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.topBottomGreenLight = topBottomGreenLight;
        this.topBottomRedLight = topBottomRedLight;
        this.trafficTimer = new Timer(topBottomGreenLight, topBottomRedLight);
    }

    //changes the traffic-lights lights according to that the bottom and top cars are green
    private void topBottomCarGreen() {
        this.left.carsRedLight();
        this.right.carsRedLight();
        this.top.carsGreenLight();
        this.bottom.carsGreenLight();
        while (trafficTimer.getStatus()) {//infinite loop to flash the sideway pedestrian lights green until the light time is over
            left.run();
            right.run();
        }
    }

    //changes the traffic-lights lights according to that the side cars are green
    private void sidesCarGreen() {
        this.top.carsRedLight();
        this.bottom.carsRedLight();
        this.left.carsGreenLight();
        this.right.carsGreenLight();
        while (!trafficTimer.getStatus()) {//infinite loop to flash the top and bottom pedestrian lights green until the light time is over
            top.run();
            bottom.run();
        }
    }

    //runs the intersection thread that starts a timer for the intersection and switches between top bottom and left right according to set timer for infinite times.
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
