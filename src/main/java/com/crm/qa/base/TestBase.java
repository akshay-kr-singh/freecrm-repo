package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	private Logger log = LogManager.getLogger(TestBase.class.getName());
	
	//Constructor
	public TestBase() {
		prop = new Properties();
		String pathToPropertiesFile = System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";		
		try {			
			FileInputStream fis = new FileInputStream(pathToPropertiesFile);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//Method to initialize WebDriver
	public WebDriver initialization() {
		//use below to control choice of browser using config.properties file 
//		String browser = prop.getProperty("browser");
		
		//use below to provide choice of browser in maven command: mvn test -Dbrowser=chrome
	    String browser = System.getProperty("browser");			   
	    
		if(browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless")) options.addArguments("headless"); //to execute tests in headless mode in chrome browser
			
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		log.info("url launched, landed on launch page");
		return driver;
	}
	

}
