package Q1;

public class MatrixMult {
    int[][] A;
    int[][] B;
    Cell[][] C;
    public MatrixMult(int n,int m,int p){
        A=new int[n][m];
        B=new int[m][p];
        C=new Cell[n][p];
    }

}
