package stepdefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import utilities.*;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hooks {
    private static final List<TestResult> results = new ArrayList<>();
    private static int counter = 1;
    private long startTime;

    @Before

    public void beforeScenario(Scenario scenario) {
              System.out.println("🔥 BEFORE HOOK triggered for: " + scenario.getName());
    }
    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("✅ AFTER HOOK triggered for: " + scenario.getName());
        long duration = System.currentTimeMillis() - startTime;
        String status = scenario.isFailed() ? "FAIL" : "PASS";
        String browser = System.getProperty("browser", "chrome");

        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "-";

        if (scenario.isFailed()) {
            try {
                WebDriver driver = DriverFactory.getDriver();
                if (driver != null) {
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                    String screenshotDir = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots";
                    new File(screenshotDir).mkdirs();

                    String fileName = scenarioName + "_" + timestamp + ".png";
                    File destFile = new File(screenshotDir, fileName);
                    FileUtils.copyFile(srcFile, destFile);

                    screenshotPath = "../screenshots/" + fileName;

                    System.out.println("✅ Screenshot saved: " + destFile.getAbsolutePath());
                } else {
                    System.out.println("🚨 WebDriver is null. Screenshot not captured.");
                }
            } catch (Exception e) {
                System.out.println("❌ Exception while taking screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        }

        String errorMessage = "-";
        if (scenario.isFailed()) {
            Throwable error = ErrorTracker.getError();
            errorMessage = (error != null) ? error.getMessage() : "Unknown error";
        }

        results.add(new TestResult(
                counter++,
                scenario.getName(),
                status,
                duration,
                browser,
                screenshotPath,
                errorMessage
        ));

        ErrorTracker.clear();
    }

    public static List<TestResult> getResults() {
        return results;
    }


    @After
    public void after_Scenario(Scenario scenario) {
        // ... existing logic
        results.add(new TestResult());
        CustomReportListener.setResults(results); // Add this
    }
    @AfterSuite
    public void finalizeReport() {
        System.out.println("Generating report");
        utilities.CustomReportListener.generateReport(results);
    }



}
