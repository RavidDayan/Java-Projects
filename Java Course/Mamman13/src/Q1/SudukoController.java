package Q1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

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
                gridBoard.setConstraints(textField, y, x);
                gridBoard.add(textField, y, x);
                textFields[x][y] = textField;
                addEnterEventListener(x, y);
            }
        }
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
                this.board.setCell(value, x, y);
                System.out.println("function addEnterEventListner");

            }
        });
    }

    private void disableTextField(int x, int y) {
        this.textFields[x][y].setEditable(false);
    }

    private void enableTextField(int x, int y) {
        this.textFields[x][y].setEditable(true);
    }

    private void clearTextField(int x, int y) {
        this.textFields[x][y].setText("");
    }

}

