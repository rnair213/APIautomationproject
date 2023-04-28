package Testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TC_GetUserInfo extends datafortests{


    @Test(dataProvider = "pagenumbers")
    public void getAllUserResponse(int page) {
        given()
                .param("page", page)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }

    @Test(dataProvider = "pagenumbers", dependsOnMethods = {"getAllUserResponse"})
    public void getAllUserNameAndEmails(int page) {
        Response response = given()
                .param("page", page)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("Status code :"+response.getStatusCode());

        Map<String, String> userDetails = extractUserDetailsFromResponse(response);

        // Use the userDetails map as needed
        System.out.println(userDetails);
    }
        // Reusable method to get Id's from the get call
    @Test(dataProvider = "userIds")
    public void getUserDetailsById(int userId) {
        given()
                .when()
                .get("https://reqres.in/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(userId));
        Assert.assertEquals("data.id", userId);
    }


    private Map<String, String> extractUserDetailsFromResponse(Response response) {
        List<String> firstNames = response.jsonPath().getList("data.first_name");
        List<String> emails = response.jsonPath().getList("data.email");

        Map<String, String> userDetails = new HashMap<>();
        for (int i = 0; i < firstNames.size(); i++) {
            userDetails.put(firstNames.get(i), emails.get(i));
        }
        return userDetails;
    }
}
