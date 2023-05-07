package Q1;

public class SudukoGame {
    private int[][] board;

    Public SudukoGame() {
        board = new int[9][9];
        initBoard();
    }

    private void initBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                this.board[x][y] = 0;
            }
        }
    }

    public boolean setCell(int value, int x, int y) {
        if (isLegalEntry(value, x, y)) {
            board[x][y] = value;
            return true;
        }
        return false;
    }

    private boolean isLlegalRowEntry(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (value == board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean isLegalColEntry(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (value == board[row][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean isLegalSquareEntry(int value, int row, int col) {
        int squareRowStart = (row / 3) * 3;
        int squareColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == board[squareRowStart + i][squareColStart + j]) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isLegalValue(int value) {
        if (value > 0 && value < 10) {
            return true;
        }
        return false;
    }

    private boolean isLegalEntry(int value, int row, int col) {
        if (isLegalValue(value) && isLegalColEntry(col, value) && isLlegalRowEntry(row, value) && isLegalSquareEntry(value, row, col)) {
            return true;
        }
        return false;
    }

    public int getCell(int x, int y) {
        return board[x][y];
    }
}

