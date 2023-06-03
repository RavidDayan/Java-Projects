package Q2;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLightsController {
    private Intersection intersection;
    int carGreenTime;
    int pedestriansGreenTime;

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

    public TrafficLightsController(String carGreenTime, String pedestriansGreenTime) {
        setCarGreenTime(carGreenTime);
        setPedestriansGreenTime(pedestriansGreenTime);
    }

    @FXML
    public void initialize() {
        top = new TrafficLight(topRedCarLight, topGreenCarLight, topRedPedestrianLight, topGreenPedestrianLight);
        right = new TrafficLight(rightRedCarLight, rightGreenCarLight, rightRedPedestrianLight, rightGreenPedestrianLight);
        bottom = new TrafficLight(bottomRedCarLight, bottomGreenCarLight, bottomRedPedestrianLight, bottomGreenPedestrianLight);
        left = new TrafficLight(leftRedCarLight, leftGreenCarLight, leftRedPedestrianLight, leftGreenPedestrianLight);
        intersection = new Intersection(top, bottom, left, right, carGreenTime, pedestriansGreenTime);
        runningInterSection();
    }

    public void setCarGreenTime(String time) {
        carGreenTime = Integer.parseInt(time);
        System.out.println(carGreenTime);
    }

    public void setPedestriansGreenTime(String time) {
        pedestriansGreenTime = Integer.parseInt(time);
    }

    private void runningInterSection() {
        Thread blinker = new Thread(intersection);
        blinker.start();
    }
}

