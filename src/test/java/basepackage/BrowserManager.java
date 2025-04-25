package basepackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import static basepackage.BaseClass.logger;

public class BrowserManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

@Parameters("browser")
    public static void initDriver(String browserName) {
        WebDriver localDriver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                localDriver = new ChromeDriver();
                logger.info("Test start");
                localDriver.manage().window().maximize();
                localDriver.manage().deleteAllCookies();
                localDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                break;
            case "firefox":
                localDriver = new FirefoxDriver();
                localDriver.manage().window().maximize();
                localDriver.manage().deleteAllCookies();
                localDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("dom.webnotifications.enabled", false); // disable notifications
                profile.setPreference("dom.push.enabled", false);           // disable push notifications
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                break;
            case "edge":
                localDriver = new EdgeDriver();
                logger.info("Test start");
                localDriver.manage().window().maximize();
                localDriver.manage().deleteAllCookies();
                localDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                break;
                default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        localDriver.manage().window().maximize();
        driver.set(localDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
