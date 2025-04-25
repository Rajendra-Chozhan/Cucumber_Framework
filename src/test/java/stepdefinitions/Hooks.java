package stepdefinitions;


import basepackage.BaseClass;
import basepackage.BrowserManager;
import basepackage.DriverFactory;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import utilities.ErrorTracker;
import utilities.TestResult;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Hooks {

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    public static List<TestResult> testResults = new ArrayList<>();
    private static int counter = 1;
    public static ThreadLocal<String> browserName = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
//       String browser = System.getProperty("browser", "chrome"); // Default to Chrome if no system property is provided
//        BrowserManager.initDriver(browser);

        String browser = System.getProperty("browser", "chrome");
        DriverFactory.setDriver(browser);

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
                browserName.get(), // Use browser name from thread local
                errorMsg
        );

        testResults.add(result);

        WebDriver currentDriver = BrowserManager.getDriver();
        if (currentDriver != null) {
            currentDriver.quit();
            DriverFactory.quitDriver();
        }


    }

    @AfterAll
    public static void afterAll() {
        try {
            utilities.CustomReportListener.generateReport(testResults, "test-output/custom-reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
