package com.cybertek.day4;

import com.cybertek.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSWithJsonPath extends HrTestBase {

    @DisplayName("Get request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        // get the second country with jsonPath

        // to use the jsonPath we assign response to json
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        //get all country name where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionId2);

    }

    @DisplayName("Get request /employees with query param")
    @Test
    public void test2() {
        Response response = given().queryParam("limit", 107).when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        // let me all the email from employees who is working as IT_PROG
        List<String> employeesITPRog = jsonPath.getList("items.findAll{it.job_id == \"IT_PROG\"}.email");
        System.out.println(employeesITPRog);


        // get me firstname of employees who is making more then 10000
        List<String> nameOFEmployees = jsonPath.getList("items.findAll{it.salary > 10000}.first_name");
        System.out.println(nameOFEmployees);

        // get the max salary firstname

        String kingFirstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);

    }
}
