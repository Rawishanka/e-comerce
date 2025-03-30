package com.pm.productservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.hamcrest.Matchers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    static GenericContainer<?> mongoDbContainer = new GenericContainer<>(DockerImageName.parse("mongo:7.0"))
            .withExposedPorts(27017)
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "password")
            .withCommand("--bind_ip_all");

    static {
        mongoDbContainer.start();
        System.setProperty("MONGO", String.valueOf(mongoDbContainer.getMappedPort(27017)));
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
    void testProductCreation() {
        String request = """
                {
                  "name": "string",
                  "description": "string",
                  "price": 0
                }
                """;
        RestAssured.given()
                .body(request)
                .contentType("application/json")
                .when()
                .post("/api/product")
                .then()
                .log().ifValidationFails()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("string"))
                .body("description", Matchers.equalTo("string"))
                .body("price", Matchers.equalTo(0));
    }
}
