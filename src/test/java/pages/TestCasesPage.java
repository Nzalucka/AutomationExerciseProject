package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumHelper;

import java.nio.channels.Selector;

public class TestCasesPage {
    private WebDriver driver;

    @FindBy(xpath = "//h2[@class='title text-center']/b[text()='Test Cases']")
    private WebElement testCases;

    public TestCasesPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public WebElement getTestCases(){
        SeleniumHelper.waitForElementToBeVisible(driver,testCases);
        return testCases;
    }

}
