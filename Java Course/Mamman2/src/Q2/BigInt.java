package Q2;

import java.util.ArrayList;

public class BigInt {
	private int multiplyer;
	private ArrayList<Integer> number;

	public BigInt(String number) throws IllegalArgumentException {
		if (legalNumberCheck(number)) {
			this.number=new ArrayList<Integer>();
			this.retrieveMultiplyer(number);
			this.insertToArray(number.substring(1));
		}

	}

	private static boolean legalNumberCheck(String number) {
		char currentNumber;
		char firstNumber = number.charAt(0);
		for (int i = 1; i < number.length(); i++) {
			currentNumber = number.charAt(i);
			if (firstNumber != '-' || firstNumber != '-' || Character.isDigit(currentNumber) == false)
				return false;
		}
		return true;
	}

	private void retrieveMultiplyer(String number) {
		if (number.charAt(0) == '+') {
			this.multiplyer = 1;
		} else {
			this.multiplyer = -1;
		}
	}

	private void insertToArray(String number) {
		String stringDigit;
		int intDigit;
		for (int i = 0; i < number.length(); i++) {
			stringDigit=number.substring(i, i + 1);
			intDigit=Integer.parseInt(num);
			this.number.add(intDigit);
		}
	}
	
	public BigInt plus() {
		
	}
	
	public BigInt plus() {
		
	}
	public BigInt plus() {
		
	}
	public BigInt plus() {
		
	}
	public void print() {
		for (int i=0;i<this.number.size();i++)
		{
			System.out.println(this.number.get(i));
		}
	}
}
