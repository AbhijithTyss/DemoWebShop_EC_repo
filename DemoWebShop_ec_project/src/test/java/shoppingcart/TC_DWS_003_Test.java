package shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricenties.ec.genericutility.BaseClass;
import com.tricenties.ec.genericutility.ListenerUtility;
@Listeners(ListenerUtility.class)
public class TC_DWS_003_Test extends BaseClass{
	@Test
	public void addProduct() {
		driver.findElement(By.xpath("//a[text()='14.1-inch Laptop']/../..//input")).click();
		WebElement prdoctAddedMsg = driver.findElement(By.xpath("//p[contains(text(),'product has been added')]"));
		Assert.assertEquals(prdoctAddedMsg.isDisplayed(), true,"product failed to add");
		test.log(Status.PASS, "product added successfully");
		eWait.until(ExpectedConditions.invisibilityOf(prdoctAddedMsg));
	}
}
