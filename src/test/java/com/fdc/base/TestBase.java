package com.fdc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.fdc.utilities.ExcelReader;

public class TestBase {
	
	/*
	 * Properties
	 * WebDriver
	 * Logs - Log4j jar, Application.log and Selenium.log Log4j.properties file, Logger class
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * 
	 * 
	 * wait
	 */
	
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static ExcelReader excel = new ExcelReader("C:\\ws\\DataDrivenFramework\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	
	
	@BeforeSuite
	public void setUp() {

		if (driver==null) {
			
			try {
				fis = new FileInputStream("C:\\ws\\DataDrivenFramework\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				System.out.println("Config file loaded !!! ");
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("C:\\ws\\DataDrivenFramework\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				System.out.println("OR file loaded !!! ");
				log.debug("OR file loaded !!! ");
			} catch (IOException e) {
				e.printStackTrace();
			}
					
			switch (config.getProperty("browser")) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("chrome");
				log.debug("Chrome Launched !!! ");
				break;

			case "IE":
				System.setProperty("webdriver.IE.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				//System.out.println("IE");
				log.debug("IE Launched !!! ");
				break;

			case "FireFox":
				System.setProperty("webdriver.gecko.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				//System.out.println("FireFox");
				log.debug("FireFox Launched !!! ");
				break;

			default:
				System.setProperty("webdriver.chrome.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				//System.out.println("chrome - default ");
				log.debug("Chrome Launched !!! ");
				break;
			}
			
			System.out.println(config.getProperty("testsiteurl"));
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to Url: " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
		}
		
	}
	
	public boolean isElementPresent (By by) {
		
		try {
			driver.findElement(by);
			return true;
		} catch(NoSuchElementException e) {
			return false;
		}
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		
		if (driver != null) {
			Thread.sleep(1000);
			driver.quit();
		}
		log.debug("Test Execution completed ");
	}
}
