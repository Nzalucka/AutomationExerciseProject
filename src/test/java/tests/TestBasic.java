package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.BrowserManager;
import utils.PropertiesLoader;

import java.io.IOException;

public class TestBasic {

    protected static ThreadLocal <WebDriver> tdriver
        = new ThreadLocal<>();
    public static synchronized WebDriver getDriver(){
    return tdriver.get();
}
@BeforeMethod
    public void setUp() throws IOException {
        String url= PropertiesLoader.loadProperty("url");
       WebDriver driver= BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);

    HomePage homePage = new HomePage(getDriver());
    homePage.acceptCookiesIfPresent();
}
    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        tdriver.remove();
    }


}
