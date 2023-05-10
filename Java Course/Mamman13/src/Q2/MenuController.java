package Q2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Data data;//shared data between controllers

    @FXML
    public Button orderButton;//order button to move to order window
    @FXML
    private GridPane menuGrid;//grid for the menu

    //constructor
    public MenuController() {
        data = Data.getData();
    }

    //pull the  data to the controllers menugrid from menugrid in data
    @FXML
    private void initialize() {
        pullMenuData();
    }

    //Hard copy each element from data menuGrid to controller Menu Grid
    //row * numCols + col is function to move the grid line by line from left to right
    private void pullMenuData() {
        int numRows = (Data.getData().getMenuGrid().getChildren().size() / 4) - 1;
        int numCols = 4;
        for (int row = numRows; row >= 0; row--) {
            for (int col = 3; col >= 0; col--) {
                Node node = data.getMenuGrid().getChildren().get(row * numCols + col);
                menuGrid.add(node, col, row);
            }
        }
        System.out.println(data.getMenuGrid().getChildren());
    }

    //opens new order window
    @FXML
    private void OrderButtonAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ravid's Restaurant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}


