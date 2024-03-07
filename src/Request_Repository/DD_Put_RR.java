package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_Utilities.Excel_Data_Extractor;

public class DD_Put_RR {

	public static String PutRR1() throws IOException {

		ArrayList<String> data = Excel_Data_Extractor.Fetch_Data("Put_API", "Put_TC1");
		String name = data.get(1);
		String job = data.get(2);

		String requestBody = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"" + job + "\"\r\n" + "}";

		return requestBody;
	}
	public static String PutRR2() throws IOException {

		ArrayList<String> data = Excel_Data_Extractor.Fetch_Data("Put_API", "Put_TC2");
		String name = data.get(1);
		String job = data.get(2);

		String requestBody = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"" + job + "\"\r\n" + "}";

		return requestBody;
	}
	public static String PutRR3() throws IOException {

		ArrayList<String> data = Excel_Data_Extractor.Fetch_Data("Put_API", "Put_TC3");
		String name = data.get(1);
		String job = data.get(2);

		String requestBody = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"" + job + "\"\r\n" + "}";

		return requestBody;
	}
	public static String PutRR4() throws IOException {

		ArrayList<String> data = Excel_Data_Extractor.Fetch_Data("Put_API", "Put_TC4");
		String name = data.get(1);
		String job = data.get(2);

		String requestBody = "{\r\n" + "    \"name\": \"" + name + "\",\r\n" + "    \"job\": \"" + job + "\"\r\n" + "}";

		return requestBody;
	}
}
