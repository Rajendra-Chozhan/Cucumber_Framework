package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;






@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"

)
public class BrowserRunner extends AbstractTestNGCucumberTests {

    private final String browser;

    public BrowserRunner(String browser) {
        this.browser = browser;
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        System.setProperty("browser", browser);
        return super.scenarios();
    }

    @Factory(dataProvider = "browserProvider")
    public static Object[] createInstances(String browser) {
        return new Object[]{ new BrowserRunner(browser) };
    }

    @DataProvider(name = "browserProvider")
    public static Object[][] browserProvider() {
        return new Object[][] {
                { "chrome" },
                { "firefox" },
                { "edge" }
        };
    }
}
