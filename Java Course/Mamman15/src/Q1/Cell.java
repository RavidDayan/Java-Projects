package Q1;

public class Cell implements Runnable {
    private int cellValue;
    boolean isPrinted=false;
    CellMonitor monitor;

    private int[][] A;
    private int[][] B;
    private int row;
    private int col;

    public Cell(int[][] A, int[][] B, int row, int col,CellMonitor monitor) {
        this.A = A;
        this.B = B;
        this.col = col;
        this.row = row;
        this.monitor=monitor;
    }

    @Override
    public void run() {
        CalculateCellValue();
        boolean cantPrintYet=true;
        while(cantPrintYet){
            cantPrintYet=!monitor.canCellPrint(row,col);
        }
        printCell();
        monitor.updatePrintedCell(row,col);
    }

    private void CalculateCellValue() {
        int[] rowA = getRow();
        int[] columnB = getCol();
        for (int i = 0; i < rowA.length; i++) {
            cellValue += rowA[i] * columnB[i];
        }
    }

    private int[] getRow() {
        return A[row];
    }

    private int[] getCol() {
        int[] columnArray = new int[B.length];
        for (int row = 0; row < B.length; row++) {
            columnArray[row] = B[row][col];
        }
        return columnArray;
    }
    private void printCell(){
        if(col+1==B[0].length){
            System.out.println(cellValue+" ");

        }
        else{
            System.out.print(cellValue+" ");
        }

    }
}
