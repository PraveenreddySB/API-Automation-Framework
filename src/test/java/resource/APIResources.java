package resource;

//enum is special class in java which has collections of constant and methods
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource; //private variable to local

	APIResources(String resource) {
		
		this.resource = resource;  // assign to local variable 
	}
	
	public String getResource() 
	{
		return resource;  // return
		
	}
}
