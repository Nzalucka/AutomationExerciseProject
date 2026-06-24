package tests;

import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginSignupPage;

import java.io.IOException;

public class TestCase5 extends TestBasic{
    @Test(description = "Test Case 5: Register User with existing email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register User with existing email")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'New User Signup!' is visible
            6. Enter name and already registered email address
            7. Click 'Signup' button
            8. Verify error 'Email Address already exist!' is visible""")
    public void registerUserWithExistingEmail(){
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase1.verifyNewUserSignupIsVisible();
    }
    @Step("Verify error 'Email Address already exist!' is visible")
    public void verifyErrorEmailAddressAlreadyExistIsVisible() throws IOException, ParseException {
        String errorEmailAddressAlreadyExistText=new LoginSignupPage(getDriver())
                .fillIncorrectSignup()
                .getEmailAddressAlreadyExist().getText();
        Assert.assertEquals(errorEmailAddressAlreadyExistText, "Email Address already exist!",
                "Verify error 'Email Address already exist!' is visible");

    }

}
