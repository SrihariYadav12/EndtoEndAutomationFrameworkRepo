package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;

public class LoginPage extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement alertPopup;

    public LoginPage() { 
        PageFactory.initElements(getDriver(), this);
        logger.info("LoginPage initialized");
    }

    public HomePage login(String username, String password) {
        logger.info("Entering username: {}", username);
        this.username.sendKeys(username);
        logger.info("Entering password");
        this.password.sendKeys(password);
        logger.info("Clicking login button");
        loginButton.click();
        return new HomePage();
    }

    public String getInvalidAlertText() {
        try {
            String alertText = alertPopup.getText();
            logger.warn("Alert text retrieved: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to retrieve alert text", e);
            return null;
        }
    }
}
