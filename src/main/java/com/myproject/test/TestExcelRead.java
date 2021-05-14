package com.myproject.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.monitorjbl.xlsx.StreamingReader;

public class TestExcelRead {
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void readExcel(String filePath) {
		try {
			readExcelUsingFile(filePath);
			readExcelUsingInputStream(filePath);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void readExcelPartially(String filePath) {
		try {
//			readExcelUsingFile(filePath);
			readExcelUsingInputStreamNew(filePath);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void readExcelWithBuffer(String filePath) {
		try {
			readExcelUsingFile(filePath);
			readExcelUsingInputStream(filePath);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}


	private Workbook readExcelUsingFile(String filePath) throws IOException, InvalidFormatException {
		System.out.println(sdf.format(new Date()) + ": start import using file");
		File file = new File(filePath);
		Workbook workbook = WorkbookFactory.create(file);
		System.out.println(sdf.format(new Date()) + ": end import using file");
		
		return workbook;
	}

	private Workbook readExcelUsingInputStream(String filePath) throws IOException, InvalidFormatException {
		System.out.println(sdf.format(new Date()) + ": start import using stream");
		File file = new File(filePath);
		InputStream source = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(source);
		System.out.println(sdf.format(new Date()) + ": end import using stream");
		
		return workbook;
	}


	private Workbook readExcelUsingFileNew(String filePath) throws IOException, InvalidFormatException {
		System.out.println(sdf.format(new Date()) + ": start import using file");
		File file = new File(filePath);
//		Workbook workbook = WorkbookFactory.create(file);
		Workbook workbook = StreamingReader.builder().rowCacheSize(1000) // number of rows to keep in memory (defaults to 10)
				.bufferSize(4096) // buffer size to use when reading InputStream to file (defaults to 1024)
				.open(file);
		System.out.println(sdf.format(new Date()) + ": end import using file");
		
		return workbook;
	}
	
	private Workbook readExcelUsingInputStreamNew(String filePath) throws IOException, InvalidFormatException {
		System.out.println(sdf.format(new Date()) + ": start import using stream");
		File file = new File(filePath);
		InputStream source = new FileInputStream(file);
//		Workbook workbook = StreamingReader.builder().rowCacheSize(1000) // number of rows to keep in memory (defaults to 10)
//				.bufferSize(4096) // buffer size to use when reading InputStream to file (defaults to 1024)
//				.open(source);
		
		Workbook workbook = StreamingReader.builder()   
		          .sstCacheSize(100)    
		          .open(source); 
		System.out.println(sdf.format(new Date()) + ": end import using stream");
		
		return workbook;
	}
}
