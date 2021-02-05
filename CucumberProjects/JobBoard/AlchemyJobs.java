package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlchemyJobs {
	
	    WebDriver driver;
	    WebDriverWait wait;
		
		@Given("^User opens the Bowser$")
		public void userIsOnAlchemyPage() {
			
			 //Create a new instance of the Firefox driver
	        driver = new FirefoxDriver();
	        wait = new WebDriverWait(driver, 15);
	                
		}
		
	    @Given("^User opens the Job Board Login Page$")
	    public void OpenWebsite() throws Throwable {
	    //Open the URL
	    driver.get("https://alchemy.hguy.co/jobs/wp-admin");
	    driver.manage().window().maximize();
	    Thread.sleep(5000);
	    }
		
		@When("^User logs in with \"(.*)\" and \"(.*)\"$")
	    public void userSignin(String username,String password) throws Throwable {
			 driver.findElement(By.id("user_login")).sendKeys(username);
			 driver.findElement(By.id("user_pass")).sendKeys(password);
			 driver.findElement(By.id("wp-submit")).click();
	    }
		
		@Then("^Create User and provide all necessary details$")
		public void createNewUser() throws InterruptedException
		{
			
			WebElement User =driver.findElement(By.linkText("Users"));
	    	User.click();
	    	// Fetching the header object of new loaded page into a web element
	    	WebElement UsersPageTitle = driver.findElement(By.tagName("h1"));
	        // Asking selenium to wait until the new page is loaded along with it's header element
	    	wait.until(ExpectedConditions.visibilityOf(UsersPageTitle));
	    	 	
	    	WebElement Addnewbutton = driver.findElement(By.linkText("Add New"));
	    	Addnewbutton.click();
	    	
	    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("add-new-user")));
	    	driver.findElement(By.id("user_login")).sendKeys("QASDET002");
	    	driver.findElement(By.id("email")).sendKeys("suresh.uce573@email.com");
	    	Thread.sleep(1000);
	    	driver.findElement(By.id("first_name")).sendKeys("Test222");
	    	driver.findElement(By.id("last_name")).sendKeys("QA002");
	    	//driver.findElement(By.cssSelector(".wp-generate-pw")).click();
	    	WebElement Passwordbutton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[4]/form/table/tbody/tr[6]/td/button"));
	    	Passwordbutton.click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.id("createusersub")).click();
		}
		
		 @And("^Validate that the user has been created$")
		    public void validateUserCreation() throws Throwable {
		    	String message = driver.findElement(By.cssSelector("#message")).getText();
		    	System.out.println(message);
		    	Assert.assertTrue(message.contains("New user created."));
		    	driver.findElement(By.id("user-search-input")).sendKeys("QASDET002");
		    	driver.findElement(By.id("search-submit")).click();
		    	WebElement userName = driver.findElement(By.cssSelector(".username > strong:nth-child(2) > a:nth-child(1)"));
		    	Assert.assertEquals(userName.getText(), "QASDET002");
		    } 
		 
		    @And ("^Close the Browser$")
		    public void closeBrowser() throws Throwable {
		    	driver.close();
		    }
		    
		    @Given("^User opens the Job Board Page$")
		    public void openJobPortal() throws Throwable {
		    	driver.get("https://alchemy.hguy.co/jobs/");
		    	driver.manage().window().maximize();
		        
		    }
		    
		    @And("^Navigate to the Job Page$")
		    public void ClickJob() throws Throwable {
		    	Thread.sleep(5000);
		    	driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)")).click();
		    }
		    
		    @When("^Find the Keyword and Type in keywords to search for jobs and change the Job type$")
		    public void findJob() throws Throwable {
		    	driver.findElement(By.id("search_keywords")).sendKeys("Selenium");
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
		    }
		    @Then("^Find the filter using XPath and filter job type to show only Full Time jobs$")
		    public void filterJob() throws Throwable {
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_internship\"]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_part-time\"]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"job_type_temporary\"]")).click();
		    }
		    @And("^Find a job listing using XPath and it to see job details$")
		    public void SelectAJob() throws Throwable {
		    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")));
		    	driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).click();    	
		    }
		    @And("^Find the title of the job listing using XPath and print it to the console$")
		    public void JobTitle() throws Throwable {
		    	String Title= driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/header/div/h1")).getText();
		    	System.out.println(Title);
		    }
		    @And("^Find and Click on the Apply for job button$")
		    public void applyForJob() throws Throwable {
		    	// Waiting for job page to load 
		 		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@class = 'application_button button']"))));
		 		// Clicking on "Apply for job" button
		 		driver.findElement(By.xpath("//input[@class = 'application_button button']")).click();
		 		String emailId = driver.findElement(By.xpath("//a[@class = 'job_application_email']")).getText();
			    System.out.println("To apply for this job email your details to : " + emailId);
		        }

		    @And("^Navigate to the Post Job Page$")
		    public void postJob() throws Throwable {
		    	driver.findElement(By.cssSelector("#menu-item-26 > a:nth-child(1)")).click();
		    }
		    
		    @When("^User provides the information \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" and \"(.*)\"$")
		    public void filltheJob(String eMail, String title, String location, String jobDescription, String companyURL, String companyName) throws Throwable {
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_email")));
		    driver.findElement(By.id("create_account_email")).sendKeys(eMail);
		    driver.findElement(By.id("job_title")).sendKeys(title);
		    driver.findElement(By.id("job_location")).sendKeys(location);
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@id=\"job_description_ifr\"]")).sendKeys(Keys.ENTER, jobDescription );
		    driver.findElement(By.id("application")).sendKeys(companyURL);
		    driver.findElement(By.id("company_name")).sendKeys(companyName);
		    driver.findElement(By.cssSelector("input.button:nth-child(4)")).click();
		    }
		    
		    @Then("^Click submit$")
		    public void submitJob() throws Throwable {
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("job_preview_submit_button")));
		    driver.findElement(By.id("job_preview_submit_button")).click();
		    }
		    
		    @And("^Go to the Jobs page$")
		    public void navigateToJob() throws Throwable {
		    	driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)")).click();
		    }
		    @And("^Confirm job listing is shown on page with Job Name and Location as \"(.*)\" and \"(.*)\"$")
		    public void searchTheJob(String jobName, String location) throws Throwable {
		    	driver.findElement(By.cssSelector("#search_keywords")).sendKeys(jobName);
		    	driver.findElement(By.cssSelector("#search_location")).sendKeys(location);
		    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")));
		    	driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).click();
		    	String findJob= driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/header/div/h1")).getText();
		    	Assert.assertEquals(findJob, jobName);
		    }
}