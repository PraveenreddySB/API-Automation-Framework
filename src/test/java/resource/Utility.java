package resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	public static RequestSpecification req;  //declare static to use the varibale throught the class
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (req==null) // Examples test data should not replace with new data 1st time null 2nd it wil go to return req
		{
			
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
        req = new RequestSpecBuilder().setBasePath("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
        	.addFilter(RequestLoggingFilter.logRequestTo(log))
        	.addFilter(ResponseLoggingFilter.logResponseTo(log))
        	.setContentType(ContentType.JSON).build();
             //System.out.println(getGlobalValues("baseUrl"));
        return req;
		}
		return req;
        
	}
	
	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Backup\\RestAssuredAPIAutoamtionTesting\\APIAutomationFramework\\src\\test\\java\\resource\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
