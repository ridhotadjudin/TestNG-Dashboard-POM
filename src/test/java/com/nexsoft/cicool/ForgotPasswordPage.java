package com.nexsoft.cicool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage {

	protected WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//p[@class='login-box-msg']" )
	private WebElement thisForgotPass;
	
	public String getForgotPasswordValue() {
		String text = thisForgotPass.getText();
		return text;
	}
	
}
