package one.digitalinnovation.shorttermparking.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import one.digitalinnovation.shorttermparking.dto.ParkingSpotRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingSpotControllerIT extends AbstractContainerBase {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = port;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured
                .given()
                .auth()
                .basic("admin", "admin")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .when()
                .get("/parking")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        ParkingSpotRequest request = new ParkingSpotRequest();
        request.setLicense("ABC-123");
        request.setBrand("Toyota");
        request.setModel("Corolla");
        request.setColor("Black");
        request.setState("SP");

        RestAssured
                .given()
                .auth()
                .basic("admin", "admin")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("ABC-123"))
                .body("brand", Matchers.equalTo("Toyota"))
                .body("model", Matchers.equalTo("Corolla"))
                .body("color", Matchers.equalTo("Black"))
                .body("state", Matchers.equalTo("SP"))
                .extract().response().body().prettyPrint();
    }
}