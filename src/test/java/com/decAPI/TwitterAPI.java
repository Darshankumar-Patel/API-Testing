package com.decAPI;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.hamcrest.Matcher;
import sun.security.x509.OtherName;


import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import javax.naming.ldap.StartTlsResponse;
import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TwitterAPI {

String ConsumerKey = "Qj9IaZIG8NGQiOzk5eHYdir4C";
String ConsumerSecret = "IB2X4xMlzXAGysqoMKsT0UeYSNcepGdV3wQFWdlnF7N45Oxl0P";
String AccessKey = "1204375673592307712-U63QHflqsjXnYmOd549rlbB1NLt1jw";
String TokenSwcret = "StaSS4QSkJ69VrE1V1w0vvBqn6r1UDFFlw0CoUUJ3EyhO";

@Test
    public void getTweet(){

    Response response = given().auth().oauth(ConsumerKey,ConsumerSecret,AccessKey,TokenSwcret)
            .get("https://api.twitter.com/1.1/statuses/home_timeline.json");

    response.prettyPrint();

    response.then().body("id_str",hasItems("1208796914730311681"))
            .body("text",hasItem("Tweet from Intellij"));
        }

@Test
    public void getTweet1(){

    Response response = given().auth().oauth(ConsumerKey,ConsumerSecret,AccessKey,TokenSwcret)
           .when().get("https://api.twitter.com/1.1/statuses/home_timeline.json");
    response.prettyPrint();

    response.then().body("text",hasItem("API Automation Test"));
        }

@Ignore
    public void postTwitt(){

    Response response = given().auth().oauth(ConsumerKey,ConsumerSecret,AccessKey,TokenSwcret)
            .queryParam("status","Second Tweet from Intellij")
            .when().post("https://api.twitter.com/1.1/statuses/update.json");

    response.prettyPrint();

    response.then().body("text",is(equalTo("Second Tweet from Intellij")));
        }

@Test
    public void destroyTwitt() {

    Response response = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessKey, TokenSwcret)
            .queryParam("id", "1208774258350075904")
            .when().post("https://api.twitter.com/1.1/statuses/destroy/1208774258350075904.json");
    response.prettyPrint();

//    response.then().body("id", hasItem("1208774258350075904"));
        }



}



