package com.cybertek.homeWork;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class ZipCodeHamcrestMatchers {
    @BeforeAll
    public static void init() {
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

    @DisplayName("Get request to /us")
    @Test
    public void test1() {
        RestAssured.given().accept(ContentType.JSON).pathParam("zipcode", 22031).when().get("us/{zipcode}").then()
                .statusCode(200).and().contentType("application/json").and().header("server", Matchers.equalTo("cloudflare"))
                .and().header("Report_To", Matchers.hasItems()).body("'post code'", Matchers.equalTo("22031"))
                .and().body("'country abbreviation'", Matchers.is("US")).body("places[0].'place name'", Matchers.is("Fairfax"))
                .body("places[0].state", Matchers.is("Virginia")).body("places[0].latitude", Matchers.is("38.8604"));
    }


    //Given Accept application/json
    //And path zipcode is 50000
    //When I send a GET request to /us endpoint
    //Then status code must be 404
    //And content type must be application/json
    @DisplayName("Get request to /us")
    @Test
    public void test3() {
        RestAssured.given().accept(ContentType.JSON).pathParam("id", 50000).when().get("us/{id}").then().statusCode(404)
                .contentType("application/json");
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
    public void test4(){
        RestAssured.given().accept(ContentType.JSON).pathParam("state", "va").pathParam("city", "fairfax")
                .when().get("us/{state}/{city}").then().statusCode(200).contentType("application/json")
                .and().body("'country abbreviation'", Matchers.is("US")).body("country", Matchers.is("United States"))
                .body("places[0].'place name'", Matchers.is("Fairfax"))
                .body("places.'place name'" , Matchers.everyItem(Matchers.containsString("Fairfax")))
                .body("places.'post code'", Matchers.everyItem(Matchers.startsWith("22")));
    }


}
