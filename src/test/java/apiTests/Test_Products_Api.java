package apiTests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Test_Products_Api {


    @Test (priority = 0)
    public void testAddProductsById () {

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request1 = new JSONObject();
        request1.put("name", "pariatur dolor cillum");
        request1.put("type", "reprehe");
        request1.put("upc", "aliqua magna ");
        request1.put("description", "elit");
        request1.put("model", "velit min");
        request1.put("price", 21579999.150000002);
        request1.put("shipping", 134319989.15);
        request1.put("manufacturer", "mollit labore");
        request1.put("url", "commodo est nostrud");
        request1.put("image", "et aute veniam");
        given().header("Content-type", "application/json").body(request1.toJSONString()).when().post("/products").then().statusCode(201).log().all();
    }

    @Test(priority = 1)
    public void testGetProductsById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",9999739).get("/products").then().statusCode(200).body("data.name[0]",equalTo("pariatur dolor cillum")).log().all();
    }

    @Test(priority = 2)
    public void testGetProductsByLimitandSkip (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("$limit",14).param("$skip",2).get("/products").then().statusCode(200).body("limit",equalTo(14),"skip",equalTo(2)).log().all();
    }

    @Test (priority = 3)
    public void testUpdateProductsById (){

        RestAssured.baseURI ="http://localhost:3030";
        JSONObject request = new JSONObject();
        request.put("name","pariatur dolor cillum");
        request.put("type","reprehe");
        request.put("upc","aliqua magna ");
        request.put("description","elit");
        request.put("model","velit min");
        request.put("price",21579999.150000002);
        request.put("shipping",134319989.15);
        request.put("manufacturer","mollit labore");
        request.put("url","commodo est nostrud");
        request.put("image","et aute veniam");
        given().body(request.toJSONString()).when().param("id",9999739).patch("/products").then().statusCode(200).log().all();
    }

    @Test (priority = 4)
    public void testDeleteProductsById (){

        RestAssured.baseURI ="http://localhost:3030";
        given().param("id",43901).delete("/products").then().statusCode(200).log().all();
    }

}
