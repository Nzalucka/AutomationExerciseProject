package pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.TestBasic;
import utils.JSONReader;
import utils.SeleniumHelper;
import utils.Util;

import java.io.IOException;

public class EnterAccountInformationPage extends TestBasic {
    private WebDriver driver;

    @FindBy(xpath = "//h2[@class='title text-center' and normalize-space()='Enter Account Information']")
    private WebElement enterAccountInformation;

    @FindBy(id ="id_gender2")
    private WebElement titleMrsCheckbox;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daysSelect;

    @FindBy(id = "months")
    private WebElement monthsSelect;

    @FindBy(id = "years")
    private WebElement yearsSelect;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//button[@type='submit' and @data-qa='create-account']")
    private WebElement createAccountButton;



    public EnterAccountInformationPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public WebElement getEnterAccountInformation(){
        SeleniumHelper.waitForElementToBeVisible(driver,enterAccountInformation);
        return enterAccountInformation;
    }

    public AccountCreatedPage fillAccountDetails() throws IOException, ParseException {
    String password= "pass"+ Util.generateCurrentDateAndTime();

    titleMrsCheckbox.click();
    passwordInput.sendKeys(password);

    Select selectDay= new Select(daysSelect);
    selectDay.selectByValue(JSONReader.accountDetails("day"));

    Select selectMonth= new Select(monthsSelect);
    selectMonth.selectByValue(JSONReader.accountDetails("month"));

    Select selectYear= new Select(yearsSelect);
    selectYear.selectByValue(JSONReader.accountDetails("year"));

    newsletterCheckbox.click();
    specialOffersCheckbox.click();

    firstNameInput.sendKeys(JSONReader.accountDetails("firstName"));
    lastNameInput.sendKeys(JSONReader.accountDetails("lastName"));
    companyInput.sendKeys(JSONReader.accountDetails("company"));

    address1Input.sendKeys(JSONReader.accountDetails("address1"));
    address2Input.sendKeys(JSONReader.accountDetails("address2"));

    Select selectCountry= new Select(countrySelect);
    selectCountry.selectByValue(JSONReader.accountDetails("country"));


    stateInput.sendKeys(JSONReader.accountDetails("state"));
    cityInput.sendKeys(JSONReader.accountDetails("city"));
    zipcodeInput.sendKeys(JSONReader.accountDetails("zipcode"));
    mobileNumberInput.sendKeys(JSONReader.accountDetails("mobileNumber"));

    createAccountButton.click();

    return new AccountCreatedPage(driver);
    }

}
