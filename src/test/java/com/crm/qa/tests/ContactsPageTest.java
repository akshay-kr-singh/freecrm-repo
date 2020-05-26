package com.crm.qa.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.NewContactsData;

public class ContactsPageTest extends TestBase {
	public WebDriver driver;
	private LaunchPage launchPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private ContactsPage contactsPage;
	private Logger log = LogManager.getLogger(ContactsPageTest.class.getName());
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeClass
	private void setUp() {
		driver = initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.validateLoginButton();
		homePage = loginPage.validateLogin(prop.getProperty("email"), prop.getProperty("password"));
		contactsPage = homePage.validateContactsPageLink();
	}
	
	@Test(dataProvider="getData")
	private void createNewContactsTest(String fn, String mn, String ln) {
		contactsPage.createNewContact(fn, mn, ln);		
		driver.navigate().back();
		driver.navigate().refresh();
	}
	
	@DataProvider
	private Iterator<Object[]> getData() {
		ArrayList<Object[]> data = null;
		try {
			data = NewContactsData.getNewContacts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Object[][] data = new Object [2][3];
//		
//		data[0][0] = "Rahul";
//		data[0][1] = "Kr";
//		data[0][2] = "Kanwal";
//		data[1][0] = "Sudhir";
//		data[1][1] = "Raj";
//		data[1][2] = "Mandal";
//		
		return data.iterator();
	}
	
	@AfterClass
	private void tearDown() {
		driver.close();
		log.info("browser closed");
	}

}
