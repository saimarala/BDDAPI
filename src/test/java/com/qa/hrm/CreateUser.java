package com.qa.hrm;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponseOptions;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class CreateUser implements Validatable {

   @Test
    void testCreateUser(ITestContext context){
       Faker faker=new Faker();
       JSONObject data=new JSONObject();
       data.put("name",faker.name().fullName());
       data.put("gender","male");
       data.put("email",faker.internet().emailAddress());
       data.put("status","inactive");
       String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";

       int id=given()
               .headers("Authorization","Bearer "+bearerToken)
               .contentType("application/json")
               .body(data.toString())
       .when()
               .post("https://gorest.co.in/public/v2/users")
       .jsonPath().getInt("id");

       Reporter.log("Generated id is : "+id);
       //test level
       //context.setAttribute("user_id",id);
      //suite leve
      context.getSuite().setAttribute("user_id",id);

    }

    @Given(": payload and bearer token")
    public void payload_and_bearer_token(){
        Faker faker=new Faker();
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");
        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";

      given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString());
    }

    @When(": send the post request")
    public void send_the_post_request(){
       when().post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
    }

    @Then(": Validate the response code")
    public void validate_the_response_code(){
        then()
                .statusCode(200).log().all();


            }

    @Override
    public ValidatableResponseOptions then() {
        return null;
    }
}
