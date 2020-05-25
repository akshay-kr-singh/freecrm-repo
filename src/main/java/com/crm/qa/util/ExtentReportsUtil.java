package com.crm.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtil {
	
	public static ExtentReports getExtentReportsObject() {
		String pathToExtentReport = System.getProperty("user.dir") + "\\reports\\freecrm_extent.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(pathToExtentReport);
		reporter.config().setDocumentTitle("FREECRM Test Result");
		reporter.config().setReportName("Daily Sanity Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshay");
		
		return extent;
	}
}