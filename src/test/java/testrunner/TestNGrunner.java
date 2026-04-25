package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import utilities.CustomReportListener;
import stepdefinitions.Hooks;
import org.testng.annotations.Listeners;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},
        glue = {"stepdefinitions"},
        tags = "@SmokeTest"
)
@Listeners(CustomReportListener.class)
public class TestNGrunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public void generateHtmlReport() {
        try {
            CustomReportListener.generateReport(Hooks.testResults, "test-output/custom-reports");
            System.out.println("✅ Custom HTML report generated after suite.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
