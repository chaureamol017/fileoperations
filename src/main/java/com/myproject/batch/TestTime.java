package com.myproject.batch;

import com.myproject.test.TestExcelRead;

public class TestTime {
	
	public static void main(String[] args) {
		String filePath1 = "/Users/chaurea/Downloads/Universal Dealer Focus1.xlsx";
		String filePath2 = "/Users/chaurea/Downloads/Universal Dealer Focus2.xlsx";
		String filePath3 = "/Users/chaurea/Documents/Local Dealer Focus_02.xlsx";
		String filePath10 = "/Users/chaurea/Documents/Local Dealer Focus_10.xlsx";
		
		TestExcelRead reader = new TestExcelRead();
		
//		reader.readExcel(filePath3);
		reader.readExcelPartially(filePath10);
	}

}
