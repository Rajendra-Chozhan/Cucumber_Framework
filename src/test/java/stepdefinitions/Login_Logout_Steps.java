package stepdefinitions;

import basepackage.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import utilities.*;
import pages.Homepage_Fab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static basepackage.BaseClass.captureScreen;

public class Login_Logout_Steps {

	private static final Logger logger = LogManager.getLogger(Login_Logout_Steps.class);

	WebDriver driver;
	Homepage_Fab hp;

	// ✅ FIX: Initialize driver AFTER @Before hook
	@Before
	public void init() {
		driver = DriverManager.getDriver();
	}

	@Given("user launches the URL")
	public void user_launches_application() {

		try {
			driver.get(new Readconfigfile().getApplicationURL());
			logger.info("Navigated to URL");
		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}

	@When("user logs in as {string}")
	public void login_as_user(String userType) throws IOException {

		try {
			Readconfigfile config = new Readconfigfile();

			String email = config.getUserData(userType + ".email");
			String password = config.getUserData(userType + ".password");

			// ✅ FIX: pass driver to page
			hp = new Homepage_Fab(driver);

			hp.clickProfileIcon();
			captureScreen(driver, "LoginTest");

			hp.clickLogin();
			captureScreen(driver, "LoginTest");

			hp.enterEmail(email);
			logger.info("Entered Email for user: " + userType);
			captureScreen(driver, "LoginTest");

			hp.enterPassword(password);
			logger.info("Entered Password for user: " + userType);
			captureScreen(driver, "LoginTest");

			hp.clicklogin();
			logger.info("Clicked Login Button");
			captureScreen(driver, "LoginTest");

		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}

	@And("user clicks on Account Info")
	public void clicks_on_account_info() {

		try {
			hp = new Homepage_Fab(driver); // ✅ FIX
			hp.clickAccountIcon();
			logger.info("Clicked Account Icon");

		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}

	@Then("User verify the UI Elements in Homepage")
	public void verify_the_UI_elements_in_homepage() throws InterruptedException {

		try {
			hp = new Homepage_Fab(driver); // ✅ FIX

			hp.verify_FabIndiaicon();
			hp.verify_Carticon();
			hp.verify_Searchicon();
			hp.verify_Wishlisticon();
			hp.verify_FabLocationicon();
			hp.verify_CopyRightsText();

			logger.info("Verified UI Elements");

		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}

	@And("user signs out of the application")
	public void signs_out_of_the_application() {

		try {
			hp = new Homepage_Fab(driver); // ✅ FIX
			hp.clicklogout();
			logger.info("Clicked Logout");

		} catch (Exception e) {
			ErrorTracker.setError(e);
			throw e;
		}
	}
}