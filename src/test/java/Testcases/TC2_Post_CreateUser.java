package Testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Post_CreateUser extends datafortests {

    @Test(priority = 1, dataProvider = "DataForPost")
    public void testCreateUser(String Firstname, String lastname) {
        // Set the base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Set the request body parameters
             String requestBody = "{ \"name\": \"" + Firstname + "\", \"lastname\": \"" + lastname + "\" }";

        // Send the POST request to create a user
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/users");
        System.out.println("Status Code :"+response.getStatusCode());
        System.out.println("time taken:"+ response.getTime());

        // Verify that the response code is 201 (Created)
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(priority = 2, dependsOnMethods = {"testCreateUser"})
    public void testVerifyUserDetails() {
        try {
            // Retrieve the user ID from the response
            Response response = RestAssured.get("/users");
            String userId = response.jsonPath().getString("data[0].id");


            // Use the user ID to retrieve the user details and verify them
            Response getUserResponse = RestAssured.get("/users/" + userId);
            System.out.println("New user ID: " + userId);
            String actualUserName = getUserResponse.jsonPath().getString("data.first_name");
            String actualname = getUserResponse.jsonPath().getString("data.last_name");

            // Verify the user details returned in the response
            Assert.assertEquals(actualUserName, "George");
            Assert.assertEquals(actualname, "Bluth");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error verifying user details: " + e.getMessage());
        }
    }
}



