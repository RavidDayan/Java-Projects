package Q2;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private Register register;
    @FXML
    public Button orderButton;
    @FXML
    private GridPane orderGrid;
    private ArrayList<ArrayList<Object>> orderObjectGrid = new ArrayList();
    @FXML
    public Label priceLabel;
    @FXML
    public Button confirmButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button cancelButton;

    public MenuController() {
        register = new Register();
        orderGrid = new GridPane();
        orderButton = new Button();
        confirmButton = new Button();
        updateButton = new Button();
        cancelButton = new Button();
        priceLabel = new Label();

    }
    @FXML
    private void initialize() {
        loadMenuToDB();
        addMenuToMenuGrid();
    }

    private void loadMenuToDB() {
        try {
            register.uploadMenu();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Problem with menu loading");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void addMenuToMenuGrid() {
        addCategoryTitle("Opening dish's");
        addOpeningDish();
        addEmptyRow();
        addCategoryTitle("Main dish's");
        addMainDish();
        addEmptyRow();
        addCategoryTitle("Dessert's");
        addDessertDish();
        addEmptyRow();
        addCategoryTitle("Drink's");
        addDrink();
        addEmptyRow();
    }

    private void addMainDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Main")) {
                insertItemToGrid(item, gridSize(orderGrid) + 1);
            }
        }
    }

    private void addOpeningDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Opening")) {
                insertItemToGrid(item, gridSize(orderGrid) + 1);
            }
        }
    }

    private void addDessertDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Dessert")) {
                insertItemToGrid(item, gridSize(orderGrid) + 1);
            }
        }
    }

    private void addDrink() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Drink")) {
                insertItemToGrid(item, gridSize(orderGrid) + 1);
            }
        }
    }

    private void insertItemToGrid(MenuItem item, int index) {
        Label itemName = new Label(item.getName());
        ComboBox<Integer> priceComboBox = createQuantityComboBox();
        Label itemPrice = new Label(String.valueOf(item.getPrice()));
        CheckBox addItem = new CheckBox();
        orderGrid.addRow(index, itemName, priceComboBox, itemPrice, addItem);
        insertItemToObjectGrid(itemName, priceComboBox, itemPrice, addItem);

    }

    private static ComboBox<Integer> createQuantityComboBox() {
        ComboBox<Integer> comboBox = new ComboBox<>();
        comboBox.setValue(0);
        comboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return comboBox;
    }

    private void addCategoryTitle(String title) {
        Label titleLabel = new Label(title);
        Label emptyLabel = new Label();
        titleLabel.setFont(new Font("Arial", 20));
        int index = gridSize(orderGrid) + 1;
        orderGrid.addRow(index, titleLabel, new Label(), new Label(), new Label());
    }

    private void addEmptyRow() {
        int index = gridSize(orderGrid) + 1;
        orderGrid.addRow(index, new Label(), new Label(), new Label(), new Label());
    }

    private void addItemsCheckLine() {
        List<Node> gridChildren = orderGrid.getChildren();
        for (int i = 0; i <= gridSize(orderGrid); i++) {
            if () {

            }
        }
    }

    private void insertItemToObjectGrid(Label itemName, ComboBox comboBox, Label itemPrice, CheckBox addItem) {
        ArrayList<Object> InnerArray = new ArrayList<>();
        InnerArray.add(itemName);
        InnerArray.add(comboBox);
        InnerArray.add(itemPrice);
        InnerArray.add(addItem);
        orderObjectGrid.add(InnerArray);

    }

    private static int gridSize(GridPane grid) {
        int size;
        size = grid.getChildren().size() / 3;
        return size;
    }
    public void addButtonOnAction() {

    }
}


