package com.crm.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LaunchPage extends TestBase {
	private Logger log = LogManager.getLogger(LaunchPage.class.getName());
	
	//PageFactory: OR -- Object Repository
	@FindBy(xpath = "//div[@class='rd-navbar-wrap'] //div[@class='rd-navbar-brand']")
	private WebElement brandLogo;
	
	@FindBy(xpath = "//a[contains(@class,'btn btn-primary')]")
	private WebElement loginLink;
	
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement signupLink;
	
	//OR initialization
	public LaunchPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Define methods
	public boolean validateBrandLogo() {
		return brandLogo.isDisplayed();
	}
	
	public LoginPage validateLoginButton() {
		loginLink.click();
		log.info("clicked on login page link");
		return new LoginPage();
	}
	
	public SignupPage validateSignupLink() {		
		signupLink.click();
		log.info("clicked on signup page link");
		return new SignupPage();
	}

}
