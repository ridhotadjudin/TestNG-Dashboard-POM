package com.nexsoft.cicool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	
	protected WebDriver driver;
	
	public Dashboard(WebDriver driver) {
		this.driver = driver;
	}
	
//	@FindBy(xpath="//span[normalize-space()='CRUD Builder']")
	@FindBy(linkText = "CRUD Builder" )
	private WebElement btnCrudBuilder;
	
	@FindBy(xpath = "//span[@class='hidden-xs']" )
	private WebElement username;
	
	public CrudBuilderPage clickCrudBuilder(){
		btnCrudBuilder.click();
		CrudBuilderPage crudBuilder = PageFactory.initElements(driver, CrudBuilderPage.class);
		return crudBuilder;
	}

	public String getUsername() {
		return username.getText();
	}
	
}
