package Parameterization;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common_Methods.API_Common_Methods;
import Common_Utilities.API_Logs;
import Common_Utilities.Directory;
import Endpoints.Post_EP;
import io.restassured.path.json.JsonPath;

public class TestClass {

	 static File log_dir ;
	 static String Endpoint ;		
	 static String RequestBody ;
	 static String ResponseBody;
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Parameterization");
		 Endpoint = Post_EP.Endpoint();				
	 }
	 
	 // Add Annotations To Specify Parameters Get From XML FIle
	 
	 @Parameters({"Req_name", "Req_job"})
	 @Test
		public static void Executor(String Req_name, String Req_job) throws IOException {		
					
			 Endpoint = Post_EP.Endpoint();
			 RequestBody = "{\r\n"
						+ "    \"name\": \""+Req_name+"\",\r\n"
						+ "    \"job\": \""+Req_job+"\"\r\n"
						+ "}";
			
			for(int i=0; i<5; i++) {
				
				int StatusCode = API_Common_Methods.Fetch_Post_StatusCode(RequestBody, Endpoint);
				System.out.println("Status Code : " + StatusCode);
				
				if(StatusCode == 201) {
					
					ResponseBody = API_Common_Methods.Fetch_Post_ResponseBody(RequestBody, Endpoint);
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
	 
	 @AfterTest
		public static void Test_TearDown() throws IOException {
			String TestClassName = TestClass.class.getName();
			API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, RequestBody, ResponseBody );		
		}
}
