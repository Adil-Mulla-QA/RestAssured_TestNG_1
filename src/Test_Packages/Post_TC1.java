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
import Endpoints.Post_EP;
import Request_Repository.Post_RR;
import io.restassured.path.json.JsonPath;

public class Post_TC1 extends API_Common_Methods {
	
	 static File log_dir ;
	 static String Endpoint ;		
	 static String RequestBody ;
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Post_TC1");
		 Endpoint = Post_EP.Endpoint();				
	 }	 	
	 
	 @AfterTest
	public static void Test_TearDown() throws IOException {
		 
		String TestClassName = Post_TC1.class.getName();
		API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, RequestBody, ResponseBody );			
	}

	@Test()
	public static void Executor() throws IOException {
					
		RequestBody  = Post_RR.PostRR1();				
		
		int statusCode = Fetch_Post_StatusCode(RequestBody, Endpoint);
		System.out.println("Status Code : " + statusCode);
				
		for(int i=0; i<5; i++) {
			
			if(statusCode == 201)
			{
				ResponseBody = Fetch_Post_ResponseBody(RequestBody, Endpoint);
				Post_TC1.Validator(RequestBody, ResponseBody);
								
				System.out.println("## Test Completed \n\n");
				break;
			}
			else {
				System.out.println(":::::: Invalid Status Code ::::::::");
			}			
		}		
	}	

	public static void Validator(String RequestBody, String ResponseBody) {
		
		JsonPath jspReq = new JsonPath(RequestBody);
		
		String req_Name = jspReq.getString("name");
		String req_job  = jspReq.getString("job");
		
		JsonPath jspRes = new JsonPath(ResponseBody);
		
		String res_Name = jspRes.getString("name");
		String res_job  = jspRes.getString("job");
		
		Assert.assertEquals(res_Name, req_Name);
		Assert.assertEquals(res_job, req_job);
		
	}
	
	
}
