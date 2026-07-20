package api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvReader;

import static io.restassured.RestAssured.given;

public class SearchProductCsvTest extends ApiBaseTest{
    @Epic("AutomationExercise API")
    @Feature("Products")
    @DataProvider(name = "searchTerms")
    public Object [][]searchTerms ()throws Exception {
        return CsvReader.readSearchTerms();
    }
    @Test(dataProvider = "searchTerms")
    @Story("Products API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 5: POST search product — CSV DataProvider")
    public void searchProductCsv(String searchTerm, String expectedFirstProduct){

        Response response=
                given()
                        .baseUri("https://automationexercise.com/api")
                        .formParam("search_product",searchTerm)
                        .when().post("/searchProduct").then().
                        log().all()
        .extract().response();

     SoftAssertions soft=new SoftAssertions();
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getString("products[0].name"))
                .isEqualTo(expectedFirstProduct);
        soft.assertAll();
    }
    }
