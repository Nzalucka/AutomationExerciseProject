package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {
    public static WebDriver doBrowserSetup(){
        ChromeOptions chromeOption=new ChromeOptions();
        chromeOption.addArguments("--incognito");
        chromeOption.addArguments("--start-maximized");
        chromeOption.addArguments("--disable-extensions");

        return new ChromeDriver(chromeOption);
    }

}
