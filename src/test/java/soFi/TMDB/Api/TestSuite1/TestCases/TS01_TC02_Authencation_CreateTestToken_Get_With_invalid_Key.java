package soFi.TMDB.Api.TestSuite1.TestCases;

/*
 * Author : Arpan Saini
 * Test Case Description: Validate the Response with InValid API Key. 
 * */

import java.util.Hashtable; 
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import static com.jayway.restassured.RestAssured.*;
import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Authentication.Pages.CreateRequestTokenFunctionPage;
import soFi.TMDB.Api.DataProviders.AuthenticationTestDataProvider;

 
public class TS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	
	@Test(dataProvider="tS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void tS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key(Hashtable<String, String> TestDataTable){
		
		TestLog = extent.startTest(TestDataTable.get("TestCaseName"));
		 TestLog.log(LogStatus.INFO,  testLogTestCaseDescriptionString("Test Case Description : " + TestDataTable.get("TestCaseDescription")) );  
		
		createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable , TestLog);
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		String createReequestTokenURL = createRequestTokenFunctionPage.urlCreateRequestToken();
		
		
        //Step 2: Extract value from Response 
		validatableResponse = given().
				              param("api_key", TestDataTable.get("Invalid_api_key"))
		                         
		                      .when()
				              //"https://api.themoviedb.org/3/authentication/token/new?api_key=b0c049db340cadd406e370aea287faff"
		                      .get(createReequestTokenURL)
				              .then()
				              .contentType(ContentType.JSON);
		
		//Get the actual Data from Response
		int actualstatus_codeint = validatableResponse.extract().path(CreateRequestTokenFunctionPage.status_code);
		String actualstatus_code = Integer.toString(actualstatus_codeint);
		String actualstatus_message = validatableResponse.extract().path(CreateRequestTokenFunctionPage.status_message);
		int actualgetstatusCodeint = validatableResponse.extract().response().getStatusCode();
		String actualgetstatusCode = Integer.toString(actualgetstatusCodeint);
		
	
		
		//Step4: Validate getStatusCode is : 401
		 Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualgetstatusCode, TestDataTable.get("getStatusCode"),"getStatusCode"));
		//Assert.assertEquals(actualgetstatusCode, TestDataTable.get("getStatusCode"));
		
		//Step5: Validate status_code	
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualstatus_code, TestDataTable.get("status_code"),"status_code"));
		//Assert.assertEquals(actualstatus_code, TestDataTable.get("status_code"));
		
		//Step5: Validate status_Message
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualstatus_message, TestDataTable.get("status_message"),"status_message"));
		//Assert.assertEquals(actualstatus_message, TestDataTable.get("status_message"));
	}

}
