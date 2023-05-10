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
        orderGrid=data.getOrderGrid();
        priceLabel = data.getPriceLabel();
        confirmButton = new Button();
        updateButton = new Button();
        cancelButton = new Button();
    }

    @FXML
    private void initialize() {
    }

}


