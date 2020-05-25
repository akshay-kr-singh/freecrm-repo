package com.crm.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	Logger log = LogManager.getLogger(HomePage.class.getName());
	
	//PageFactory -- OR: Object Repository
	@FindBy(xpath="//span[@class='user-display']")
	private WebElement userIcon;
	
	@FindBy(xpath="//span[text()='Contacts']/parent::a")
	private WebElement contactsPageLink;
	
	
	
	//OR initialization
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Define methods
	public WebElement validateUserIcon() {
		return userIcon;
	}
	
	public ContactsPage validateContactsPageLink() {
		log.info("clicking on 'Contacts'");
		contactsPageLink.click();
		return new ContactsPage();
	}
	

}
