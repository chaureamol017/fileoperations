package com.myproject.temp;

public class TestClass {
	private short a;

	public short getA() {
		return a;
	}

	public void setA(short a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "TestClass [a=" + a + "]";
	}
	
	public static void main(String[] args) {
		TestClass t = new TestClass();
		
		System.out.println(t);
		
	}

}
