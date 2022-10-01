package com.nexsoft.cicool;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLoginUser {

	protected WebDriver driver;
	private JavascriptExecutor jsExe;
	
	public void delayMS(int inInt) {
		try {
			Thread.sleep(inInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String screenShot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "D:\\selenium-workspace\\CicoolPOM\\hasilScreenshot\\" + waktu + ".png";
		File screenshot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return namaFile;
	}
	
	@BeforeTest
	public void init() {
		System.setProperty("url", "http://localhost/cicool");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExe = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get(System.getProperty("url"));
	}
	
	@BeforeMethod
	public void cekSession() {
		driver.get(System.getProperty("url"));
	}
	
	@Test(priority = 1)
	public void testLogin_usernameValid_passwordValid() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		Dashboard dash = home.clickSignIn().loginValidUser("ridhotadjudin@gmail.com", "123456");
		
		delayMS(500);
		// take screenshot
//		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);

		// verify login success
		Assert.assertEquals(dash.getUsername(), "Ridho");
	}
	
	@Test(priority = 2)
	public void testLogin_usernameValid_passwordNotValid() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		home.logout();
		
		SignInPage signin = home.clickSignIn();
		Dashboard dashboard = signin.loginValidUser("ridhotadjudin@gmail.com", "123");
		
		//delay and scroll
		delayMS(500);
		jsExe.executeScript("window.scrollBy(0, 100)", "");
		
		// take screenshot
//		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);

		// verify login failed do not match
		Assert.assertEquals(signin.getErrorPassword(), "E-mail Address and Password do not match.");
		try {
			dashboard.getUsername();
		} catch (Exception e) {
			assertTrue(true, "Text Tidak ditemukan " + e.getMessage());
		}
	}
	
	@Test(priority = 3)
	public void testLogin_usernameNotValid_passwordNotValid() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		SignInPage signin = home.clickSignIn();
		Dashboard dashboard = signin.loginValidUser("123@gmail.com", "ridho");
		
		delayMS(500);
		jsExe.executeScript("window.scrollBy(0, 100)", "");
		
//		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);

		// verify login failed user doesn't exist
		Assert.assertEquals(signin.getErrorUsernamePassword(), "User does not exist");
		try {
			dashboard.getUsername();
		} catch (Exception e) {
			assertTrue(true, "Text not found " + e.getMessage());
		}
	}
	
	@Test(priority = 4)
	public void testLogin_usernameEmpty_passwordEmpty() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		
		SignInPage signin = home.clickSignIn();
		Dashboard dashboard = signin.loginValidUser("", "");
		
		delayMS(500);
		jsExe.executeScript("window.scrollBy(0, 100)", "");
		
//		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);

		// verify login failed user and pass empty
		Assert.assertEquals(signin.getErrorEmptyUsernamePassword(), "uname and pass catch");
		try {
			dashboard.getUsername();
		} catch (Exception e) {
			assertTrue(true, "Text not found " + e.getMessage());
		}
	}
	
	@Test(priority = 5)
	public void testforgotPassword() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		SignInPage signin = home.clickSignIn();
		ForgotPasswordPage forgot = signin.clickForgotPassword();
		
		delayMS(500);
		jsExe.executeScript("window.scrollBy(0, 100)", "");
		
//		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);

		// verify forgot password
		Assert.assertEquals(forgot.getForgotPasswordValue(), "Send a link to reset the password");
		try {
			forgot.getForgotPasswordValue();
		} catch (Exception e) {
			assertTrue(true, "Text not found " + e.getMessage());
		}
		
		delayMS(3000);
	}
	
	@AfterTest
	public void close(){
		driver.quit();
	}
}
