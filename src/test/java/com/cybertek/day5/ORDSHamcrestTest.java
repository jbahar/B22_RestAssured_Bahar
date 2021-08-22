package com.cybertek.day5;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HrTestBase {

    @DisplayName("Get request to Employees IT_PROG endpoint and chaining")
    @Test
    public void regionTest() {
        //send a get request to emoloyees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        // expected names

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").when().
                get("/employees").then().statusCode(200).body("items.job_id", everyItem(equalTo("IT_PROG"))).and()
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")).and()
                .body("items.email", containsInAnyOrder("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ")).and()
                .body("items.first_name", equalTo(names)); // equality of names

// containsInRelativeOrder  ==> it need to be exact order
        //containsInAnyOrder ==> it can be in any order


    }


    @Test
    public void test2 () {
        // we want to chain and also get response object


        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").when().
                get("/employees").then().statusCode(200).body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response();

        response.prettyPrint();
    }


    @Test
    public void test3 () {
        // we want to chain and also get response object


        JsonPath jsonPath = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").when().
                get("/employees").then().statusCode(200).body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();


        //assert that we have only 5 names

        assertThat(jsonPath.getList("items.firt_name"), hasSize(5));

        //assert firstname order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));


    }

}

