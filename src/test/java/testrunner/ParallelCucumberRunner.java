package testrunner;

import basepackage.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;


@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"
)


@Listeners(utilities.CustomReportListener.class)
public class ParallelCucumberRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
