package testrunner;




import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-reports/report.html","json:target/cucumber-reports/report.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        features = {"src/main/resources/Features"},		glue= {"stepdefinitions"},
        tags = "@SmokeTest"


)
public class TestNGrunner extends AbstractTestNGCucumberTests {

//    @DataProvider(parallel = true)
//    @Override
//    public Object[][] scenarios(){
//
//        return super.scenarios();
//    }

}