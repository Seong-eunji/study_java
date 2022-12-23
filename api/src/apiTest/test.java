package apiTest;

import java.util.Random;

public class test {
	public static void main(String[] args) {
		Random random = new Random();
		int number = random.nextInt(10) * 10000;
		System.out.println(number);
		number = (int)(Math.random() * 10000);
		System.out.println(number);
	}
}
