package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBasic;
import utils.SeleniumHelper;

public class AccountCreatedPage  {
    private WebDriver driver;
    @FindBy(xpath = "//h2[@class='title text-center' and normalize-space()='Account Created!']")
    private WebElement accountCreated;
    @FindBy(xpath = "//a[@data-qa='continue-button' and text()='Continue']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement getAccountCreated() {
        SeleniumHelper.waitForElementToBeVisible(driver,accountCreated);
        return accountCreated;
    }
    public LoggedHomePage continueButtonClick(){
        SeleniumHelper.safeClick(driver,continueButton);
        //continueButton.click();
        SeleniumHelper.closeAnyPopup(driver);
        return new LoggedHomePage(driver);
    }

}
