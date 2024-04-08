package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class LoginPage extends BaseClass {

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
	private WebElement loginbutton;

	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
	private WebElement alerpopup;

	public void Login(String Username, String Password) {
		username.sendKeys(Username);
		password.sendKeys(Password);
	}

	public HomePage clickLoginButton() {
		loginbutton.click();
		return new HomePage();
	}

	public String getInvalidAlerttext() {
		String AlertText = alerpopup.getText();

		return AlertText;
	}

}
