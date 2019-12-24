package com.decAPI;
import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.junit.Test;
import sun.security.x509.OtherName;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import javax.naming.ldap.StartTlsResponse;
import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TwitterPracticeAutomation {

    String ConsumerKey = "Qj9IaZIG8NGQiOzk5eHYdir4C";
    String ConsumerSecret = "IB2X4xMlzXAGysqoMKsT0UeYSNcepGdV3wQFWdlnF7N45Oxl0P";
    String AccessKey = "1204375673592307712-U63QHflqsjXnYmOd549rlbB1NLt1jw";
    String TokenSwcret = "StaSS4QSkJ69VrE1V1w0vvBqn6r1UDFFlw0CoUUJ3EyhO";

    @Test
    public void getTwittNew(){

        Response response = given().auth().oauth(ConsumerKey,ConsumerSecret,AccessKey,TokenSwcret)
                .when().get("https://api.twitter.com/1.1/statuses/home_timeline.json");
        response.prettyPrint();

        response.then().body("text",hasItems("This is Darshan...!"));

    }

    @Test
    public void postTwittNew(){

        Response response = given().auth().oauth(ConsumerKey,ConsumerSecret,AccessKey,TokenSwcret)
                .queryParam("status","New Twitte from Intelij IDE")
                .when().post("https://api.twitter.com/1.1/statuses/update.json");

        response.prettyPrint();

    }


}
