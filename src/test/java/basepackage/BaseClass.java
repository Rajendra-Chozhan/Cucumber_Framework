package basepackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import pages.Homepage_Fab;
import utilities.Readconfigfile;


public class BaseClass {

    public static final Logger logger = LogManager.getLogger("QA TEST");
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public Homepage_Fab hp;
    Readconfigfile read = new Readconfigfile();

    public String BaseURL=read.getApplicationURL();
    public String Email=read.getEmail();
    public String Password=read.getPassword();


    public static void highLighterMethod(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid green;');", element);
    }


    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }


    public static  void captureScreen(WebDriver driver, String tname) throws IOException {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver; // interface
            File source = ts.getScreenshotAs(OutputType.FILE); // method
            File target = new File(System.getProperty("user.dir") + "/Screenshots/" + "/Snapshots/" + tname + timestamp() + ".png");
            FileUtils.copyFile(source, target);
            System.out.println("Screenshot taken");
        } catch (Exception e) {

            System.out.println("Exception while taking screenshot" + e.getMessage());
        }
    }

}