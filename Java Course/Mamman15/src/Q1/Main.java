package Q1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        int m;
        int p;
        System.out.println("please enter rows of matrix A");
        n=sc.nextInt();
        System.out.println("please enter col of matrix A");
        m=sc.nextInt();
        System.out.println("please enter col of matrix B");
        p=sc.nextInt();
        new MatrixMult(n,m,p);
    }
}
