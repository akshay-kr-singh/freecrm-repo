package com.crm.qa.util;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;

public class Listeners extends TestBase implements ITestListener {
	
	private ExtentReports extent = TestUtil.getExtentReportsObject();
	private ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = extent.createTest(testName);
		
		extentTest.set(test);				
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String testName = result.getMethod().getMethodName();
		
		try {
			extentTest.get().addScreenCaptureFromPath(TestUtil.takeScreenshot(driver, testName), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String testName = result.getMethod().getMethodName();
		
		try {
			extentTest.get().addScreenCaptureFromPath(TestUtil.takeScreenshot(driver, testName), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
