package Q2;

import java.util.ArrayList;

public class BigInt implements Comparable<BigInt> {
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
		int dominantNumber = this.compareAbsoluteValue(addedNumber);
		if (dominantNumber == 1 || dominantNumber == 0) {
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
		int dominantNumber = this.compareAbsoluteValue(subtracttedNumber);
		if (dominantNumber == 1 || dominantNumber == 0) {
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
		if(this.sameMultiplyer(subtracttedNumber)==false)
				{
			newNumber.flipMultiplyer();
				}
		return newNumber;
	}

	private void cleanZeroFromHead() {
		while (this.number.get(0) == 0 && this.size() > 1) {
			this.number.remove(0);
		}
	}

	private boolean sameMultiplyer(BigInt other) {
		if (this.multiplyer == other.multiplyer) {
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
		this.cleanZeroFromHead();
	}

	private int getReletiveIndex(BigInt other, int index) {
		return this.size() - other.size() + index;
	}

	private void hopNumberForward(int index, int addedNumber) {
		int sum;
		while (index >= 0) {
			sum = this.number.get(index) + addedNumber;
			if (sum > 9) {
				this.number.set(index, sum % 10);
				addedNumber = sum / 10;
				index--;
				if (index == -1) {
					this.number.add(0, addedNumber);
				}
			} else {
				this.number.set(index, sum);
				break;
			}
		}
	}

	private void hopNumberBackwards(int index, int subNumber) {
		int sum;
		while (index >= 0) {
			sum = this.number.get(index) - subNumber;
			if (sum < 0) {
				this.number.set(index, sum + 10);
				this.number.set(index - 1, this.number.get(index - 1) - 1);
				index--;
				subNumber = 0;
			} else {
				this.number.set(index, sum);
				break;
			}
		}
	}

	public BigInt multiply(BigInt multiplyNumber) {
		BigInt newNumber = this.clone();
		BigInt one;
		if (this.checkIfNumberIsZero() || multiplyNumber.checkIfNumberIsZero())
		{
			return zero();
		}
		if (multiplyNumber.multiplyer == 1) {
			one = one();
		} else {
			one = minusOne();
		}
		while (multiplyNumber.number.get(0) != 1) {
			newNumber = newNumber.plus(this);
			multiplyNumber = multiplyNumber.minus(one);
		}
		if (this.sameMultiplyer(multiplyNumber) == false && newNumber.multiplyer == 1) {
			newNumber.flipMultiplyer();
		}
		if (this.sameMultiplyer(multiplyNumber) && newNumber.multiplyer == -1) {
			newNumber.flipMultiplyer();
		}
		return newNumber;
	}

	private void flipMultiplyer() {
		this.multiplyer *= -1;
	}

	public BigInt divide(BigInt divider) {
		BigInt devided = this.clone();
		BigInt counter = zero();
		BigInt newNumber = zero();
		if (divider.checkIfNumberIsZero()) {
			throw new ArithmeticException("A number cant be divided by 0");
		} else {
			if (devided.checkIfNumberIsZero())
				return zero();

			if (devided.compareTo(divider) == 0)
				return one();

			if (devided.compareAbsoluteValue(divider) == 0) {
				if (devided.sameMultiplyer(divider))
					return one();
				else
					return minusOne();
			}
			if (devided.compareAbsoluteValue(divider) == -1) {
				return zero();
			}

			while (newNumber.compareAbsoluteValue(devided) <= 0) {
				newNumber = newNumber.plus(divider);
				counter=counter.plus(one());
			}
		}
		newNumber=counter.minus(one());
		if(devided.sameMultiplyer(divider))
			return newNumber;
		else
		{
			newNumber.flipMultiplyer();
			return newNumber;
		}
	}
	
 	private boolean checkIfNumberIsZero() {
		if (this.size() == 1 && this.number.get(0) == 0) {
			return true;
		} else {
			return false;
		}
	}

	private int compareAbsoluteValue(BigInt other) {// check which is the bigger absolute value,if they are the same
		if (this.size() > other.size()) {
			return 1;
		} else if (this.size() < other.size()) {
			return -1;
		} else {
			for (int i = 0; i < this.size(); i++) {
				if (this.number.get(i) > other.number.get(i)) {
					return 1;
				}
				if (this.number.get(i) < other.number.get(i)) {
					return -1;
				}
			}
			return 0;
		}
	}

	private int size() {
		return this.number.size();
	}
	
	private static BigInt one() {
		return new BigInt("+1");
	}
	
	private static BigInt minusOne() {
		return new BigInt("-1");
	}
	
	private static BigInt zero() {
		return new BigInt("+0");
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

	public int compareTo(BigInt other) {
		int absoluteValue = this.compareAbsoluteValue(other);
		int returnValue = 0;
		if (this.multiplyer > other.multiplyer)
			returnValue = 1;
		if (this.multiplyer < other.multiplyer)
			returnValue = 1;
		if (this.multiplyer == other.multiplyer) {
			if (absoluteValue == 1)
				returnValue = 1;
			if (absoluteValue == -1)
				returnValue = -1;
			if (absoluteValue == 0)
				returnValue = 0;
			}
		return returnValue;
	}

	public String toString() {
		String returnedString;
		if (this.multiplyer == 1) {
			returnedString = "+";
		} else {
			returnedString = "-";
		}
		for (int index = 0; index < this.size(); index++) {
			returnedString += this.number.get(index);
		}
		return returnedString;
	}

	public boolean equals(BigInt other) {
		if (this.compareTo(other) == 0)
			return true;
		else
			return false;
	}
}
