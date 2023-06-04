package Q1;

//class that receives 2 matrix sizes and prints their multiplication
public class MatrixMult {
    int[][] A;//matrix A
    int[][] B;//matrix B
    CellMonitor monitor;//matrix C cell monitor
    Cell[][] C;//matrix c

    public MatrixMult(int n, int m, int p) {
        A = new int[n][m];
        B = new int[m][p];
        C = new Cell[n][p];
        monitor = new CellMonitor(C);
        randomizedMatrix(A);
        randomizedMatrix(B);
        printMatrix(A, "A");
        printMatrix(B, "B");
        System.out.println("Matrix C");
        calculateMatrixC();
    }//constructor and initiates the multiplication process

    private void calculateMatrixC() {
        Thread runner;
        for (int row = 0; row < C.length; row++) {
            for (int col = 0; col < C[0].length; col++) {
                C[row][col] = new Cell(A, B, row, col, monitor);
                runner = new Thread(C[row][col]);
                runner.start();
            }
        }
    }//creates threads for each cell in matrix C and starts each cell's calculating and printing process

    private int randomCellValue() {
        return (int) (Math.random() * 10);
    }//returns a random cell value between 0-10

    private void randomizedMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = randomCellValue();
            }
        }
    }//inserts random numbers between 1-10 into each matrix cell

    private void printMatrix(int[][] matrix, String name) {
        System.out.println("Matrix " + name + ":");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }//prints a 2D matrix as a readable matrix
}
