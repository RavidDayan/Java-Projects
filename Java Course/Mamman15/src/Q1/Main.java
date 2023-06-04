package Q1;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//assuming user will enter only integers.
        int n = 0;//matrix A rows
        int m = 0;//matrix A cols,B rows, this way matrix will always be compatible
        int p = 0;//Matrix B cols
        System.out.println("please enter rows of matrix A");
        for (int i = 0; i < 1; i++) {
            n = sc.nextInt();
            if (n <= 0) {
                i = -1;
                System.out.println("please enter a number above 0");
            }
        }//in case of illegal integer it asks for a new one by re setting  counter
        System.out.println("please enter col of matrix A");
        for (int i = 0; i < 1; i++) {
            m = sc.nextInt();
            if (m <= 0) {
                i = -1;
                System.out.println("please enter a number above 0");
            }
        }//in case of illegal integer it asks for a new one by re setting  counter
        System.out.println("please enter col of matrix B");
        for (int i = 0; i < 1; i++) {
            p = sc.nextInt();
            if (p <= 0) {
                i = -1;
                System.out.println("please enter a number above 0");
            }
        }//in case of illegal integer it asks for a new one by re setting  counter
        new MatrixMult(n, m, p);//makes a new Calculation
    }
}
