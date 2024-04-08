package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class PIMAddEmployeePage extends BaseClass {

	public PIMAddEmployeePage() {

		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//i[@class='oxd-icon bi-plus']")
	private WebElement imagePlusicon;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")
	private WebElement employeeId;

	@FindBy(name = "firstName")
	private WebElement employeefirstName;

	@FindBy(name = "lastName")
	private WebElement employeelastName;

	@FindBy(xpath = " //button[@type='submit']")
	private WebElement Save;

	public void clickOnImagePlusIcon() {
		imagePlusicon.click();
	}

	public void enteremployeedetails(String employeeid) throws Throwable {
		employeeId.clear();
		Thread.sleep(1000);
		employeeId.sendKeys(employeeid);
		employeefirstName.sendKeys(prop.getProperty("employeefirstname"));
		employeelastName.sendKeys(prop.getProperty("employeeLastname"));
	}

	public PIMEmployeeList clickOnSave() {
		Save.click();
		return new PIMEmployeeList();
	}

}
