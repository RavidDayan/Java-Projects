package Q2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.FileNotFoundException;

public class MenuController {
    private Register register;
    @FXML
    public Button orderButton;
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
    public MenuController(){
        register=new Register();
        orderGrid=new GridPane();
        orderButton=new Button();
        confirmButton=new Button();
        updateButton=new Button();
        cancelButton=new Button();
        priceLabel=new Label();

    }
    @FXML
    private void initialize(){
        loadMenuToGrid();
    }
    private void loadMenuToGrid(){
        try {
            register.uploadMenu();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Illegal entry");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}


