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
import Endpoints.Delete_EP;

public class Delete_TC {

	 static File log_dir ;
	 static String Endpoint ;	
	 
	 @BeforeTest
	 public static void TestSetup() {
		 
		 log_dir = Directory.Create_Log_Directory("Delete_TC");
		 Endpoint = Delete_EP.Endpoint();				
	 }
	 
		
	 
	@Test()
	public static void Executor() throws IOException {		
								
		for(int i=0; i<5; i++) {
			
			int StatusCode = API_Common_Methods.Fetch_Delete_StatusCode(Endpoint);
			System.out.println("Status Code : " + StatusCode);
			
			if(StatusCode == 204) {
				
				// Validate Status Code
				
				Assert.assertEquals(204, StatusCode);
												
				
				System.out.println("## Test Completed \n\n");
				break;				
			}
			else {
				System.out.println("::::::: Invalid Status Code : " + StatusCode);
			}
		}		
	}	
	
	@AfterTest
	public static void Test_TearDown() throws IOException {
		String TestClassName = Delete_TC.class.getName();
		API_Logs.Evidence_Creator(log_dir, TestClassName, Endpoint, " No RequestBody", "No ResponseBody");		
	}
	
}
