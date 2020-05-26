package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenUtil {

	public static ArrayList<String> getData() throws IOException {
		String pathToTestData = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\crm\\qa\\testdata\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(pathToTestData);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<String> data = new ArrayList<String>();

		for (int i = 0; i < sheetCount; i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().equalsIgnoreCase("freecrm")) {
				Iterator<Row> rowItr = sheet.iterator();
				Row firstRow = rowItr.next();
				Iterator<Cell> cellItr = firstRow.cellIterator();
				int k = 0;
				int columnInd = 0;
				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					if (cell.getStringCellValue().equalsIgnoreCase("TestCase")) {
						columnInd = k;
					}
					k++;
				}

				while (rowItr.hasNext()) {
					Row row = rowItr.next();
					if (row.getCell(columnInd).getStringCellValue().equalsIgnoreCase("Payment")) {
						Iterator<Cell> clItr = row.cellIterator();
						while (clItr.hasNext()) {
							Cell cl = clItr.next();
							if (cl.getCellTypeEnum() == CellType.STRING)
								data.add(cl.getStringCellValue());
							else
								data.add(NumberToTextConverter.toText(cl.getNumericCellValue()));
						}
					}
				}

			}

		}
		return data;
	}
	
	
}
