package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SystemUsersPage;

public class NewAdminUserCreationTest extends BaseClass {
	LoginPage loginpage;
	HomePage homepage;
	AdminPage adminpage;
	SystemUsersPage systemuserspage;

	public NewAdminUserCreationTest() {
		super();

	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String browser) {
		intlizeBrowse(browser);

	}

	@Parameters("browser")
//	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
//	public void tearDown() {
//		driver.quit();
//	}

	@Test(priority = 1, groups = { "Sanity", "Regression" })
	public void createAdminUser() {
		loginpage = new LoginPage();
		loginpage.Login(prop.getProperty("Username"), prop.getProperty("Password"));
		homepage = loginpage.clickLoginButton();
		adminpage = homepage.clickonAdminTab();
		adminpage.clickOnAddoption();
		String NewAdminusername = randomestring();
		adminpage.NewUsercreation(prop.getProperty("EmployeeName"), NewAdminusername,
				prop.getProperty("NewAdminpassword"));
		SystemUsersPage systemuserspage = adminpage.clickonSave();
		Assert.assertTrue(systemuserspage.systemUsersassertion());
		systemuserspage.enterCreatedUser(NewAdminusername);
		systemuserspage.clickonSearchbutton();
		String ActualUserrecord = systemuserspage.verifyUserRecord();
		System.out.println(ActualUserrecord);
		String ExpectedUserrecord = NewAdminusername;
		Assert.assertEquals(ActualUserrecord, ExpectedUserrecord);
	}

}
