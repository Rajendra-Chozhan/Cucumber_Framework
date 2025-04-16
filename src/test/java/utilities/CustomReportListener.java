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
        // Ensure the custom report folder exists or create it
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) {
            boolean created = reportFolder.mkdirs();  // Create directories if they don't exist
            if (created) {
                System.out.println("Created directory: " + reportFolderPath);
            }
        }

        // Generate a timestamp to append to the report file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFilePath = reportFolderPath + File.separator + "TestExecutionReport_" + timestamp + ".html";

        // File path for the latest report (which Jenkins or email could use)
        String latestReportPath = reportFolderPath + File.separator + "latest.html";

        // Build the HTML content for the report with inline CSS
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head><style>");
        htmlContent.append("table { border-collapse: collapse; width: 100%; }");
        htmlContent.append("th, td { padding: 8px; text-align: left; border: 1px solid #ddd; }");
        htmlContent.append("th { background-color: #FFCC99; }"); // Light orange header
        htmlContent.append(".pass { background-color: #90EE90; }"); // Green for pass
        htmlContent.append(".fail { background-color: #FFCCCB; }"); // Red for fail
        htmlContent.append(".skip { background-color: #FFFF99; }"); // Yellow for skip
        htmlContent.append("</style></head><body>");
        htmlContent.append("<h1>Test Execution Report</h1>");
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>S.No</th><th>Test Case Name</th><th>Status</th><th>Duration</th><th>Browser</th><th>Error Message</th><th>Screenshot</th></tr>");

        // Loop through the test results and add rows to the table
        for (TestResult result : testResults) {
            String statusClass = "";
            if (result.getStatus().equalsIgnoreCase("PASS")) {
                statusClass = "pass";  // Green for pass
            } else if (result.getStatus().equalsIgnoreCase("FAIL")) {
                statusClass = "fail";  // Red for fail
            } else if (result.getStatus().equalsIgnoreCase("SKIP")) {
                statusClass = "skip";  // Yellow for skip
            }

            htmlContent.append("<tr class='" + statusClass + "'>");
            htmlContent.append("<td>").append(result.getSno()).append("</td>");  // Serial Number
            htmlContent.append("<td>").append(result.getName()).append("</td>");  // Test Case Name
            htmlContent.append("<td>").append(result.getStatus()).append("</td>");  // Status (PASS/FAIL)
            htmlContent.append("<td>").append(result.getDuration()).append("</td>");  // Duration
            htmlContent.append("<td>").append(result.getBrowser()).append("</td>");  // Browser used
            htmlContent.append("<td>").append(result.getErrorMessage()).append("</td>");  // Error Message (if failed)
            htmlContent.append("<td>").append(result.getScreenshotPath()).append("</td>");  // Screenshot Path (if failed)
            htmlContent.append("</tr>");
        }

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
