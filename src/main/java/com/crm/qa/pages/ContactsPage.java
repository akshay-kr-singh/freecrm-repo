package com.crm.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	private Logger log = LogManager.getLogger(ContactsPage.class.getName());
	
	//PageFactory -- OR: Object Repository
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement displayContacts;
	
	
	//OR initialization
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Define methods
	public WebElement validateContactsDisplay() {
		return displayContacts;
	}

}
