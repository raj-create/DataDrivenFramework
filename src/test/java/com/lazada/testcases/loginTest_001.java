package com.lazada.testcases;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lazada.base.TestBase;

public class loginTest_001 extends TestBase{
	@Test
	public void loginAsUser() {
		click("popupcross_XPATH");
		click("loginbutton_XPATH");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("userEmail_XPATH"))), "login button not clicked");
		
	}

}
