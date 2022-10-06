package javaBasic;

import java.util.Random;

public class Topic_04_Random_Data {

	public static void main(String[] args) {
		Random rand = new Random();
		// 0 -> 999 = 1000 số
		// 1 -> 1000 = 1000 số
		System.out.println(rand.nextInt(999));
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextLong());
		System.out.println(rand.nextFloat());
		System.out.println("miss.trang"+rand.nextInt(999)+"@gmail.com");

	}

}
