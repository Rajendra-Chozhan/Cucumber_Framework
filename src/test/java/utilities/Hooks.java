package utilities;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
