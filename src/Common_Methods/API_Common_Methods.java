package Common_Methods;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class API_Common_Methods {

	// Post Method
	
	public static int Fetch_Post_StatusCode(String RequestBody, String Endpoint) {
		
		int statusCode = given()
				.contentType(ContentType.JSON)
				.body(RequestBody)
		.when()
				.post(Endpoint)
		.then()
				.extract().statusCode(); 
		
		return statusCode;
	}
	public static String Fetch_Post_ResponseBody(String RequestBody, String Endpoint) {
		
		String responseBody = 
				
				given()
					.contentType(ContentType.JSON)
					.body(RequestBody)
				.when()
					.post(Endpoint)
				.then()
					.extract().response().asString();
		
		return responseBody;
	}
	
	// Put Method
	
	public static int Fetch_Put_StatusCode(String RequestBody, String Endpoint) {
		
		int statusCode = given()
				.contentType(ContentType.JSON)
				.body(RequestBody)
		.when()
				.put(Endpoint)
		.then()
				.extract().statusCode(); 
		
		return statusCode;
	}
	public static String Fetch_Put_ResponseBody(String RequestBody, String Endpoint) {
		
		String responseBody = 
				
				given()
					.contentType(ContentType.JSON)
					.body(RequestBody)
				.when()
					.put(Endpoint)
				.then()
					.extract().response().asString();
		
		return responseBody;
	}
	
	// Patch Method
	
	public static int Fetch_Patch_StatusCode(String RequestBody, String Endpoint) {
		
		int statusCode = given()
				.contentType(ContentType.JSON)
				.body(RequestBody)
		.when()
				.patch(Endpoint)
		.then()
				.extract().statusCode(); 
		
		return statusCode;
	}
	public static String Fetch_Patch_ResponseBody(String RequestBody, String Endpoint) {
		
		String responseBody = 
				
				given()
					.contentType(ContentType.JSON)
					.body(RequestBody)
				.when()
					.patch(Endpoint)
				.then()
					.extract().response().asString();
		
		return responseBody;
	}
	
	// Get Method
	
	public static int Fetch_Get_StatusCode(String Endpoint) {
		
		int statusCode = given()
								
		.when()
				.get(Endpoint)
		.then()
				.extract().statusCode(); 
		
		return statusCode;
	}
	public static String Fetch_Get_ResponseBody(String Endpoint) {
		
		String responseBody = 
				
				given()
					
				.when()
					.get(Endpoint)
				.then()
					.extract().response().asString();
		
		return responseBody;
	}
	
	// Delete Method
	
	public static int Fetch_Delete_StatusCode(String Endpoint) {
		
		int statusCode = given()
				
		.when()
				.delete(Endpoint)
		.then()
				.extract().statusCode(); 
		
		return statusCode;
	}
	
}
