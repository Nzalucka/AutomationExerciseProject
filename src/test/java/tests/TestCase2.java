package tests;

import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginSignupPage;
import utils.JSONReader;

import java.io.IOException;

public class TestCase2 extends TestBasic{
    @Test(description = "Test Case 2: Login User with correct email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login User with correct email and password")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter correct email address and password
            7. Click 'login' button
            8. Verify that 'Logged in as username' is visible""")
    public static void loginUserWithCorrectEmailAndPassword() throws IOException, ParseException {
    TestCase1.verifyThatHomePageIsVisibleSuccessfully();
    verifyLoginToYourAccountTxtIsVisible();
    verifyThatLoggedInAsUsernameIsVisible();
    }


    @Step("Verify 'Login to your account' is visible")
    public static void verifyLoginToYourAccountTxtIsVisible(){
    String loginToYourAccountText= new HomePage(getDriver())
            .signupLoginClick()
            .getLoginToYourAccount()
                    .getText();

    Assert.assertEquals(loginToYourAccountText,"Login to your account",
            "Verify 'Login to your account' is visible");
    }

    @Step("Verify that 'Logged in as username' is visible")
    private static void verifyThatLoggedInAsUsernameIsVisible() throws IOException, ParseException {
        String username= new LoginSignupPage(getDriver())
                .fillCorrectLogin(JSONReader.existingUser("email"),JSONReader.existingUser("password"))
                .getUsername().getText();

        Assert.assertEquals(username,JSONReader.existingUser("name"),
                "Verify that 'Logged in as username' is visible");

    }

}
