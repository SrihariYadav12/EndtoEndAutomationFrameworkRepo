package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utilities.ReadExcelData;

@Listeners(utilities.MyListener.class)
public class LoginTestCase extends BaseClass {
	private static final Logger logger = LoggerFactory.getLogger(LoginTestCase.class);
	private LoginPage loginPage;

	public LoginTestCase() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String br) {
		intlizeBrowse(br);
		logger.info("Browser initialized: {}", br);
		loginPage = new LoginPage(); // Initialize the LoginPage here
	}

	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
		logger.info("Browser closed");
	}

	@Test(priority = 1, groups = { "Smoke", "Sanity",
			"Regression" }, dataProvider = "LoginData", dataProviderClass = ReadExcelData.class)
	public void successfulLogin(String userName, String password) {
		logger.info("Attempting to log in with username: {}", userName);
		HomePage homePage = loginPage.login(userName, password); // Use the login method
		Assert.assertTrue(homePage.verifyDashBoardOption(), "Dashboard option not verified");
		logger.info("Login successful for user: {}", userName);
	}

	@Test(priority = 2, groups = "Sanity")
	public void loginWithInvalidPassword() {
		String username = prop.getProperty("Username");
		String invalidPassword = prop.getProperty("Invalidpassword");
		logger.info("Attempting to log in with invalid password for user: {}", username);
		loginPage.login(username, invalidPassword); // Use the login method
		Assert.assertEquals(loginPage.getInvalidAlertText(), "Invalid credentials", "Alert text did not match");
		logger.info("Invalid login attempt verified.");
	}
}
