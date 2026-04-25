package pages;

import java.time.Duration;

import basepackage.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage_Fab extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	// ✅ Constructor (ONLY initialization)
	public Homepage_Fab(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	// ================= LOGIN =================

	@FindBy(xpath = "//button[@class='border-0 bg-transparent']//*[name()='svg']")
	WebElement profileButton;

	public void clickProfileIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
	}

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	WebElement loginButton;

	public void clickLogin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	@FindBy(id = "logiemail")
	WebElement emailField;

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(emailField));
		highLighterMethod(driver, emailField);
		emailField.sendKeys(email);
	}

	@FindBy(id = "logipass")
	WebElement passwordField;

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		highLighterMethod(driver, passwordField);
		passwordField.sendKeys(password);
	}

	@FindBy(id = "send-otp")
	WebElement submitLogin;

	public void clickSubmitLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(submitLogin)).click();
	}

	// ================= ACCOUNT =================

	@FindBy(xpath = "//button[@class='border-0 bg-transparent']//*[name()='svg']")
	WebElement accountButton;

	@FindBy(xpath = "//img[contains(@src,'Store')]")
	WebElement locationIcon;

	public void clickAccountIcon() {
		Actions act = new Actions(driver);
		act.moveToElement(locationIcon).perform();
		act.moveToElement(accountButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
	}

	// ================= LOGOUT =================

	@FindBy(xpath = "//button[normalize-space()='Log Out']")
	WebElement logoutButton;

	public void clicklogout() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}

	// ================= UI VALIDATIONS =================

	@FindBy(xpath = "//img[@alt='FabIndia']")
	WebElement fabIndiaIcon;

	public void verify_FabIndiaicon() {
		wait.until(ExpectedConditions.visibilityOf(fabIndiaIcon));
		fabIndiaIcon.isDisplayed();
	}

	@FindBy(xpath = "//div[contains(@class,'search')]//i[contains(@class,'magnifying-glass')]")
	WebElement searchIcon;

	public void verify_Searchicon() {
		wait.until(ExpectedConditions.visibilityOf(searchIcon));
		searchIcon.isDisplayed();
	}

	@FindBy(xpath = "//a[@class='wishlist_icon_header']")
	WebElement wishlistIcon;

	public void verify_Wishlisticon() {
		wait.until(ExpectedConditions.visibilityOf(wishlistIcon));
		wishlistIcon.isDisplayed();
	}

	@FindBy(xpath = "//a[contains(@aria-label,'cart')]")
	WebElement cartIcon;

	public void verify_Carticon() {
		wait.until(ExpectedConditions.visibilityOf(cartIcon));
		cartIcon.isDisplayed();
	}

	@FindBy(xpath = "//div[@class='cx-notice']")
	WebElement copyRightsText;

	public void verify_CopyRightsText() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copyRightsText);
		wait.until(ExpectedConditions.visibilityOf(copyRightsText));
		copyRightsText.isDisplayed();
	}

	public void verify_FabLocationicon() {
		wait.until(ExpectedConditions.visibilityOf(locationIcon));
		locationIcon.isDisplayed();
	}
}