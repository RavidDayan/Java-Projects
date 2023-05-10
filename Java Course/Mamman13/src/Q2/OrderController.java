package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OrderController {
    @FXML
    private GridPane orderGrid;
    @FXML
    public Label priceLabel;
    @FXML
    public Button confirmButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button cancelButton;
    public OrderController(GridPane orderGrid,double totalPrice){
        this.orderGrid=orderGrid;

    }
    @FXML
    private void cancelButtonOnAction() {
        System.out.println("cancel button function");
    }
    private void setTotalOrderPrice(double totalOrderPrice){
        priceLabel.setText(String.valueOf(totalOrderPrice));
    }
}