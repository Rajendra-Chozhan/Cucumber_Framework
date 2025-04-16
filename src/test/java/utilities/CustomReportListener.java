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

    public static void generateReport(List<TestResult> testResults, String reportFolderPath) throws IOException {
        // Generate a timestamp to append to the report file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFilePath = reportFolderPath + File.separator + "TestExecutionReport_" + timestamp + ".html";

        // File path for the latest report (which Jenkins or email could use)
        String latestReportPath = reportFolderPath + File.separator + "latest.html";

        // Build the HTML content for the report
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body style='font-family: Arial, sans-serif; line-height: 1.6;'>");
        htmlContent.append("<h1 style='color: #2c3e50; text-align: center;'>Test Execution Report</h1>");

        // Test summary section
        htmlContent.append("<h3 style='color: #2980b9;'>Test Execution Summary</h3>");
        htmlContent.append("<p><strong>Total Test Cases:</strong> ${TEST_COUNTS,total}</p>");
        htmlContent.append("<p><strong>Passed:</strong> ${TEST_COUNTS,pass}</p>");
        htmlContent.append("<p><strong>Failed:</strong> ${TEST_COUNTS,fail}</p>");
        htmlContent.append("<p><strong>Skipped:</strong> ${TEST_COUNTS,skip}</p>");
        htmlContent.append("<hr>");

        // Test case details table
        htmlContent.append("<h3 style='color: #2980b9;'>Test Case Details</h3>");
        htmlContent.append("<table style='width: 100%; border-collapse: collapse; margin: 20px 0;'>");

        // Header row with light orange background
        htmlContent.append("<thead>");
        htmlContent.append("<tr style='background-color: #f39c12; color: white;'>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>S.No</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Test Case Name</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Status</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Duration</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Browser</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Error Message</th>");
        htmlContent.append("<th style='padding: 10px; text-align: left;'>Screenshot</th>");
        htmlContent.append("</tr>");
        htmlContent.append("</thead>");

        // Body rows (test results)
        htmlContent.append("<tbody>");
        for (TestResult result : testResults) {
            // Set color for the status based on pass/fail/skip
            String statusColor = "";
            if (result.getStatus().equalsIgnoreCase("PASS")) {
                statusColor = "background-color: #2ecc71; color: white;"; // Green for pass
            } else if (result.getStatus().equalsIgnoreCase("FAIL")) {
                statusColor = "background-color: #e74c3c; color: white;"; // Red for fail
            } else if (result.getStatus().equalsIgnoreCase("SKIPPED")) {
                statusColor = "background-color: #f39c12; color: white;"; // Yellow for skipped
            }

            // Add test case row with status color
            htmlContent.append("<tr>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getSno() + "</td>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getName() + "</td>");
            htmlContent.append("<td style='" + statusColor + " padding: 8px;'>" + result.getStatus() + "</td>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getDuration() + "</td>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getBrowser() + "</td>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getErrorMessage() + "</td>");
            htmlContent.append("<td style='padding: 8px;'>" + result.getScreenshotPath() + "</td>");
            htmlContent.append("</tr>");
        }
        htmlContent.append("</tbody>");
        htmlContent.append("</table>");

        htmlContent.append("</body></html>");

        // Save the new report to a file with the timestamp
        try (FileWriter writer = new FileWriter(reportFilePath)) {
            writer.write(htmlContent.toString());
        }

        // Log the file path of the newly created report
        System.out.println("HTML Report generated at: " + reportFilePath);

        // Copy the newly generated report to the 'latest.html' for Jenkins or email to use
        File latestReportFile = new File(latestReportPath);
        if (latestReportFile.exists()) {
            latestReportFile.delete();  // Delete the old 'latest.html' if it exists
        }

        // Copy the new report to 'latest.html'
        File newReportFile = new File(reportFilePath);
        newReportFile.renameTo(latestReportFile);
        System.out.println("Latest report is now: " + latestReportPath);
    }

}
