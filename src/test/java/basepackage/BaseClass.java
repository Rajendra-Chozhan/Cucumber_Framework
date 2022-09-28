package basepackage;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	static WebDriver driver;
	
	
	 @BeforeClass
	    public static void setUp(){
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\msi\\Desktop\\Selenium\\Chrome Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }

	    @AfterClass
	    public static void tearDown(){
	      driver.quit();
	    }
	}