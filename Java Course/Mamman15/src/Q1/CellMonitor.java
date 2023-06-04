package Q1;

//monitor for cells printing
public class CellMonitor {
    boolean[] printedCells;// array of which cell has already printed
    int rowSize;//rows of original 2d matrix
    int colSize;//columns of original 2d matrix

    public CellMonitor(Cell[][] C) {
        rowSize = C.length;
        colSize = C[0].length;
        this.printedCells = new boolean[rowSize * colSize + 1];
        this.printedCells[0] = true;//initiater cell so original matrix C[0][0] can print
        for (int i = 1; i < this.printedCells.length; i++) {
            this.printedCells[i] = false;
        }

    }//constructor,sets all printedCells to false except first

    public synchronized boolean canCellPrint(int row, int col) {
        int convertedIndex = 1 + (row) * colSize + col;//converts 2D index to 1D index
        return printedCells[convertedIndex - 1] == true;
    }//returns true if the cell before the asking cell has printed and false if not

    public synchronized void updatePrintedCell(int row, int col) {
        int convertedIndex = 1 + (row) * colSize + col;//converts 2D index to 1D index
        printedCells[convertedIndex] = true;
    }//updates printCells that the Matrix C[row][col] cell has printed inserts true.

}
