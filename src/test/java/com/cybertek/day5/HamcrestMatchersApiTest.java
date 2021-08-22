package com.cybertek.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("http://52.207.61.129:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106)).log().all();

    }

    @DisplayName("CBTraining teacher request with chaining and matchers")
    @Test
    public void test2 () {

        given().accept(ContentType.JSON).and().pathParam("id", 10423).and().when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().statusCode(200).and().contentType("application/json;charset=UTF-8").and().header("Content-length", is("236")).
                header("Date", notNullValue()).and().body("teachers[0].firstName", is("Alexander")).
                body("teachers[0].lastName", is("Syrup")).and().body("teachers[0].gender", is("male")).log().all();

    }

    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void test3 () {
        // verify Alexander, Darleen , Sean inside the all teacher

        given().accept(ContentType.JSON).when().get("http://api.cybertektraining.com/teacher/all").
                then().statusCode(200).and().body("teachers.firstName", hasItems("Alexander", "Darleen", "Sean"));

    }
}
