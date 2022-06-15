package com.nexsoft.cicool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	protected WebDriver driver;
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//tanpa page factory
//	private By email = By.xpath("//input[@placeholder='Email']");
//	private By password = By.xpath("//input[@placeholder='Password']");
//	private By btnSignIn = By.xpath("//button[@type='submit']");
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSignIn;
	
	@FindBy(xpath = "//p[normalize-space()='E-mail Address and Password do not match.']" )
	private WebElement errorPassword;
	
	@FindBy(xpath = "//p[normalize-space()='User does not exist']" )
	private WebElement errorUsernamePassword;
	
	@FindBy(xpath = "//p[normalize-space()='The Username field is required.']" )
	private WebElement errorEmptyUsernamePassword1;
	
	@FindBy(xpath = "//p[normalize-space()='The Password field is required.']" )
	private WebElement errorEmptyUsernamePassword2;
	
	@FindBy(xpath = "//a[normalize-space()='I forgot my password.']" )
	private WebElement forgotPassword;
	
	public Dashboard loginValidUser(String username,String usrPassword) {
		email.clear();
		email.sendKeys(username);
		password.clear();
		password.sendKeys(usrPassword);
		btnSignIn.click();
		
		return PageFactory.initElements(driver, Dashboard.class);
	}
	
	public String getErrorPassword() {
		return errorPassword.getText();
	}
	
	public String getErrorUsernamePassword() {
		return errorUsernamePassword.getText();
	}
	
	public String getErrorEmptyUsernamePassword() {
		String hasil = null;
		String teksSatu = errorEmptyUsernamePassword1.getText();
		String teksDua = errorEmptyUsernamePassword2.getText();
		if(teksSatu.equalsIgnoreCase("The Username field is required.") && teksDua.equalsIgnoreCase("The Password field is required.")){
			hasil = "uname and pass catch";
		}
		return hasil;
	}
	
	public ForgotPasswordPage clickForgotPassword() {
		forgotPassword.click();
		ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver, ForgotPasswordPage.class);
		return forgotPasswordPage;
	}
}
