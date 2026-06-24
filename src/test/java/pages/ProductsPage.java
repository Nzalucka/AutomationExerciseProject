package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumHelper;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static utils.SeleniumHelper.*;

public class ProductsPage {
    private WebDriver driver;
    @FindBy(css = ".title.text-center")
    private WebElement titleTextCenter;

    @FindBy(xpath = "//a[@href='/product_details/1']")
    private WebElement viewProductOfFirstProductButton;


    @FindBy(id = "search_product")
    private WebElement searchProductInput;

    @FindBy(id = "submit_search")
    private WebElement submitSearchInput;
    @FindBy(xpath = "//div[contains(@class, 'productinfo text-center')]//p")
    private List<WebElement> searchResultsNames;



    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getTitleTextCenter(){
        return titleTextCenter;
    }

    public ProductDetailPage viewProductOfFirstProductButtonClick() {
      ((JavascriptExecutor) driver)
              .executeScript("arguments[0].scrollIntoView(true);", viewProductOfFirstProductButton);
       viewProductOfFirstProductButton.click();
        return new ProductDetailPage(driver);
    }

    public ProductsPage fillSearchProductInput(String searchProduct){
        searchProductInput.sendKeys(searchProduct);
        submitSearchInput.click();

        return this;
    }

    public List<String>getProductsSearchNames(){
        return searchResultsNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

}
