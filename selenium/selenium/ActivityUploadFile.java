package SeleniumActivities;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ActivityUploadFile {


public static void main(String[] args) throws InterruptedException {
    WebDriver driver = new FirefoxDriver();
    File file = new File("filepath");
    
    //Open browser
    driver.get("https://www.training-support.net/selenium/upload");
    
    //Find upload field
    WebElement uploadInput = driver.findElement(By.id("uploadFile"));
    uploadInput.sendKeys(file.getAbsolutePath());
    
    Thread.sleep(900);
    //Click button to upload
    driver.findElement(By.id("uploadButton")).click();
    Thread.sleep(900);
    //Read file name
    String fileName = driver.findElement(By.tagName("h4")).getText();
    System.out.println(fileName);
    
    //Close browser
    driver.close();
}

}
