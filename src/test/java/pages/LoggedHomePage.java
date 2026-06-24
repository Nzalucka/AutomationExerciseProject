package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBasic;
import utils.SeleniumHelper;

public class LoggedHomePage extends TestBasic {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]/b")
    private WebElement username;
    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutButton;
    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountButton;


    public LoggedHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        SeleniumHelper.closeAnyPopup(driver);
    }

    public WebElement getUsername(){
       // SeleniumHelper.waitForPopupAndClose(driver);
        return username;
    }

    public LoginSignupPage logoutButtonClick(){
        logoutButton.click();
        return new LoginSignupPage(driver);
    }
    public AccountDeletedPage deleteAccountButtonClick(){
        deleteAccountButton.click();
        return new AccountDeletedPage(driver);
    }




}
