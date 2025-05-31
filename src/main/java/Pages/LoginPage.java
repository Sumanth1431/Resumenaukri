
package Pages;

//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class LoginPage {
//
//	WebDriver driver;
//	WebDriverWait wait;
//	
//	By loginview = By.id("login_Layer");
//	By usernameField = By.xpath("//*[contains(@placeholder,'Username')]"); // Replace with actual ID
//	By passwordField = By.xpath("//*[contains(@placeholder,'password')]"); // Replace with actual ID
//	By loginButton = By.xpath("//button[@type='submit']"); // Replace with actual ID
//	By errormessage = By.name("login-form");
//	By viewprofile = By.cssSelector(".view-profile-wrapper");
//
//	public LoginPage(WebDriver driver) {
//		this.driver = driver;
//		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	}
//
//	public void loginclick1() {
//
//		wait.until(ExpectedConditions.elementToBeClickable(loginview)).click();
//
//	}
//
////	public void enterusername(String username) {
////
////		driver.findElement(usernameField).sendKeys(username);
////	}
//
//	public void enterusername(String username) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement usernameField = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder,'Username')]")));
//
//		// Now that the element is interactable, enter the username
//		usernameField.sendKeys(username);
//	}
//
//	public void enterpassword(String password) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement usernameField = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder,'password')]")));
//
//		// Now that the element is interactable, enter the username
//		usernameField.sendKeys(password);
//	}
//
//	public void loginclick() {
//
//		driver.findElement(loginButton).click();
//
//	}
//
////	public void loginclick12() {
////
////		driver.findElement(viewprofile).click();
////
////	}
//
//	public void viewprofile() {
//		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement usernameField1 = wait
//				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".view-profile-wrapper")));
//
//		// Now that the element is interactable, enter the username
//		usernameField1.click();// .sendKeys(password);
//	}
//
//	public String login(String username, String password) {
//		loginclick1();
//		enterusername(username);
//		enterpassword(password);
//		loginclick();
//		viewprofile();
//
//		if (isErrorMessageDisplayed()) {
//
//			WebElement error = driver.findElement(errormessage);
//			return error.getText();
//		}
//		return null; // Return null if no error, implying login was successful
//
//	}
//
//	private boolean isErrorMessageDisplayed() {
//
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//			WebElement errorele = wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage));
//
//			return errorele.isDisplayed();
//
//		} catch (Exception e) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//	}
//}



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	// Locators
	private By loginview = By.id("login_Layer");
	private By usernameField = By.xpath("//*[contains(@placeholder,'Username')]");
	private By passwordField = By.xpath("//*[contains(@placeholder,'password')]");
	private By loginButton = By.xpath("//button[@type='submit']");
	private By errormessage = By.name("login-form");
	private By viewprofile = By.cssSelector(".view-profile-wrapper");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loginclick1() {
		System.out.println("🔍 Waiting for login button to be visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		
		WebElement loginLayer = wait.until(ExpectedConditions.visibilityOfElementLocated(loginview));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_Layer")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login_Layer"))).click();

		//
		System.out.println("✅ Clicked on login layer.");
	}

	public void enterusername(String username) {
		System.out.println("⌨️  Entering username...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement userField = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		userField.clear();
		userField.sendKeys(username);
		System.out.println("✅ Username entered.");
	}

	public void enterpassword(String password) {
		System.out.println("🔐 Entering password...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passField.clear();
		passField.sendKeys(password);
		System.out.println("✅ Password entered.");
	}

	public void loginclick() {
		System.out.println("🔘 Clicking login...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		button.click();
		System.out.println("✅ Login button clicked.");
	}

	public void viewprofile() {
		System.out.println("👤 Waiting for profile view button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(viewprofile));
		profileBtn.click();
		System.out.println("✅ Clicked on profile view.");
	}

	public String login(String username, String password) {
		try {
			loginclick1();
			enterusername(username);
			enterpassword(password);
			loginclick();
			viewprofile();

			if (isErrorMessageDisplayed()) {
				WebElement error = driver.findElement(errormessage);
				System.out.println("❌ Login error displayed.");
				return error.getText();
			}

		} catch (Exception e) {
			System.out.println("⚠️ Exception during login: " + e.getMessage());
			return "Exception: " + e.getMessage();
		}

		System.out.println("✅ Login flow completed.");
		return null;
	}

	private boolean isErrorMessageDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement errorEle = wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage));
			return errorEle.isDisplayed();
		} catch (Exception e) {
			// No error message found
			return false;
		}
	}
}
