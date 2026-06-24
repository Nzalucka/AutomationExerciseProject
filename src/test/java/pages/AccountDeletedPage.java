package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBasic;
import utils.SeleniumHelper;

public class AccountDeletedPage extends TestBasic {
    private WebDriver driver;
    @FindBy(xpath = "//a[@data-qa='continue-button' and text()='Continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeleted;

    public AccountDeletedPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement getAccountDeleted() {
        SeleniumHelper.waitForElementToBeVisible(driver,accountDeleted);
        return accountDeleted;
    }
    public HomePage continueButtonClick(){
        SeleniumHelper.safeClick(driver,continueButton);
       // continueButton.click();
        SeleniumHelper.closeAnyPopup(driver);
        return new HomePage(driver);
    }


}
