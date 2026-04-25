package basepackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import pages.Homepage_Fab;
import utilities.Readconfigfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static synchronized String captureScreen(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        String fileName = testName + "_" + Thread.currentThread().getId() + "_" + timestamp + ".png";

        String path = System.getProperty("user.dir") + "/Screenshots/" + fileName;

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/Screenshots/"));
            Files.copy(src.toPath(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }


}
