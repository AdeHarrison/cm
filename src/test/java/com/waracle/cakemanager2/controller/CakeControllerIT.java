package com.waracle.cakemanager2.controller;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;
//import static net.openathens.myathens.utils.TestUtils.SESSION_COOKIE_NAME;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

class CakeControllerIT {
    @Test
    public void shouldRetrieveAllCakesSuccessfully() {
        // @formatter:off
            given()
                    .when()
                    .get("http://localhost:8080/")
                    .then()

                    .statusCode(equalTo(200));
//                    .and()
//                    .cookie(SESSION_COOKIE_NAME, notNullValue());
//        }

    }
}