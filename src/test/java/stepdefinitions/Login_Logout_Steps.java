package stepdefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import io.cucumber.java.en.Then;
import basepackage.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.Homepage_Fab;

import org.apache.logging.log4j.LogManager;
import utilities.SendEmailWithReport;

public class Login_Logout_Steps extends BaseClass{

	@Test
	@Given("user launches the URL")
	public void user_launches_application(){

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		logger.info("Test start");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Deleted Cookies");
		driver.get(BaseURL);
		logger.info("Navigated to URL");

		
}	
		
	

	
	@When("^user sign in to the application$")
	public void signin() throws IOException {

		hp= new Homepage_Fab(driver);
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clickProfileIcon();
		logger.info("Clicked Profile Icon");
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clickLogin();
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.enterEmail(Email);
		logger.info("Entered Email");
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(190, TimeUnit.SECONDS);
		hp.enterPassword(Password);
		logger.info("Entered Password");
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clicklogin();
		logger.info("Clicked Login Button");
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);

	}

	@And("^user clicks on Account Info$")
	public void clicks_n_the_logo() throws IOException {

		hp= new Homepage_Fab(driver);
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		captureScreen(driver,"LoginTest");
		hp.clickAccountIcon();
logger.info("clicked Account icon");
		driver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
	}

	@Then("^User verify the UI Elements in Homepage$")
	public void verify_the_UI_Elements_in_HomePage() throws InterruptedException, IOException {

		hp= new Homepage_Fab(driver);
		captureScreen(driver,"Verify UI Element Test");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.verify_FabIndiaicon();
		captureScreen(driver,"Verify UI Element Test");
		hp.verify_Carticon();
		driver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
		hp.verify_Searchicon();
		hp.verify_Wishlisticon();
		captureScreen(driver,"Verify UI Element Test");
		driver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
		hp.verify_FabLocationicon();
		captureScreen(driver,"Verify UI Element Test");
		hp.verify_CopyRightsText();

		driver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);

		logger.info("Verified UI Elements");
	}
	
	
	@And("^user signs out of the application$")
	public void signs_out_of_the_application(){
		hp= new Homepage_Fab(driver);
	    hp.clicklogout();
		logger.info("Clicked Logout");
	    driver.quit();
		SendEmailWithReport.sendjavaemail();

	}





	@When("^User Sign into the application with (.*) and (.*)$")
	public void signintotheapplication(String Email, String Password) throws IOException {

		hp= new Homepage_Fab(driver);
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clickProfileIcon();
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clickLogin();
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.enterEmail(Email);
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(190, TimeUnit.SECONDS);
		hp.enterPassword(Password);
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		hp.clicklogin();
		captureScreen(driver,"LoginTest");
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);

		
	}
	

	@When("^User Search for product$")
	public void searchfortheproductn() {
		
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	

		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search puma.com']"));
		
		
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		 driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);	
		search.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		search.sendKeys("Mens wear");
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		search.sendKeys(Keys.ENTER);
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
	}
	



	@When("^User filters the product$")
	public void filterfortheproductn() {
		
				
		
		Select drp = new Select(driver.findElement(By.xpath("//body/div[@id='page']/div[@id='product-search-results']/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/select[1]")));
		drp.selectByVisibleText("Price Low To High");
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	
	}
	@When("^User Add item to Cart$")
	public void addtocart() {
		
		WebElement element = driver.findElement(By.cssSelector("a[href*='/in/en/pd/puma-essential-small-logo-regular-fit-mens-t-shirt/847225.html?dwvar_847225_color=04']"));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,450)", "");
		 
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		 element.click();
		
		//driver.findElement(By.xpath("//body/div[@id='page']/div[@id='product-search-results']/div[2]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/a[1]")).click();
	
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		 
		 WebElement addtocart = driver.findElement(By.xpath("//*[contains(@class,'add-to-cart btn btn-primary btn-full-width')][contains(text(),'Add to Cart')]"));
 
		WebElement size = driver.findElement(By.xpath("//*[contains(@class,'product-variation-swatch-link')][contains(text(),'S')]"));
		
		
		 JavascriptExecutor js1 = (JavascriptExecutor) driver;
		 js1.executeScript("window.scrollBy(0,1550)", "");
		size.click();
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 addtocart.click();
		 
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		 driver.findElement(By.xpath("//*[contains(@class,'btn btn-outline-dark')][contains(text(),'View Cart')]")).click();
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//span[contains(@class,'p-header-actions-icon p-header-actions-icon--account')]")).click();
	
	driver.quit();
	}


}
