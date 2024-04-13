package com.tricenties.ec.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtility {
	public Actions act;
	
	public WebDriverUtility(WebDriver driver) {
		act=new Actions(driver);
	}
	
	public void mouseHover(WebElement element) {
		act.moveToElement(element).perform();
	}
}
