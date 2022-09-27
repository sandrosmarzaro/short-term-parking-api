package one.digitalinnovation.shorttermparking.controllers;

import io.restassured.RestAssured;
import one.digitalinnovation.shorttermparking.dto.ParkingSpotRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingSpotControllerIT {

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
                .when()
                .get("/parking")
                .then()
                .header("Content-Type", "application/json")
                .statusCode(HttpStatus.OK.value())
                .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        ParkingSpotRequest request = new ParkingSpotRequest();
        request.setLicense("JKL-0987");
        request.setBrand("Hyundai");
        request.setModel("Elantra");
        request.setColor("Red");
        request.setState("RS");

        RestAssured
                .given()
                .body(request)
                .header("Content-Type", "application/json")
                .when()
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response().body().prettyPrint();
    }
}