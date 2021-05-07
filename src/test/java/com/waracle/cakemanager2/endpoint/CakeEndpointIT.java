package com.waracle.cakemanager2.endpoint;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class CakeEndpointIT {
    @Test
    public void shouldRetrieveAllCakesSuccessfully() {
        // @formatter:off
        given()
                .when()
                .get("http://localhost:8281/")
                .then()

                .statusCode(equalTo(200));
//                    .and()
//                    .cookie(SESSION_COOKIE_NAME, notNullValue());
//        }

    }
}