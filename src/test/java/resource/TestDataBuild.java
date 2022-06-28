package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.AddGetPlaceDetails;
import pojo.location;

public class TestDataBuild {
	
	public AddGetPlaceDetails addPlacePayload(String name, String language, String address) {
		
		AddGetPlaceDetails addplace = new AddGetPlaceDetails();
		
		addplace.setAccuracy(50);
		addplace.setAddress(address);
		addplace.setLanguage(language);
		addplace.setName(name);
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setWebsite("https://rahulshettyacademy.com");
		
		List<String> myList = new ArrayList<String>();		
		myList.add("shoe park");
		myList.add("shop");
		addplace.setTypes(myList);
		
		location loc = new location();
		loc.setLatitude(-38.383494);
		loc.setLongitude(33.427362);		
		addplace.setLocation(loc);
		return addplace;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\": \""+placeID+"\"\r\n"
				+ "}";
	}
}
