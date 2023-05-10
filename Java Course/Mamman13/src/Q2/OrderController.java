package Q2;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class OrderController {
    @FXML
    private GridPane orderGrid;
    public OrderController(GridPane orderGrid,double totalPrice){

    }
    @FXML
    private void cancelButtonOnAction() {
        System.out.println("cancel button function");
        uploadOrder();
        setTotalOrderPrice();

    }
}