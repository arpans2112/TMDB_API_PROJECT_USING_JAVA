package soFi.TMDB.Api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadXlsxFile {
	
	@Test
	public void readTestDataFile() throws InvalidFormatException, IOException{
		
		File f = new File("D:\\Companies Preparations\\SoFi_TMDB_API_PROJECT_USING_JAVA\\SoFi_TMDB_Api_Java_Project\\src\\test\\java\\soFi\\TMDB\\Api\\InputData\\Authentication_TestData.xlsx");
		FileInputStream fis = new FileInputStream(f);	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Authentication_TestData");
		System.out.println(sheet.getRow(0).getCell(0).getStringCellValue());
		
		
		
	}

}
