package basepackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();  // Thread-local driver

    // Getter
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Setter
    public static void setDriver(String browser, String runMode) {
        WebDriver drv;

        try {
            if (runMode.equalsIgnoreCase("grid")) {
                URL gridUrl = new URL("http://192.168.0.104:4444/wd/hub");  // URL of your Selenium Grid hub

                switch (browser.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        drv = new RemoteWebDriver(gridUrl, firefoxOptions);
                        break;
                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        drv = new RemoteWebDriver(gridUrl, edgeOptions);
                        break;
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        Map<String, Object> prefs = new HashMap<>();
                        prefs.put("profile.default_content_setting_values.notifications", 2);
                        chromeOptions.setExperimentalOption("prefs", prefs);
                        drv = new RemoteWebDriver(gridUrl, chromeOptions);
                }
            } else { // Local mode
                switch (browser.toLowerCase()) {
                    case "firefox":
                        drv = new FirefoxDriver();
                        break;
                    case "edge":
                        drv = new EdgeDriver();
                        break;
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        Map<String, Object> prefs = new HashMap<>();
                        prefs.put("profile.default_content_setting_values.notifications", 2);
                        chromeOptions.setExperimentalOption("prefs", prefs);
                        drv = new ChromeDriver(chromeOptions);
                }
            }

            drv.manage().window().maximize();
            drv.manage().deleteAllCookies();
            drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.set(drv);  // Set the driver for this thread

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Driver setup failed: " + e.getMessage());
        }
    }

    // Quit driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
