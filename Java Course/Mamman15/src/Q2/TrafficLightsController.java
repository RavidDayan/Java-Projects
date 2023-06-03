package Q2;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLightsController {
    private Intersection intersection;//intersection of the graphics
    //time for traffic light green and red
    int topBottomGreenLightTime;
    int topBottomRedLightTime;
    //lights of all traffic lights.
    @FXML
    private Circle topRedCarLight;
    @FXML
    private Circle topGreenCarLight;
    @FXML
    private Rectangle topRedPedestrianLight;
    @FXML
    private Rectangle topGreenPedestrianLight;
    @FXML
    private Circle rightRedCarLight;
    @FXML
    private Circle rightGreenCarLight;
    @FXML
    private Rectangle rightRedPedestrianLight;
    @FXML
    private Rectangle rightGreenPedestrianLight;
    @FXML
    private Circle bottomRedCarLight;
    @FXML
    private Circle bottomGreenCarLight;
    @FXML
    private Rectangle bottomRedPedestrianLight;
    @FXML
    private Rectangle bottomGreenPedestrianLight;
    @FXML
    private Circle leftRedCarLight;
    @FXML
    private Circle leftGreenCarLight;
    @FXML
    private Rectangle leftRedPedestrianLight;
    @FXML
    private Rectangle leftGreenPedestrianLight;
    private TrafficLight top;
    private TrafficLight right;
    private TrafficLight bottom;
    private TrafficLight left;

    //constructor sets the time for timers.
    public TrafficLightsController(String topBottomGreenLightTime, String topBottomRedLightTime) {
        setTopBottomGreenLightTime(topBottomGreenLightTime);
        setTopBottomRedLightTime(topBottomRedLightTime);
    }

    //initialize the traffic lights and intersection and starts the light logic of intersection
    @FXML
    public void initialize() {
        top = new TrafficLight(topRedCarLight, topGreenCarLight, topRedPedestrianLight, topGreenPedestrianLight);
        right = new TrafficLight(rightRedCarLight, rightGreenCarLight, rightRedPedestrianLight, rightGreenPedestrianLight);
        bottom = new TrafficLight(bottomRedCarLight, bottomGreenCarLight, bottomRedPedestrianLight, bottomGreenPedestrianLight);
        left = new TrafficLight(leftRedCarLight, leftGreenCarLight, leftRedPedestrianLight, leftGreenPedestrianLight);
        intersection = new Intersection(top, bottom, left, right, topBottomGreenLightTime, topBottomRedLightTime);
        runningInterSection();
    }

    //converts time received in strings to integer
    public void setTopBottomGreenLightTime(String time) {
        topBottomGreenLightTime = Integer.parseInt(time);
    }

    //converts time received in strings to integer
    public void setTopBottomRedLightTime(String time) {
        topBottomRedLightTime = Integer.parseInt(time);
    }
//starts the intersection light logic
    private void runningInterSection() {
        Thread blinker = new Thread(intersection);
        blinker.start();
    }
}

