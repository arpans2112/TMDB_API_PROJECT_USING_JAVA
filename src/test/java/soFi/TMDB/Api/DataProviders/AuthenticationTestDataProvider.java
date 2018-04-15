package soFi.TMDB.Api.DataProviders;

import org.testng.annotations.DataProvider;

import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Configuration.Config;
import soFi.TMDB.Api.Utilities.TestUtil;

public class AuthenticationTestDataProvider extends AbstractBaseTestCase{
	
	
	
	@DataProvider
	public  Object[][] tS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_TestData(){
		
		final String testDataInputExcel=  Config.TestDataFileLocation + "Authentication_TestData.xlsx";
		final String sheetName = "Authentication_TestData";
		final String testDataRowName = "TS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_TestData";		
		return testutil.getTestData(AccessTestDataFile(testDataInputExcel), sheetName, testDataRowName);
	}

	@DataProvider
	public  Object[][] tS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData(){
		
		final String testDataInputExcel=  Config.TestDataFileLocation + "Authentication_TestData.xlsx";
		final String sheetName = "Authentication_TestData";
		final String testDataRowName = "TS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData";		
		return testutil.getTestData(AccessTestDataFile(testDataInputExcel), sheetName, testDataRowName);
	}
	
	@DataProvider
	public  Object[][] TS01_TC03_Authencation_CreateTestToken_Get_With_invalid_Resource_TestData(){
		
		final String testDataInputExcel=  Config.TestDataFileLocation + "Authentication_TestData.xlsx";
		final String sheetName = "Authentication_TestData";
		final String testDataRowName = "TS01_TC03_Authencation_CreateTestToken_Get_With_invalid_Resource_TestData";		
		return testutil.getTestData(AccessTestDataFile(testDataInputExcel), sheetName, testDataRowName);
	}
	
	

}
