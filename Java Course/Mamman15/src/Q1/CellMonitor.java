package Q1;

public class CellMonitor {
    boolean[] C;
    int rowSize;
    int colSize;

    public CellMonitor(Cell[][] C) {
        rowSize = C.length;
        colSize = C.length;
        this.C = new boolean[rowSize * colSize + 1];
        this.C[0] = true;
        for (int i = 1; i < this.C.length; i++) {
            this.C[i] = false;
        }
    }

    public synchronized boolean canCellPrint(int row, int col) {
        int convertedIndex = 1+(row) * rowSize + col;
        return C[convertedIndex - 1] == true;
    }

    public synchronized void updatePrintedCell(int row, int col) {
        int convertedIndex = 1+(row) * rowSize + col;
        C[convertedIndex] = true;
    }

}
