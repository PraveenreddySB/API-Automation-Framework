Feature: Validating place API's

@AddPlaceAPI @sanity
Scenario Outline: Verify if place is being succesfully added using AddPlace API
			Given Add Place Payload "<name>" "<language>" "<address>"
			When User calls "AddPlaceAPI" with "POST" http request
			Then API call got success with status code 200
			And "status" in response body is "OK"
			And Verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
				|name |language|address                  |
				|Jason|English |Bassett rd village       |
#				|RObin|Kannada |Silk institute rd village|
		
@DeletePlaceAPI @sanity
Scenario: Verify if Delete place functionality is working
			Given DeletePlace payload
			When User calls "deletePlaceAPI" with "POST" http request
			Then API call got success with status code 200
			And "status" in response body is "OK"