package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class SystemUsersPage extends BaseClass {
	String createuser;

	public SystemUsersPage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//div[@class ='oxd-input-group oxd-input-field-bottom-space']//div[2]/input")
	private WebElement EnterCreatedUser;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement search;

	@FindBy(xpath = "//div[@class='oxd-table-card']//div//div[2]")
	private WebElement verifyuserRecord;

	@FindBy(xpath = "//div[@class='oxd-table-filter-header-title']//h5")
	private WebElement systemUsersassertion;

	public boolean systemUsersassertion() {
		boolean systemusers = systemUsersassertion.isDisplayed();

		return systemusers;
	}

	public void enterCreatedUser(String createdAdminUser) {
		EnterCreatedUser.sendKeys(createdAdminUser);
	}

	public void clickonSearchbutton() {
		search.click();
	}

	public String verifyUserRecord() {
		String username = verifyuserRecord.getText();

		return username;
	}

}
