package com.waracle.cakemanager2.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class CakeEndpointIT {

    private final String URL = "http://localhost:8281";

    @Autowired
    private CakeRepository cakeRepository;

    @BeforeEach()
    public void setup() {
        cakeRepository.deleteAll();
    }

    @Test
    public void shouldReturnAllCakes() {
        int expectedSize = 2;

        loadTestCakes();

        // @formatter:off
        List<Map<String, Object>> actual =
                    given()
                    .when()
                        .get(URL)
                    .then()
                        .contentType(ContentType.JSON)
                    .and()
                    .   statusCode(200)
                    .and()
                        .extract()
                            .body()
                                .as(new TypeRef<>() {
                    });
        // @formatter:on

        assertThat(actual.size(), equalTo(expectedSize));
    }

    @Test
    public void shouldCreateCakeWithGeneratedEmployeeId() throws JsonProcessingException {
        String testCakeBody = "{\"title\": \"title1\", \"description\": \"description1\", \"image\": \"image1\"}";
        CakeDTO expected = new CakeDTO(-1, "title1", "description1", "image1");

        List<Cake> l = cakeRepository.findAll();

        // @formatter:off
        String bodyAsString =
                given()
                .when()
                    .contentType(ContentType.JSON)
                    .body(testCakeBody)
                    .post(URL + "/cakes")
               .then()
                    .contentType(ContentType.JSON)
                .and()
                    .statusCode(201)
                .and()
                    .extract()
                        .body()
                            .asString();
        // @formatter:on

        CakeDTO actual = new ObjectMapper().readValue(bodyAsString, CakeDTO.class);

        assertThat(actual.getEmployeeId(), greaterThan(0));
        assertThat(actual.getTitle(), is(expected.getTitle()));
        assertThat(actual.getDescription(), is(expected.getDescription()));
        assertThat(actual.getImage(), is(expected.getImage()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInvalidCakeData() {
        String testCakeBody = null;
        boolean actual = false;

        // @formatter:off
        try {
            given()
            .when()
                .contentType(ContentType.JSON)
                .body(testCakeBody)
                .post(URL + "/cakes");
        } catch (IllegalArgumentException iae) {
            actual = true;
        }
        // @formatter:on

        assertThat(actual, is(actual));
    }

    @Test
    public void shouldRejectWith500WhenTitleAlreadyExists() {
        String testCakeBody = "{\"title\": \"Lemon cheesecake\", \"description\": \"description1\", \"image\": \"image1\"}";

        loadTestCake1();

        // @formatter:off
        given()
        .when()
            .contentType(ContentType.JSON)
            .body(testCakeBody)
            .post(URL + "/cakes")
        .then()
            .statusCode(500);
        // @formatter:on
    }

    @Test
    public void shouldReturnJsonFileContainingCakes() throws IOException {
        int expectedSize = 2;

        loadTestCakes();

        // @formatter:off
        byte[] image =
                given()
                .when()
                    .get(URL + "/cakes")
                .then()
                    .statusCode(200)
                    .and()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cakes.json")
                .extract()
                    .asByteArray();

        List<CakeDTO> actual = new ObjectMapper().readValue(image, new TypeReference<>() {});
        // @formatter:on

        assertThat(actual.size(), equalTo(expectedSize));
    }

    private void loadTestCakes() {
        loadTestCake1();
        loadTestCake2();
    }

    private void loadTestCake1() {
        cakeRepository.save(createCake1());
    }

    private void loadTestCake2() {
        cakeRepository.save(createCake2());
    }

    private Cake createCake1() {
        return new Cake("Lemon cheesecake", "A cheesecake made of lemon",
                "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
    }

    private Cake createCake2() {
        return new Cake("victoria sponge", "sponge with jam",
                "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg");
    }
}