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

public class Get_TC5 {

	 static File log_dir ;
	 static String Endpoint ;	 
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Get_TC5");
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
		
		String res_Url = jspRes.getString("support.url");
		String res_Text  = jspRes.getString("support.text");
		
//		System.out.println(res_Url);
//		System.out.println(res_Text);
		
		// Validate ResponseBody by Using TestNG
		
		Assert.assertNotNull(res_Url);
		Assert.assertNotNull(res_Text);
		
		Assert.assertEquals(res_Url, "https://reqres.in/#support-heading");
		
		Assert.assertTrue(res_Text.contains("ReqRes"));
	}
	
	@AfterTest
	public static void Test_TearDown() throws IOException {
		String TestClassName = Get_TC5.class.getName();
		API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, null , ResponseBody );		
	}
 
}
