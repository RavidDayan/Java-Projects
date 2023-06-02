package Q2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TrafficLight {
    private Circle redCircleLight;
    private Circle greenCircleLight;
    private Rectangle redRectangleLight;
    private Rectangle greenRectangleLight;

    public TrafficLight(Circle redCircle, Circle greenCircle, Rectangle redRectangle, Rectangle greenRectangle) {
        redCircleLight = redCircle;
        greenCircleLight = greenCircle;
        redRectangleLight = redRectangle;
        greenRectangleLight = greenRectangle;
    }
    public void carsGreenLight(){

    }
    public void carsRedLight(){

    }
    public void HideShapeColor(Shape shape){
        shape.setFill(Color.TRANSPARENT);
    }
}
