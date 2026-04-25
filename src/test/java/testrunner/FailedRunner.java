package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "rerun:target/FailedRerun.txt"
        },
        monochrome = true,
        features = {"@target/FailedRerun.txt"},
        glue = {"stepdefinitions"},
        tags = "@SmokeTest"
)
public class FailedRunner extends AbstractTestNGCucumberTests {
}