package apiTests;

import io.restassured.RestAssured;
import org.hamcrest.core.StringContains;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Test_Utilities_Api {


    @Test
    public void testGetMonitoringVersion (){

        RestAssured.baseURI ="http://localhost:3030";
        given().get("/version").then().statusCode(200).body("version",equalTo("1.1.0")).log().all();
    }

    @Test
    public void testGetHealthInformation (){

        RestAssured.baseURI ="http://localhost:3030";
        given().get("/healthcheck").then().statusCode(200).body(StringContains.containsString("uptime")).log().all();
    }


}
