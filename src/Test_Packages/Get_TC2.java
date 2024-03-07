package Test_Packages;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Common_Methods.API_Common_Methods;
import Common_Utilities.API_Logs;
import Common_Utilities.Directory;
import Endpoints.Get_EP;
import io.restassured.path.json.JsonPath;

public class Get_TC2 {

	 static File log_dir ;
	 static String Endpoint ;	 
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Get_TC2");
		 Endpoint = Get_EP.Endpoint_List();				
	 }	 
		
	@Test()
	public static void Executor() throws IOException {		
		
		for(int i=0; i<5; i++) {
			
			int StatusCode = API_Common_Methods.Fetch_Get_StatusCode(Endpoint);
			System.out.println("Status Code : " + StatusCode);
			
			if(StatusCode == 200) {
				
				ResponseBody = API_Common_Methods.Fetch_Get_ResponseBody(Endpoint);
				Validator(ResponseBody);				
				
				System.out.println("## Test Completed \n\n");
				break;				
			}
			else {
				System.out.println("::::::: Invalid Status Code : " + StatusCode);
			}
		}
		
	}	
	public static void Validator(String ResponseBody) {
				
		JsonPath jspRes = new JsonPath(ResponseBody);
		
		// Validate Array Values
		
		String res_Id = jspRes.getString("data[1].id");
		String res_Email  = jspRes.getString("data[1].email");
		String res_FirstName = jspRes.getString("data[1].first_name");
		String res_LastName  = jspRes.getString("data[1].last_name");
		String res_Avatar  = jspRes.getString("data[1].avatar");
		// Validate ResponseBody by Using TestNG
		
		Assert.assertNotNull(res_Id);
		
		Assert.assertEquals(res_Id, "8");
		Assert.assertEquals(res_Email, "lindsay.ferguson@reqres.in");
		Assert.assertEquals(res_FirstName, "Lindsay");		
		Assert.assertEquals(res_LastName, "Ferguson");
		Assert.assertNotNull(res_Avatar);
	}
	
	@AfterTest
	public static void Test_TearDown() throws IOException {
		String TestClassName = Get_TC2.class.getName();
		API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, null , ResponseBody );		
	}
 
}
