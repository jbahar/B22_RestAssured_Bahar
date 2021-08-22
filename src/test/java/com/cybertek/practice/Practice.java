package com.cybertek.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Practice {

    String baseUrl = "http://44.195.43.243:8000";
    String hr = "http://44.195.43.243:1000/ords/hr";

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then stay code must be 200
//    And response Content Type must be application/json
//    And response body should include spartan result


    @DisplayName("send get request to api/spartans end points")
    @Test
    public void test1 () {

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();
    }

     /*
        Given accept is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("sends a get request to /api/spartans/3")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }


     /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */


    @Test
    public void test3() {
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        Assertions.assertEquals("17", response.header("Content-Length"));

        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Hello from Sparta"));


    }


      /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @Test
    public void test4() {
        Response response = RestAssured.given().accept(ContentType.XML).when().get(baseUrl + "/api/spartans/10");

        Assertions.assertEquals(406, response.statusCode());
        Assertions.assertEquals("application/xml;charset=UTF-8", response.contentType());

    }

    /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains Americas
     */


    @DisplayName("get request to /regions/2")
    @Test
    public void test5 () {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(hr + "/regions/2");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Americas"));
    }




}
