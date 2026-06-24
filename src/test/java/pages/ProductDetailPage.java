package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestBasic;
import utils.SeleniumHelper;

public class ProductDetailPage extends TestBasic {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='product-information']/h2")
    private WebElement productName;
    @FindBy(xpath = "/p[contains(text(),'Category:')]")
    private WebElement productCategory;

    @FindBy(xpath = "//div[@class='product-information']//span[contains(text(),'Rs.')]")
    private WebElement productPrice;
    @FindBy(xpath = "//p[b[text()='Availability:']]")
    private WebElement productAvailability;
    @FindBy(xpath = "//p[b[text()='Condition:']]")
    private WebElement productCondition;
    @FindBy(xpath = "//p[b[text()='Brand:']]")
    private WebElement productBrand;
    @FindBy(id = "quantity")
    private WebElement quantityInput;
    @FindBy(xpath = "//button[normalize-space()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElement writeYourReview;

    @FindBy(id = "name")
    private WebElement yourNameInput;

    @FindBy(id = "email")
    private WebElement emailAddress;

    @FindBy(id = "review")
    private WebElement addReviewHere;

    @FindBy(id = "button-review")
    private WebElement submitButton;
    @FindBy()
    private WebElement successMessage;

    public ProductDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

        SeleniumHelper.waitForElementToBeVisible(driver,productName);
    }

    public WebElement getProductName() {

        return productName;
    }

    public WebElement getProductCategory() {
        return productCategory;
    }

    public WebElement getProductPrice() {
        return productPrice;
    }

    public WebElement getProductAvailability() {
        return productAvailability;
    }

    public WebElement getProductCondition() {
        return productCondition;
    }

    public WebElement getProductBrand() {
        return productBrand;
    }
}
