package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports generateExtentReport() {
		ExtentReports extentreporter = new ExtentReports();

		File reportfile = new File(System.getProperty("user.dir") + "//test-output//ExtentReports//extentreports.html");

		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(reportfile);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setDocumentTitle("Orange HRM");
		sparkreporter.config().setReportName("Orange HRM Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM//yyyy hh:mm:ss");
		extentreporter.attachReporter(sparkreporter);

		Properties prop = new Properties();
		File f = new File("./Configuration/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentreporter.setSystemInfo("Application Url", prop.getProperty("Url"));
		extentreporter.setSystemInfo("BrowserName", prop.getProperty("Browser"));
		extentreporter.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreporter.setSystemInfo("Username", System.getProperty("user.name"));
		extentreporter.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentreporter;
	}

}
