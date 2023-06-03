package Q2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static java.lang.Thread.sleep;

//class that represents a single traffic light
public class TrafficLight implements Runnable {
    private Circle redCircleLight;
    private Circle greenCircleLight;
    private Rectangle redRectangleLight;
    private Rectangle greenRectangleLight;

    //constructor,
    public TrafficLight(Circle redCircle, Circle greenCircle, Rectangle redRectangle, Rectangle greenRectangle) {
        redCircleLight = redCircle;
        greenCircleLight = greenCircle;
        redRectangleLight = redRectangle;
        greenRectangleLight = greenRectangle;
    }

    //changes traffic lights to show a green light for cars and red for pedestrians
    public void carsGreenLight() {
        colorShapeGreen(greenCircleLight);
        hideShapeColor(redCircleLight);
        colorShapeRed(redRectangleLight);
        hideShapeColor(greenRectangleLight);
    }

    //changes traffic lights to show a ref light for cars and green for pedestrians
    public void carsRedLight() {
        colorShapeGreen(greenRectangleLight);
        hideShapeColor(redRectangleLight);
        colorShapeRed(redCircleLight);
        hideShapeColor(greenCircleLight);
    }

    //sets a light color to transparent
    public void hideShapeColor(Shape shape) {
        shape.setFill(Color.TRANSPARENT);
    }

    //sets a light color to red
    public void colorShapeRed(Shape shape) {
        shape.setFill(Color.RED);
    }

    //sets a light color to green
    public void colorShapeGreen(Shape shape) {
        shape.setFill(Color.GREEN);
    }

    //changes the green pedestrian light off and on when it green
    @Override
    public void run() {
        colorShapeGreen(greenRectangleLight);
        try {
            sleep(200);
        } catch (InterruptedException e) {

        }
        hideShapeColor(greenRectangleLight);
        try {
            sleep(200);
        } catch (InterruptedException e) {
        }
    }
}
