package api;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("AutomationExercise API")
@Feature("Products")
public class ProductsApiTest extends ApiBaseTest {
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
                .body(matchesJsonSchemaInClasspath("schemas/products-schema.json"))
                .extract().response();

        validateProductsListResponse(response);

    }

    private void validateProductsListResponse(Response response) {
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(200);
        soft.assertThat(response.jsonPath().getList("products")).isNotEmpty();
        soft.assertThat(response.jsonPath().getList("products").size()).isEqualTo(34);
        soft.assertThat(response.jsonPath().getString("products[0].name")).isEqualTo("Blue Top");
        soft.assertThat(response.jsonPath().getInt("products[0].id")).isEqualTo(1);
        soft.assertThat(response.jsonPath().getString("products[0].brand")).isEqualTo("Polo");
        soft.assertThat(response.jsonPath().getString("products[0].category.category")).isEqualTo("Tops");
        soft.assertThat(response.header("Content-Type")).contains("text/html");
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
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(405);
        soft.assertThat(response.jsonPath().getString("message"))
                .isEqualTo("This request method is not supported.");
        soft.assertThat(response.header("Content-Type")).contains("text/html");
        soft.assertAll();
    }

    @Test
    @Story("Brands API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 3: Get all brands list")
    public void getAllBrandsList() {
        Response response = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when().get("/brandsList")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/brands-schema.json"))
                .extract().response();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(9000L);
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(200);
        soft.assertThat(response.jsonPath().getList("brands")).isNotEmpty();
        soft.assertThat(response.jsonPath().getString("brands[0].brand")).isEqualTo("Polo");
        soft.assertThat(response.header("Content-Type")).contains("text/html");
        soft.assertAll();
    }
    @Test
    @Story("Brands API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 4: PUT to all brands list — method not supported")
    public void putToAllBrandsList() {
        Response response = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when().put("/brandsList")
                .then()
                .extract().response();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(405);
        soft.assertThat(response.jsonPath().getString("message"))
                .isEqualTo("This request method is not supported.");
        soft.assertThat(response.header("Content-Type")).contains("text/html");
        soft.assertAll();
    }

    @Test
    @Story("Products API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 5: POST search product — formParam")
    public void searchProductFormParam() {
        Response response = given()
                .baseUri("https://automationexercise.com/api")
                .formParam("search_product", "top")
                .when().post("/searchProduct")
                .then()
                .log().all()
                .extract().response();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.statusCode()).isEqualTo(200);
        soft.assertThat(response.jsonPath().getString("responseCode")).isEqualTo("200");
        soft.assertThat(response.jsonPath().getList("products")).isNotEmpty();
        soft.assertThat(response.header("Content-Type")).contains("text/html");

        List<String> names = response.jsonPath().getList("products.name");
        soft.assertThat(names).anyMatch(n -> n.toLowerCase().contains("top"));

        soft.assertAll();

    }
    @Test
    @Story("Products API")
    @Severity(SeverityLevel.NORMAL)
    @Description("API 6: POST search product without parameter — 400")
    public void searchProductWithoutParameter() {
        Response response = given()
                .baseUri("https://automationexercise.com/api")
                .when().post("/searchProduct")
                .then()
                .extract().response();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response.time()).isLessThan(3000L);
        soft.assertThat(response.jsonPath().getInt("responseCode")).isEqualTo(400);
        soft.assertThat(response.jsonPath().getString("message"))
                .isEqualTo("Bad request, search_product parameter is missing in POST request.");
        soft.assertThat(response.header("Content-Type")).contains("text/html");
        soft.assertAll();
    }
}

