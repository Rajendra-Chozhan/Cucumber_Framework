package stepdefinitions;

import basepackage.BrowserManager;
import basepackage.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.*;
import basepackage.BaseClass;
import pages.Homepage_Fab;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static basepackage.BaseClass.captureScreen;

public class Login_Logout_Steps extends BaseClass{

	private static final Logger logger = LogManager.getLogger(Login_Logout_Steps.class);
	//WebDriver currentDriver = BrowserManager.getDriver();
	WebDriver currentDriver = DriverFactory.getDriver();

	@Test(description = "Login Test")
	@Given("user launches the URL")
	public void user_launches_application() {

		try {
			currentDriver.get(BaseURL);
			logger.info("Navigated to URL");
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}

	@When("user sign in to the application")
	public void signin() throws IOException {

		try {
			hp = new Homepage_Fab();
			currentDriver.manage().timeouts().implicitlyWait(555, TimeUnit.SECONDS);
			hp.clickProfileIcon();
			logger.info("Clicked Profile Icon");
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
			hp.clickLogin();
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(555, TimeUnit.SECONDS);
			hp.enterEmail(Email);
			logger.info("Entered Email");
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(555, TimeUnit.SECONDS);
			hp.enterPassword(Password);
			logger.info("Entered Password");
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(555, TimeUnit.SECONDS);
			hp.clicklogin();
			logger.info("Clicked Login Button");
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(555, TimeUnit.SECONDS);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@And("user clicks on Account Info")
	public void clicks_on_account_info() throws IOException {
		try {
			hp = new Homepage_Fab();
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			captureScreen(currentDriver, "LoginTest");
			hp.clickAccountIcon();
			logger.info("Clicked Account Icon");
			currentDriver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@Then("User verify the UI Elements in Homepage")
	public void verify_the_UI_elements_in_homepage() throws InterruptedException, IOException {
			try {
			hp = new Homepage_Fab();
			captureScreen(currentDriver, "Verify UI Element Test");
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			hp.verify_FabIndiaicon();
			captureScreen(currentDriver, "Verify UI Element Test");
			hp.verify_Carticon();
			currentDriver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
			hp.verify_Searchicon();
			hp.verify_Wishlisticon();
			captureScreen(currentDriver, "Verify UI Element Test");
			currentDriver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
			hp.verify_FabLocationicon();
			captureScreen(currentDriver, "Verify UI Element Test");
			hp.verify_CopyRightsText();
			currentDriver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
			logger.info("Verified UI Elements");
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@And("user signs out of the application")
	public void signs_out_of_the_application() throws InterruptedException {

		try {
			hp = new Homepage_Fab();
			hp.clicklogout();
			logger.info("Clicked Logout");
			currentDriver.quit();
			Thread.sleep(1000);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@When("^User Sign into the application with (.*) and (.*)$")
	public void signintotheapplication(String Email, String Password) throws IOException {

		try {
			hp = new Homepage_Fab();
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			hp.clickProfileIcon();
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			hp.clickLogin();
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			hp.enterEmail(Email);
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(190, TimeUnit.SECONDS);
			hp.enterPassword(Password);
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			hp.clicklogin();
			captureScreen(currentDriver, "LoginTest");
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@When("User Search for product")
	public void search_for_the_product() {

		try {
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebElement search = currentDriver.findElement(By.xpath("//input[@placeholder='Search puma.com']"));
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			currentDriver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
			search.click();
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			search.sendKeys("Mens wear");
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			search.sendKeys(Keys.ENTER);
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@When("user send the test report in email")
	public void user_send_the_email_report() {
		try {
			SendEmailWithReport rp = new SendEmailWithReport();
			rp.sendjavaemail();
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

	@When("User filters the product")
	public void filter_for_the_product() {

		try {
			Select drp = new Select(currentDriver.findElement(By.xpath("//body/div[@id='page']/div[@id='product-search-results']/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/select[1]")));
			drp.selectByVisibleText("Price Low To High");
			currentDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e; // Re-throw so Cucumber knows it's a failure
		}
	}

}