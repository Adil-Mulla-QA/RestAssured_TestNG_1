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
import Endpoints.Put_EP;
import Request_Repository.Put_RR;
import io.restassured.path.json.JsonPath;

public class Put_TC1 {

	 static File log_dir ;
	 static String Endpoint ;		
	 static String RequestBody ;
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Put_TC1");
		 Endpoint = Put_EP.Endpoint();				
	 }
	 
		@AfterTest
		public static void Test_TearDown() throws IOException {
			String TestClassName = Put_TC1.class.getName();
			API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, RequestBody, ResponseBody );		
		}
	 
	@Test()
	public static void Executor() throws IOException {		
				
		RequestBody = Put_RR.PutRR1();
		
		for(int i=0; i<5; i++) {
			
			int StatusCode = API_Common_Methods.Fetch_Put_StatusCode(RequestBody, Endpoint);
			System.out.println("Status Code : " + StatusCode);
			
			if(StatusCode == 200) {
				
				ResponseBody = API_Common_Methods.Fetch_Put_ResponseBody(RequestBody, Endpoint);
				Validator(RequestBody, ResponseBody);				
				
				System.out.println("## Test Completed \n\n");
				break;				
			}
			else {
				System.out.println("::::::: Invalid Status Code : " + StatusCode);
			}
		}
		
	}	
	public static void Validator(String RequestBody, String ResponseBody) {
		
		JsonPath jspReq = new JsonPath(RequestBody);		
		String req_Name = jspReq.getString("name");
		String req_Job  = jspReq.getString("job");
		
		JsonPath jspRes = new JsonPath(ResponseBody);		
		String res_Name = jspRes.getString("name");
		String res_Job  = jspRes.getString("job");
		
		Assert.assertEquals(res_Name, req_Name);
		Assert.assertEquals(res_Job, req_Job);
	}
}
