package apiTests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Test_Services_Api {


    @Test (priority = 0)
    public void testAddServicesById () {

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request1 = new JSONObject();
        request1.put("name", "in Lorem");
        given().header("Content-type", "application/json").body(request1.toJSONString()).when().post("/services").then().statusCode(201).log().all();
    }

    @Test(priority = 1)
    public void testGetServicesById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",20).get("/services").then().statusCode(200).body("data.name[0]",equalTo("Best Buy Mobile Specialty Store")).log().all();
    }

    @Test(priority = 2)
    public void testGetServicesByLimitandSkip (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("$limit",5).param("$skip",2).get("/services").then().statusCode(200).body("limit",equalTo(5),"skip",equalTo(2)).log().all();
    }

    @Test (priority = 3)
    public void testUpdateServicesById (){

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request = new JSONObject();
        request.put("name", "aute dolor");
        given().body(request.toJSONString()).when().param("id",24).patch("/services").then().statusCode(200).log().all();
    }

    @Test (priority = 4)
    public void testDeleteServicesById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",29).delete("/services").then().statusCode(200).log().all();
    }

}
