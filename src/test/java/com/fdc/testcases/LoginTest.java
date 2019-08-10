package com.fdc.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.fdc.base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() {
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
	}
	

}
