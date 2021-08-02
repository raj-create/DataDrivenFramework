package com.lazada.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.lazada.base.TestBase;
import com.lazada.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener{
	public void onTestFailure(ITestResult arg0) {
		// to create html link of screenshot
		System.getProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, arg0.getInstanceName().toUpperCase()+"fail");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
		Reporter.log("screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"</img></a>");
		
	}
	public void onTestSuccess(ITestResult arg0) {
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"pass");
		rep.endTest(test);
		rep.flush();
	}
	public void onTestStart(ITestResult arg0) {
		test = rep.startTest(arg0.getName().toUpperCase());
		
		}
	
	
	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName()+"skiping the test");
		rep.endTest(test);
		rep.flush();
	}

}
