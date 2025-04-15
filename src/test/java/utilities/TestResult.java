package utilities;
public class TestResult {
    public int sno;
    public String name;
    public String status;
    public long duration;
    public String browser;
    public String screenshotPath;
    public String errorMessage;

    public TestResult(int sno, String name, String status, long duration, String browser, String screenshotPath, String errorMessage) {
        this.sno = sno;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.browser = browser;
        this.screenshotPath = screenshotPath;
        this.errorMessage = errorMessage;
    }

    public TestResult() {

    }
}

