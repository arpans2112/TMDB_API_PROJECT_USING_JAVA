package soFi.TMDB.Api.TestSuite1.TestCases;

/*
 * Author : Arpan Saini
 * Test Case Description: Validate the Response with InValid Resource. 
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
import soFi.TMDB.Api.Utilities.PropertyFileReader;

 
public class TS01_TC03_Authencation_CreateTestToken_Get_With_invalid_Resource extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	
	@Test(dataProvider="TS01_TC03_Authencation_CreateTestToken_Get_With_invalid_Resource_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void TS01_TC03_Authencation_CreateTestToken_Get_With_invalid_Resource_TestData(Hashtable<String, String> TestDataTable){
		
		 TestLog = extent.startTest(TestDataTable.get("TestCaseName"));
		 TestLog.log(LogStatus.INFO,  testLogTestCaseDescriptionString("Test Case Description : " + TestDataTable.get("TestCaseDescription")) ); 
		
		createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable , TestLog);
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		String createReequestTokenURL = createRequestTokenFunctionPage.urlCreateRequestToken();
		
		createReequestTokenURL = createReequestTokenURL.replaceAll("/new", "/old");
		
        //Step 2: Extract value from Response 
		validatableResponse = given()
				             .param("api_key", PropertyFileReader.configpropertyReader("api_key"))
		                         
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
		
	
		
		//Step4: Validate getStatusCode is : 404
		//Assert.assertEquals(actualgetstatusCode, TestDataTable.get("getStatusCode"));
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualgetstatusCode, TestDataTable.get("getStatusCode"),"getStatusCode"));
		//Step5: Validate status_code : 34
		//Assert.assertEquals(actualstatus_code, TestDataTable.get("status_code"));
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualstatus_code, TestDataTable.get("status_code"), "status_code"));
	
		
		//Step5: Validate status_Message : The resource you requested could not be found.
		//Assert.assertEquals(actualstatus_message, TestDataTable.get("status_message"));
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualstatus_message, TestDataTable.get("status_message"),"status_message"));
	}

}
