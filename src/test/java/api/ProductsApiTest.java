package api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductsApiTest extends ApiBaseTest{
    @Test
    @Story("Products API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("API 1: Get all products list")
    public void getAllProductsList() {
            Response response = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when().get("/productsList")
                    .then()
                    .log().all()
                    .extract().response();

    validateProductsListResponse(response);

    }
    private void validateProductsListResponse(Response response){
        SoftAssertions soft=new SoftAssertions();
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(200);
        soft.assertThat(response.jsonPath().getList("products")).isNotEmpty();
        soft.assertThat(response.jsonPath().getList("products").size()).isEqualTo(34);
        soft.assertThat(response.jsonPath().getString("products[0].name")).isEqualTo("Blue Top");
        soft.assertThat(response.jsonPath().getInt("products[0].id")).isEqualTo(1);
        soft.assertThat(response.jsonPath().getString("products[0].brand")).isEqualTo("Polo");
        soft.assertThat(response.jsonPath().getString("products[0].category.category")).isEqualTo("Tops");
        soft.assertAll();
    }
    @Test
    @Story("Products API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 2: POST to all products list — method not supported")
    public void postToAllProductsList() {
        Response response = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when().post("/productsList")
                .then()
                .log().all()
                .extract().response();
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(405);
        soft.assertThat(response.jsonPath().getString("message"))
                .isEqualTo("This request method is not supported.");
        soft.assertAll();
    }

    }


