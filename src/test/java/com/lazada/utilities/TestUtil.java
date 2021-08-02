package com.lazada.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.lazada.base.TestBase;

public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;
	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ssFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
	}
	
	
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName1="test_runmode";
		int rows = excel.getRowCount(sheetName1);
		for (int rNum=2;rNum<=rows;rNum++) {
			String testCase =excel.getCellData(sheetName1, "TCID", rNum);
			if(testCase.equals(testName)) {
				String runmode = excel.getCellData(sheetName1, "Runmode", rNum);
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
}
