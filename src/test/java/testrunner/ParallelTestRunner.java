package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.FeatureWrapper;
import org.testng.annotations.*;
import stepdefinitions.Hooks;

@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"

)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {

    @DataProvider(name = "scenariosWithBrowser", parallel = true)
    public Object[][] scenariosWithBrowser() {
        Object[][] scenarios = super.scenarios();
        String[] browsers = new String[]{"chrome", "firefox"};

        Object[][] expanded = new Object[scenarios.length * browsers.length][3];

        int index = 0;
        for (String browser : browsers) {
            for (Object[] scenario : scenarios) {
                expanded[index][0] = scenario[0]; // PickleWrapper
                expanded[index][1] = scenario[1]; // FeatureWrapper
                expanded[index][2] = browser;
                index++;
            }
        }

        return expanded;
    }

    @Test(dataProvider = "scenariosWithBrowser")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper, String browser) throws Throwable {
        Hooks.browserName.set(browser); // set browser for current thread
        super.runScenario(pickleWrapper, featureWrapper);
    }
}