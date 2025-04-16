package utilities;

public class TestResult {
    private int sno;                  // Serial number
    private String name;               // Test case name
    private String status;             // Test case status (PASS/FAIL)
    private String duration;           // Duration of the test
    private String browser;            // Browser used
    private String screenshotPath;     // Screenshot path if available
    private String errorMessage;       // Error message if test failed

    // Constructor
    public TestResult(int sno, String name, String status, String duration,
                      String browser, String screenshotPath, String errorMessage) {
        this.sno = sno;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.browser = browser;
        this.screenshotPath = screenshotPath;
        this.errorMessage = errorMessage;
    }

    // Getter methods
    public int getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDuration() {
        return duration;
    }

    public String getBrowser() {
        return browser;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
