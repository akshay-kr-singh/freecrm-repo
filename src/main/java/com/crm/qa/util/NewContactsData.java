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

public class NewContactsData {
	public static ArrayList<Object[]> getNewContacts() throws IOException {
		String pathToTestData = System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\testdata\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(pathToTestData);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);		
		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<Object[]> contacts = new ArrayList<Object[]>();
		
		for(int i=0;i<sheetCount;i++) {
			if(workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("NewContacts")) {
				//desired sheet is obtained
				XSSFSheet desiredSheet = workbook.getSheetAt(i);
				
				Iterator<Row> rowItr = desiredSheet.iterator();
				rowItr.next();
				
				while(rowItr.hasNext()) {
					Row row = rowItr.next();
					Iterator<Cell> cellItr = row.cellIterator();
					ArrayList<String> contact = new ArrayList<String>();
					
					while(cellItr.hasNext()) {
						Cell cell = cellItr.next();
						if(cell.getCellType() == CellType.STRING) contact.add(cell.getStringCellValue());
						else contact.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
					}
					contacts.add(contact.toArray());
				}
			}
		}
		return contacts;	
	}		
}