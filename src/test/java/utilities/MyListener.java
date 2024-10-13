package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import base.BaseClass;

public class MyListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
    private String testName;
    private ExtentReports extenreport;
    private ExtentTest extenttest;
    

    @Override
    public void onStart(ITestContext context) {
        extenreport = ExtentReport.generateExtentReport();
        logger.info("Test execution started");
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        String[] tags = result.getMethod().getGroups(); // Get tags from the test method
        extenttest = ExtentReport.createTest(testName, tags); // Create test with tags
        extenttest.log(Status.INFO, testName + " Test Execution started successfully");
        logger.info("Test {} started", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extenttest.log(Status.PASS, testName + " TestCase Passed");
        logger.info("Test {} passed", testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extenttest.log(Status.INFO, result.getThrowable());
        extenttest.log(Status.SKIP, result.getName() + " got skipped");
        logger.warn("Test {} skipped: {}", testName, result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            extenttest.addScreenCaptureFromPath(BaseClass.captureScreenShot(testName));
        } catch (Throwable e) {
            logger.error("Failed to capture screenshot for test: {}", testName, e);
        }
        extenttest.log(Status.INFO, result.getThrowable());
        extenttest.log(Status.FAIL, testName + " TestCase Failed");
        logger.error("Test {} failed: {}", testName, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extenreport.flush();
        logger.info("Test execution finished, report generated.");

        File reportfile = new File(System.getProperty("user.dir") + "//test-output//ExtentReports//extentreports.html");
        try {
            Desktop.getDesktop().browse(reportfile.toURI());
        } catch (IOException e) {
            logger.error("Failed to open report: {}", reportfile.getAbsolutePath(), e);
        }
    }
}
