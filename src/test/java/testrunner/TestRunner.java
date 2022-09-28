package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/main/resources/Features"},
		glue= {"stepdefinitions"},
		
		tags = "@SmokeTest"
		
	
		
		)

public class TestRunner {

}