package Q2;

public class Test {

	public static void main(String[] args) {
		BigInt num = new BigInt("-7");
		BigInt num2 = new BigInt("+8");
		System.out.println(num.divide(num2).toString());
	}
}
