package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.FeatureWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},
        glue = {"stepdefinitions"},
        tags = "@SmokeTest"
)
public class ParallelCucumberRunner extends AbstractTestNGCucumberTests {

    private String browser;
    private String runMode;


    public ParallelCucumberRunner(String browser, String runMode) {
        this.browser = browser;
        this.runMode = runMode;
    }

    @Factory(dataProvider = "browserProvider")
    public static Object[] createInstances(String browser, String runMode) {
        return new Object[] { new ParallelCucumberRunner(browser, runMode) };
    }

    @DataProvider(name = "browserProvider", parallel = true)
    public static Object[][] browserProvider() {
        return new Object[][] {
                {"chrome", "grid"},
                {"firefox", "grid"},
                {"edge", "grid"}
        };
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        System.setProperty("browser", browser);
        System.setProperty("runMode", runMode);
        super.runScenario(pickleWrapper, featureWrapper);
    }
}
