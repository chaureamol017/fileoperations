package com.myproject.test;

public class UserMainCode {

	public int candies(int input1, int input2, int[] input3) {
		int[] a = input3.clone();

		for (int i = 0; i < input1; i++) {
			for (int j = i + 1; j < input1; j++) {
				if (a[i] < a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		
		return a[input2-1];
	}

	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		UserMainCode main = new UserMainCode();
		int input1 = 3;
		int input2 = 2;
		int[] input3 = { 26, 20, 23 };
		System.out.println(main.candies(input1, input2, input3));
		
		input1 = 6;
		input2 = 4;
		input3 = new int[]{ 100, 20, 40, 20, 50, 50 };
		System.out.println(main.candies(input1, input2, input3));

	}
}
