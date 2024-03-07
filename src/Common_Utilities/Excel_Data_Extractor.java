package Common_Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Data_Extractor {

	public static ArrayList<String> Fetch_Data(String SheetName, String TestCase_Name) throws IOException{
		
		ArrayList<String> ArrayData = new ArrayList<>();
		
		String ProjectDir = System.getProperty("user.dir");
		
		FileInputStream fis = new FileInputStream(ProjectDir + "\\Data_Files\\API_Data.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int count = wb.getNumberOfSheets();
		//System.out.println("Count of Sheets : "+count);
		//System.out.println("------------");
			
		for(int i=0; i<count; i++) {
			
			String sheetName = wb.getSheetName(i);
			//System.out.println("Sheet Name : " + sheetName);
						
			if(sheetName.equals(SheetName)) {
				
				XSSFSheet sheet = wb.getSheetAt(i);
				
				Iterator<Row> row = sheet.iterator();
				row.next();
				
				while(row.hasNext())
				{
					Row datarow = row.next();
					String TCName = datarow.getCell(0).getStringCellValue();
					//System.out.println("Test Case Name : " + TCName);
					
					if(TCName.equalsIgnoreCase(TestCase_Name))
					{
						Iterator<Cell> cellvalue = datarow.iterator();
						
						while(cellvalue.hasNext())
						{
							String testData = cellvalue.next().getStringCellValue();
							//System.out.println(testData);
							ArrayData.add(testData);
						}
					}
				}
				//System.out.println("------");
				break;							
			}			
		}
		wb.close();
		return ArrayData;		
	}								
}

