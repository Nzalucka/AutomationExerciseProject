package api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


@Epic("AutomationExercise API")
@Feature("Login")
public class LoginApiTest extends ApiBaseTest{
    private static final String EMAIL = "testlogin123@test.com";
    private static final String PASSWORD = "test123";

    @Test
    @Story("Login API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("API 7: POST verify login with valid details. " +
            "User created manually via Terminal: " +
            "curl -X POST https://automationexercise.com/api/createAccount " +
            "-d name=TestUser&email=testlogin123@test.com&password=test123")

    public void verifyLoginWithValidDetails() {
        Response response = given()
                .baseUri("https://automationexercise.com/api")
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .when().post("/verifyLogin")
                .then()
                .extract().response();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(200);
        soft.assertThat(response.jsonPath().getString("message")).isEqualTo("User exists!");
        soft.assertAll();
    }

}
