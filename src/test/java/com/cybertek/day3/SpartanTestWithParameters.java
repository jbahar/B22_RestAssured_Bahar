package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithParameters {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each type
        baseURI = "http://44.195.43.243:8000";
    }

    /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

    @DisplayName("GET request to /api/spartans/{id}")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 5).when().get("/api/spartans/{id}");

        //Then response status code should be 200
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());

        //And "Blythe" should be in response payload
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */


    @DisplayName("GET request to /api/spartans/{id} Navigate test")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 500).when().get("/api/spartans/{id}");

        //Then response status code should be 404
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(404, response.statusCode());

        //And response content-type: application/json
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());

        //And "Not Found" message should be in response payload
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Not Found"));
    }


    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));


    }


    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test4() {
        Response response = given().accept(ContentType.JSON).log().all()
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test5() {
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().
                log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

    }


}

