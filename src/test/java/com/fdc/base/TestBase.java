package com.fdc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	/*
	 * Properties
	 * WebDriver
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 */
	
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
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
			} catch (IOException e) {
				e.printStackTrace();
			}
					
			switch (config.getProperty("browser")) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("chrome");
				break;

			case "IE":
				System.setProperty("webdriver.IE.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				System.out.println("IE");
				break;

			case "FireFox":
				System.setProperty("webdriver.gecko.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				System.out.println("FireFox");
				break;

			default:
				System.setProperty("webdriver.chrome.driver",
						"C:\\ws\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("chrome - default ");
				break;
			}
			
			
			
			
			
			System.out.println(config.getProperty("testsiteurl"));
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
			
		}
		
	}

	@AfterSuite
	public void tearDown() {
		
		if (driver != null) {
			driver.quit();
		}
	}
}
