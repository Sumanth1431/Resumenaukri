package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	
	By loginview = By.id("login_Layer");
	By usernameField = By.xpath("//*[contains(@placeholder,'Username')]"); // Replace with actual ID
	By passwordField = By.xpath("//*[contains(@placeholder,'password')]"); // Replace with actual ID
	By loginButton = By.xpath("//button[@type='submit']"); // Replace with actual ID
	By errormessage = By.name("login-form");
	By viewprofile = By.cssSelector(".view-profile-wrapper");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loginclick1() {

		driver.findElement(loginview).click();

	}

//	public void enterusername(String username) {
//
//		driver.findElement(usernameField).sendKeys(username);
//	}

	public void enterusername(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement usernameField = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder,'Username')]")));

		// Now that the element is interactable, enter the username
		usernameField.sendKeys(username);
	}

	public void enterpassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement usernameField = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder,'password')]")));

		// Now that the element is interactable, enter the username
		usernameField.sendKeys(password);
	}

	public void loginclick() {

		driver.findElement(loginButton).click();

	}

//	public void loginclick12() {
//
//		driver.findElement(viewprofile).click();
//
//	}

	public void viewprofile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement usernameField1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".view-profile-wrapper")));

		// Now that the element is interactable, enter the username
		usernameField1.click();// .sendKeys(password);
	}

	public String login(String username, String password) {
		loginclick1();
		enterusername(username);
		enterpassword(password);
		loginclick();
		viewprofile();

		if (isErrorMessageDisplayed()) {

			WebElement error = driver.findElement(errormessage);
			return error.getText();
		}
		return null; // Return null if no error, implying login was successful

	}

	private boolean isErrorMessageDisplayed() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement errorele = wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage));

			return errorele.isDisplayed();

		} catch (Exception e) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
