package com.myproject.test;

public class Challenge {
	
	public static void main(String[] args) {
		
		System.out.println(mergeString("abc", "def"));
		System.out.println(mergeString("ab", "def"));
		System.out.println(mergeString("abc", "de"));
		
	}
	
	
	public static String mergeString(String a, String b) {
		String newString = "";
		int index = a.length() < b.length() ? a.length() : b.length();
		
		for (int i = 0; i < index; i ++) {
			newString += a.substring(i, i+1) + b.substring(i, i+1);
		}
		
		newString += a.length() < b.length() ? b.substring(index) : a.substring(index);
		
		return newString;
		
	}

}
