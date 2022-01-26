package apiTests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Test_Categories_Api {

    //using javaFaker for unique id every time test this case
    Faker faker = new Faker();
    String uniqueId = faker.name().firstName();

    @Test (priority = 0)
    public void testAddCategoriesById () {

        RestAssured.baseURI ="http://localhost:3030";

        //Test for unique id(Valid) and used id(Invalid)
        JSONObject requestV = new JSONObject();
        JSONObject requestInV = new JSONObject();

        requestV.put("id", uniqueId);
        requestV.put("name", "voluptate minim cillum");
        requestInV.put("id", "1");
        requestInV.put("name", "voluptate minim cillum");

        given().header("Content-type", "application/json").body(requestV.toJSONString()).when().post("/categories").then().statusCode(201).log().all();
        given().header("Content-type", "application/json").body(requestInV.toJSONString()).when().post("/categories").then().statusCode(400).body("errors.message[0]",equalTo("id must be unique")).log().all();

    }

    @Test(priority = 1)
    public void testGetCategoriesById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",1).get("/categories").then().statusCode(200).body("data.name[0]",equalTo("voluptate minim cillum")).log().all();
    }

    @Test(priority = 2)
    public void testGetCategoriesByLimitandSkip (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("$limit",5).param("$skip",2).get("/categories").then().statusCode(200).body("limit",equalTo(5),"skip",equalTo(2)).log().all();
    }

    @Test (priority = 3)
    public void testUpdateCategoriesById (){

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request = new JSONObject();
        request.put("name", "aute dolor");
        given().body(request.toJSONString()).when().param("id","Excepteur anim").patch("/categories").then().statusCode(200).log().all();
    }

    @Test (priority = 4)
    public void testDeleteCategoriesById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id","Excepteur anim").delete("/categories").then().statusCode(200).log().all();
    }

}
