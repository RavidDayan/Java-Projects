package Q2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static java.lang.Thread.sleep;

public class TrafficLight implements Runnable {
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

    public void carsGreenLight() {
        colorShapeGreen(greenCircleLight);
        hideShapeColor(redCircleLight);
        colorShapeRed(redRectangleLight);
        hideShapeColor(greenRectangleLight);
    }

    public void carsRedLight() {
        colorShapeGreen(greenRectangleLight);
        hideShapeColor(redRectangleLight);
        colorShapeRed(redCircleLight);
        hideShapeColor(greenCircleLight);
    }

    public void hideShapeColor(Shape shape) {
        shape.setFill(Color.TRANSPARENT);
    }

    public void colorShapeRed(Shape shape) {
        shape.setFill(Color.RED);
    }

    public void colorShapeGreen(Shape shape) {
        shape.setFill(Color.GREEN);
    }

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
