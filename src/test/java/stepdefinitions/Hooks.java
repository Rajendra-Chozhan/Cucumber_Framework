package stepdefinitions;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hooks {
    private static final List<TestResult> testResults = new ArrayList<>();
    private static int counter = 1;
    private long startTime;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("🔥 BEFORE HOOK triggered for: " + scenario.getName());
        startTime = System.currentTimeMillis(); // Capture the start time of the scenario
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("✅ AFTER HOOK triggered for: " + scenario.getName());

        long durationInMillis = System.currentTimeMillis() - startTime; // Calculate the duration after the scenario ends
        String duration = String.valueOf(durationInMillis); // Convert duration to String

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
                    System.out.println("📸 Screenshot saved: " + destFile.getAbsolutePath());
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

        // Add the test result to the list
        testResults.add(new TestResult(
                counter++,                          // Increment the counter for each test case
                scenario.getName(),                 // Scenario name
                status,                             // Test status (PASS/FAIL)
                duration,                           // Duration as String
                browser,                            // Browser used for the test
                screenshotPath,                     // Screenshot path (if any)
                errorMessage                        // Error message (if any)
        ));

        // Clear any error information from ErrorTracker
        ErrorTracker.clear();
    }

    @AfterAll
    public static void generateReport() {
        try {
            // Call the generateReport method from the report class
            CustomReportListener.generateReport(testResults, "test-output/custom-reports");  // Make sure the ReportGenerator class is responsible for report creation
            System.out.println("📄 HTML report generated.");
        } catch (IOException e) {
            System.out.println("❌ Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<TestResult> getTestResults() {
        return testResults;
    }
}
