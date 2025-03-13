package pages;

import basepackage.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {


    @FindBy(xpath = "//button[contains(@data-test-id, 'quicklinks-popout-trigger')]")
    WebElement ProfileButton;

    public void clickProfileIcon()
    {
        ProfileButton.click();
    }


    @FindBy(xpath = "//*[@id=\"radix-1\"]/div/div/div/div[4]/div/button[1]/div/div")
    WebElement LoginButton;

    public void clickLogin()
    {
        LoginButton.click();
    }

    @FindBy(xpath = "//div[contains(text(),'Login with Email and Password')]")
    WebElement LoginwithEmail;

    public void clickLoginwithEmail()
    {
        LoginwithEmail.click();
    }


}
