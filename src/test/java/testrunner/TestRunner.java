package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.Listeners;
import utilities.CustomReportListener;


@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/FailedRerun.txt"
		},
		features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
		tags = "@SmokeTest"
		
		
		)


@Listeners(CustomReportListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {

}
