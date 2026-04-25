package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class GridMultiBrowserTest {



        public static void main(String[] args) {

            Runnable chromeTask = () -> {
                try {
                    DesiredCapabilities chromeCaps = new DesiredCapabilities();
                    chromeCaps.setBrowserName("chrome");

                    WebDriver chromeDriver = new RemoteWebDriver(
                            new URL("http://192.168.0.104:4444/wd/hub"), chromeCaps);

                    chromeDriver.get("https://www.google.com");
                    System.out.println("Chrome Title: " + chromeDriver.getTitle());
                    Thread.sleep(2000);
                    chromeDriver.quit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            Runnable edgeTask = () -> {
                try {
                    DesiredCapabilities edgeCaps = new DesiredCapabilities();
                    edgeCaps.setBrowserName("MicrosoftEdge");

                    WebDriver edgeDriver = new RemoteWebDriver(
                            new URL("http://192.168.0.104:4444/wd/hub"), edgeCaps);

                    edgeDriver.get("https://www.bing.com");
                    System.out.println("Edge Title: " + edgeDriver.getTitle());
                    Thread.sleep(2000);
                    edgeDriver.quit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            // Run both tasks in parallel threads
            Thread chromeThread = new Thread(chromeTask);
            Thread edgeThread = new Thread(edgeTask);

            chromeThread.start();
            edgeThread.start();
        }
    }

