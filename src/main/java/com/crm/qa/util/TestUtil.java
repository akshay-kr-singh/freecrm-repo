package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestUtil {
	public static int PAGE_LOAD_TIME = 40;
	public static int IMPLICIT_WAIT_TIME = 20;
	
	public static String takeScreenshot(WebDriver driver, String testName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String pathToScreenshot = System.getProperty("user.dir") + "\\screenshot\\" + testName + ".png";
		FileUtils.copyFile(source, new File(pathToScreenshot));
		return pathToScreenshot;		
	}
	
	public static ExtentReports getExtentReportsObject() {
		//ExtentSparkReporter // ExtentReports //ExtentTest		
		String pathToExtentReport = System.getProperty("user.dir") + "\\reports\\freecrm_extent.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(pathToExtentReport);
		reporter.config().setDocumentTitle("FREECRM Test Result");
		reporter.config().setReportName("Daily Sanity Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshay");
		
		return extent;		
	}
	
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
		workbook.close();
		return contacts;	
	}
	

}
