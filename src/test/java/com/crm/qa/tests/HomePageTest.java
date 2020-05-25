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
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	public WebDriver driver;
	private LaunchPage launchPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private ContactsPage contactsPage;
	private Logger log = LogManager.getLogger(HomePageTest.class.getName());
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	private void setUp() {
		driver = initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.validateLoginButton();
		homePage = loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));		
	}
	
	@Test(priority=1)
	private void validateUserIconTest() {
		WebElement user = homePage.validateUserIcon();
		Assert.assertTrue(user.isDisplayed());
		Assert.assertEquals(user.getText(), "Akshay Singh");
		log.info("user name display validation completed");
	}
	
	@Test(priority=2)
	private void validateContactsPageLinkTest() {		
		contactsPage = homePage.validateContactsPageLink();
		WebElement contactsIcon = contactsPage.validateContactsDisplay();
		Assert.assertTrue(contactsIcon.isDisplayed());
		log.info("redirection to contacts page validated, successfully landed on contacts page");
	
	}
	
	@AfterMethod
	private void tearDown() {
		driver.close();
		log.info("browser closed");
	}

}
