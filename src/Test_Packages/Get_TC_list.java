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

public class Get_TC_list {

	 static File log_dir ;
	 static String Endpoint ;	 
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Get_TC_List_User");
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
		
		String res_page = jspRes.getString("page");
		String res_per_page = jspRes.getString("per_page");
		String res_total = jspRes.getString("total");
		String res_total_pages = jspRes.getString("total_pages");	
				
		// Fetching Size of Array
		int count = jspRes.getInt("data.id.size()"); 
		
		// Validate ResponseBody by Using TestNG

		Assert.assertNotNull(res_page);
		Assert.assertEquals(res_page, "2");

		Assert.assertNotNull(res_per_page);
		Assert.assertEquals(res_per_page, "6");

		Assert.assertNotNull(res_total);
		Assert.assertEquals(res_total, "12");

		Assert.assertNotNull(res_total_pages);
		Assert.assertEquals(res_total_pages, "2");

		Assert.assertNotNull(count);
		Assert.assertEquals(count, 6);	
	}
	
	@AfterTest
	public static void Test_TearDown() throws IOException {
		
		String TestClassName = Get_TC_list.class.getName();
		API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, null , null );		
	}
 
}
