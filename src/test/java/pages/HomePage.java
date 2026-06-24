package pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBasic;
import utils.JSONReader;
import utils.SeleniumHelper;

import javax.xml.xpath.XPath;
import java.io.IOException;
import java.time.Duration;

public class HomePage extends TestBasic {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='item active']//img[@alt='demo website for practice']")
    private WebElement girlImgResponsive;

    @FindBy(xpath = "//a[@href='/test_cases' and normalize-space()='Test Cases']")
    private WebElement testCasesButton;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginButton;

    @FindBy(xpath = "//a[@href='/contact_us' and text()=' Contact us']")
    private WebElement contactUsButton;

    //footer
    @FindBy(xpath = "//div[@class='single-widget']/h2")
    private WebElement subscription;
    @FindBy(id = "susbscribe_email")
    private WebElement subscribeEmailInput;
    @FindBy(id = "subscribe")
    private WebElement subscribeButton;
    @FindBy(id = "success-subscribe")
    private WebElement alertSuccessSubscribe;

    @FindBy(css = "a[href='/view_cart']")
    private WebElement cartButton;

    @FindBy(xpath = "//a[@href='/products' and text()=' Products']")
    private WebElement productsButton;

   @FindBy(xpath = "//div[@class='recommended_items'] /h2")
    private WebElement recommendedItems;

    @FindBy(css = "div[id='recommended-item-carousel'] a[class='btn btn-default add-to-cart']")
    private WebElement blueTopAddToCartButton;

    @FindBy(css = "div[class='modal-content'] a[href='/view_cart']")
    private WebElement viewCartButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement homePageIsVisible() {
        return girlImgResponsive;
    }

    public TestCasesPage testCasesButtonClick() {

        testCasesButton.click();
        return new TestCasesPage(driver);

    }
    public LoginSignupPage signupLoginClick(){
        signupLoginButton.click();
        return new LoginSignupPage(driver);
    }
    public ContactUsPage contactUsButtonClick(){
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public void acceptCookiesIfPresent() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
        const buttons = [...document.querySelectorAll('p.fc-button-label')];
        const accept = buttons.find(b => b.innerText.trim() === 'Zgadzam się');
        if (accept) accept.click();
    """);

    }
   public CartPage cartButtonClick(){
        cartButton.click();
        return new CartPage(driver);
   }

    public ProductsPage productsButtonClick() {
        productsButton.click();
        return new ProductsPage(driver);
    }

    //footer
    public WebElement getSubscription() {
        return subscription;
    }

   public HomePage fillSubscribe() throws IOException, ParseException {
        subscribeEmailInput.sendKeys(JSONReader.existingUser("email"));
       SeleniumHelper.waitForElementToBeClickable(driver,subscribeButton);
       subscribeButton.click();
        return this;
   }
    public WebElement getAlertSuccessSubscribe() {
        return alertSuccessSubscribe;
    }

   public WebElement getRecommendedItems() {return recommendedItems;
   }
    public HomePage blueTopAddToCartButtonClick() {
       /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
wait.until(ExpectedConditions.elementToBeClickable(blueTopAddToCartButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueTopAddToCartButton);*/
        SeleniumHelper.waitForElementToBeVisible(driver, blueTopAddToCartButton);
        SeleniumHelper.scrollToElement(driver, blueTopAddToCartButton);
        SeleniumHelper.waitForElementToBeClickable(driver, blueTopAddToCartButton);


        blueTopAddToCartButton.click();
        return this;
    }

    public CartPage viewCartButtonClick(){
       /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(viewCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewCartButton);
*/
        SeleniumHelper.waitForElementToBeVisible(driver,viewCartButton);
        SeleniumHelper.scrollToElement(driver,viewCartButton);
        SeleniumHelper.waitForElementToBeClickable(driver,viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }


}