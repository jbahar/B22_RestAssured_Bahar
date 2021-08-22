package com.cybertek.homeWork;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCode {

    @BeforeAll
    public static void init () {
        baseURI = "https://www.zippopotam.us/";
    }

    //Given Accept application/json
    //And path zipcode is 22031
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And Server header is cloudflare
    //And Report-To header exists
    //And body should contains following information
    //    post code is 22031
    //    country  is United States
    //    country abbreviation is US
    //    place name is Fairfax
    //    state is Virginia
    //    latitude is 38.8604

    @DisplayName("Get request to /us/22031")
    @Test
    public void test1 () {
        Response response = given().accept(ContentType.JSON).and().pathParam("zipcode", 22031).when().get("us/{zipcode}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());


        assertEquals("cloudflare", response.header("Server"));

        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        JsonPath jsonPath = response.jsonPath();

        int zipcode = jsonPath.getInt("'post code'");
        assertEquals(22031, zipcode);

        String country = jsonPath.getString("country");
        assertEquals("United States" , country);

        String countryAbbreviation = jsonPath.getString("'country abbreviation'");
        assertEquals("US", countryAbbreviation);

        String placeName = jsonPath.getString("places[0].'place name'");
        assertEquals("Fairfax", placeName);

        double latitude = jsonPath.getDouble("places[0].latitude");
        assertEquals(38.8604, latitude);

    }

    //Given Accept application/json
    //And path zipcode is 50000
    //When I send a GET request to /us endpoint
    //Then status code must be 404
    //And content type must be application/json

    @DisplayName("Get request to /us/50000")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().pathParam("zipcode", 50000).when().get("us/{zipcode}");

        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());

    }

    //Given Accept application/json
    //And path state is va
    //And path city is farifax
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And payload should contains following information
    //    country abbreviation is US
    //    country  United States
    //    place name  Fairfax
    //    each places must contains fairfax as a value
    //    each post code must start with 22


    @DisplayName("Get request to /us/state/city")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON).and().pathParam("state", "va").and().pathParam("city", "fairfax").when().get("us/{state}/{city}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        String countryAbbreviation = jsonPath.getString("'country abbreviation'");
        assertEquals("US", countryAbbreviation);

        String country = jsonPath.getString("'country'");
        assertEquals("United States", country);

        List<String> postCode = jsonPath.getList("places.'post code'");
        for (String eachPostCode : postCode) {
            assertTrue(eachPostCode.startsWith("22"));
        }
    }

}

