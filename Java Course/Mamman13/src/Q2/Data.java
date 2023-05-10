package Q2;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private Register register;
    private static Data data = null;
    Stage orderStage;
    private double totalOrderPrice;
    private ArrayList<ArrayList<Object>> orderObjectGrid;
    private GridPane menuGrid;
    private GridPane orderGrid;
    private Label priceLabel;

    private Data() {
        register = new Register();
        orderObjectGrid = new ArrayList<>();
        menuGrid=new GridPane();
        priceLabel = new Label();
        orderGrid = new GridPane();
        loadMenuToDB();
        addMenuToMenuGrid();
    }

    public static Data getData() {
        if (data == null) {
            data = new Data();
            return data;
        }
        return data;
    }
    public GridPane getOrderGrid(){
        return this.orderGrid;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }

    public GridPane getMenuGrid() {
        return menuGrid;
    }

    private void setTotalOrderPrice() {
        priceLabel.setText(String.valueOf(totalOrderPrice));
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
                insertItemToMenuGrid(item, menuGridSize(menuGrid) + 1);
            }
        }
    }

    private void addOpeningDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Opening")) {
                insertItemToMenuGrid(item, menuGridSize(menuGrid) + 1);
            }
        }
    }

    private void addDessertDish() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Dessert")) {
                insertItemToMenuGrid(item, menuGridSize(menuGrid) + 1);
            }
        }
    }

    private void addDrink() {
        List<MenuItem> menu = register.getMenu();
        MenuItem item;
        for (int i = 0; i < menu.size(); i++) {
            item = menu.get(i);
            if (item.getCategory().equals("Drink")) {
                insertItemToMenuGrid(item, menuGridSize(menuGrid) + 1);
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
        comboBox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return comboBox;
    }

    private void addCategoryTitle(String title) {
        Label titleLabel = new Label(title);
        Label emptyLabel = new Label();
        titleLabel.setFont(new Font("Arial", 20));
        int index = menuGridSize(menuGrid) + 1;
        menuGrid.addRow(index, titleLabel, new Label(), new Label(), new Label());
    }

    private void addEmptyRow() {
        int index = menuGridSize(menuGrid) + 1;
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

    private static int menuGridSize(GridPane grid) {
        int size;
        size = grid.getChildren().size() / 4;
        return size;
    }

    private static int orderGridSize(GridPane grid) {
        int size;
        size = grid.getChildren().size() / 3;
        return size;
    }

    private void uploadOrder() {
        Label itemName;
        ComboBox comboBox;
        Label itemPrice;
        CheckBox addItem;
        for (ArrayList<Object> orderRow : orderObjectGrid) {
            addItem = (CheckBox) orderRow.get(3);
            System.out.println(addItem.isSelected());
            if (addItem.isSelected()) {
                itemName = new Label(((Label) orderRow.get(0)).getText());
                comboBox = new ComboBox();
                comboBox.setValue(((ComboBox) orderRow.get(1)).getValue());
                itemPrice = new Label(((Label) orderRow.get(2)).getText());
                insetItemToOrderGrid(itemName, comboBox, itemPrice);
            }
        }
        setTotalOrderPrice();
        System.out.println("ended function uploadOrder");
    }

    private void insetItemToOrderGrid(Label itemName, ComboBox<Integer> itemQuantity, Label itemPrice) {
        int index = orderGridSize(orderGrid) + 1;
        String itemQuantityBought = String.valueOf(itemQuantity.getValue());
        Label Quantity = new Label(itemQuantityBought);
        Label totalItemPrice = new Label(itemTotalPrice(itemQuantity, itemPrice));
        orderGrid.addRow(index, itemName, Quantity, totalItemPrice);
    }

    private String itemTotalPrice(ComboBox<Integer> itemQuantity, Label itemPrice) {
        double quantity = itemQuantity.getValue();
        double price = Double.valueOf(itemPrice.getText());
        double totalPrice = quantity * price;
        totalOrderPrice += totalPrice;
        String stringTotalPrice = String.valueOf(totalPrice);
        return stringTotalPrice;

    }
}
