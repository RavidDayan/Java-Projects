package Q1;

public class MatrixMult {
    int[][] A;
    int[][] B;
    CellMonitor monitor;
    Cell[][] C;

    public MatrixMult(int n, int m, int p) {
        A = new int[n][m];
        B = new int[m][p];
        C = new Cell[n][p];
        monitor = new CellMonitor(C);
        randomizedMatrix(A);
        randomizedMatrix(B);
        printMatrix(A,"A");
        printMatrix(B,"B");
        calculateMatrixC();
    }

    private void calculateMatrixC() {
        Thread runner;
        for (int row = 0; row < C.length; row++) {
            for (int col = 0; col < C[0].length; col++) {
                C[row][col] = new Cell(A, B, row, col, monitor);
                runner = new Thread(C[row][col]);
                runner.start();
            }
        }
    }

    private int randomCellValue() {
        return (int) Math.random() * 10;
    }

    private void randomizedMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j]=randomCellValue();
            }
        }
    }
    private void printMatrix(int[][] matrix,String name) {
        System.out.println("Matrix"+name+":");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
