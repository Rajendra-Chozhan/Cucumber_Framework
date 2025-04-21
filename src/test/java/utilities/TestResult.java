package utilities;

public class TestResult {
    private int sno;
    private String name;
    private String status;
    private long duration; // in milliseconds
    private String browser;
    private String errorMessage;

    public TestResult(int sno, String name, String status, long duration, String browser, String errorMessage) {
        this.sno = sno;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.browser = browser;
        this.errorMessage = errorMessage;
    }

    // Getters and Setters
    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
