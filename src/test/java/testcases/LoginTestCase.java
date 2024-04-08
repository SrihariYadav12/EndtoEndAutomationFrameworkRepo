package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

@Listeners(utilities.MyListener.class)
public class LoginTestCase extends BaseClass {
	LoginPage loginpage;

	public LoginTestCase() {
		super();

	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String br ) {
		intlizeBrowse(br);

	}
	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
	}

	@Test(priority = 1, groups = { "Smoke", "Sanity", "Regression" })
	public void successfulLogin() {
		loginpage = new LoginPage();
		loginpage.Login(prop.getProperty("Username"), prop.getProperty("Password"));
		HomePage homePage = loginpage.clickLoginButton();
		Assert.assertTrue(homePage.verifyDashBoardOption());
	}

	@Test(priority = 2, groups = "Sanity")
	public void LoginwithInvalidpassword() {
		loginpage = new LoginPage();
		loginpage.Login(prop.getProperty("Username"), prop.getProperty("Invalidpassword"));
		loginpage.clickLoginButton();
		Assert.assertEquals(loginpage.getInvalidAlerttext(), "Invalid credentials", "Not Matched Alert text");
	}

}
