package Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Pages.ConfigLoader;
import Pages.LoginPage;
import Pages.ResumePage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResumeTest {

	private WebDriver driver;
	private ConfigLoader config;

	@BeforeMethod
	public void Setup() {

		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless");
		// Initialize WebDriver
		driver = new ChromeDriver(opt);
		// Load configuration properties
		config = new ConfigLoader("src\\main\\java\\resources\\config.properties");

	}

	@Test
	public void testlogin() throws InterruptedException {

		// Open the login URL
		driver.get(config.getProperty("url"));

		// Create page objects
		LoginPage loginPage = new LoginPage(driver);
		ResumePage resumePage = new ResumePage(driver); //

		// Try to login with the first user's credentials
		String errorMessage = loginPage.login(config.getProperty("username"), config.getProperty("password"));

		if (errorMessage != null) {
			System.out.println("Login failed: " + errorMessage);
			Assert.fail("Login failed: " + errorMessage); // Fail the test if login is unsuccessful
		
		} else {
			// If login is successful, update the resume
			resumePage.updateresume(config.getProperty("resumefilepath"));
			System.out.println("Login successful and resume updated.");
			System.out.println("✅ Resume upload attempted at: " + LocalDateTime.now());

		}

	}
}
