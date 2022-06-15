//package com.nexsoft.cicool;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class LoginFunctionality {
//protected WebDriver driver;
//	
//	public void delayMS(int inInt) {
//		try {
//			Thread.sleep(inInt);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@BeforeClass
//	public void init() {
//		System.setProperty("url", "http://localhost/cicool");
//		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get(System.getProperty("url"));
//		driver.manage().window().maximize();
//	}
//	
//	@BeforeMethod
//	public void cekSession() {
//		driver.get(System.getProperty("url"));
//		HomePage home = PageFactory.initElements(driver, HomePage.class);
//		home.clickSignIn().loginValidUser("ridhotadjudin@gmail.com", "123456");
//		driver.get(System.getProperty("url"));
//		home.logout();
//	}
//	
//	@Test(priority=0)
//	public void testLogin_usernameValid_passwordValid() {
//		HomePage home = PageFactory.initElements(driver, HomePage.class);
//		home.clickSignIn().loginValidUser("ridhotadjudin@gmail.com", "123456");
//		
////		Dashboard dash = home.clickSignIn().loginValidUser("ridhotadjudin@gmail.com", "123456");
////		Assert.assertEquals(dash.getUsername(), "Ridho");
//		
//		//verify login success
//		String msg = driver.findElement(By.xpath("//*[@id=\"app\"]/header/nav/div/ul/li[3]/a/span")).getText();
//		Assert.assertTrue(msg.toLowerCase().contains("ridho"));
//		delayMS(1000);
//		driver.get("http://localhost:80/cicool/administrator/auth/logout");
//	}
//	
//	@Test(priority=1)
//	public void testLogin_usernameValid_passwordNotValid() {
//		HomePage home = PageFactory.initElements(driver, HomePage.class);
//		home.clickSignIn().loginValidUser("ridhotadjudin@gmail.com", "123");
//		
//		//verify login failed
//		String msg = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/p")).getText();
//		Assert.assertTrue(msg.toLowerCase().contains("Error.!\r\nE-mail, Username or Password do not match."));
//		delayMS(1000);
//	}
//	
//	@Test(priority=2)
//	public void testLogin_usernameNotValid_passwordNotValid() {
//		driver.get("http://localhost/cicool/");
//		HomePage home = PageFactory.initElements(driver, HomePage.class);
//		home.clickSignIn().loginValidUser("123@gmail.com", "ridho");
//		
//		//verify login failed
//		String msg = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/p")).getText();
//		Assert.assertTrue(msg.toLowerCase().contains("user does not exist"));
//		delayMS(1000);
//		driver.get("http://localhost/cicool/");
//	}
//}
