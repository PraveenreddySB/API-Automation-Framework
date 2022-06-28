package stepDefinations;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resource.APIResources;
import resource.TestDataBuild;
import resource.Utility;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
@RunWith(Cucumber.class)
public class placeValidations extends Utility{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	
	static String place_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
    	            
        res = given().spec(requestSpecification())
        		.body(data.addPlacePayload(name,language,address));
                 System.out.println("Given Praveen");
                // System.out.println(res);
    }

	@When("User calls {string} with {string} http request")
    public void user_calls_something_with_post_http_request(String resource, String method) throws Throwable {
    	
		//constructor will be called with value of resource which you pass
    	APIResources resourceAPI = APIResources.valueOf(resource);
    	
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        
        if(method.equalsIgnoreCase("POST"))
    	response =res.when().post(resourceAPI.getResource())
        	.then().spec(resspec).extract().response();
        else if(method.equalsIgnoreCase("GET"))
        	response =res.when().get(resourceAPI.getResource())
        	.then().spec(resspec).extract().response();

  	         System.out.println("when Praveen");
    }

    @Then("API call got success with status code {int}")
    public void api_call_got_success_with_status_code_200(int statuscode) throws Throwable {
    	assertEquals(response.statusCode(),200);
    	System.out.println("Then Praveen");
    	System.out.println(response.statusCode());
    }

    @And("{string} in response body is {string}")
    public void something_in_response_body_is_something(String keyvalue, String Expectedvalue) throws Throwable {   	
    	assertEquals(getJsonPath(response, keyvalue).toString(),Expectedvalue); 	
    }
    
    @Then("Verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
    	
    	place_id = getJsonPath(response, "place_id");
    	res = given().spec(requestSpecification()).queryParam("place_id", place_id);
    	
    	user_calls_something_with_post_http_request(resource, "GET");
    	
    	String actualName = getJsonPath(response, "name");
    	assertEquals(actualName,expectedName);
    }
    
    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
    	System.out.println("Delete");
    	given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}