package pages;

import java.util.concurrent.TimeUnit;

import basepackage.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import basepackage.BaseClass;

public class Homepage_Fab extends BaseClass{

	
WebDriver ldriver;
	public Homepage_Fab(WebDriver rdriver)
		{

		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);

	}

	@FindBy(xpath = "//button[@class='border-0 bg-transparent']//*[name()='svg']")
	public  WebElement ProfileButton;
	public void clickProfileIcon()
	{
		ProfileButton.click();
	}


	@FindBy(xpath = "//button[normalize-space()='Login using password']")
	public  WebElement LoginButton;
	public void clickLogin()
	{
		highLighterMethod(driver,LoginButton);
		LoginButton.click();
	}

	@FindBy(xpath = "//input[@id='logiemail']")
	public WebElement EmailField;
	public void enterEmail(String email)
	{
		highLighterMethod(driver,EmailField);
		EmailField.sendKeys(email);
	}

	@FindBy(xpath = "//input[@id='logipass']")
	public WebElement Password;

	public void enterPassword(String password)
	{
		highLighterMethod(driver,Password);
		Password.sendKeys(password);
	}

	@FindBy(xpath = "//button[@id='send-otp']")
	public  WebElement loginButton;

	public void clicklogin()
	{
		highLighterMethod(driver,loginButton);
		loginButton.click();
	}

	@FindBy(xpath = "//button[@class='border-0 bg-transparent']//*[name()='svg']")
	public  WebElement AccountButton;
	public void clickAccountIcon()
	{
		highLighterMethod(driver,AccountButton);
		Actions action = new Actions((WebDriver) driver);
		action.moveToElement(AccountButton).build().perform();
		AccountButton.click();
	}

	@FindBy(xpath = "//button[normalize-space()='Log Out']")
	public  WebElement logoutButton;

	public void clicklogout()
	{
		highLighterMethod(driver,logoutButton);
		logoutButton.click();
	}

	
	
	
	@FindBy(xpath= "//*[@id=\"CC-loginHeader-logout\"]")
	WebElement SIGNOUT;

	
	public void clicksignout() {
		highLighterMethod(driver,SIGNOUT);
		SIGNOUT.click();
		
	
		}


	@FindBy(xpath= "//img[@alt='FabIndia']")
	WebElement FABINDIA_ICON;

	public void verify_FabIndiaicon() {
		FABINDIA_ICON.isDisplayed();
	}


	@FindBy(xpath= "//div[@id='searchIcon']//*[name()='svg']")
	WebElement SEARCHICON;

	public void verify_Searchicon() {
		SEARCHICON.isDisplayed();
	}

	public void searchicon() {
			SEARCHICON.click();
		}

	@FindBy(xpath= "//i[@class='fas fa-map-marker-alt mr-1']")
	WebElement Location_ICON;

	public void verify_FabLocationicon() {
		Location_ICON.isDisplayed();
	}

	@FindBy(xpath= "//a[@class='wishlist_icon_header']")
	WebElement Wishlist_ICON;

	public void verify_Wishlisticon() {
		Wishlist_ICON.isDisplayed();
	}

	@FindBy(xpath= "//a[@aria-label='0 items currently in your cart']//*[name()='svg']")
	WebElement Cart_ICON;

	public void verify_Carticon() {
		Cart_ICON.isDisplayed();
	}

	@FindBy(xpath= "//div[@class='cx-notice']")
	WebElement CopyRights_text;

	public void verify_CopyRightsText() throws InterruptedException {


		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CopyRights_text);
		Thread.sleep(500);
		CopyRights_text.isDisplayed();
	}


	public void VerifyProfileIcon()
	{
		ProfileButton.isDisplayed();
	}
	
	@FindBy(id= "CC-headerWidget-Search")
	WebElement SEARCHBAR;
	
	public void searchbar() {
		highLighterMethod(driver,SEARCHBAR);
		SEARCHBAR.click();
		SEARCHBAR.clear();
		SEARCHBAR.sendKeys("KURTA FOR MEN");
		
		}
	
	
	@FindBy(xpath= "/html/body/div[3]/header/div/div/div/div/div[1]/div/div[1]/div/div/div/div[2]/div[4]/div/div/div/div/div/form/div[1]/button/span[2]/img")
	WebElement CLICKSEARCH;
	
	public void clicksearch() {
		
		CLICKSEARCH.click();
		
		}
	
	
	
	
	
	
	@FindBy(xpath= "//p[contains(text(),'Linen Dobby Weave Long Kurta')]")
	WebElement LinenDobbyWeaveLongKurta;
	

	public void clickkurta() {
		

	    highLighterMethod(driver,LinenDobbyWeaveLongKurta);
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;

	    LinenDobbyWeaveLongKurta.click();
		
		}
	
	

	@FindBy(xpath= "//body/div[@id='page']/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[4]/div[2]/div[1]/div[1]/ul[1]/li[3]/button[1]")
	WebElement SELECT_M_SIZE;
	
	
public void selectMsize() throws InterruptedException  {
		
		highLighterMethod(driver,SELECT_M_SIZE);
		
		SELECT_M_SIZE.click();
		
		}


@FindBy(xpath= "//body/div[@id='page']/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[6]/div[2]/div[1]/button[1]")
WebElement ADDTOCART;


public void addtocart()  {
	
	highLighterMethod(driver,ADDTOCART);
	ADDTOCART.click();
}


@FindBy(xpath= "//body[1]/div[3]/header[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")
WebElement CLOSEICON;


public void closeicon()  {
	
	highLighterMethod(driver,CLOSEICON);
	CLOSEICON.click();
}

@FindBy(xpath= "//header/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")
WebElement CARTICON;


public void carticon()  {
	
	highLighterMethod(driver,CARTICON);
	CARTICON.click();
}





@FindBy(xpath= "//*[@id=\"cat100137\"]")
WebElement FOOD;

public void movetofood() throws InterruptedException {
	
	highLighterMethod(driver,FOOD);
	Actions action = new Actions(driver);
	action.moveToElement(FOOD).build().perform();
	Thread.sleep(3000);
	
}

public void food()  {
	
	highLighterMethod(driver,FOOD);
	FOOD.click();
}


}