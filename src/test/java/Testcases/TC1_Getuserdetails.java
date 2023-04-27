package Testcases;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.net.CacheRequest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Listeners;


public class TC1_Getuserdetails {

        @Test(priority = 1)
        public void getAllUserResponse() {
            try{
             Response response = given()
                    .param("page", 1)
                    .when()
                    .get("https://reqres.in/api/users")
                    .then()
                     .statusCode(200)
                     .extract().response();
            System.out.println("Response body: " + response.getBody().asString());
            // Assert Response code is 200 , validated the user Info:
            int actualStatusCode = response.getStatusCode();
            int expectedStatusCode = 200;
            Assert.assertEquals(actualStatusCode, expectedStatusCode);

            // Validate user details in response body
            String ExpectedFirst_Name = "George";
            String actualFirst_Name = response.jsonPath().getString("first_name");
            Assert.assertEquals(actualFirst_Name, ExpectedFirst_Name );
            System.out.println("User details has been verified and passed");

        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("Error verifying user details: " + e.getMessage());
        }
}
      @Test(priority = 2 , dependsOnMethods = {"getAllUserResponse"})
    public void getAllUserNameAndEmails() {
        given()
                .param("page", 1)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("data.first_name", hasItems("George", "Janet", "Emma", "Eve", "Charles", "Tracey"))
                .body("data.email", hasItems("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in", "eve.holt@reqres.in", "charles.morris@reqres.in", "tracey.ramos@reqres.in"));
    }

        @Test
        public void getUserDetailsById() {
            int userId = 1;
            given()
                    .when()
                    .get("https://reqres.in/api/users/" + userId)
                    .then()
                    .statusCode(200)
                   .body("data.id", equalTo(userId))
                    .body("data.first_name", equalTo("George"))
                    .body("data.last_name", equalTo("Bluth"))
                    .body("data.email", equalTo("george.bluth@reqres.in"));
        }
    }


