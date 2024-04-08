package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class HomePage extends BaseClass {

	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement DashBoardOption;

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement adminTab;

	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement PIMTab;

	public HomePage() {

		PageFactory.initElements(getDriver(), this);

	}

	public boolean verifyDashBoardOption() {

		boolean displayStatus = DashBoardOption.isDisplayed();

		return displayStatus;

	}

	public AdminPage clickonAdminTab() {
		adminTab.click();
		return new AdminPage();
	}

	public PIMPage clickOnPIMTab() {
		PIMTab.click();
		return new PIMPage();
	}

}
