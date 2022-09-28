package stepdefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import basepackage.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Stepdefinition_One extends BaseClass {

	WebDriver driver;
    
	@Test
	@Given("user launches the URL")
	public void user_launches_application(){
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\msi\\Desktop\\Selenium\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://in.puma.com/in/en/home");
		
}	
		
	

	
	@When("^user sign in to the application$")
	public void signin() {
		
		

		driver.findElement(By.xpath("//*[contains(@class,'p-header-actions-icon p-header-actions-icon--account')]")).click();
		driver.findElement(By.xpath("//*[contains(@class,'p-user-menu-authenticate-button')][contains(text(),'Login')]")).click();
		
		 driver.findElement(By.xpath("//*[contains(@id,\"login-form-email\")]")).sendKeys("rajchozhan024@gmail.com");
		 driver.findElement(By.xpath("//*[contains(@id,\"login-form-password\")]")).sendKeys("Puma@761645");
	
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,250)", "");
		 
		 WebElement element = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Login')]"));
       	element.click();
		
	}

	@And("^user clicks on Account Info$")
	public void clicks_n_the_logo(){
	
		
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		 
			
		driver.findElement(By.xpath("//*[contains(@class,'p-header-user-status-link')][contains(text(),'My Account')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}	
	
	
	@And("^user signs out of the application$")
	public void signs_out_of_the_application(){
	
				
		driver.findElement(By.xpath("//*[contains(@class,'dashboard__logout__link')][contains(text(),'Logout')]")).click();

		driver.quit();
		
			
	}





	@When("^User Sign into the application with (.*) and (.*)$")
	public void signintotheapplication(String Email, String Password) {
		
		

		driver.findElement(By.xpath("//*[contains(@class,'p-header-actions-icon p-header-actions-icon--account')]")).click();
		driver.findElement(By.xpath("//*[contains(@class,'p-user-menu-authenticate-button')][contains(text(),'Login')]")).click();
		
		 driver.findElement(By.xpath("//*[contains(@id,\"login-form-email\")]")).sendKeys(Email);
		 driver.findElement(By.xpath("//*[contains(@id,\"login-form-password\")]")).sendKeys(Password);
	
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,250)", "");
		 
		 WebElement element = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Login')]"));
       	element.click();
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		
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
