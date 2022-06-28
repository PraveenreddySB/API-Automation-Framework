package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws Throwable {
		
		placeValidations placeValObj = new placeValidations();
		
		if(placeValidations.place_id == null)  //call static varibale (place_id) using the class name not by creating objects
		{
		placeValObj.add_place_payload("Praveen", "India", "Asia");
		placeValObj.user_calls_something_with_post_http_request("AddPlaceAPI", "POST");
		placeValObj.verify_place_id_created_maps_to_using("Praveen", "getPlaceAPI");		
		}
	}

}
