package Q1;

//Class the represents a single cell in matrix
public class Cell implements Runnable {
    private int cellValue;//Value of current Cell
    CellMonitor monitor;//monitor for all the same matrix cells

    private int[][] A;//original A Matrix
    private int[][] B;//original B Matrix
    private int row;//cells row index
    private int col;//cells col index

    public Cell(int[][] A, int[][] B, int row, int col, CellMonitor monitor) {
        this.A = A;
        this.B = B;
        this.col = col;
        this.row = row;
        this.monitor = monitor;
    }//constructor

    @Override
    public void run() {
        CalculateCellValue();
        boolean cantPrintYet = true;
        while (cantPrintYet) {
            cantPrintYet = !monitor.canCellPrint(row, col);
        }//checks if cell before printed until cell before has printed
        printCell();
        monitor.updatePrintedCell(row, col);
    }//calculates a cells value ,checks if the cell before it has already printed, prints itself only after cell already printed

    private void CalculateCellValue() {
        int[] rowA = getRow();
        int[] columnB = getCol();
        for (int i = 0; i < rowA.length; i++) {
            cellValue += rowA[i] * columnB[i];
        }

    }//calculates value of cell by using matrix cell multiplication(row x col)

    private int[] getRow() {
        return A[row];
    }//returns the cells row of multiplication from first matrix

    private int[] getCol() {
        int[] columnArray = new int[B.length];
        for (int row = 0; row < B.length; row++) {
            columnArray[row] = B[row][col];
        }
        return columnArray;
    }//returns the cells col of multiplication from first second

    private void printCell() {
        if (col + 1 == B[0].length) {
            System.out.println(cellValue + " ");

        } else {
            System.out.print(cellValue + " ");
        }

    }//prints cell value and goes down a line if cell is last in row
}
