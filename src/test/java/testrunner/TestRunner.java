package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		 plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber.json"},
		features = {"src/main/resources/Features"},
		glue= {"stepdefinitions"},
		
		tags = "@SmokeTest"
		
		
		)

public class TestRunner {

}