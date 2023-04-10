package Q2;

import java.util.ArrayList;

public class BigInt {
	private int multiplyer;
	private ArrayList<Integer> number;

	public BigInt(String number) {
		if (legalNumberCheck(number)) {
			this.number = new ArrayList<Integer>();
			retrieveMultiplyer(number);
			insertToArray(number.substring(1));
		} else {
			throw new IllegalArgumentException("Number is not according to syntax");
		}

	}

	private static boolean legalNumberCheck(String number) {// checks if number structure is "+/-" followed by numbers
															// only
		char currentNumber;
		char firstNumber = number.charAt(0);
		if (firstNumber != '-' && firstNumber != '+') {
			return false;
		}
		for (int i = 1; i < number.length(); i++) {
			currentNumber = number.charAt(i);
			if (Character.isDigit(currentNumber) == false)
				return false;
		}
		return true;
	}

	private void retrieveMultiplyer(String number) {// converts the +/- symbol to 1/-1
		if (number.charAt(0) == '+') {
			this.multiplyer = 1;
		} else {
			this.multiplyer = -1;
		}
	}

	private void insertToArray(String number) {// Converts String to int array
		String stringDigit;
		int intDigit;
		while (number.charAt(0) == '0' && number.length() > 1)// remove irrelevant zeros
		{
			number = number.substring(1);
		}
		for (int i = 0; i < number.length(); i++) {// insert numbers from string into array
			stringDigit = number.substring(i, i + 1);
			intDigit = Integer.parseInt(stringDigit);
			this.number.add(intDigit);
		}
	}

	public BigInt plus(BigInt addedNumber) {
		BigInt newNumber;
		int dominantNumber = this.dominant(addedNumber);
		if (dominantNumber == 1) {
			if (this.sameMultiplyer(addedNumber)) {
				newNumber = this.clone();
				newNumber.simpleAdd(addedNumber);
			} else {
				newNumber = this.clone();
				newNumber.simpleSub(addedNumber);
			}
		} else {
			if (this.sameMultiplyer(addedNumber)) {
				newNumber = addedNumber.clone();
				newNumber.simpleAdd(this);
			} else {
				newNumber = addedNumber.clone();
				newNumber.simpleSub(this);
			}
		}
		return newNumber;
	}
	
	public BigInt minus(BigInt subtracttedNumber) {
		BigInt newNumber;
		int dominantNumber = this.dominant(subtracttedNumber);
		if (dominantNumber == 1) {
			if (this.sameMultiplyer(subtracttedNumber)) {
				newNumber = this.clone();
				newNumber.simpleSub(subtracttedNumber);
			} else {
				newNumber = this.clone();
				newNumber.simpleAdd(subtracttedNumber);
			}
		} else {
			if (this.sameMultiplyer(subtracttedNumber)) {
				newNumber = subtracttedNumber.clone();
				newNumber.simpleSub(this);
			} else {
				newNumber = subtracttedNumber.clone();
				newNumber.simpleAdd(this);
			}
		}
		return newNumber;
	}
	
	private boolean sameMultiplyer(BigInt addedNumber) {
		if (this.multiplyer == addedNumber.multiplyer) {
			return true;
		}
		return false;

	}

	private void simpleAdd(BigInt other) {
		for (int index = other.size() - 1; index >= 0; index--) {
			this.hopNumberForward(this.getReletiveIndex(other, index), other.number.get(index));
		}
	}
	
	private void simpleSub(BigInt other) {
		for (int index = other.size() - 1; index >= 0; index--) {
			this.hopNumberBackwards(this.getReletiveIndex(other, index), other.number.get(index));
		}
	}

	private int getReletiveIndex(BigInt other, int index) {
		return this.size() - other.size() + index;
	}


	// public BigInt multiply() {

//	}

	// public BigInt divide() {

	// }

	public void print() {
		System.out.println(this.multiplyer);
		System.out.print(this.multiplyer*this.number.get(0));
		for (int i = 1; i < this.number.size(); i++) {
			System.out.print(this.number.get(i));
		}
	}

	private void hopNumberForward(int index, int addedNumber) {
		int sum;
		while (index >= 0) {
			sum = this.number.get(index) + addedNumber;
			if (sum > 9) {
				this.number.set(index, sum % 10);
				addedNumber = (sum) / 10;
			} else {
				this.number.set(index, sum);
				break;
			}
			index--;
			if (index == -1) {
				this.number.add(0, 1);
			}
		}
	}

	private void hopNumberBackwards(int index, int subNumber) {
		int sum;

		while (index >= 0) {
			sum = this.number.get(index) - subNumber;
			if (sum < 0) {
				this.number.set(index, sum +10);
				this.number.set(index-1, this.number.get(index)-1);
			} else {
				this.number.set(index, sum);
				break;
			}
			index--;
			if (index == -1) {
				this.number.add(0, 1);
			}
		}
	}

	private int dominant(BigInt other) {//check which is the bigger absolute value,if they are the same 
		if (this.size() > other.size()) {
			return 1;
		} else if (this.size() < other.size()) {
			return -1;
		} else {
			for(int i=0;i<this.size();i++) {
				if (this.number.get(i) > other.number.get(i)) {
					return 1;
				} 
				if (this.number.get(i) < other.number.get(i)){
					return -1;
				}
			}
			return 1;
		}
	}

	private int size() {
		return this.number.size();
	}

	public BigInt clone() {
		BigInt cloneNum;
		if (this.multiplyer == 1) {
			cloneNum = new BigInt("+0");
		} else {
			cloneNum = new BigInt("-0");
		}
		cloneNum.number.remove(0);
		for (int i = 0; i < this.size(); i++) {
			cloneNum.number.add(this.number.get(i));
		}
		return cloneNum;
	}
}
