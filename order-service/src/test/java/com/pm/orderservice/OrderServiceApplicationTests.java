package com.pm.orderservice;

import com.pm.orderservice.client.InventoryClient;
import com.pm.orderservice.stubs.InventoryClientStubs;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {
    static GenericContainer<?> mySqlContainer = new GenericContainer<>(DockerImageName.parse("mysql:9.2"))
            .withExposedPorts(3306)
            .withEnv("MYSQL_ROOT_USER", "root")
            .withEnv("MYSQL_ROOT_PASSWORD", "password");
    static {
        mySqlContainer.start();
        System.setProperty("MYSQL_PORT", mySqlContainer.getMappedPort(3306).toString());
        System.out.println("MySQL running on port: " + System.getProperty("MYSQL_PORT"));
    }

    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;

        System.out.println("Running tests on: " + RestAssured.baseURI + ":" + port);
    }
    @Test
    void contextLoads() {
        InventoryClientStubs.inventoryGetCall("SKU008",10);
        String request = """
                {
                  "orderNumber": "string",
                  "skuCode": "SKU008",
                  "price": 10,
                  "quantity": 10
                }
                """;
        RestAssured.given()
                .body(request)
                .contentType("application/json")
                .when()
                .log().ifValidationFails()
                .log().all()
                .post("/api/order")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("orderNumber", Matchers.equalTo("string"))
                .body("skuCode", Matchers.equalTo("SKU008"))
                .body("quantity", Matchers.equalTo(10));
    }

}
