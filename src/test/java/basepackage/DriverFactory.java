package basepackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static basepackage.BaseClass.logger;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browser) {
        WebDriver drv;
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();

                profile.setPreference("dom.webnotifications.enabled", false); // disable notifications
                profile.setPreference("dom.push.enabled", false); // disable push
                firefoxOptions.setProfile(profile);

                drv = new FirefoxDriver(firefoxOptions);

                drv.manage().window().maximize();
                drv.manage().deleteAllCookies();
                drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "edge":
                logger.info("Test start");

                EdgeOptions edgeOptions = new EdgeOptions();

// Set preferences to block notifications
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                edgeOptions.setExperimentalOption("prefs", prefs);
                edgeOptions.addArguments("--disable-notifications"); // optional

// Create EdgeDriver with the configured options
                drv = new EdgeDriver(edgeOptions);

                drv.manage().window().maximize();
                drv.manage().deleteAllCookies();
                drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();

                Map<String, Object> prefs1 = new HashMap<>();
                prefs1.put("profile.default_content_setting_values.notifications", 2); // 2 = block
                chromeOptions.setExperimentalOption("prefs", prefs1);
                chromeOptions.addArguments("--disable-notifications"); // optional

                drv = new ChromeDriver(chromeOptions);

                drv.manage().window().maximize();
                drv.manage().deleteAllCookies();
                drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        driver.set(drv);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}