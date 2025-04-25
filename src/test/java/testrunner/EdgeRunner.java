package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"

)
public class EdgeRunner extends AbstractTestNGCucumberTests {


    @BeforeClass
    public void setUp() {
        System.setProperty("browser", "edge");
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
