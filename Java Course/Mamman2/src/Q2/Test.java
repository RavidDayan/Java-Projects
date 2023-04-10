package Q2;

public class Test {

	public static void main(String[] args) {
		BigInt num = new BigInt("-1234");
		BigInt num2 = new BigInt("+4321");
		num.plus(num2).print();
	}
}
