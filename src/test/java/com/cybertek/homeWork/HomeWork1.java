package com.cybertek.homeWork;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWork1 extends HrTestBase {

    //Q1:
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //- And Region_id is 2
    @DisplayName("Get request to the countries")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).pathParam("country_id", "US").when().get("/countries/{country_id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        String countryId = jsonPath.getString("country_id");
        System.out.println(countryId);
        assertEquals("US", countryId);

        String countryName = jsonPath.getString("country_name");
        System.out.println(countryName);
        assertEquals("United States of America", countryName);

        int regionId = jsonPath.getInt("region_id");
        System.out.println(regionId);
        assertEquals(2, regionId);
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
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"department_id\":80}").when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath =response.jsonPath();


        List<String> jobId = jsonPath.getList("items.job_id");
       for (String eachJobId : jobId) {
           assertTrue(eachJobId.startsWith("SA"));
       }

        List<Integer> departmentsId = jsonPath.getList("items.findAll{it.department_id == 80}.department_id");
            assertTrue(departmentsId.contains(80));

        assertEquals(25, jsonPath.getInt("count"));

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
    public void test3(){

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\": 3}").when().get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        List<Integer> regionId = jsonPath.getList("items.findAll{it.region_id == 3}.region_id");
        for (int eachRegionId : regionId) {
            System.out.println(eachRegionId);
            assertEquals(3, eachRegionId);
        }

        assertEquals(6, jsonPath.getInt("count"));

        assertFalse(jsonPath.getBoolean("hasMore"));

        List<String> expectedCountries = new ArrayList<>(Arrays.asList("Australia" , "China", "India", "Japan", "Malaysia", "Singapore"));
        List<String> actualCountries = jsonPath.getList("items.country_name");
        assertEquals(expectedCountries, actualCountries);

    }

}
