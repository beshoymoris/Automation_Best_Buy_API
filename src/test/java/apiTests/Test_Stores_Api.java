package apiTests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Test_Stores_Api {


    @Test (priority = 0)
    public void testAddStoresById () {

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request1 = new JSONObject();
        request1.put("name", "aute dolor");
        request1.put("address", "re");
        request1.put("city", "nostrud aute do");
        request1.put("state", "do aliqua incididunt");
        request1.put("zip", "do ipsum ");
        request1.put("type", "reprehenderit deserunt volup");
        request1.put("address2", "eu sit de");
        request1.put("lat", 85445071.63007307);
        request1.put("lng", 19718726.095101014);
        request1.put("hours", "lab");
        given().header("Content-type", "application/json").body(request1.toJSONString()).when().post("/stores").then().statusCode(201).log().all();
    }

    @Test(priority = 1)
    public void testGetStoresById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",8931).get("/stores").then().statusCode(200).body("data.name[0]",equalTo("aute dolor")).log().all();
    }

    @Test(priority = 2)
    public void testGetStoresByLimitandSkip (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("$limit",5).param("$skip",2).get("/stores").then().statusCode(200).body("limit",equalTo(5),"skip",equalTo(2)).log().all();
    }

    @Test (priority = 3)
    public void testUpdateStoresById (){

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request = new JSONObject();
        request.put("name", "aute dolor");
        request.put("address", "re");
        request.put("city", "nostrud aute do");
        request.put("state", "do aliqua incididunt");
        request.put("zip", "do ipsum ");
        request.put("type", "reprehenderit deserunt volup");
        request.put("address2", "eu sit de");
        request.put("lat", 85445071.63007307);
        request.put("lng", 19718726.095101014);
        request.put("hours", "lab");
        given().body(request.toJSONString()).when().param("id",8923).patch("/stores").then().statusCode(200).log().all();
    }

    @Test (priority = 4)
    public void testDeleteStoresById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",8923).delete("/stores").then().statusCode(200).log().all();
    }

}
