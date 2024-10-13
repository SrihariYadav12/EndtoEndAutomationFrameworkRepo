package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SystemUsersPage;

public class NewAdminUserCreationTest extends BaseClass {
	private static final Logger logger = LoggerFactory.getLogger(NewAdminUserCreationTest.class);

	private LoginPage loginPage;
	private HomePage homePage;
	private AdminPage adminPage;
	private SystemUsersPage systemUsersPage;

	public NewAdminUserCreationTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String browser) {
		intlizeBrowse(browser);
		logger.info("Browser initialized: {}", browser);
		loginPage = new LoginPage(); // Initialize the LoginPage here
	}

	@Test(priority = 1, groups = { "Sanity", "Regression" })
	public void createAdminUser() {
		logger.info("Logging in with admin credentials.");
		homePage = loginPage.login(prop.getProperty("Username"), prop.getProperty("Password"));

		logger.info("Navigating to Admin page.");
		adminPage = homePage.clickonAdminTab();
		adminPage.clickOnAddoption();

		String newAdminUsername = randomestring();
		logger.info("Creating new admin user with username: {}", newAdminUsername);

		adminPage.NewUsercreation(prop.getProperty("EmployeeName"), newAdminUsername,
				prop.getProperty("NewAdminpassword"));

		systemUsersPage = adminPage.clickonSave();
		Assert.assertTrue(systemUsersPage.systemUsersassertion(), "System users assertion failed.");

		logger.info("Searching for the created user: {}", newAdminUsername);
		systemUsersPage.enterCreatedUser(newAdminUsername);
		systemUsersPage.clickonSearchbutton();

		String actualUserRecord = systemUsersPage.verifyUserRecord();
		logger.info("Actual user record retrieved: {}", actualUserRecord);

		Assert.assertEquals(actualUserRecord, newAdminUsername, "User records do not match.");
		logger.info("New admin user created and verified successfully: {}", newAdminUsername);
	}
}
