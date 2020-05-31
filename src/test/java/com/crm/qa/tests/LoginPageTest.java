package com.crm.qa.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	public WebDriver driver;
	private LaunchPage launchPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private Logger log = LogManager.getLogger(LoginPageTest.class.getName());
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	private void setUp() {		
		driver = initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.validateLoginButton();
	}
	
	@Test(priority=1)
	private void validateLoginTest() {						
		homePage = loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));
		WebElement user = homePage.validateUserIcon(); 
		Assert.assertTrue(user.isDisplayed());
		log.info("logged in successfully and landed on home page");
	}
	
	@Test(priority=2)
	private void validateForgotPasswordLinkTest() {		
		loginPage.validateForgotPasswordLink();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://register.cogmento.com/password/reset/request/");
		log.info("password reset page redirection validated successfully");		
	}
	
	@AfterMethod
	private void tearDown() {
		driver.close();
		log.info("browser closed");
	}

}
