package com.templatetasks.java.quarkus.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.templatetasks.java.quarkus.dto.Jedi;

import java.util.*;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 18:25
 */
@QuarkusTest
@DisplayName("RestController endpoints tests")
class RestControllerTest {

    @Test
    @DisplayName("Endpoint '/plain' test")
    void testPlainEndpoint() {
        String response = when()
                .get("/examples/quarkus/plain")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        UUID responseUuid = UUID.fromString(response);
        assertNotNull(responseUuid);
    }

    @Test
    @DisplayName("Endpoint '/jedi' test, Jedi exists")
    void testJediEndpoint() {
        Jedi response = when()
                .get("/examples/quarkus/jedi/{name}", "Obi-Wan")
                .then()
                .statusCode(200)
                .extract()
                .as(Jedi.class);

        assertNotNull(response);
        assertEquals("Obi-Wan Kenobi", response.getName());
        assertEquals("Jedi Master", response.getTitle());
    }

    @Test
    @DisplayName("Endpoint '/jedi' test, no Jedi found")
    void testNoJediEndpoint() {
        when()
                .get("/examples/quarkus/jedi/{name}", "Kirk")
                .then()
                .statusCode(404);
    }
}