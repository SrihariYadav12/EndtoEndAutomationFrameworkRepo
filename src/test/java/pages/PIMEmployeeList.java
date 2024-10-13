package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;

public class PIMEmployeeList extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(PIMEmployeeList.class);

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsText;

    @FindBy(xpath = "//label[contains(text(),'License Number')]//parent::div//following-sibling::div//input")
    private WebElement licenseNumber;

    @FindBy(xpath = "//label[text()='License Expiry Date']//parent::div//following-sibling::div//input")
    private WebElement licenseExpiryDate;

    @FindBy(xpath = "//div[@class='oxd-calendar-selector-month-selected']")
    private WebElement licenseMonthDropdown;

    @FindBy(xpath = "//ul[@class='oxd-calendar-dropdown']//li[text()='August']")
    private WebElement licenseSelectMonth;

    @FindBy(xpath = "//div[@class='oxd-calendar-date-wrapper']//div[text()='2']")
    private WebElement licenseSelectTargetDate;

    @FindBy(xpath = "//label[text()='Nationality']//parent::div//following-sibling::div//div")
    private WebElement nationalityHeader;

    public PIMEmployeeList() {
        PageFactory.initElements(getDriver(), this);
        logger.info("PIMEmployeeList initialized");
    }

    public boolean validatePersonalDetailsText() {
        boolean isDisplayed = personalDetailsText.isDisplayed();
        logger.info("Personal Details text is displayed: {}", isDisplayed);
        return isDisplayed;
    }

    public void enterEmployeePersonalDetails(String licenseNumberValue) {
        logger.info("Entering license number: {}", licenseNumberValue);
        licenseNumber.sendKeys(licenseNumberValue);
        licenseExpiryDate.click();
        logger.info("Selecting license expiry date");
        licenseMonthDropdown.click();
        licenseSelectMonth.click();
        licenseSelectTargetDate.click();
        logger.info("License expiry date selected");
    }

	
}
