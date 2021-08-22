package com.cybertek.day2;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest extends HrTestBase {

//    //beforeAll is a annotation equals to @BeforeClass in testNg , we use with static methode name
//    @BeforeAll
//    public static void init() {
//        //save baseurl inside this variable so that we dont need to type each type
//        RestAssured.baseURI = "http://44.195.43.243:1000/ords/hr";
//
//    }

    @DisplayName("Get request to /regions")
    @Test
    public void test1() {
        Response response = get("/regions");
        System.out.println(response.statusCode());
    }

    /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains Americas
     */

    @DisplayName("Get request to /regions/2")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/2");

        //verify response status code must be 200
        assertEquals(200, response.statusCode());

        //verify content type equals to application/json
        assertEquals("application/json", response.contentType());

        //print the body
        response.prettyPrint();

        //verify response body contains Americas
        assertTrue(response.body().asString().contains("Americas"));


    }

}
