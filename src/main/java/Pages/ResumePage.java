package Pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResumePage {

    WebDriver driver;

    // Locator for the resume upload field
    By resumefield = By.cssSelector("input[value='Update resume']");  // Update to a file input field locator

    // Locator for the uploaded resume filename (change as needed)
    By uploadedFileNameLocator = By.xpath("//span[contains(text(), 'Pavan_3.5+YOE_FrontEnd.pdf')]"); // A more general locator to check file upload

    public ResumePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to upload resume
    public void updateresume(String resumefilepath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement resumeElement = wait.until(ExpectedConditions.elementToBeClickable(resumefield));

        resumeElement.click();
		//return resumefilepath;
		
        //used here autoit to upload file because it type button for uploadresume other than type file we need to use autoit tool
        //otherwise we can use normally through sendkeys  fileupload and all
		try {
	        // Execute the AutoIT script to handle the file picker dialog
	        String autoItScript = "C:\\Users\\jshar\\Downloads\\autofile.exe";
	        Runtime.getRuntime().exec(autoItScript);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
      
    }
    

   
    }

