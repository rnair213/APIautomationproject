# APIAutomationproject
RestAssured API Test (TestNG Framework)
This project is Automating the API using rest assured library using TestNG Framework

Reqres.in API Testing
This project is designed to test the various methods available in the Reqres.in API, including GET, POST, and PUT requests. The approach taken for testing each of these methods is outlined below.

GET Method
The GET method for the Reqres.in API is used to retrieve a list of users based on a specified page number. To test this method, the following approach was taken:

Send a GET request to the following URL: https://reqres.in/api/users?page=1

Verify that the response code is 200 (OK).

Verify that the response body contains a list of users.

Verify that the response body contains the expected number of users.

Verify that the response body contains the expected user details, such as name, email, and ID.

Verify that email Id and Names are filtered out from the Userdetails.
Verify the 

POST Method
The POST method for the Reqres.in API is used to create a new user. To test this method, the following approach was taken:

Send a POST request to the following URL: https://reqres.in/api/users

Include the required user data in the request body, such as First_name, last_name,.

Verify that the response code is 201 (Created).

Verify that the response body contains the newly created user's details, including the user's ID.

Verify that the user was successfully created by sending a GET request to the user's URL using the user ID returned in the previous step.

PUT Method
The PUT method for the Reqres.in API is used to update an existing user's details. To test this method, the following approach was taken:

Send a PUT request to the URL of the user to be updated, using the user's ID.

Include the updated user data in the request body.

Verify that the response code is 200 (OK).

Verify that the response body contains the updated user details.

Verify that the user was successfully updated by sending a PUT request to the user's URL using the user ID.

By following this approach for testing the various API methods, we can ensure that the API is working as expected and that any issues or bugs are identified and resolved quickly.

Assumptions 
Assumptions made for https://reqres.in/api/users?page=1
while testing for get post and put Request for this API are listed Below:

Assumptions made for testing the GET, POST, and PUT methods of the Reqres.in API include:

The Reqres.in API is functioning as expected and is returning the correct responses based on the requests made.

The API documentation for the Reqres.in API is accurate and up-to-date, and the API follows the specified protocols and standards.

The API endpoints and parameters for the Reqres.in API are accessible and can be used to make the required requests and obtain the desired responses.

The GET method for the Reqres.in API will return a list of users based on the specified page number.

The POST method for the Reqres.in API will create a new user based on the provided user data.

The PUT method for the Reqres.in API will update an existing user's details based on the provided user data.

The API returns a 200 (OK) status code for successful requests and returns the expected response body.

The API returns a 201 (Created) status code for successful POST requests and returns the newly created user's details.

The API returns a 200 (OK) status code for successful PUT requests and returns the updated user details.

The API can handle various error conditions and returns appropriate error codes and messages.

It is important to keep these assumptions in mind while testing the Reqres.in API to ensure that the API is functioning as expected and any issues or bugs are identified and resolved quickly.






Regenerate response
