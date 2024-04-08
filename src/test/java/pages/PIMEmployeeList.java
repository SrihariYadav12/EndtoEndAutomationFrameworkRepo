package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class PIMEmployeeList extends BaseClass {

	@FindBy(xpath = "//h6[text()='Personal Details']")
	private WebElement personaldetailstext;

	@FindBy(xpath = "//label[contains(text(),'License Number')]//parent::div//following-sibling::div//input")
	private WebElement LicenseNumber;

	@FindBy(xpath = "//label[text()='License Expiry Date']//parent::div//following-sibling::div//input")
	private WebElement LicenseExpiryDate;

	@FindBy(xpath = "//div[@class='oxd-calendar-selector-month-selected']")
	private WebElement licencemonthDropdown;

	@FindBy(xpath = "//ul[@class='oxd-calendar-dropdown']//li[text()='August']")
	private WebElement licenceselectmonth;

	@FindBy(xpath = "//div[@class='oxd-calendar-date-wrapper']//div[text()=2]")
	private WebElement licenceselecttargetDate;

	@FindBy(xpath = "//label[text()='Nationality']//parent::div//following-sibling::div//div")
	private WebElement nationalityheader;

	public PIMEmployeeList() {

		PageFactory.initElements(getDriver(), this);
	}

	public boolean validatepersonaldetailstext() {
		boolean validatetext = personaldetailstext.isDisplayed();
		return validatetext;
	}

	public void employeePersonalDetails(String licensenumber) {
		LicenseNumber.sendKeys(licensenumber);
		LicenseExpiryDate.click();
		licencemonthDropdown.click();
		licenceselectmonth.click();
		licenceselecttargetDate.click();

	}
}
