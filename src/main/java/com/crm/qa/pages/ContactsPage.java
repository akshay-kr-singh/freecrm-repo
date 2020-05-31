package com.crm.qa.pages;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	private Logger log = LogManager.getLogger(ContactsPage.class.getName());
	
	//PageFactory -- OR: Object Repository
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement displayContacts;
	
	@FindBy(xpath="//a[contains(@href,'contacts/new')]")
	private WebElement newContactBtn;
	
	@FindBy(xpath="//input[@name='first_name']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@name='middle_name']")
	private WebElement middleName;
	
	@FindBy(xpath="//input[@name='last_name']")
	private WebElement lastName;
	
	@FindBy(xpath="//div[@name='company']")
	private WebElement company;
	
	@FindBy(css="button.ui.linkedin.button")
	private WebElement saveBtn;
	
	
	//OR initialization
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Define methods
	public WebElement validateContactsDisplay() {
		return displayContacts;
	}
	
	public void createNewContact(String fn, String mn, String ln) throws InterruptedException {
		String clickOnNewBtn = Keys.chord(Keys.CONTROL, Keys.ENTER);		
		log.info("clicking on 'New' button");
//		newContactBtn.click();
		newContactBtn.sendKeys(clickOnNewBtn);
//		Thread.sleep(5000);
		Set<String> handleIds = driver.getWindowHandles();
		Iterator<String> itr = handleIds.iterator();
		String parentWindow = itr.next();
		String childWindow = null;
		while(itr.hasNext()) {
			childWindow = itr.next();	
		}
				
		driver.switchTo().window(childWindow);
		Thread.sleep(2000);
		driver.navigate().refresh();
				
		log.info("entering first name");
		firstName.clear();
		firstName.sendKeys(fn);
		log.info("entering middle name");
		middleName.clear();
		middleName.sendKeys(mn);
		log.info("entering last name");
		lastName.clear();
		lastName.sendKeys(ln);
//		log.info("entering company name");
//		company.sendKeys(cmp);
		log.info("clicking on save button");
		saveBtn.click();
		Thread.sleep(2000);
		
		driver.switchTo().window(parentWindow);
	}


}
