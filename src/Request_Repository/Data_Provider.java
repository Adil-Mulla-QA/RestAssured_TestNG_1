package Request_Repository;

import org.testng.annotations.DataProvider;

public class Data_Provider {

	 //Declare Method which provides data	 
	
	@DataProvider()
	 public Object[][] post_requestBody(){
		 return new Object[][]
				 {
			 		{"ABC","000"},
			 		{"PQR", "111"},
			 		{"XYZ","222"}
				 };
		 	 }
}


