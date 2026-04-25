package pages;

import basepackage.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Homepage_Fab extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	public Homepage_Fab(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// ================= COMMON METHODS =================

	public void waitForPageLoad() {
		wait.until(driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public WebElement getFreshElement(By locator) {
		return wait.until(driver -> {
			try {
				WebElement element = driver.findElement(locator);
				return (element.isDisplayed()) ? element : null;
			} catch (StaleElementReferenceException e) {
				return null;
			}
		});
	}

	public void safeClick(By locator) {
		WebElement element = getFreshElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void safeSendKeys(By locator, String value) {
		WebElement element = getFreshElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void scrollIntoView(By locator) {
		WebElement element = getFreshElement(locator);
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// ================= LOCATORS =================

	By profileButton = By.xpath("//button[@class='border-0 bg-transparent']//*[name()='svg']");
	By loginButton = By.xpath("//button[contains(text(),'Login')]");
	By emailField = By.id("logiemail");
	By passwordField = By.id("logipass");
	By submitLogin = By.id("send-otp");

	By accountButton = By.xpath("//button[@class='border-0 bg-transparent']//*[name()='svg']");
	By locationIcon = By.xpath("//img[contains(@src,'Store')]");
	By logoutButton = By.xpath("//button[normalize-space()='Log Out']");

	By fabIndiaIcon = By.xpath("//img[@alt='FabIndia']");
	By searchIcon = By.xpath("//div[contains(@class,'search')]//i[contains(@class,'magnifying-glass')]");
	By wishlistIcon = By.xpath("//a[@class='wishlist_icon_header']");
	By cartIcon = By.xpath("//a[contains(@aria-label,'cart')]");
	By copyRightsText = By.xpath("//div[@class='cx-notice']");

	// ================= ACTIONS =================

	public void clickProfileIcon() {
		safeClick(profileButton);
	}

	public void clickLogin() {
		safeClick(loginButton);
	}

	public void enterEmail(String email) {
		safeSendKeys(emailField, email);
	}

	public void enterPassword(String password) {
		safeSendKeys(passwordField, password);
	}

	public void clickSubmitLogin() {
		safeClick(submitLogin);
	}

	public void clickAccountIcon() {
		waitForPageLoad();
		Actions act = new Actions(driver);
		act.moveToElement(getFreshElement(locationIcon)).perform();
		act.moveToElement(getFreshElement(accountButton)).perform();
		safeClick(accountButton);
	}

	public void clickLogout() {
		WebElement element = getFreshElement(logoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// ================= VALIDATIONS =================

	public void verify_FabIndiaicon() {
		getFreshElement(fabIndiaIcon).isDisplayed();
	}

	public void verify_Searchicon() {
		getFreshElement(searchIcon).isDisplayed();
	}

	public void verify_Wishlisticon() {
		getFreshElement(wishlistIcon).isDisplayed();
	}

	public void verify_Carticon() {
		getFreshElement(cartIcon).isDisplayed();
	}

	public void verify_CopyRightsText() {
		waitForPageLoad();
		scrollIntoView(copyRightsText);
		getFreshElement(copyRightsText).isDisplayed();
	}

	public void verify_FabLocationicon() {
		getFreshElement(locationIcon).isDisplayed();
	}
}