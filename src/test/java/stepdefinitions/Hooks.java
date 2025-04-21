package stepdefinitions;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import io.cucumber.shaded.messages.types.TestStepResult;
import utilities.CustomReportListener;
import utilities.ErrorTracker;
import utilities.TestResult;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String browser = "Chrome"; // Fetch dynamically if needed
        String errorMsg = "";

        Throwable error = ErrorTracker.getError();
        if (error != null) {
            errorMsg = error.getMessage();
        }

        testResults.add(new TestResult(
                counter++,
                scenario.getName(),
                scenario.getStatus().toString(),
                durationMillis,
                browser,
                errorMsg
        ));
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
