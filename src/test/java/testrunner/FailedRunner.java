package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.Listeners;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "rerun:target/FailedRerun.txt"
        },
        monochrome=true,
        features = {"@target/FailedRerun.txt"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"


)
public class FailedRunner {
}