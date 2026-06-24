package pages;


import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBasic;
import utils.JSONReader;

import javax.xml.xpath.XPath;
import java.io.IOException;

public class LoginSignupPage extends TestBasic {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='login-form']/h2[text()='Login to your account']")
    private WebElement loginToYourAccount;

    @FindBy(xpath = "//input[@type='email'][@data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy(xpath = "//input[@type='password'][@data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@type='submit'][@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='signup-form']/h2[text()='New User Signup!']")
    private WebElement newUserSignup;

    @FindBy(xpath = "//input[@type='text'][@data-qa='signup-name']")
    private WebElement signupNameInput;

    @FindBy(xpath = "//input[@type='email'][@data-qa='signup-email']")
    private WebElement signupEmailInput;

    @FindBy(xpath = "//button[@type='submit'][@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    private WebElement errorLogin;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    private WebElement emailAddressAlreadyExist;

    public LoginSignupPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement getLoginToYourAccount(){
    return loginToYourAccount;
}

    private void fillLogin(String email, String password){
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginButton.click();
    }
    public LoggedHomePage fillCorrectLogin(String email, String password){
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginButton.click();
        return new LoggedHomePage(driver);
    }
    public LoginSignupPage fillIncorrectLogin(String email, String password){
        fillLogin(email,password);
        return this;
    }
    public WebElement getNewUserSignup() {
        return newUserSignup;
    }
    private void fillSignup(String name, String email){
        signupNameInput.sendKeys(name);
        signupEmailInput.sendKeys(email);
        signupButton.click();
    }
    public EnterAccountInformationPage fillCorrectSignup(String name, String email){
        fillSignup(name, email);
        return new EnterAccountInformationPage(driver);
    }

    public LoginSignupPage fillIncorrectSignup() throws IOException, ParseException {
        fillSignup(JSONReader.existingUser("name"),JSONReader.existingUser("email"));
        return this;
    }
    public WebElement getErrorLogin(){
        return errorLogin;
    }
    public WebElement getEmailAddressAlreadyExist() {
        return emailAddressAlreadyExist;
    }
}
