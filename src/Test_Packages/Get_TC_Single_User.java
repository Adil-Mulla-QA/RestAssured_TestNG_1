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

public class Get_TC_Single_User {

	 static File log_dir ;
	 static String Endpoint ;		
	 
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Get_TC_Single_User");
		 Endpoint = Get_EP.Endpoint_Single();				
	 }
	 
		@AfterTest
		public static void Test_TearDown() throws IOException {
			String TestClassName = Get_TC_Single_User.class.getName();
			API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, null , ResponseBody );		
		}
	 
	@Test()
	public static void Executor() throws IOException {		
		
		for(int i=0; i<5; i++) {
			
			int StatusCode = API_Common_Methods.Fetch_Get_StatusCode(Endpoint);
			System.out.println("Status Code : " + StatusCode);
			
			if(StatusCode == 200) {
				
				ResponseBody = API_Common_Methods.Fetch_Get_ResponseBody(Endpoint);
				System.out.println(ResponseBody);
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
		
		String res_Id = jspRes.getString("data.id");
		String res_Email  = jspRes.getString("data.email");
		String res_FirstName = jspRes.getString("data.first_name");
		String res_LastName  = jspRes.getString("data.last_name");
		
		Assert.assertNotNull(res_Id);
		Assert.assertEquals(res_Id, "2");
		Assert.assertEquals(res_Email, "janet.weaver@reqres.in");
		Assert.assertEquals(res_FirstName, "Janet");		
		Assert.assertEquals(res_LastName, "Weaver");
	}
}
