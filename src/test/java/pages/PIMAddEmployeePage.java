package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import base.BaseClass;

public class PIMAddEmployeePage extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(PIMAddEmployeePage.class);

    @FindBy(xpath = "//i[@class='oxd-icon bi-plus']")
    private WebElement imagePlusIcon;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")
    private WebElement employeeId;

    @FindBy(name = "firstName")
    private WebElement employeeFirstName;

    @FindBy(name = "lastName")
    private WebElement employeeLastName;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public PIMAddEmployeePage() {
        PageFactory.initElements(getDriver(), this);
        logger.info("PIMAddEmployeePage initialized");
    }

    public void clickOnImagePlusIcon() {
        logger.info("Clicking on the image plus icon");
        imagePlusIcon.click();
    }

    public void enterEmployeeDetails(String employeeIdValue) throws Throwable {
        logger.info("Entering employee details with ID: {}", employeeIdValue);
        employeeId.clear();
        Thread.sleep(1000); // Consider using WebDriverWait instead of Thread.sleep
        employeeId.sendKeys(employeeIdValue);
        employeeFirstName.sendKeys(prop.getProperty("employeefirstname"));
        employeeLastName.sendKeys(prop.getProperty("employeeLastname"));
        logger.info("Employee details entered: First Name - {}, Last Name - {}", 
                     prop.getProperty("employeefirstname"), 
                     prop.getProperty("employeeLastname"));
    }

    public PIMEmployeeList clickOnSave() {
        logger.info("Clicking on the save button");
        saveButton.click();
        return new PIMEmployeeList();
    }
}
