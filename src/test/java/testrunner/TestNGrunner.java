package testrunner;




import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import stepdefinitions.Hooks;
import utilities.CustomReportListener;


@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"


)
@Listeners(utilities.CustomReportListener.class)
public class TestNGrunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void generateHtmlReport() {
        try {
            CustomReportListener.generateReport(Hooks.testResults, "test-output/custom-reports");
            System.out.println("✅ Custom HTML report generated afxter suite.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}