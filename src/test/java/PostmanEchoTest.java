import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostmanEchoTest {

    @Test
    public void whenGet_thenStatus200() {

        given()
                .baseUri("https://postman-echo.com")
        .when()
                .get("/get")
        .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void whenCreateValue_thenStatus200() {

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body("{\"test\":\"value\"}")
        .when()
                .post("/post")
        .then()
                .log().body()
                .statusCode(200)
                .body("data.test", equalTo("value"));
    }

    @Test
    public void whenCreateBar_thenStatus200() {

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON)
                .body("{\"foo1\":\"bar1\", \"foo2\":\"bar2\"}")
        .when()
                .post("/post")
        .then()
                .log().body()
                .statusCode(200)
                .body("data.foo1", equalTo("bar1"))
                .body("data.foo2", equalTo("bar2"));
    }

    @Test
    public void whenPutRequestBody_thenStatus200() {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.TEXT)
                .body(requestBody)
        .when()
                .put("/put")
        .then()
                .log().body()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void whenPatchRequestBody_thenStatus200() {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.TEXT)
                .body(requestBody)
        .when()
                .patch("/patch")
        .then()
                .log().body()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void whenDeleteRequestBody_thenStatus200() {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.TEXT)
                .body(requestBody)
        .when()
                .delete("/delete")
        .then()
                .log().body()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }
}
