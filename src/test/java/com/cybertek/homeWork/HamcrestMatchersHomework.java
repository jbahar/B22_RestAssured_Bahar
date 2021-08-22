package com.cybertek.homeWork;

import com.cybertek.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HamcrestMatchersHomework extends HrTestBase {

    //Q1:
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //- And Region_id is 2

    @DisplayName("Get request to /countries")
    @Test
    public void test1() {
         RestAssured.given().accept(ContentType.JSON).pathParam("id", "US").when().get("/countries/{id}")
                .then().statusCode(200).and().contentType("application/json").and()
                .body("country_id", Matchers.is("US")).and().body("country_name", Matchers.equalTo("United States of America")).
                        body("region_id", Matchers.equalTo(2));

    }

    //Q2:
    //- Given accept type is Json
    //- Query param value - q={"department_id":80}
    //- When users sends request to /employees
    //- Then status code is 200
    //- And Content - Type is Json
    //- And all job_ids start with 'SA'
    //- And all department_ids are 80
    //- Count is 25


    @DisplayName("Get request to /employees")
    @Test
    public void test2() {
        RestAssured.given().accept(ContentType.JSON).and().queryParam("q", "{\"department_id\": 80}")
                .when().get("/employees").then().statusCode(200).and().contentType("application/json").and()
               .body("items.job_id", Matchers.everyItem(Matchers.startsWith("SA"))).and().body("items.department_id", Matchers.everyItem(Matchers.is(80)))
                .and().body("items" , Matchers.hasSize(25));
    }

    //Q3:
    //- Given accept type is Json
    //-Query param value q= region_id 3
    //- When users sends request to /countries
    //- Then status code is 200
    //- And all regions_id is 3
    //- And count is 6
    //- And hasMore is false
    //- And Country_name are;
    //Australia,China,India,Japan,Malaysia,Singapore



    @DisplayName("Get request to /countries")
    @Test
    public void test3() {

        RestAssured.given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\": 3}").when().get("/countries").then()
                .statusCode(200).contentType("application/json").body("items.region_id" , Matchers.everyItem(Matchers.equalTo(3))).body("items", Matchers.hasSize(6))
                .and().body("hasMore", Matchers.is(false)).and()
                .body("items.country_name", Matchers.containsInAnyOrder("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
    }
}
