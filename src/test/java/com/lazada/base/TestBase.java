package com.lazada.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.lazada.utilities.ExcelReader;
import com.lazada.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static ExcelReader excel =new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\userData.xlsx");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	public static void click(String Key) {
		if (Key.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(Key))).click();
		}else if(Key.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(Key))).click();
		}else if(Key.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(Key))).click();
		}else if(Key.endsWith("_CLASS")){
			driver.findElement(By.className(OR.getProperty(Key))).click();
		}
		log.info("Clicking on an Element:" + Key);
	}
	//while typing(type)
	public static void type(String Key, String Value) {
		if (Key.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(Key))).sendKeys(Value);
		}else if(Key.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(Key))).sendKeys(Value);
		}else if(Key.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(Key))).sendKeys(Value);
		}else if(Key.endsWith("_CLASS")){
			driver.findElement(By.className(OR.getProperty(Key))).sendKeys(Value);
		}
		log.info("Entering the value as:" + Value);
	}
	
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}else if(config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
		}
		
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	@AfterSuite
	public void tearDown() {
		
	}

}
