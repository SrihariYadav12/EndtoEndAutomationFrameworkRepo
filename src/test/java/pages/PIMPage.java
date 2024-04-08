package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class PIMPage extends BaseClass {

	@FindBy(xpath = "//a[text()='Add Employee']")
	private WebElement addEmployeeTab;

	public PIMPage() {

		PageFactory.initElements(getDriver(), this);

	}

	public PIMAddEmployeePage clickOnAddEmployeeTab() {
		addEmployeeTab.click();
		return new PIMAddEmployeePage();
	}

}
