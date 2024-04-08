package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMAddEmployeePage;
import pages.PIMEmployeeList;
import pages.PIMPage;

public class PIMAddEmployeeTest extends BaseClass {

	LoginPage loginpage;
	HomePage homepage;
	AdminPage adminpage;
	PIMEmployeeList pimemployeelist;

	public PIMAddEmployeeTest() {
		super();
	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setUp(String browser) {
		intlizeBrowse(browser);

	}

	@Parameters("browser")
	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
	}

	@Test(groups = { "Sanity", "Regression" })
	public void addEmplyee() throws Throwable {
		loginpage = new LoginPage();
		loginpage.Login(prop.getProperty("Username"), prop.getProperty("Password"));
		homepage = loginpage.clickLoginButton();
		PIMPage pimpage = homepage.clickOnPIMTab();
		PIMAddEmployeePage addempoyeepage = pimpage.clickOnAddEmployeeTab();
		addempoyeepage.clickOnImagePlusIcon();
		robotClass(prop.getProperty("ImagePath"));
		String generatenumber = randomenumber();
		addempoyeepage.enteremployeedetails(generatenumber);
		pimemployeelist = addempoyeepage.clickOnSave();
		Assert.assertTrue(pimemployeelist.validatepersonaldetailstext());
		pimemployeelist.employeePersonalDetails(generatenumber);

	}

}
