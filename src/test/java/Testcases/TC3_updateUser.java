package Testcases;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TC3_updateUser extends datafortests {


    @Test(priority = 1, dataProvider = "DataForPost")
    public void testCreateUser(String name, String lastname) {
        try {
            // Set the base URI for the API
            baseURI = "https://reqres.in/api";

            // Set the request body parameters

            String requestBody = "{ \"name\": \"" + name + "\", \"Lastname\": \"" + lastname + "\" }";

            // Send the POST request to create a user
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .post("/users/1");

            // Verify that the response code is 201 (Created)
            Assert.assertEquals(response.getStatusCode(), 201);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error creating user: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = {"testCreateUser"}, dataProvider = "DataForPut" )
    public void testUpdateUser(String name,  String lastname) {
        try {
            // Retrieve the user ID from the response
            Response response = get("/users");
            String userId = response.jsonPath().getString("data[0].id");

            // Set the request body parameters for the PUT request
            String requestBody = "{ \"name\": \"" + name + "\", \"lastname\": \"" + lastname + "\" }";

            // Send the PUT request to update the user details
            Response putResponse = given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .put("/users/" + userId);

            // Verify that the response code is 200 (OK)
            Assert.assertEquals(putResponse.getStatusCode(), 200);

            // Verify that the user details have been updated
            Response getUserResponse = get("/users/" + userId);
            String actualUserName = getUserResponse.jsonPath().getString("data.first_name");
            String actuallastname = getUserResponse.jsonPath().getString("data.last_name");

            // Verify the updated user details returned in the response
            Assert.assertEquals(actualUserName, "George");
            Assert.assertEquals(actuallastname, "Bluth");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error updating user: " + e.getMessage());
            System.out.println("User is updated");
        }
    }
}




