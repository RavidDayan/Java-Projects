package Q1;

public class Cell implements Runnable {
    private int cellValue;
    private int[][] A;
    private int[][] B;
    private int row;
    private int col;
    public Cell(int[][] A, int[][] B,int row,int col){
        this.A=A;
        this.B=B;
        this.col=col;
        this.row=row;
    }

    @Override
    public void run() {

    }

    private void cellValue() {
        int[] rowA=getRow();
        int[] columnB=getCol();
        for(int i=0;i<rowA.length;i++){
            cellValue+=rowA[i]*columnB[i];
        }
    }
    private int[] getRow(){
        return A[row];
    }
    private int[] getCol(){
        int[] columnArray=new int[B.length];
        for(int row=0;row<B.length;row++){
            columnArray[row]=B[row][col];
        }
        return columnArray;
    }
}
