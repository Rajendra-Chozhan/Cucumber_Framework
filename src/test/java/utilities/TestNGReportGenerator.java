package utilities;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGReportGenerator {

    public static void generateTestSummaryHtmlFromTestNG(String testngResultsPath, String outputDir) {
        try {
            // Load testng-results.xml
            File xmlFile = new File(testngResultsPath);
            if (!xmlFile.exists()) {
                System.out.println("TestNG results file not found at: " + testngResultsPath);
                return;
            }

            // Parse XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Extract attributes
            Element testngResults = (Element) doc.getElementsByTagName("testng-results").item(0);
            String total = testngResults.getAttribute("total");
            String passed = testngResults.getAttribute("passed");
            String failed = testngResults.getAttribute("failed");
            String skipped = testngResults.getAttribute("skipped");

            // Get timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String htmlFilename = "TestNG_Report_" + timestamp + ".html";

            // Build HTML content
            StringBuilder html = new StringBuilder();
            html.append("<html><head><style>")
                    .append("table { border-collapse: collapse; width: 50%; font-family: Arial; }")
                    .append("th, td { border: 1px solid #dddddd; text-align: center; padding: 8px; }")
                    .append("th { background-color: #f2f2f2; }")
                    .append("td.pass { color: green; font-weight: bold; }")
                    .append("td.fail { color: red; font-weight: bold; }")
                    .append("td.skip { color: orange; font-weight: bold; }")
                    .append("</style></head><body>");

            html.append("<p>Hi Team,</p>")
                    .append("<p>Good Morning.</p>")
                    .append("<p>Please find the status of Test Execution below:</p>")
                    .append("<table>")
                    .append("<tr><th>Total Test Cases</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr>")
                    .append("<tr>")
                    .append("<td>").append(total).append("</td>")
                    .append("<td class='pass'>").append(passed).append("</td>")
                    .append("<td class='fail'>").append(failed).append("</td>")
                    .append("<td class='skip'>").append(skipped).append("</td>")
                    .append("</tr>")
                    .append("</table><br>")
                    .append("<p>📝 Detailed report is attached.</p>")
                    .append("<p>Regards,<br>Automation Jenkins</p>")
                    .append("</body></html>");

            // Write to file
            File dir = new File(outputDir);
            if (!dir.exists()) dir.mkdirs();

            File reportFile = new File(dir, htmlFilename);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
                writer.write(html.toString());
            }

            System.out.println("HTML Summary Report generated at: " + reportFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
