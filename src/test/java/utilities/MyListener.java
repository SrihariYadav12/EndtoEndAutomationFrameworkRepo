package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseClass;

public class MyListener implements ITestListener {
	String testName;
	ExtentReports extenreport;
	ExtentTest extenttest;

	@Override
	public void onStart(ITestContext context) {
		extenreport = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extenttest = extenreport.createTest(testName);
		extenttest.log(Status.INFO, testName + " Test Execution started successfully");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extenttest.log(Status.PASS, testName + " TestCase Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			extenttest.addScreenCaptureFromPath(BaseClass.captureScreenShot(testName));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		extenttest.log(Status.INFO, result.getThrowable());

		extenttest.log(Status.FAIL, testName + " TestCase Failed");

	}

	@Override
	public void onFinish(ITestContext context) {
		extenreport.flush();

		File reportfile = new File(System.getProperty("user.dir") + "//test-output//ExtentReports//extentreports.html");

		try {
			Desktop.getDesktop().browse(reportfile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
