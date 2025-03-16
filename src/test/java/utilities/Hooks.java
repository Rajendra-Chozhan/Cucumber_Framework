package utilities;


import basepackage.BaseClass;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks extends BaseClass {


    @BeforeAll
    public static void before(){

        logger.info("Before all");
    }


    @AfterAll
    public static void after(){

        logger.info("After all");
    }
}
