package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    //beforeAll is a annotation equals to @BeforeClass in testNg , we use with static methode name
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each type
        RestAssured.baseURI = "http://44.195.43.243:8000";

    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("Get request to /api/spartans/10")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");

        System.out.println("response.statusCode() = " + response.statusCode());
        //verify status code must be 406
        assertEquals(406 , response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        //verify Content Type must be application/xml;charset=UTF-8
        assertEquals("application/xml;charset=UTF-8", response.contentType());

    }

}
