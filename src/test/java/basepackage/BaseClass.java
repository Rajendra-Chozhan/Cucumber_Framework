package basepackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pages.Homepage_Fab;
import utilities.DriverFactory;
import utilities.Readconfigfile;


public class BaseClass {

	public static final Logger logger = LogManager.getLogger("QA TEST");
	public  static WebDriver driver;
	public Homepage_Fab hp;
	Readconfigfile read = new Readconfigfile();

	public String BaseURL=read.getApplicationURL();
	public String Email=read.getEmail();
	public String Password=read.getPassword();
	public String Emailpwd = read.getEmailpwd();


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