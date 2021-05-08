package com.waracle.cakemanager2.endpoint;

import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CakeEndpointIT {

    private final String URL = "http://localhost:8281";

    @Test
    public void shouldRetrieveAllCakesSuccessfully() {
        int expectedSize = 5;

        // @formatter:off
        List<Map<String, Object>> actual =
                given()
                        .when()
                        .get(URL)
                        .then()
                        .contentType(ContentType.JSON)
                        .and()
                        .statusCode(200)
                        .and()
                        .extract()
                        .body()
                        .as(new TypeRef<List<Map<String, Object>>>() {
                        });
        // @formatter:on

        assertThat(actual.size(), equalTo(expectedSize));
    }
}