package Q1;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class SudukoController {
    @FXML
    private Button setButton;
    @FXML
    private GridPane gridBoard;
    @FXML
    private Button clearButton;
    private SudukoGame board;
    private TextField[][] textFields;

    public SudukoController() {
        gridBoard = new GridPane();
        textFields = new TextField[9][9];
        board = new SudukoGame();
        setButton = new Button();
    }

    public void setButtonOnAction() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (this.board.getCell(x, y) != 0) {
                    disableTextField(x, y);
                    changeTextFieldTextColor(x,y);
                } else {
                    clearTextField(x, y);
                }
            }
        }
    }

    public void clearButtonOnAction() {
        clearBoards();
        System.out.println("function clearButtonOnAction");
    }

    private void clearBoards() {
        clearTextFields();
        clearNumberBoard();
    }

    private void clearTextFields() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                enableTextField(x, y);
                clearTextField(x, y);
            }
        }
    }

    private void clearNumberBoard() {
        board.initBoard();
    }

    private void gridInit() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField textField = new TextField("");
                initCell(textField, x, y);
                initTextField(textField, x, y);
            }
        }
        colorGrid();
    }

    @FXML
    private void initialize() {
        gridInit();
    }

    private void addEnterEventListener(int x, int y) {
        this.textFields[x][y].setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String number = textFields[x][y].getText();
                int value = Integer.valueOf(number);
                try {
                    this.board.setCell(value, x, y);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Illegal entry");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                System.out.println("function addEnterEventListener");

            }
        });
    }

    private void disableTextField(int x, int y) {
        this.textFields[x][y].setEditable(false);
    }
    private void changeTextFieldTextColor(int x, int y){
        this.textFields[x][y].setStyle("-fx-border-color: BLACK; -fx-background-color: RED;");
    }
    private void enableTextField(int x, int y) {
        this.textFields[x][y].setEditable(true);
    }

    private void clearTextField(int x, int y) {
        this.textFields[x][y].setText("");
    }

    private void setTextFieldBTransparent(int x, int y) {
        this.textFields[x][y].setStyle("-fx-background-color: transparent;");
    }

    private void centerTextFieldText(int x, int y) {
        this.textFields[x][y].setAlignment(Pos.CENTER);
    }

    private void colorGrid() {
        boolean color = true;
        Node cell = null;
        for (int x = 0; x < 9; x++) {
            if (x % 3 != 0) {
                color = flipColor(color);
            }
            for (int y = 1; y < 10; y++) {
                if ((y - 1) % 3 == 0) {
                    color = flipColor(color);

                }
                cell = gridBoard.getChildren().get((x * 9) + y);

                if (color) {

                    cell.setStyle("-fx-border-color: black; -fx-background-color: grey;");
                } else {
                    cell.setStyle("-fx-border-color: black; -fx-background-color: white;");
                }
            }

        }
//        Node cell = gridBoard.getChildren().get(0 * 9 + 81);
//        cell.setStyle("-fx-background-color: grey;");
//        System.out.println("color the grid");

    }

    private void initCell(TextField textField, int x, int y) {
        gridBoard.setConstraints(textField, y, x);
        gridBoard.add(textField, y, x);
        textField.setMaxHeight(Double.MAX_VALUE);
        gridBoard.setVgrow(textField, Priority.ALWAYS);
    }

    private void initTextField(TextField textField, int x, int y) {
        textFields[x][y] = textField;
        setTextFieldBTransparent(x, y);
        addEnterEventListener(x, y);
        centerTextFieldText(x, y);
    }

    private static boolean flipColor(boolean color) {
        if (color) {
            return false;
        }
        return true;
    }
}

