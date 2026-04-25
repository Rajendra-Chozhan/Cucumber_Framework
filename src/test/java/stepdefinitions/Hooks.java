package stepdefinitions;

import basepackage.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import utilities.ErrorTracker;
import utilities.TestResult;
import utilities.CustomReportListener;

import java.util.ArrayList;
import java.util.List;

public class Hooks {

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    public static List<TestResult> testResults = new ArrayList<>();
    private static int counter = 1;

    // This will run before each scenario
    @Before
    public void setup() {
        // Fetch browser and runMode from system properties (set via command line or configuration)
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if not provided
        String runMode = System.getProperty("runMode", "local");  // Default to local if not provided

        // Pass the parameters to DriverManager to set up the WebDriver
        DriverManager.setDriver(browser, runMode);

        // Start timing
        startTime.set(System.currentTimeMillis());
        ErrorTracker.clear();
    }

    @After
    public void afterScenario(Scenario scenario) {
        long durationMillis = System.currentTimeMillis() - startTime.get();
        String errorMsg = "";

        Throwable error = ErrorTracker.getError();
        if (error != null) {
            errorMsg = error.getMessage();
        }

        TestResult result = new TestResult(
                counter++,
                scenario.getName(),
                scenario.getStatus().toString(),
                durationMillis,
                System.getProperty("browser"), // Directly from system property
                errorMsg
        );

        testResults.add(result);

        // Quit driver safely
        DriverManager.quitDriver();
    }

    @AfterAll
    public static void afterAll() {
        try {
            CustomReportListener.generateReport(testResults, "test-output/custom-reports");
            System.out.println("✅ Custom HTML report generated after all scenarios.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
