package com.nexsoft.cicool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	protected WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//a[@class='page-scroll']")
	private WebElement signIn;
	
//	@FindBy(xpath = "//a[@class='dropdown-toggle']")
//	private WebElement languageSetting;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//a[@class='page-scroll dropdown-toggle']")
	private WebElement btnDashboardUsername;

	// tanpa page factory
//	private By signIn = By.xpath("//a[@class='page-scroll']");
//	private By languageSetting = By.xpath("//a[@class='dropdown-toggle']");

	public SignInPage clickSignIn() {
		signIn.click();
		SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);

		return signInPage;

		// tanpa page factory
//		driver.findElement(signIn).click();
//		return new SignInPage(driver);
	}

	public void logout() {
		btnDashboardUsername.click();
		btnLogout.click();
	}
}
