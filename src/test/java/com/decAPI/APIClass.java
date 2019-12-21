package com.decAPI;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.hamcrest.Matcher;
import sun.security.x509.OtherName;

import static org.hamcrest.Matchers.equalTo;
import javax.naming.ldap.StartTlsResponse;
import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APIClass {

    @Test

    public void listUsers()
    {
        // Response is comes from Rest Assured Library - pre define class. Store in variable    response.
   Response response = given()
        .when().get("https://reqres.in/api/users?page=2");

   response.prettyPrint();
   // prettyPrint is like system println out
        // Assertion
        // 200 is the http code you are expecting
        //other Assertions are...
        response.then().statusCode(200)
        .body("page",is(2))
        .body("data.first_name",hasItem("Tobias"))
        .body("total_pages", Matchers.is(equalTo(2)))
        .body("data.id",hasItems(7,8,9));

         }

    @Test
            public void singleUser () {
        Response response =  given()
                .when().get("https://reqres.in/api/users/2");
        response.prettyPrint();
        response.then().statusCode(200)
                .body("data.id",is(2))
                .body("data.email",is("janet.weaver@reqres.in"))
                .body("data.avatar",is("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"));

    }

    @Test
    public void singleUserNotFound(){
        Response responce = given()
                .when().get("https://reqres.in/api/users/23");
        responce.then().statusCode(404);
        responce.prettyPrint();

    }

    @Test
    public void listResource(){
        Response response = given()
                .when().get("https://reqres.in/api/unknown");
        response.then().statusCode(200)
                .body("page",is(1))
                .body("data.year",hasItems(2000,2001))
                .body("data.color",hasItem("#53B0AE"));
        response.prettyPrint();

    }

    @Test
    public void singleResource(){
        Response response = given()
                .when().get("https://reqres.in/api/unknown/2");
        response.then().statusCode(200)
                .body("data.id",is(2))
                .body("data.year",equalTo(2001))
                .body("data.pantone_value",is("17-2031"));
        response.prettyPrint();
    }

    @Test
    public void singleResourceNotFound(){
        Response response = given()
                .when().get("https://reqres.in/api/unknown/23");
        response.then().statusCode(404);
        response.prettyPrint();

    }

    @Test
    public void delayedResponse(){

        Response response = given()
                .when().get("https://reqres.in/api/users?delay=3");
        response.then().statusCode(200)
                .body("page",is(1))
                .body("total",is(12))
                .body("data.last_name",hasItems("Bluth","Weaver"));
        response.prettyPrint();

    }

    @Test
    public void posts(){

        Response response = given()
                .when().get("https://jsonplaceholder.typicode.com/posts");
        response.then().body("userId",hasItem(1))
                .body("id",hasItems(1,3,5))
                .body("title",hasItems("qui est esse"));
        response.prettyPrint();
    }

    @Test
    public void post1(){
        Response response = given()
                .when().get("https://jsonplaceholder.typicode.com/posts/1");
        response.then().body("userId",equalTo(1))
                .body("id",is(1))
                .body("title",is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        response.prettyPrint();
    }

    @Test
    public void post1Comments(){
        Response response = given()
                .when().get("https://jsonplaceholder.typicode.com/posts/1/comments");
        response.then().body("postId",hasItems(1,2))
                .body("id",hasItems(3,4))
                .body("name",hasItems("id labore ex et quam laborum"));
        response.prettyPrint();
    }

    @Test
    public void postId1Comments (){

        Response response = given()
                .when().get("https://jsonplaceholder.typicode.com/comments?postId=1");
        response.then().body("postId",hasItem(1))
                .body("id",hasItems(3,5))
                .body("email",hasItems("Jayne_Kuhic@sydney.com"));
        response.prettyPrint();
    }

    @Test
    public void postsUserId1(){

        Response response = given()
                .when().get("https://jsonplaceholder.typicode.com/posts?userId=1");
        response.then().body("userId",hasItem(1))
                .body("id",hasItems(1,3,5))
                .body("title",hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        "eum et est occaecati"))
                .body("body",hasItems("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
        response.prettyPrint();
    }

    // for post method
    @Test
    public void registerSuccessfull(){
        //method from Default class contenstType (appliocation/jason)
        Response responce = given().contentType("application/json")
                .when().body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}")
                .post("https://reqres.in/api/register");

        responce.then().statusCode(200)
                .body("id",is(4));
        responce.prettyPrint();
    }

    @Test
    public void create (){
        Response response = given().contentType("application/json")
                .when().body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users");
        response.then().statusCode(201)
                .body("name",is(equalTo("morpheus")));

        response.prettyPrint();

    }

    @Test
    public void registerUnsucessfull(){

        Response response = given().contentType("application/json")
                .when().body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .post("https://reqres.in/api/register");
        response.then().statusCode(400);

        response.prettyPrint();
    }

    @Test
    public void loginSucessfull (){

        Response responce = given().contentType("application/json")
                .when().body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("https://reqres.in/api/login");
        responce.then().statusCode(200)
                .body("token",is(equalTo("QpwL5tke4Pnpja7X4")));
        responce.prettyPrint();
    }

    @Test
    public void loginUnsucessfull(){

        String payload = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";

        Response response = given().contentType("application/json")
                            .when().body(payload)
                            .post("https://reqres.in/api/login");
        response.then().statusCode(400)
                .body("error",is(equalTo("Missing password")));
        response.prettyPrint();

    }

}

