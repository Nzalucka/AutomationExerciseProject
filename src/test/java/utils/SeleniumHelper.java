package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }


    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3L));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void safeClick(WebDriver driver, WebElement element){
       closeAnyPopup(driver);

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForElementToBeClickable(driver, element);
            element.click();
        }
        catch (Exception e){
            // when somenthing went wrong , try again
            closeAnyPopup(driver);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForElementToBeClickable(driver, element);
            element.click();
        }
    }

    public static void closeAnyPopup(WebDriver driver) {
        List<By> possibleCloseButtons = Arrays.asList(
                By.xpath("//*[contains(@aria-label,'Close')]"),
                By.xpath("//*[contains(@id,'dismiss')]"), // np. starsze dismiss-button-element
                By.id("dismiss-button"),                   // nowy Close button
                By.xpath("//*[contains(@class,'close')]"),
                By.xpath("//div[contains(@class,'close-button')]")
        );
        for (By locator : possibleCloseButtons) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

                //List<WebElement> elements = driver.findElements(locator);
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        waitForElementToBeClickable(driver, element);
                        element.click();
                        Thread.sleep(100);
                        break;
                    }
                }
            } catch (TimeoutException ignored) {

            } catch (Exception ignored) {
            }
        }}

    public static void waitForPopupAndClose(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='modal'] | //div[contains(@class,'popup')]"))); // dopasuj XPath do popupu
            popup.findElement(By.xpath(".//button[contains(text(),'Close') or text()='×']")).click();
        } catch (Exception e) {
            // popup nie pojawił się → ok
        }
    }

    public static String waitForAlertAndAccept(WebDriver driver) {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
      Alert alert =wait.until(ExpectedConditions.alertIsPresent());
        String text=alert.getText();
        alert.accept();
        return text;

    }
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
