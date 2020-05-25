package com.crm.qa.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LaunchPage;

public class LaunchPageTest extends TestBase {	
	public WebDriver driver;
	private LaunchPage launchPage;
	private Logger log = LogManager.getLogger(LaunchPageTest.class.getName());
	
	public LaunchPageTest() {
		super();
	}
	
	@BeforeMethod
	private void setUp() {
		driver = initialization();		
		launchPage = new LaunchPage();		
	}
	
	@Test(priority=1)
	private void validateBrandLogoTest() {
		boolean flag = launchPage.validateBrandLogo();
		log.info(flag);
		Assert.assertTrue(false);
		log.info("FREECRM logo validated successfully");
	}
	
	@Test(priority=2)
	private void validateLoginButtonTest() {
		launchPage.validateLoginButton();
		String loginPageTitle = driver.getTitle();
		log.info("login page title is: " + loginPageTitle);
		Assert.assertEquals(loginPageTitle, "Cogmento CRM");
		log.info("login page title validated successfully");
	}
	
	@Test(priority=3)
	private void validateSignupLinkTest() {
		launchPage.validateSignupLink();
		String signupURL = driver.getCurrentUrl();
		log.info("signup page url is: " + signupURL);
		Assert.assertEquals(signupURL, "https://register.freecrm.com/register/");
		log.info("redirection to signup page validated successfully");
	}
	
	@AfterMethod
	private void tearDown() {
		driver.close();
		log.info("browser closed");		
	}

}
