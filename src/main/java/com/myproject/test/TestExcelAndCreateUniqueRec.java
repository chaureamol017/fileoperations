package com.myproject.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fileoperations.excel.ExcelReader;
import com.fileoperations.excel.ExcelWriter;
import com.fileoperations.excel.details.ExcelDetails;

public class TestExcelAndCreateUniqueRec {
	private static final String PATH = "/Users/chaurea/Downloads/";
	private static final String FILE_TO_READ = "Universal Dealer Focus - Search_072320";
	private static final String FILE_TO_WRITE = "testAdCopies";
	private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		try {
			
			System.out.println(sdf.format(new Date()) + ": Start");
			ExcelReader excelReader = new ExcelReader(PATH, FILE_TO_READ);
			ExcelDetails excelDetails = excelReader.readFile();
			System.out.println(sdf.format(new Date()) + ": Read file");
			
			List<List<String>> sheetData = getAdCopiesDetails(excelDetails);
			System.out.println(sdf.format(new Date()) + ": Get Ad Copies Details");

			ExcelDetails excelDetailsToWrite = createExcelDetails(sheetData, excelDetails.getSheetHeaders("ad copies"));
			System.out.println(sdf.format(new Date()) + ": Created excel details to write");

			ExcelWriter excelWriter = new ExcelWriter(excelDetailsToWrite);
			excelWriter.writeFile();
			System.out.println(sdf.format(new Date()) + ": End");
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static List<List<String>> getAdCopiesDetails(ExcelDetails excelDetails) {
		List<List<String>> sheetData = null;
		List<String> allSheetNames = excelDetails.getAllSheetNames();
		for(String sheetName : allSheetNames) {
			if ("ad copies".equals(sheetName)) {
				sheetData = excelDetails.getSheetData(sheetName);
			}
		}

		return sheetData;
	}

	private static ExcelDetails createExcelDetails(List<List<String>> sheetData, List<String> headerRow) {
		String sheetName = "ad copies";
		ExcelDetails excelDetails = new ExcelDetails(PATH, FILE_TO_WRITE);
		excelDetails.addSheet(sheetName);
		
		int campaignTemplateIndex = getCampaignTemplateIndex(headerRow);
		
		Set<String> dataSet = getUniqueRec(sheetData, campaignTemplateIndex);
		
		excelDetails.addHeader(sheetName, headerRow.get(campaignTemplateIndex));
		System.out.println();
		dataSet.forEach(cell -> {
			excelDetails.addData(sheetName, cell);
			System.out.println(" '" + cell + "',");
		});
		System.out.println();
		
		return excelDetails;
	}

	private static Set<String> getUniqueRec(List<List<String>> sheetData, int campaignTemplateIndex) {
		Set<String> dataSet = new HashSet<>();

		sheetData.forEach(row -> dataSet.add(row.get(campaignTemplateIndex)));

		return dataSet;
	}

	private static int getCampaignTemplateIndex(List<String> headerRow) {
		for(int i = 0; i < headerRow.size()-1; i++) {
			if ("campaign template".equals(headerRow.get(i))) {
				return i;
			}
		}
		return -1;
	}
}
