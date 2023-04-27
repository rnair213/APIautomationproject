package Testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Post_CreateUser {

    @Test(priority = 1)
    public void testCreateUser() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Set the request body parameters
        String Firstname = "George";
        String lastname = "Clerk";
        String requestBody = "{ \"name\": \"" + Firstname + "\", \"job\": \"" + lastname + "\" }";

        // Send the POST request to create a user
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/users");

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



