package com.crm.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static String takeScreenshot(WebDriver driver,String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String pathToScreenshot = System.getProperty("user.dir") + "\\screenshot\\" + testName + ".png";
		FileUtils.copyFile(source, new File(pathToScreenshot));
		return pathToScreenshot;
		
	}
}