package Q2;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
        Scene scene = cancelButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    @FXML
    private void updateButtonOnAction() {
        Scene scene = updateButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    @FXML
    private void confirmButtonOnAction() {
        inputNameId();
        Scene scene = confirmButton.getScene();
        Window window = scene.getWindow();
        window.hide();
    }

    public OrderController() {
        data = Data.getData();
        confirmButton = new Button();
        updateButton = new Button();
        cancelButton = new Button();
    }

    @FXML
    private void initialize() {
        hardCopy();
        updatePriceLabel();
    }

    private void hardCopy() {
        int numRows = (Data.getData().getOrderGrid().getChildren().size() / 3) - 1;
        int numCols = 3;
        for (int row = numRows; row >= 0; row--) {
            for (int col = 2; col >= 0; col--) {
                Node node = data.getOrderGrid().getChildren().get(row * numCols + col);
                orderGrid.add(node, col, row);
            }
        }
    }

    private void updatePriceLabel() {
        String price = data.getPriceLabel().getText();
        priceLabel.setText(price);
    }

    private void inputNameId() {
        String name = inputName();
        String id = inputId();
        saveOrder(name + id);
    }

    private String inputName() {
        TextInputDialog name = new TextInputDialog();
        name.setTitle("Confirmation");
        name.setHeaderText("please enter your name:");
        name.setContentText("name:");
        Optional<String> result = name.showAndWait();
        return result.get();
    }

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

    private void illegalIdAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Warning!");
        alert.setContentText("The Id is not valid, please make sure the id is made up by 9 digits");
        alert.showAndWait();
    }

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

    private String OrderPerLine(ArrayList<Object> line) {
        String orderLine = "Name: " + ((Label) line.get(0)).getText();
        orderLine += "  Quantity: " + ((ComboBox<Integer>) line.get(1)).getValue();
        orderLine += "  Price: " + itemTotalPrice(((ComboBox<Integer>) line.get(1)), (Label) line.get(2));
        return orderLine;
    }

    private String itemTotalPrice(ComboBox<Integer> itemQuantity, Label itemPrice) {
        double quantity = itemQuantity.getValue();
        double price = Double.valueOf(itemPrice.getText());
        double totalPrice = quantity * price;
        String stringTotalPrice = String.valueOf(totalPrice);
        return stringTotalPrice;
    }

}



