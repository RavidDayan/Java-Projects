package Q1;

public class SudukoGame {
    //variables
    private int[][] board;//game board for numbers

    //constructor
    public SudukoGame() {
        board = new int[9][9];
        initBoard();
    }
//initiate board to have zeros on all cells
    public void initBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                this.board[x][y] = 0;
            }
        }
    }
//enter a value to a game board cell and check if it fits all the gam,es logic
    public void setCell(int value, int x, int y) throws Exception {
        if (isLegalEntry(value, x, y)) {
            board[x][y] = value;
        }
    }
//checks if number inserted is the only one in row
    private boolean isLegalRowEntry(int row, int value) throws Exception {
        for (int col = 0; col < 9; col++) {
            if (value == board[row][col]) {
                Exception e = new NumberExistsInRowException(col + 1);
                throw e;
            }
        }
        return true;
    }
    //checks if number inserted is the only one in column
    private boolean isLegalColEntry(int col, int value) throws Exception {
        for (int row = 0; row < 9; row++) {
            if (value == board[row][col]) {
                Exception e = new NumberExistsInColException(row + 1);
                throw e;
            }
        }
        return true;
    }
    //checks if number inserted is the only one in relevant square
    private boolean isLegalSquareEntry(int value, int row, int col) throws Exception {
        int squareRowStart = (row / 3) * 3;
        int squareColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == board[squareRowStart + i][squareColStart + j]) {
                    Exception e = new NumberExistsInSquareException(squareRowStart + i + 1, squareColStart + j + 1);
                    throw e;
                }
            }
        }
        return true;
    }
//checks if number is between 1-9
    private boolean isLegalValue(int value) throws Exception {
        if (value > 0 && value < 10) {
            return true;
        }
        Exception e = new IlegalNumberException();
        throw e;
    }
//checks that all the games rules are obied
    private boolean isLegalEntry(int value, int row, int col) throws Exception {
        if (isLegalValue(value) && isLegalColEntry(col, value) && isLegalRowEntry(row, value) && isLegalSquareEntry(value, row, col)) {
            return true;
        }
        return false;
    }
//returns value in specific game board cell
    public int getCell(int x, int y) {
        return board[x][y];
    }
}

