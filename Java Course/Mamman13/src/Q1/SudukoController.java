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
    private Button setButton;//setButton used to set numbers before game starts
    @FXML
    private GridPane gridBoard;//grid containing the text fields
    @FXML
    private Button clearButton;//restarts the game and clears the board
    private SudukoGame board;//backend of suduko game containing the board logic and numbers placed
    private TextField[][] textFields;//text-fields containing the text to enter numbers on grid

    //constructor
    public SudukoController() {
        gridBoard = new GridPane();
        textFields = new TextField[9][9];
        board = new SudukoGame();
        setButton = new Button();
    }

    //when set button is pressed all numbers entered change color to red and cant be replaced, button is disabled until game is cleared(by clearbutton)
    public void setButtonOnAction() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (this.board.getCell(x, y) != 0) {//when cell is 0 no number was entered to it
                    disableTextField(x, y);
                    changeTextFieldTextColor(x, y);
                } else {
                    clearTextField(x, y);//cleans  text entered as starting point for game
                }
            }
        }
        setButton.setDisable(true);
    }

    //clears board of existing game and enables the set button
    public void clearButtonOnAction() {
        clearBoards();
        colorGrid();
        setButton.setDisable(false);
    }

    // cleans each cell from text and cleans the corresponding cell from logic board
    private void clearBoards() {
        clearTextFields();
        clearNumberBoard();
    }

    //clears text fields of all textfield cells
    private void clearTextFields() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                enableTextField(x, y);
                clearTextField(x, y);
            }
        }
    }

    //clears the numbers from the logic board
    private void clearNumberBoard() {
        board.initBoard();
    }

    //initiates a all cells in grid with a TextField and the box color pattern
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

    //starts the basic game layout
    @FXML
    private void initialize() {
        gridInit();
    }

    //listens for ENTER key presses of text fields
// deals with exceptions by showing them as alerts
//try to insert entered value to game-board
//clears text entered if it's an illegal value
    private void addEnterEventListener(int x, int y) {
        this.textFields[x][y].setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String number = textFields[x][y].getText();
                try {
                    int value = Integer.valueOf(number);
                    this.board.setCell(value, x, y);
                } catch (Exception e) {
                    clearTextField(x, y);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Illegal entry");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } finally {
                    refreshTextFieldBoard();
                }
            }
        });
    }

    //turn text-field to be uneditable
    private void disableTextField(int x, int y) {
        this.textFields[x][y].setEditable(false);
    }

    //changes text-field to have red background(used to differentiate set upon text-fields)
    private void changeTextFieldTextColor(int x, int y) {
        this.textFields[x][y].setStyle("-fx-border-color: BLACK; -fx-background-color: RED;");
    }

    //turn text-field to be editable
    private void enableTextField(int x, int y) {
        this.textFields[x][y].setEditable(true);
    }

    //turns text-field to a blank text-field
    private void clearTextField(int x, int y) {
        this.textFields[x][y].setText("");
    }

    //centers text-field text
    private void centerTextFieldText(int x, int y) {
        this.textFields[x][y].setAlignment(Pos.CENTER);
    }

    //colors the grid box pattern
//we change if to color or not every 3 columns and everytime we move 3 rows
//grid text-fields are organized from 1 to 81 so to get node[x][y] the function is [x][y]=x*9+y(because node are 1-81 y starts from 1 to 9)
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
    }

    //constructs a cell in the grid and makes it contain a text-field from the text-field board
    private void initCell(TextField textField, int x, int y) {
        gridBoard.setConstraints(textField, y, x);
        gridBoard.add(textField, y, x);
        gridBoard.setVgrow(textField, Priority.ALWAYS);
    }

    //initiates a text-field
    //add events listener
    //sets text-field to fit cell and center text
    private void initTextField(TextField textField, int x, int y) {
        textFields[x][y] = textField;
        addEnterEventListener(x, y);
        centerTextFieldText(x, y);
        textField.setMaxHeight(Double.MAX_VALUE);
    }
//flips the value of boolean received and returns the opposite boolean
    private static boolean flipColor(boolean color) {
        if (color) {
            return false;
        }
        return true;
    }
//refreshes the text-field to show their assigned value if it exists
    private void refreshTextFieldBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.getCell(x, y) != 0) {
                    textFields[x][y].setText(String.valueOf(board.getCell(x, y)));
                }
            }
        }
    }

}

