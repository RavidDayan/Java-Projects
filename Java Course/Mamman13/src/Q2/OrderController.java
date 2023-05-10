package Q2;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private Data data;
    @FXML
    private GridPane orderGrid;
    @FXML
    private Label priceLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button cancelButton;
    @FXML
    private void cancelButtonOnAction() {
    }

    public OrderController() {
        data=Data.getData();
        confirmButton = new Button();
        updateButton = new Button();
        cancelButton = new Button();
    }

    @FXML
    private void initialize() {
        hardCopy();
        updatePriceLabel();
        System.out.println("initialize function");
    }
    private void hardCopy() {
        int numRows = (Data.getData().getOrderGrid().getChildren().size() / 3) - 1;
        int numCols = 3;
        for (int row = numRows; row >= 0; row--) {
            for (int col = 2; col >= 0; col--) {
                Node node = data.getOrderGrid().getChildren().get(row * numCols + col);
                orderGrid.add(node, col, row);
            }
        }
        System.out.println(data.getOrderGrid().getChildren());
    }
    private void updatePriceLabel(){
       String price= data.getPriceLabel().getText();
        priceLabel.setText(price);
    }

}


