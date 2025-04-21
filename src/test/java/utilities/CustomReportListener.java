package utilities;

import org.testng.ITestListener;

import java.io.FileWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.*;

public class CustomReportListener implements ITestListener {

    public static void generateReport(List<TestResult> testResults, String reportFolderPath) throws Exception {
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        String timestamp = new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date());
        String reportFilePath = reportFolderPath + File.separator + "TestExecutionReport_" + timestamp.replace(":", ".") + ".html";
        String latestReportPath = reportFolderPath + File.separator + "latest.html";

        int total = testResults.size();
        int passed = (int) testResults.stream().filter(r -> r.getStatus().equalsIgnoreCase("PASSED")).count();
        int failed = (int) testResults.stream().filter(r -> r.getStatus().equalsIgnoreCase("FAILED")).count();
        int skipped = (int) testResults.stream().filter(r -> r.getStatus().equalsIgnoreCase("SKIPPED")).count();
        double passPercentage = total == 0 ? 0.0 : (passed * 100.0 / total);

        DecimalFormat df = new DecimalFormat("0.00");

        StringBuilder html = new StringBuilder();
        html.append("<html><body style='font-family: Arial;'>");
//        html.append("<h2>Hi,</h2>");
//        html.append("<p>Please find the below Test execution report</p>");
        html.append("<h2 style='color: #2c3e50;'>Test Execution Report for ").append(timestamp).append("</h2>");

        // Summary Table
        html.append("<h3>Test Execution Summary</h3>");
        html.append("<table border='1' style='border-collapse: collapse; width: 50%; text-align: center;'>");
        html.append("<tr><th>Total Test Cases</th><td style='font-weight: normal;'>").append(total).append("</td></tr>");
        html.append("<tr><th>Passed</th><td style='color: green; font-weight: normal;'>").append(passed).append("</td></tr>");
        html.append("<tr><th>Failed</th><td style='color: red; font-weight: normal;'>").append(failed).append("</td></tr>");
        html.append("<tr><th>Skipped</th><td style='color: orange; font-weight: normal;'>").append(skipped).append("</td></tr>");
        html.append("<tr><th>Pass Percentage</th><td style='font-weight: normal;'>").append(df.format(passPercentage)).append(" %</td></tr>");
        html.append("</table><br>");

        // Details Table
        html.append("<h3>Test Case Details</h3>");
        html.append("<table border='1' style='border-collapse: collapse; width: 100%; text-align: center;'>");
        html.append("<tr style='background-color: #f39c12; color: white;'>");
        html.append("<th>S.No</th><th>Test Case Name</th><th>Status</th><th>Duration (min)</th><th>Browser</th><th>Error Message</th></tr>");

        for (TestResult result : testResults) {
            double durationMin = result.getDuration() / (60.0 * 1000.0);
            String statusColor;
            switch (result.getStatus().toUpperCase()) {
                case "PASSED":
                    statusColor = "green";
                    break;
                case "FAILED":
                    statusColor = "red";
                    break;
                case "SKIPPED":
                    statusColor = "orange";
                    break;
                default:
                    statusColor = "black";
            }

            html.append("<tr>");
            html.append("<td>").append(result.getSno()).append("</td>");
            html.append("<td>").append(result.getName()).append("</td>");  // ✅ Real Scenario Name
            html.append("<td style='color: ").append(statusColor).append("; font-weight: bold;'>").append(result.getStatus()).append("</td>");
            html.append("<td>").append(df.format(durationMin)).append("</td>");
            html.append("<td>").append(result.getBrowser()).append("</td>");
            html.append("<td>").append(result.getErrorMessage() != null ? result.getErrorMessage() : "").append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        // Write to file
        try (FileWriter writer = new FileWriter(reportFilePath)) {
            writer.write(html.toString());
        }

        // Copy to latest.html
        File latest = new File(latestReportPath);
        if (latest.exists()) latest.delete();
        new File(reportFilePath).renameTo(latest);
    }
}
