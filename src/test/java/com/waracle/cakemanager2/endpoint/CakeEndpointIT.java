package com.waracle.cakemanager2.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CakeEndpointIT {

    private final String URL = "http://localhost:8281";

    @Autowired
    private CakeRepository cakeRepository;

    @BeforeEach()
    public void setup() {
        cakeRepository.deleteAll();
    }

    @Test
    public void shouldRetrieveAllCakesSuccessfully() {
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
                                .as(new TypeRef<List<Map<String, Object>>>() {
                        });
        // @formatter:on

        assertThat(actual.size(), equalTo(expectedSize));
    }

    @Test
    public void shouldCreateCakeWithGeneratedId() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String testCakeBody = "{\"title\": \"title1\", \"description\": \"description1\", \"image\": \"image1\"}";
        CakeDTO expected = new CakeDTO(6, "title1", "description1", "image1");

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
                        .statusCode(200)
                    .and()
                        .extract()
                            .body()
                                .asString();
        // @formatter:on

        CakeDTO actual = mapper.readValue(bodyAsString, CakeDTO.class);

        assertThat(actual, equalTo(expected));
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
    public void shouldCreateDownloadableJsonFileContainingCakes() {
        //todo
        assert false;
    }

    //todo move below test utils class?
    private void loadTestCakes() {
        loadTestCake1();
        loadTestCake2();
    }

    private void loadTestCake1() {
        cakeRepository.save(new Cake("Lemon cheesecake", "A cheesecake made of lemon",
                "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));
    }

    private void loadTestCake2() {
        cakeRepository.save(new Cake("victoria sponge", "sponge with jam",
                "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"));
    }
}