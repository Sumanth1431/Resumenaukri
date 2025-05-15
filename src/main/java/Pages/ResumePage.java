package Pages;

import java.io.File;
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
    By uploadedFileNameLocator = By.xpath("//span[contains(text(), 'Sumanth M B_QA_3.7.pdf')]"); // A more general locator to check file upload

    public ResumePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to upload resume
    public void updateresume(String resumefilepath) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement resumeElement = wait.until(ExpectedConditions.elementToBeClickable(resumefield));

        resumeElement.click();
		//freturn resumefilepath;
		
        //used here autoit to upload file because it type button for uploadresume other than type file we need to use autoit tool
        //otherwise we can use normally through sendkeys  fileupload and all
        
        
        File file = new File("E:\\Resume\\Sumanth M B_QA_3.7.pdf");
        if (!file.exists()) {
            System.out.println("❌ File not found!");
        }

        Thread.sleep(2000);
        try {
        	
        
        String resumefilePath = "E:\\Resume\\Sumanth M B_QA_3.7.pdf";
        String autoItPath = "D:\\\\Applications\\\\Autoit\\\\autofile.exe";
        
        

        String command = "\"" + autoItPath + "\" \"" + resumefilePath + "\"";
        System.out.println("Running: " + command);
        Runtime.getRuntime().exec(command);
        }catch (IOException e) {
	        e.printStackTrace();
	    }
        
        
//		try {
//	        // Execute the AutoIT script to handle the file picker dialog
//	        String autoItScript = "C:\\Users\\jshar\\OneDrive\\Desktop\\Sumant docs\\autoit\\autofile.exe";
//	        Runtime.getRuntime().exec(autoItScript);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
      
    }
    

   
    }

