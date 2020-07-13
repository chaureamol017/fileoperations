package com.myproject;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fileoperations.excel.ExcelReader;
import com.fileoperations.excel.details.ExcelDetails;

public class ExcelReaderMain {

	public static void main(String[] args) {
		String fileName = "testFile";
		try {
			ExcelReader excelReader = new ExcelReader("", fileName);
			ExcelDetails excelDetails = excelReader.readFile();
			
			writeToConsole(excelDetails);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeToConsole(ExcelDetails excelDetails) {
		List<String> allSheetNames = excelDetails.getAllSheetNames();
		System.out.println("File Name: " + excelDetails.getFileName());
		System.out.println("Number of sheets: " + allSheetNames.size());
		for (String sheetName : allSheetNames) {
			List<String> headers = excelDetails.getSheetHeaders(sheetName);

			System.out.println("");
			System.out.println("Sheet Name: " + sheetName);
			System.out.println("Sheet headers");
			System.out.println(headers);
			System.out.println("Sheet data");
//			System.out.println(excelDetails.getSheetData(sheetName));
			for (List<String> row : excelDetails.getSheetData(sheetName)) {
				System.out.println(row);
			}
		}
		
	}

}
