package Q1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudukoController {
    @FXML
    private Button setButton;
    @FXML
    private GridPane gridBoard;
    private int[][] board;
    private TextField[][] textFields;


    public SudukoController() {
        gridBoard = new GridPane();
        textFields = new TextField[9][9];
        board = new int[9][9];
        setButton = new Button();

    }

    public void setButtonOnAction() {
        this.setButton.setText("ravid");
        System.out.println("function setButtonOnAction");
    }

    private void gridInit() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField textField = new TextField("0");
                gridBoard.setConstraints(textField, y, x);
                gridBoard.add(textField, y, x);
            }
        }
    }
    @FXML
    private void initialize(){
        gridInit();
        System.out.println("function initalize");

    }
}
