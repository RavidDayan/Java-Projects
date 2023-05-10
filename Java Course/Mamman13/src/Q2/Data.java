package Q2;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//the data class constructs a singelton to be used by controllers so they will all pull data from the same source
public class Data {
    private Register register;//operating register for menu
    private static Data data = null;//singelton instance
    Stage orderStage;//new stage for order window
    private double totalOrderPrice;//price of total order
    private ArrayList<ArrayList<Object>> orderObjectGrid;//2d array list in order to have quick refrences to all objects used on grids(name label,quantity combobox,price label,add checkbox)
    private GridPane menuGrid;//grid that represents the layout of the (name label,quantity combobox,price label,add checkbox
    private GridPane orderGrid;//grid that represents the layout of orders(name label,quantity combobox,totalprice label)
    private Label priceLabel;//shows total order price at order screen

    //the constructor is private, so we can make sure only 1 instance will be made
    private Data() {
        register = new Register();
        orderObjectGrid = new ArrayList<>();
        menuGrid = new GridPane();
        priceLabel = new Label();
        orderGrid = new GridPane();
        loadMenuToDB();
        addMenuToMenuGrid();
    }

    //method for outer classes to receive an instance of Data class, if no instance has been made a new one will be sent, otherwise the first instance will be sent again
    public static Data getData() {
        if (data == null) {
            data = new Data();
            return data;
        }
        return data;
    }

    //getters
    public GridPane getOrderGrid() {
        setTotalOrderPrice();
        uploadOrder();
        return this.orderGrid;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }

    public GridPane getMenuGrid() {
        return menuGrid;
    }

    public ArrayList<ArrayList<Object>> getOrderObjects() {
        return orderObjectGrid;
    }

    //change the price lable according to the latest order
    private void setTotalOrderPrice() {
        priceLabel.setText(String.valueOf(totalOrderPrice));
    }

    //loads the txt file to register
    private void loadMenuToDB() {
        try {
            register.uploadMenu();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Problem with menu loading,please check Menu file");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    //adds the uploaded menu to the menu grid
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

    //add a titles to categorys
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

    //receives a single menuItem and converts it to the relevant object and adds to menu grid.
    private void insertItemToMenuGrid(MenuItem item, int index) {
        Label itemName = new Label(item.getName());
        ComboBox<Integer> priceComboBox = createQuantityComboBox();
        Label itemPrice = new Label(String.valueOf(item.getPrice()));
        CheckBox addItem = new CheckBox();
        menuGrid.addRow(index, itemName, priceComboBox, itemPrice, addItem);
        insertItemToObjectGrid(itemName, priceComboBox, itemPrice, addItem);

    }

    //returns a combobox that has value between 0-10, we asume that we dont allow a customer to order more than 10 of the same item on menu.
    private static ComboBox<Integer> createQuantityComboBox() {
        ComboBox<Integer> comboBox = new ComboBox<>();
        comboBox.setValue(0);
        comboBox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return comboBox;
    }

    //adds a grid line that is specif for category titles(bigger text)
    private void addCategoryTitle(String title) {
        Label titleLabel = new Label(title);
        Label emptyLabel = new Label();
        titleLabel.setFont(new Font("Arial", 20));
        int index = menuGridSize(menuGrid) + 1;
        menuGrid.addRow(index, titleLabel, new Label(), new Label(), new Label());
    }

    //adds rempty row to menu grid
    private void addEmptyRow() {
        int index = menuGridSize(menuGrid) + 1;
        menuGrid.addRow(index, new Label(), new Label(), new Label(), new Label());
    }

    //recicves the grid objects of a line and inserts them in the 2d array so we will have the same x,y for the same item in menu grid and item array
    private void insertItemToObjectGrid(Label itemName, ComboBox comboBox, Label itemPrice, CheckBox addItem) {
        ArrayList<Object> InnerArray = new ArrayList<>();
        InnerArray.add(itemName);
        InnerArray.add(comboBox);
        InnerArray.add(itemPrice);
        InnerArray.add(addItem);
        orderObjectGrid.add(InnerArray);
    }

    //returns the grids menu row size
    private static int menuGridSize(GridPane grid) {
        int size;
        size = grid.getChildren().size() / 4;
        return size;
    }

    //returns the order grids row size
    private static int orderGridSize(GridPane grid) {
        int size;
        size = grid.getChildren().size() / 3;
        return size;
    }

    //uploads all checked to be added items from menu to ordergrid.we go through each item line in 2d array and if the checkbox is selected we add the whole line to the orderGrid
    public void uploadOrder() {
        orderGrid = new GridPane();
        totalOrderPrice = 0;
        Label itemName;
        ComboBox comboBox;
        Label itemPrice;
        CheckBox addItem;
        for (ArrayList<Object> orderRow : orderObjectGrid) {
            addItem = (CheckBox) orderRow.get(3);
            if (addItem.isSelected()) {
                itemName = new Label(((Label) orderRow.get(0)).getText());
                comboBox = new ComboBox();
                comboBox.setValue(((ComboBox) orderRow.get(1)).getValue());
                itemPrice = new Label(((Label) orderRow.get(2)).getText());
                insetItemToOrderGrid(itemName, comboBox, itemPrice);
            }
        }
    }

    //inserts a single item to grid
    private void insetItemToOrderGrid(Label itemName, ComboBox<Integer> itemQuantity, Label itemPrice) {
        int index = orderGridSize(orderGrid) + 1;
        String itemQuantityBought = String.valueOf(itemQuantity.getValue());
        Label Quantity = new Label(itemQuantityBought);
        Label totalItemPrice = new Label(itemTotalPrice(itemQuantity, itemPrice));
        orderGrid.addRow(index, itemName, Quantity, totalItemPrice);
    }

    //converts and calculates the price of an item by price*quantity and adds the sum to the total lorder sum
    private String itemTotalPrice(ComboBox<Integer> itemQuantity, Label itemPrice) {
        double quantity = itemQuantity.getValue();
        double price = Double.valueOf(itemPrice.getText());
        double totalPrice = quantity * price;
        totalOrderPrice += totalPrice;
        String stringTotalPrice = String.valueOf(totalPrice);
        return stringTotalPrice;
    }

    //clears all ticked checkboxes and sets comboxes to 0;
    public void resetMenu() {
        for (ArrayList<Object> itemLine : data.getOrderObjects()) {
            resetLine(itemLine);
        }

    }

    //clears a specific item line.
    private void resetLine(ArrayList<Object> ItemLine) {
        ((ComboBox<Integer>) ItemLine.get(1)).setValue(0);
        ((CheckBox) ItemLine.get(3)).setSelected(false);
    }

}
