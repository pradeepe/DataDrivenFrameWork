package com.fdc.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fdc.base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		log.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not Successfull");
		log.debug("Login successfully executed");
	}
	

}
