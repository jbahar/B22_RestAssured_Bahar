package com.cybertek.homeWork;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class HRDataBaseVSAPI extends HrTestBase {

    //   Given accept type is application/json
    //And query param q is {"location_id":1700}
    //When I send a GET request to /departments endpoint
    //Then status code must be 200
    //And each location_id must be 1700
    //And number of departments is 21 in total
    //And database department names are matching with api department names
    //
    //To get this homework done
    //1.you need to make sure you have odjbc(oracle dependency for jdbc)
    //2.do needed configuration to connect hr database both base class and dbutils(if needed)
    //3.write your query to get departments location id is 1700
    //4.get your result from database
    //5.get your result from api do needed assertions (try with hamcrest)
    //6.store your api result into java collection (as() method not mandatory)
    //7.compare department names against to each other (list to list equality)

    @DisplayName("Get request to /departments")
    @Test
    public void test1 (){
        String query = "SELECT * from departments where location_id = 1700";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println(rowMap);


        // get info from API
        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"location_id\": 1700}").when().get("/departments")
                .then().statusCode(200).contentType("application/json").body("items.location_id", everyItem(equalTo(1700)),
                        "items.count", hasSize(21)).extract().response();


        Map<String,Object> map = response.as(Map.class);
        System.out.println(map);

        assertThat(map.get("items.department_id").toString(), is(rowMap.get("DEPARTMENT_ID").toString()));
        assertThat(map.get("items.department_name").toString(), is(rowMap.get("DEPARTMENT_NAME").toString()));
        assertThat(map.get("items.manager_id").toString(), is(rowMap.get("MANAGER_ID").toString()));
        assertThat(map.get("items.location_id").toString(), is(rowMap.get("LOCATION_ID").toString()));

    }

}
