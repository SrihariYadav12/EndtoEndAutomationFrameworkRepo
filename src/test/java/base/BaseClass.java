package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

	// public static WebDriver driver;
	public static Properties prop;

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		// get driver from threadLocalmap
		return driver.get();
	}

	public BaseClass() {

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\Configuration\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void intlizeBrowse(String Browser) {
		if (Browser.equalsIgnoreCase("Chrome")) {
			// driver = new ChromeDriver();
			// set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		} else if (Browser.equalsIgnoreCase("Edge")) {
			// driver = new EdgeDriver();
			driver.set(new EdgeDriver());
		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("Url"));

	}

	public static String captureScreenShot(String testName) throws Throwable {
	    TakesScreenshot ts = (TakesScreenshot) getDriver();
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    // Create a timestamp
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String screenshotName = testName + "_" + timeStamp + ".png";
	    String path = System.getProperty("user.dir") + "/Screenshots/" + screenshotName;
	    
	    File target = new File(path);
	    FileUtils.copyFile(source, target);

	    return path;
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public String randomenumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(8);
		return (generatedNumber);
	}

	public void robotClass(String ImgPath) {
//		1)Copy Path of file
//		2)CTRL+V
//		3)Enter key on the keyboard

		try {
			Robot rb = new Robot();
			rb.delay(2000);

			// put path to file in a clipboard
			StringSelection ss = new StringSelection(ImgPath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// CTRL+V
			rb.keyPress(KeyEvent.VK_CONTROL);// press on CTRL key on your keyboard
			rb.keyPress(KeyEvent.VK_V);

			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);

			// ENTER
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

}
