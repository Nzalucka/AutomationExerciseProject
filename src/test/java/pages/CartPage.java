package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private WebDriver driver;

    @FindBy(xpath = "//td[contains(@class, 'cart_description')]//a")
    private List<WebElement> productName;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public List<String> getProductsNames() {
        return productName
                .stream()
                .map(WebElement::getText).
                collect(Collectors.toList());
    }
}

