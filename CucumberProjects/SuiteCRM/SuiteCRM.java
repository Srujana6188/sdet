package stepDefinitions;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class SuiteCRM {
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	
	@Given("^User opens chrome Browser$")
    public void OpenChromeBrowser() throws Throwable {
		//Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }
	
	@When("^Open the SuiteCRM page and login with credentials provided \"(.*)\" and \"(.*)\"$")
	public void OpenURLandLogin(String username, String password) throws Throwable {
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.findElement(By.id("bigbutton")).click();	
	}
	@Given("^Count the number of Dashlets on the homepage$")
	public void countDashlets() throws Throwable {
		Thread.sleep(5000);
		List<WebElement> webletsCount = driver.findElements(By.className("dashlet-title"));
		System.out.println("Count of weblets on the page are: " + webletsCount.size());
	}
	@When("^Print the number and title of each Dashlet into the console$")
	public void titleDashlets() throws Throwable {
		List<WebElement> dashletTitles = driver.findElements(By.className("dashlet-title"));
		for(WebElement dashletTitle : dashletTitles) {
		    System.out.println(dashletTitle.getText());
		}
	}
	@And("^Close the browser$")
	public void CloseBrowser() throws Throwable{
		driver.quit();
	}
	
	@Given("^Navigate to Sales -> Leads -> Create Lead$")
	public void navigateToCretaeLeads() throws Throwable{
		Thread.sleep(5000);
		WebElement Leads = driver.findElement(By.id("grouptab_0")); 
		Actions actions = new Actions(driver);
		actions.moveToElement(Leads).perform();
		WebElement selectLeads = driver.findElement(By.id("moduleTab_9_Leads"));
		selectLeads.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[2]")).click();
		Thread.sleep(5000);
	}
	
	@When("^Fill in the necessary details to create lead accounts using the values passed from the Feature file as \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
	public void fillLeadsForms(String title, String Fname, String Lname, String eMail) throws Throwable {
		WebElement Title = driver.findElement(By.id("salutation"));  
		Select dropdown = new Select(Title); 
		dropdown.selectByVisibleText(title);

		
		WebElement Name = driver.findElement(By.xpath("//*[@id=\"first_name\"]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Name));
    	Name.sendKeys(Fname);
    	
    	WebElement Surname = driver.findElement(By.xpath("//*[@id=\"last_name\"]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Surname));
    	Surname.sendKeys(Lname);
    	
    	WebElement Email = driver.findElement(By.xpath("//*[@id=\"Leads0emailAddress0\"]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Email));
    	Email.sendKeys(eMail);
	}
	
	@Then("^Click Save to finish$")
	public void SavetheLead() throws Throwable {
		driver.findElement(By.cssSelector("div.buttons:nth-child(5) > input:nth-child(1)")).click();
	}
	
	@And("^Navigate to the View Leads page to see results as \"(.*)\" and \"(.*)\" and \"(.*)\"$")
	public void validateResult(String title, String Fname, String Lname) throws Throwable{
		Thread.sleep(5000);
		String leadname= driver.findElement(By.className("module-title-text")).getText();
		Assert.assertEquals(title + " " + Fname + " " + Lname, leadname);
		
	}	
	@Given("^Navigate to Activities -> Meetings -> Schedule a Meeting$")
	public void scheduleMeetingPage() throws Throwable{
		Thread.sleep(5000);
		WebElement Activities = driver.findElement(By.id("grouptab_3")); 
		Actions actions = new Actions(driver);
		actions.moveToElement(Activities).perform();
		WebElement meetings = driver.findElement(By.id("moduleTab_9_Meetings"));
		meetings.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[2]")).click();
		Thread.sleep(5000);
	}
	
	 @And ("^User first enters the\"(.*)\"and add to meeting$")
	    public void AddFirstinvitee (String FirstName) throws Throwable {
	    	
	    	WebElement FirstInvitee = driver.findElement(By.id("search_first_name"));
	    	
	    	FirstInvitee.sendKeys(FirstName);
	    	
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.id("invitees_search")).click();
	    	
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.id("invitees_add_1")).click();
	    	
	    }
	    
	    @And ("^User second enters the\"(.*)\"and add to meeting$")
	    public void AddSecondinvitee(String SecondName) throws Throwable {
	    	
	    	WebElement SecondInvitee = driver.findElement(By.id("search_first_name"));
	    	
	    	SecondInvitee.clear();
	    	
	    	SecondInvitee.sendKeys(SecondName);
	    	
	    	driver.findElement(By.id("invitees_search")).click();
	    	
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.id("invitees_add_1")).click();
	    	
	    }
	  
	    @And ("^User third enters the\"(.*)\"and add to meeting$")
	    public void AddThirdinvitee(String ThirdName) throws Throwable {
	    	
	    	WebElement ThirdInvitee = driver.findElement(By.id("search_first_name"));
	    	
	    	ThirdInvitee.clear();
	    	
	    	ThirdInvitee.sendKeys(ThirdName);
	    	driver.findElement(By.id("invitees_search")).click();
	    	
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.id("invitees_add_1")).click();
	    	
	    }
	    
	    
	    
	@And("^Click On Save$")
	public void clickSave() throws Throwable{
		driver.findElement(By.id("name")).sendKeys("Meeting to Discuss Business");
    	driver.findElement(By.id("date_start_date")).sendKeys("12/15/2020");
    	driver.findElement(By.id("date_start_hours")).sendKeys("09");
    	driver.findElement(By.id("date_start_minutes")).sendKeys("45");
    	driver.findElement(By.id("date_end_date")).sendKeys("12/15/2020");
    	driver.findElement(By.id("date_end_hours")).sendKeys("12");
    	driver.findElement(By.id("date_end_minutes")).sendKeys("45");
		driver.findElement(By.id("SAVE_HEADER")).click();
	}
	@And("^Navigate to View Meetings page$")
	public void NavigateBacktoMeetings() throws Throwable{
    driver.findElement(By.linkText("View Meetings")).click();
    	
    	Thread.sleep(3000);
    	
	}
	
	  @And ("^confirm the meeting")
	    public void ValidateMeeting()  {
	    	
	    	if(driver.findElement(By.linkText("Meeting to Discuss Business")).isDisplayed())
	    		
	    	{
	    		System.out.println("The Meeting is scheduled successfully");
	    		
	    	}
	    	
	    	else
	    	{
	    		System.out.println("The meeting could not be scheduled");
	    	}
	    }
	@Given("^Navigate to All -> Products-> Create Product$")
	public void createProduct() throws Throwable{
		Thread.sleep(5000);
		WebElement All = driver.findElement(By.id("grouptab_5")); 
		Actions actions = new Actions(driver);
		actions.moveToElement(All).perform();
		WebElement products = driver.findElement(By.linkText("Products"));
		products.click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)")).click();
		Thread.sleep(5000);
	}
	@When("^Retrieve information about the product as \"(.*)\" and \"(.*)\" and \"(.*)\"$")
	public void productForm(String productName, String price, String description) throws Throwable{
		driver.findElement(By.id("name")).sendKeys(productName);
		driver.findElement(By.id("price")).sendKeys(price);
		driver.findElement(By.id("description")).sendKeys(description);
		Thread.sleep(5000);
	}
	@Then("^Click On Product Save Button$")
	public void clickSaveProduct() throws Throwable{
		driver.findElement(By.id("SAVE")).click();
		Thread.sleep(5000);
	}
	@And("^Go to the View Products page to see all products listed as \"(.*)\"$")
	public void validateProduct(String productName) throws Throwable{
		String titleProduct= driver.findElement(By.id("name")).getText();
		Assert.assertEquals(productName, titleProduct);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/ul/li[2]/a/div[2]")).click();
		Thread.sleep(5000);
		String titleProductpage= driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr[1]/td[3]/b/a")).getText();
		Assert.assertEquals(productName, titleProductpage);	
	}
}