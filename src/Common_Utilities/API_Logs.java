package Common_Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class API_Logs {

	public static File Evidence_Creator (File DirectoryName ,String FileName, String Endpoint, String RequestBody, String ResponseBody) 
			throws IOException {
		
		String ProjectDir = System.getProperty("user.dir");
		
		File newFile = new File(DirectoryName + "\\" + FileName + ".txt");
				
		System.out.println("::::To Save Request Body & Response Body New File Created As : " + newFile.getName());
		
		FileWriter dataWriter = new FileWriter(newFile);
	
		dataWriter.write("Endpoint : " +Endpoint+  "\n\n");
		dataWriter.write("Request Body :" +RequestBody+ "\n\n");
		dataWriter.write("Response Body : " + ResponseBody);
		
		dataWriter.close();
		
		return newFile;
		
	}
}

