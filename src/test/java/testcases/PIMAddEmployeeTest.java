package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMAddEmployeePage;
import pages.PIMEmployeeList;
import pages.PIMPage;

public class PIMAddEmployeeTest extends BaseClass {
	private static final Logger logger = LoggerFactory.getLogger(PIMAddEmployeeTest.class);

	private LoginPage loginPage;
	private HomePage homePage;
	private AdminPage adminPage;
	private PIMEmployeeList pimEmployeeList;

	public PIMAddEmployeeTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String browser) {
		intlizeBrowse(browser);
		logger.info("Browser initialized: {}", browser);
		loginPage = new LoginPage(); // Initialize LoginPage
	}

	@Parameters("browser")
	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
		logger.info("Browser closed");
	}

	@Test(groups = { "Sanity", "Regression" })
	public void addEmployee() throws Throwable {
		logger.info("Logging in with credentials.");
		homePage = loginPage.login(prop.getProperty("Username"), prop.getProperty("Password"));

		logger.info("Navigating to PIM tab.");
		PIMPage pimPage = homePage.clickOnPIMTab();
		PIMAddEmployeePage addEmployeePage = pimPage.clickOnAddEmployeeTab();

		logger.info("Uploading employee image.");
		addEmployeePage.clickOnImagePlusIcon();
		robotClass(prop.getProperty("ImagePath")); // Assuming robotClass is properly defined

		String generatedNumber = randomenumber();
		logger.info("Entering employee details with number: {}", generatedNumber);
		addEmployeePage.enterEmployeeDetails(generatedNumber);

		logger.info("Saving employee details.");
		pimEmployeeList = addEmployeePage.clickOnSave();
		Assert.assertTrue(pimEmployeeList.validatePersonalDetailsText(), "Personal details validation failed.");

		logger.info("Verifying personal details for employee number: {}", generatedNumber);
		pimEmployeeList.enterEmployeePersonalDetails(generatedNumber);
		logger.info("Employee added and details verified successfully.");
	}
}
