package Testcases;

import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;



import static io.restassured.RestAssured.given;

public class TC1_Get {
    @Test
    public void test_getuser() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=1").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void testGetUserDetails() {
        // the base URI of the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Send a GET request to retrieve all user details
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();
        System.out.println("Response body: " + response.getBody().asString());

        // retrieve the list of users from response
        // List<User> users = response.jsonPath().getObject("$", new TypeRef<List<User>>(){});


        // System.out.println(users);
    }

    @Test
    public User getUserDetailsById(int userId) {
        String endpoint = "https://reqres.in/api/" + userId;
        Response response = given().get(endpoint);
        User user = response.as(User.class);
        return user;
    }
    {int userId = 1; // Example user ID
        User user = getUserDetailsById(userId);
        System.out.println("User details for ID " + userId + ": " + user);

    }


}
