package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static final Logger logger = LoggerFactory.getLogger(ExtentReport.class);
    private static ExtentReports extentreporter;

    public static ExtentReports generateExtentReport() {
        if (extentreporter == null) {
            extentreporter = new ExtentReports();
            File reportfile = new File(
                    System.getProperty("user.dir") + "//test-output//ExtentReports//extentreports.html");
            ExtentSparkReporter sparkreporter = new ExtentSparkReporter(reportfile);
            sparkreporter.config().setTheme(Theme.DARK);
            sparkreporter.config().setDocumentTitle("Orange HRM");
            sparkreporter.config().setReportName("Orange HRM Automation Report");
            sparkreporter.config().setTimeStampFormat("dd-MMM-yyyy hh:mm:ss");
            extentreporter.attachReporter(sparkreporter);

            Properties prop = new Properties();
            File f = new File("./Configuration/Config.properties");
            try {
                FileInputStream fis = new FileInputStream(f);
                prop.load(fis);
            } catch (IOException e) {
                logger.error("Failed to load properties file", e);
            }
            extentreporter.setSystemInfo("Application Url", prop.getProperty("Url"));
            extentreporter.setSystemInfo("BrowserName", prop.getProperty("Browser"));
            extentreporter.setSystemInfo("Operating System", System.getProperty("os.name"));
            extentreporter.setSystemInfo("Username", System.getProperty("user.name"));
            extentreporter.setSystemInfo("Java Version", System.getProperty("java.version"));
            logger.info("ExtentReport generated");
        }
        return extentreporter;
    }

    public static ExtentTest createTest(String testName, String... tags) {
        ExtentTest test = extentreporter.createTest(testName);
        if (tags.length > 0) {
            test.assignCategory(tags); // Assign tags to the test
        }
        logger.info("Test created: {}", testName);
        return test;
    }
}
