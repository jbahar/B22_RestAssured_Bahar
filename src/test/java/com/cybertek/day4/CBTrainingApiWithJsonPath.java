package com.cybertek.day4;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";

    }

    @DisplayName("Get request to individual student")
    @Test
    public void test1() {
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

        Response response = given().accept(ContentType.JSON).and().queryParam("id", 23401).when().get("/student/23401");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();

        String firstname = jsonPath.getString("students[0].firstName");
        System.out.println(firstname);
        assertEquals("Vera", firstname);

        int batch = jsonPath.getInt("students[0].batch");
        System.out.println(batch);
        assertEquals(14, batch);

        int section = jsonPath.getInt("students[0].section");
        System.out.println(section);
        assertEquals(12, section);

        String email = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println(email);
        assertEquals("aaa@gmail.com", email);

        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println(companyName);
        assertEquals("Cybertek", companyName);

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println(state);
        assertEquals("IL", state);

        int zipcode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println(zipcode);
        assertEquals(60606, zipcode);
    }
}
