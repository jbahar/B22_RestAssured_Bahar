package com.cybertek.day6;
import com.cybertek.pojo.Region;
import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class ORDSPojoGetRequestTest extends HrTestBase {

    @Test
    public void regionTest(){
        JsonPath jsonPath = given().accept(ContentType.JSON).when().get("/regions").then().statusCode(200)
                .contentType("application/json").extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


    }
}
