package com.lazada.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lazada.base.TestBase;
import com.lazada.utilities.TestUtil;

public class SignInTest_002 extends TestBase{
	
	@Test(dataProvider="getData")
	public void SignIn(String Email, String Password ) {
		if(!(TestUtil.isTestRunnable("SignInTest_002", excel))) {
			throw new SkipException("Skipping the test"+"SignIn");
		}
		type("userEmail_XPATH", Email);
		type("userPassword_XPATH", Password);
		
		click("loginbtn_XPATH");
		

}
	@DataProvider()
	public Object[][] getData() {
		String sheetName = "SignInTest_002";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		for (int rowNum = 2;rowNum<=rows;rowNum++) {
			for(int colNum = 0;colNum<cols;colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
		
	}