package com.crm.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	Logger log = LogManager.getLogger(LoginPage.class.getName());

	//PageFactory -- OR: Object Repository
	@FindBy(xpath="//input[@name='email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath="//div[text()='Login']")
	private WebElement loginBtn;
	
	@FindBy(linkText="Forgot your password?")
	WebElement resetPwdLink;
	
	//OR initialization
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Define methods
	public HomePage validateLogin(String id, String pwd) {
		log.info("entering email address");
		email.sendKeys(id);
		log.info("entering password");
		password.sendKeys(pwd);
		log.info("clicking on login button");
		loginBtn.click();
		return new HomePage();
	}
	
	public ResetPasswordPage validateForgotPasswordLink() {
		log.info("clicking on 'Forgot your password?' link");
		resetPwdLink.click();
		return new ResetPasswordPage();
	}

}
