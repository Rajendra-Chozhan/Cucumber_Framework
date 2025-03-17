package stepdefinitions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

    public static void main(String [] args){

        String fullPathStr = "C:\\Users\\rgovindraj\\OneDrive - Deloitte (O365D)\\Desktop\\Automation projects\\Cucumber_Framework\\ExtentReports\\"+"SparkReport_17_Mar_25 10_09_13";
        Path fullPath = Paths.get(fullPathStr);
        Path fileName = fullPath.getFileName();
        System.out.println(fileName);

    }
}
