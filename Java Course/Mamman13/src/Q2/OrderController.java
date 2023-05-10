package Q2;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderController {

    private Data data;//shared data between controllers
    @FXML
    private GridPane orderGrid;//grid pane shown for the orders(name,quanitity,totalprice)
    @FXML
    private Label priceLabel;//the label showing the orders price
    @FXML
    private Button confirmButton;//button to accept the order
    @FXML
    private Button updateButton;//button to go back and update the order
    @FXML
    private Button cancelButton;////button to cancel the order and reset the menu

    //resets the menu to a clean slate and closes the order window
    @FXML
    private void cancelButtonOnAction() {
        data.resetMenu();
        Scene scene = cancelButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    //closes the order window without cleaning the menu
    @FXML
    private void updateButtonOnAction() {
        Scene scene = updateButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    //asks for a name and id for the current order.
    //writes id to a file name the name+id and saves it in dirc.
    //resets the menu to a clean slate
    //closes the window after finished
    @FXML
    private void confirmButtonOnAction() {
        inputNameId();
        Scene scene = confirmButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    //constructor
    public OrderController() {
        data = Data.getData();
        confirmButton = new Button();
        updateButton = new Button();
        cancelButton = new Button();
    }

    //pull the  data to the controllers ordergrid from orderGrid in data
    //updates the price label with total price for order
    @FXML
    private void initialize() {
        pullOrderData();
        updatePriceLabel();
    }

    //Hard copy each element from data orderGrid ato controller orderGrid
    //row * numCols + col is function to move the grid line by line from left to right
    private void pullOrderData() {
        int numRows = (Data.getData().getOrderGrid().getChildren().size() / 3) - 1;
        int numCols = 3;
        for (int row = numRows; row >= 0; row--) {
            for (int col = 2; col >= 0; col--) {
                Node node = data.getOrderGrid().getChildren().get(row * numCols + col);
                orderGrid.add(node, col, row);
            }
        }
    }

    //pull the price label text from data and make a hard copy in order Controller
    private void updatePriceLabel() {
        String price = data.getPriceLabel().getText();
        priceLabel.setText(price);
    }

    //reeqests name and id and writes the order into a txt file
    private void inputNameId() {
        String name = inputName();
        String id = inputId();
        saveOrder(name + id);
    }

    //pop up a request for the person thats orderings name
    private String inputName() {
        TextInputDialog name = new TextInputDialog();
        name.setTitle("Confirmation");
        name.setHeaderText("please enter your name:");
        name.setContentText("name:");
        Optional<String> result = name.showAndWait();
        return result.get();
    }

    //pop up a request for the person thats orderings id
    //in case id is ilegal(not number only and not 9 digits) pops an alert and ask for id again
    private String inputId() {
        TextInputDialog iD = new TextInputDialog();
        iD.setTitle("Confirmation");
        iD.setHeaderText("please enter your id:");
        iD.setContentText("id:");
        Optional<String> result = iD.showAndWait();
        while (!isLegalId(result.get())) {
            illegalIdAlert();
            result = iD.showAndWait();
        }
        return result.get();
    }

    //checks if is number only and 9 digits
    private boolean isLegalId(String id) {
        if (id.length() != 9) {
            return false;
        }
        char digit;
        for (int i = 0; i < 9; i++) {
            digit = id.charAt(i);
            if (!Character.isDigit(digit)) {
                return false;
            }
        }
        return true;
    }

    //alerts of an ilegal id input
    private void illegalIdAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Warning!");
        alert.setContentText("The Id is not valid, please make sure the id is made up by 9 digits");
        alert.showAndWait();
    }

    //writes file by going throught 2d array list of items and goes throught each item line, if checkbox is ticked rights the item line in txt file,adds the total price in the end.
    //checks for interuptions in writing
    private void saveOrder(String nameId) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/Q2/" + nameId + ".txt"));
            String content;
            CheckBox itemCheckbox;
            for (ArrayList<Object> array : data.getOrderObjects()) {
                itemCheckbox = ((CheckBox) array.get(3));
                if (itemCheckbox.isSelected()) {
                    content = OrderPerLine(array);
                    writer.write(content);
                    content = "\n";
                    writer.write(content);
                    writer.flush();
                }
            }
            content = "\n";
            writer.write("Total order price is: " + priceLabel.getText());
            writer.flush();
            data.resetMenu();
            System.out.println("File write operation completed.");

        } catch (IOException e) {
            System.out.println("File write operation not completed.");
            ;
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("File write operation not completed.");
            }
        }
    }

    //converts item line to a string
    private String OrderPerLine(ArrayList<Object> line) {
        String orderLine = "Name: " + ((Label) line.get(0)).getText();
        orderLine += "  Quantity: " + ((ComboBox<Integer>) line.get(1)).getValue();
        orderLine += "  Price: " + itemTotalPrice(((ComboBox<Integer>) line.get(1)), (Label) line.get(2));
        return orderLine;
    }

    //calculates the total sum for each item ordered byp price*quantity
    private String itemTotalPrice(ComboBox<Integer> itemQuantity, Label itemPrice) {
        double quantity = itemQuantity.getValue();
        double price = Double.valueOf(itemPrice.getText());
        double totalPrice = quantity * price;
        String stringTotalPrice = String.valueOf(totalPrice);
        return stringTotalPrice;
    }

}



