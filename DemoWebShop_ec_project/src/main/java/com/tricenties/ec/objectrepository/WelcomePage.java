package com.tricenties.ec.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
	public WelcomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	 @FindBy(linkText = "Log in")
	 private WebElement loginLink;

	 @FindBy(xpath = "//span[text()='Shopping cart']")
	 private WebElement shoppingCartLink;
	 
	public WebElement getShoppingCartLink() {
		return shoppingCartLink;
	}

	public WebElement getLoginLink() {
		return loginLink;
	}
	 
}
