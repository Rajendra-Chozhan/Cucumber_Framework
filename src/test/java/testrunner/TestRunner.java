package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.Listeners;


@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
		tags = "@SmokeTest"
		
		
		)

public class TestRunner  {

}
