package Tests;

//import org.testng.annotations.Test;
//import org.testng.AssertJUnit;
//
//import java.time.LocalDateTime;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import Pages.ConfigLoader;
//import Pages.LoginPage;
//import Pages.ResumePage;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class ResumeTest {
//
//	private WebDriver driver;
//	private ConfigLoader config;
//
//	@BeforeMethod
//	public void Setup() {
//
//		
//		ChromeOptions opt = new ChromeOptions();
//		opt.addArguments("--headless");
//		opt.addArguments("--disable-gpu"); // recommended for Windows
//		opt.addArguments("--window-size=1920,1080"); // ensures full rendering
//		
//		// Initialize WebDriver
//		driver = new ChromeDriver();
//		// Load configuration properties
//		config = new ConfigLoader("src\\main\\java\\resources\\config.properties");
//
//	}
//
//	@Test
//	public void testlogin() throws InterruptedException {
//
//		// Open the login URL
//		driver.get(config.getProperty("url"));
//		Thread.sleep(2000);
//
//		// Create page objects
//		LoginPage loginPage = new LoginPage(driver);
//		ResumePage resumePage = new ResumePage(driver); //
//
//		// Try to login with the first user's credentials
//		String errorMessage = loginPage.login(config.getProperty("username"), config.getProperty("password"));
//
//		if (errorMessage != null) {
//			System.out.println("Login failed: " + errorMessage);
//			Assert.fail("Login failed: " + errorMessage); // Fail the test if login is unsuccessful
//		
//		} else {
//			// If login is successful, update the resume
//			resumePage.updateresume(config.getProperty("resumefilepath"));
//			System.out.println("Login successful and resume updated.");
//			System.out.println("✅ Resume upload attempted at: " + LocalDateTime.now());
//
//		}
//
//	}
//	
//	@AfterMethod
//	public void tearDown() {
//		
//		driver.close();
//	}
//
//	
//}





import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Pages.ConfigLoader;
import Pages.LoginPage;
import Pages.ResumePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ResumeTest {

	private WebDriver driver;
	private ConfigLoader config;

	@BeforeMethod
	public void Setup() {
		// Setup headless Chrome options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // For Chrome 109+
	    options.addArguments("--disable-gpu");
	    options.addArguments("--window-size=1920,1080"); // Important!
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");

		// Initialize WebDriver
		driver = new ChromeDriver();

		// Load config
		config = new ConfigLoader("src\\main\\java\\resources\\config.properties");
	}

	
	

	
	@Test
	public void testlogin() {
		try {
			driver.get(config.getProperty("url"));
			Thread.sleep(2000); // helpful for headless debug

			LoginPage loginPage = new LoginPage(driver);
			ResumePage resumePage = new ResumePage(driver);

			String errorMessage = loginPage.login(config.getProperty("username"), config.getProperty("password"));

			if (errorMessage != null) {
				System.out.println("Login failed: " + errorMessage);
				captureScreenshot("login-failure");
				Assert.fail("Login failed: " + errorMessage);
			} else {
				resumePage.updateresume(config.getProperty("resumefilepath"));
				System.out.println("✅ Login successful and resume updated.");
				System.out.println("⏰ Resume upload attempted at: " + LocalDateTime.now());
				 System.out.println(((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return navigator.userAgent"));
			        
			}

		} catch (Exception e) {
			captureScreenshot("exception");
			Assert.fail("❌ Test failed with exception: " + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Screenshot helper
	public void captureScreenshot(String name) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("screenshots/" + name + "_" + System.currentTimeMillis() + ".png"));
			System.out.println("📸 Screenshot captured: " + name);
		} catch (IOException e) {
			System.out.println("⚠️ Failed to capture screenshot: " + e.getMessage());
		}
	}
}

