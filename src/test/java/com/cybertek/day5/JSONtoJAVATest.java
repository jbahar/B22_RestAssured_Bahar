package com.cybertek.day5;
import com.cybertek.utilities.spartanTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class JSONtoJAVATest extends spartanTest{

    @DisplayName("Get one spartan and deselilize to Map")
    @Test
    public void test1() {
        Response response = given().pathParam("id", 15).when().get("/api/spartans/{id}").then().statusCode(200).extract().response();

        //get json and convert it to map

        Map<String , Object> jsonMap = response.as(Map.class);
        System.out.println(jsonMap.toString());

        //after we got a map , we can use hamcrest or junit assertion to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));

        //deserialization is converting json to java

    }

    @DisplayName("Get all spartans to JAVA data structure")
    @Test
    public void test2() {
        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        // we need to convert  from json to java which is deserialization
        List<Map<String,Object>> list = response.as(List.class);

        System.out.println("list.get(1).get(\"name\") = " + list.get(1).get("name"));

        Map<String, Object> spartan3 = list.get(2);
        System.out.println("spartan3 = " + spartan3);
    }


}
