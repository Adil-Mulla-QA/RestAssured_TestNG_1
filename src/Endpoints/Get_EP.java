package Endpoints;

public class Get_EP {

	public static String Endpoint_Single() {
		
		String endpoint = "https://reqres.in/api/users/2";
		
		return endpoint;
	}
	
	public static String Endpoint_List() {
		
		String endpoint = "https://reqres.in/api/users?page=2";
		
		return endpoint;
	}
}




