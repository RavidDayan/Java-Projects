package Q2;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		BigInt[] number=new BigInt[2];
		for(int i=1;i<3;i++) {
			try {
				System.out.print("please enter the "+i+" number: ");
				input=sc.next();
				number[i-1] = new BigInt(input);
			} catch (IllegalArgumentException IAE) {
				System.err.println(IAE.getMessage());
				i--;
			}
		}
		System.out.println(number[0].plus(number[1]).toString());
		System.out.println(number[0].minus(number[1]).toString());
		System.out.println(number[0].multiply(number[1]).toString());
		try {
			System.out.println(number[0].divide(number[1]).toString());
		}
		catch(ArithmeticException AE)
		{
			System.err.println(AE.getMessage());
		}
	}
}
