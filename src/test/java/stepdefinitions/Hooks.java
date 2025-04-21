package stepdefinitions;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import utilities.CustomReportListener;
import utilities.ErrorTracker;
import utilities.TestResult;

import java.util.ArrayList;
import java.util.List;

public class Hooks {

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    public static List<TestResult> testResults = new ArrayList<>();
    private static int counter = 1;

    @Before
    public void beforeScenario(Scenario scenario) {
        startTime.set(System.currentTimeMillis());
        ErrorTracker.clear(); // Clear any previous error
    }

    @After
    public void afterScenario(Scenario scenario) {
        long durationMillis = System.currentTimeMillis() - startTime.get();
        String browser = "Chrome"; // You can fetch this dynamically if needed
        String errorMsg = "";

        Throwable error = ErrorTracker.getError();
        if (error != null) {
            errorMsg = error.getMessage();
        }

        TestResult result = new TestResult(
                counter++,
                scenario.getName(), // ✅ Correct scenario name
                scenario.getStatus().toString(),
                durationMillis,
                browser,
                errorMsg
        );

        testResults.add(result);
    }

    @AfterAll
    public static void afterAll() {
        try {
            CustomReportListener.generateReport(testResults, "test-output/custom-reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
