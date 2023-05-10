package Q2;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
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
    private Register register;
    Stage orderStage ;
    @FXML
    public Button orderButton;
    @FXML
    private GridPane menuGrid;

    private GridPane orderGrid;
    private double totalOrderPrice;
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
        menuGrid = new GridPane();
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
                insertItemToMenuGrid(item, gridSize(menuGrid) + 1);
            }
        }
    }

    private void addOpeningDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Opening")) {
                insertItemToMenuGrid(item, gridSize(menuGrid) + 1);
            }
        }
    }

    private void addDessertDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Dessert")) {
                insertItemToMenuGrid(item, gridSize(menuGrid) + 1);
            }
        }
    }

    private void addDrink() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Drink")) {
                insertItemToMenuGrid(item, gridSize(menuGrid) + 1);
            }
        }
    }

    private void insertItemToMenuGrid(MenuItem item, int index) {
        Label itemName = new Label(item.getName());
        ComboBox<Integer> priceComboBox = createQuantityComboBox();
        Label itemPrice = new Label(String.valueOf(item.getPrice()));
        CheckBox addItem = new CheckBox();
        menuGrid.addRow(index, itemName, priceComboBox, itemPrice, addItem);
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
        int index = gridSize(menuGrid) + 1;
        menuGrid.addRow(index, titleLabel, new Label(), new Label(), new Label());
    }

    private void addEmptyRow() {
        int index = gridSize(menuGrid) + 1;
        menuGrid.addRow(index, new Label(), new Label(), new Label(), new Label());
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

    @FXML
    private void orderButtonOnAction() throws Exception {
        uploadOrder();
        setTotalOrderPrice();
        orderStage = new Stage();
        startOrder(orderStage);

    }
    public void startOrder(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Order");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void uploadOrder() {
        orderGrid = new GridPane();
        totalOrderPrice=0;
        Label itemName;
        ComboBox comboBox;
        Label itemPrice;
        CheckBox addItem;
        for (ArrayList<Object> orderRow : orderObjectGrid) {
            addItem = (CheckBox) orderRow.get(3);
            if (addItem.isSelected()) {
                itemName = (Label) orderRow.get(0);
                comboBox = (ComboBox) orderRow.get(1);
                itemPrice = (Label) orderRow.get(2);
                insetItemToOrderGrid(itemName, comboBox, itemPrice);
                System.out.println("uploadeditems");
            }
        }
    }

    private void insetItemToOrderGrid(Label itemName, ComboBox<Integer> itemQuantity, Label itemPrice) {
        int index = gridSize(orderGrid) + 1;
        String itemQuantityBought = String.valueOf(itemQuantity.getValue());
        Label Quantity = new Label(itemQuantityBought);
        Label totalItemPrice = new Label(itemTotalPrice(itemQuantity, itemPrice));
        orderGrid.addRow(index, itemName, Quantity, totalItemPrice);
    }

    private String itemTotalPrice(ComboBox<Integer> itemQuantity, Label itemPrice) {
        double quantity = itemQuantity.getValue();
        double price = Double.valueOf(itemPrice.getText());
        double totalPrice = quantity * price;
        totalOrderPrice+=totalPrice;
        String stringTotalPrice = String.valueOf(totalPrice);
        return stringTotalPrice;

    }
    private void setTotalOrderPrice(){
        priceLabel.setText(String.valueOf(totalOrderPrice));
    }
}


