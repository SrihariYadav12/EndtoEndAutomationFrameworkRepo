package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class AdminPage extends BaseClass {

	public AdminPage() {
		PageFactory.initElements(getDriver(), this);

	}

	@FindBy(xpath = "//div[@class='orangehrm-header-container']//button")
	private WebElement addOption;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")
	private WebElement userroleoption;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")
	private WebElement selectstatusdropdwon;

	@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[2]/span")
	private WebElement userrole;

	@FindBy(xpath = "//div[contains(@class ,'oxd-autocomplete-text-input')]//input")
	private WebElement employeename;

	@FindBy(xpath = "//div[contains(@class ,'oxd-autocomplete-option')]/span")
	private WebElement employeenamedropdown;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div[2]")
	private WebElement status;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")
	private WebElement username;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")
	private WebElement password;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
	private WebElement confirmPassword;

	@FindBy(xpath = "//div[@class='oxd-form-actions']//button[2]")
	private WebElement save;

	public void clickOnAddoption() {
		addOption.click();
	}

	public void NewUsercreation(String employeeName, String NewUserName, String NewPassword) {
		userroleoption.click();
		userrole.click();

		employeename.sendKeys(employeeName);
		employeenamedropdown.click();
		selectstatusdropdwon.click();
		status.click();
		username.sendKeys(NewUserName);
		password.sendKeys(NewPassword);
		confirmPassword.sendKeys(NewPassword);
	}

	public SystemUsersPage clickonSave() {
		save.click();

		return new SystemUsersPage();
	}

}
