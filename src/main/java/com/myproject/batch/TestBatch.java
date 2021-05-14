package com.myproject.batch;

import java.util.Arrays;

public class TestBatch {

	private static final int BATCH_SIZE = 5;

	public static void main(String[] args) {
		int size = 50;
		int[] sheet = getArray(size);
		System.out.println("Sheet data: " + arrToString(sheet));
		
		process(sheet);
	}

	private static int[] getArray(int size) {
		final int[] sheet = new int[size];
		for (int i = 0; i < size; i++) {
			sheet[i] = i;
		}

		return sheet;
	}

	private static void process(int[] sheet) {
		final int numberOfRecords = sheet.length;
		final int numberOfBatches = numberOfRecords % BATCH_SIZE == 0 ? numberOfRecords / BATCH_SIZE
				: numberOfRecords / BATCH_SIZE + 1;
		System.out.println("Total number of batches to process: " + numberOfBatches);

		for (int j = 0; j < numberOfBatches; j++) {
			final int fromIndex = j * BATCH_SIZE;
			final int toIndex = fromIndex + BATCH_SIZE <= numberOfRecords ? fromIndex + BATCH_SIZE : numberOfRecords;
			final int currentBatch = j + 1;
			final int[] subSheet = Arrays.copyOfRange(sheet, fromIndex, toIndex);
			System.out.println("Batch No: " + currentBatch + " data: " + arrToString(subSheet));
		}
	}

	private static String arrToString(int[] sheet) {
		String str = "";
		for (int i = 0; i < sheet.length; i++) {
			if (i != 0) {
				str += ", ";
			}
			str += sheet[i];
		}
		return str;
	}
}
