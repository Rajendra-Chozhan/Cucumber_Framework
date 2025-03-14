package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class Foodpage_Fab extends BaseClass{

	
	
WebDriver ldriver;
	
	public Foodpage_Fab(WebDriver rdriver)
	
	{
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver,this);
		
	}
	
	
	@FindBy(xpath="/html[1]/body[1]/div[3]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/li[12]/a[1]/span[1]")
	public WebElement SWEETENERS;
	
public void clicksweeterners() throws InterruptedException {
	highLighterMethod(driver,SWEETENERS);
	Thread.sleep(2000);
	SWEETENERS.click();
	}



@FindBy(xpath="//p[@id='CC-product-grid-displayName-10576056OF-22']")
public WebElement RudrapraygHoney;

public void clickhoney() throws InterruptedException {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();", RudrapraygHoney);
	Thread.sleep(2000);
highLighterMethod(driver,RudrapraygHoney);	
RudrapraygHoney.click();
}






@FindBy(xpath="//body/div[@id='page']/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[5]/div[1]/div[1]/span[2]/button[1]")
public WebElement no_of_items;


public void clicknumberrequired() throws InterruptedException {
	highLighterMethod(driver,no_of_items);
	for (int i = 0; i <=3; i++){
		 //click the button
		no_of_items.click();
		 //wait 2 seconds
		  Thread.sleep(2000);
		  //check that data is being generated correctly
		
		}

}
	@FindBy(xpath="//body[1]/div[3]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[5]/div[2]/div[1]/button[1]")
	public WebElement addtocart;


	public void clickaddtocart() throws InterruptedException {
		highLighterMethod(driver,addtocart);
		
		addtocart.click();
			
			
			}
	
	public void scrolldown() {
		
			JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
	}
	
	
	@FindBy(xpath="/html[1]/body[1]/div[3]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/button[1]")
	public WebElement gotocart;


	public void clickgotocart() throws InterruptedException {
		highLighterMethod(driver,gotocart);
		
		gotocart.click();
			
			
			}
	
	
	
	@FindBy(xpath="//*[@id=\"cat100168\"]")
	public WebElement sugar;


	public void clicksugar() throws InterruptedException {
		highLighterMethod(driver,sugar);
		
		sugar.click();
			
			
			}
	
	
	
	
	
	
	
}




