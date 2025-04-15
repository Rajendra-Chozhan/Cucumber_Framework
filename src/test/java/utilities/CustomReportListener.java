package utilities;

import java.io.FileWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.*;
import java.nio.file.*;

public class CustomReportListener implements ITestListener {

    public static List<TestResult> results;
    public static int counter = 1;

    public static void setResults(List<TestResult> testResults) {
        results = testResults;
    }


    public static void generateReport(List<TestResult> results) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dirPath = "/test-output/custom-report";
            new File(dirPath).mkdirs();
            String filePath = dirPath + "/summary_latest.html";

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write("<html><head><style>");
                writer.write("table { border-collapse: collapse; width: 100%; }");
                writer.write("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
                writer.write("th { background-color: #f2f2f2; }");
                writer.write("</style></head><body>");
                writer.write("<h3>Automation Test Summary - " + new Date() + "</h3>");
                writer.write("<table>");
                writer.write("<thead><tr><th>S.No</th><th>Test Case</th><th>Status</th><th>Time (ms)</th><th>Browser</th><th>Screenshot</th><th>Error</th></tr></thead>");
                writer.write("<tbody>");
                for (TestResult result : results) {
                    String color = result.status.equals("PASS") ? "green" : "red";
                    String screenshotLink = result.screenshotPath.equals("-")
                            ? "-"
                            : "<a href='" + result.screenshotPath + "' target='_blank'>View</a>";
                    writer.write(String.format(
                            "<tr><td>%d</td><td>%s</td><td style='color:%s;'>%s</td><td>%d</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                            result.sno, result.name, color, result.status,
                            result.duration, result.browser, screenshotLink,
                            result.errorMessage
                    ));
                }
                writer.write("</tbody></table></body></html>");
            }

            System.out.println("✅ HTML report generated at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
