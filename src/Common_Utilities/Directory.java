package Common_Utilities;

import java.io.File;

public class Directory {
	
	public static File Create_Log_Directory(String log_dir) {
		
		String ProjectDir = System.getProperty("user.dir");
		
		File directory = new File(ProjectDir + "\\API_Logs\\" + log_dir);
		
		if (directory.exists()) {
			
			directory.delete();
			System.out.println(directory + " : --- Existong Directorry Deleted --");
			
			directory.mkdir();
			System.out.println(directory + " : ---- New Directorry Created -----");
		}
		else {
			
			directory.mkdir();
			System.out.println(directory + " : ---- New Directorry Created -----");
		}
		
		return directory;		
	}
}


