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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private Data data;
    @FXML
    public Button orderButton;
    @FXML
    private GridPane menuGrid;

    public MenuController() {
        data = Data.getData();
    }

    private void hardCopy() {
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
    @FXML
    private void OrderButtonAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("Ravid's Restaurant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize() {
        hardCopy();
    }

}


