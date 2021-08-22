package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class spartanTest {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.195.43.243:8000";

//        String dbUrl = "jdbc:oracle:thin:@44.195.43.243:1521:XE";
//        String dbUsername = "SP";
//        String dbPassword = "SP";
//        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);

    }
@AfterAll
    public static void teardown(){

      //  DBUtils.destroy();
    }
}
