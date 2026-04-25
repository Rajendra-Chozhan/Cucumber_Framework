package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@CucumberOptions(
		plugin = {
				"pretty",
				"html:target/cucumber-reports/report.html",
				"json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		features = {"src/main/resources/Features"},
		glue = {"stepdefinitions"},
		tags = "@SmokeTest",
		monochrome = true
)
@Listeners(utilities.CustomReportListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
