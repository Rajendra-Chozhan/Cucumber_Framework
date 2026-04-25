package basepackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import pages.Homepage_Fab;
import utilities.Readconfigfile;

import java.io.File;
import java.io.IOException;

public class BaseClass {

    public static final Logger logger = LogManager.getLogger("QA TEST");
    public static ThreadLocal<String> browserName = new ThreadLocal<>();
    public static ThreadLocal<String> runMode = new ThreadLocal<>();
    // ThreadLocal to store ITestContext for parallel execution
    private static final ThreadLocal<ITestContext> context = new ThreadLocal<>();

    // Static setter for context
    public static void setContext(ITestContext context) {
        BaseClass.context.set(context);
    }

    // Static getter for context
    public static ITestContext getContext() {
        return context.get();
    }
    public Homepage_Fab hp;
    Readconfigfile read = new Readconfigfile();

    public String BaseURL = read.getApplicationURL();
    public String Email = read.getEmail();
    public String Password = read.getPassword();

    public static void highLighterMethod(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid green;');", element);
    }

    public static String timestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());
    }

    public static void captureScreen(WebDriver driver, String tname) throws IOException {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File target = new File(System.getProperty("user.dir") + "/Screenshots/" + "/Snapshots/" + tname + timestamp() + ".png");
            org.apache.commons.io.FileUtils.copyFile(source, target);
            System.out.println("Screenshot taken");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot" + e.getMessage());
        }
    }


}
