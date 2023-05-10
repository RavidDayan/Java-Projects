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

public class MenuController {
    private Data data;
    @FXML
    public Button orderButton;
    @FXML
    private GridPane menuGrid;

    public MenuController() {
        data=Data.getData();
        menuGrid = new GridPane();
        orderButton = new Button();
        System.out.println(menuGrid);
    }
    private void hardCopy() {
        int numRows = (Data.getData().getMenuGrid().getChildren().size()/4)-1;
        int numCols = 4;

// loop through each cell in the source GridPane
        for (int row = numRows; row >= 0; row--) {
            for (int col = 3; col >= 0; col--) {
                // get the child node (if any) at the current row and column of the source GridPane
                Node node = data.getMenuGrid().getChildren().get(row * numCols + col);
                menuGrid.add(node, col, row);
                System.out.println(menuGrid.getChildren());
            }
        }
    }
    @FXML
    private void initialize() {
        System.out.println(data.getOrderGrid().getChildren());
        hardCopy();
        System.out.println(menuGrid.getChildren());
        System.out.println(data.getOrderGrid().getChildren());
    }

}


