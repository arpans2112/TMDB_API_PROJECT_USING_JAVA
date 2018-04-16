package soFi.TMDB.Api.TestSuite1.TestCases;

/*
 * Author : Arpan Saini
 * Test Case Description: Validate Response of [Create Session with Login] Get Method, with Valid API Key.
 *  
 * */

import java.util.Hashtable; 
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import static com.jayway.restassured.RestAssured.*;
import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Authentication.Pages.CreateRequestTokenFunctionPage;
import soFi.TMDB.Api.Authentication.Pages.CreateSessionFunctionPage;
import soFi.TMDB.Api.Authentication.Pages.CreateSessionWithLoginFunctionPage;
import soFi.TMDB.Api.Configuration.Config;
import soFi.TMDB.Api.DataProviders.AuthenticationTestDataProvider;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

 
public class TS01_TC05_Authentication_CreateSessionWithLogin_Get_With_Valid_Key extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	public  CreateSessionWithLoginFunctionPage createSessionWithLoginFunctionPage = null;
	
	@Test(dataProvider="tS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void tS01_TC01_Authencation_CreateTestToken_Get(Hashtable<String, String> TestDataTable){
		  
		TestLog = extent.startTest("TS01_TC05_Authentication_CreateSessionWithLogin_Get_With_Valid_Key");
		TestLog.log(LogStatus.INFO,  testLogTestCaseDescriptionString("Test Case Description : " + "Validate Response of Create Session with Login Get Method with Valid API Key"));
	
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		 createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable , TestLog);
		 String createReequestTokenURL = createRequestTokenFunctionPage.urlCreateRequestToken();
		
		
        //Step 2: Extract value from Response 
		validatableResponse = given().
				              param("api_key", PropertyFileReader.configpropertyReader("api_key"))
		                         
		                      .when()
				              //"https://api.themoviedb.org/3/authentication/token/new?api_key=b0c049db340cadd406e370aea287faff"
		                      .get(createReequestTokenURL)
				              .then()
				              .contentType(ContentType.JSON);
		
		//Get the actual Data from Response
		 Config.request_token  = validatableResponse.extract().path(CreateRequestTokenFunctionPage.request_token);
		 System.out.println("Request Token : " + Config.request_token);
		 TestLog.log(LogStatus.INFO, testLogTestStepMessage("Request Token : " + Config.request_token));
		 String actualsuccess = validatableResponse.extract().path(CreateRequestTokenFunctionPage.success).toString();
		 String actualexpires_at = validatableResponse.extract().path(CreateRequestTokenFunctionPage.expires_at);
		 TestLog.log(LogStatus.INFO, testLogTestStepMessage("expires_at : " + actualexpires_at));
		  int actualgetstatusCode = validatableResponse.extract().response().getStatusCode();
		String actualgetStatusCodestr = Integer.toString(actualgetstatusCode);
		
		
		//Step3: Validate Success message is True
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualsuccess, TestDataTable.get("success"),"success"));
	
		//Step4: Validate getStatusCode
		Assert.assertTrue(createRequestTokenFunctionPage.verifyActionMessage(actualgetStatusCodestr, TestDataTable.get("getStatusCode"), "getStatusCode"));
	}
	
	
	@Test(dataProvider="tS1_TC05_Authentication_CreateSessionWithLogin_Get_With_Valid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class, dependsOnMethods = { "tS01_TC01_Authencation_CreateTestToken_Get" })
	public void tS1_TC05_Authentication_CreateSessionWithLogin_Get_With_Valid_Key(Hashtable<String, String> TestDataTable){
		
		// TestLog = extent.startTest(TestDataTable.get("TestCaseName"));
		// TestLog.log(LogStatus.INFO,  testLogTestCaseDescriptionString("Test Case Description : " + TestDataTable.get("TestCaseDescription")) ); 
		
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		 createSessionWithLoginFunctionPage = new CreateSessionWithLoginFunctionPage(TestDataTable , TestLog);
		 CreateSessionWithLoginFunctionPage.createSessionWithLoginUrl = createSessionWithLoginFunctionPage.urlcreateSessionWithLogin();
	
/*		 System.out.println("URL : " + CreateSessionWithLoginFunctionPage.createSessionWithLoginUrl);
		 System.out.println("Request Token : " + Config.request_token);	
		 System.out.println("api_key : "+ PropertyFileReader.configpropertyReader("api_key"));
		 System.out.println("username : " + TestDataTable.get("username"));
		 System.out.println("password : " + TestDataTable.get("password"));
		 System.out.println("request_token :" +  Config.request_token);*/
		 
        //Step 2: Extract value from Response 
		validatableResponse = given()
				              .param("api_key", PropertyFileReader.configpropertyReader("api_key"))
				              .param("username", TestDataTable.get("username"))
				              .param("password", TestDataTable.get("password"))
				              .param("request_token", Config.request_token)
				              
		                         
		                      .when()
				              //"https://api.themoviedb.org/3/authentication/token/new?api_key=b0c049db340cadd406e370aea287faff"
		                      .get(CreateSessionWithLoginFunctionPage.createSessionWithLoginUrl)
				              .then()
				              .contentType(ContentType.JSON);
		
		//Get the actual Data from Response
		
		 String actualsuccess = validatableResponse.extract().path(CreateSessionFunctionPage.success).toString();
		 int actualgetstatusCode = validatableResponse.extract().response().getStatusCode();
		 String actualgetStatusCodestr = Integer.toString(actualgetstatusCode);
		 System.out.println("get Status Code : " + actualgetStatusCodestr);
		
		
		//Step3: Validate Success message is True
		Assert.assertTrue(createSessionWithLoginFunctionPage.verifyActionMessage(actualsuccess, TestDataTable.get("success"),"success"));
	
		//Step4: Validate getStatusCode
		Assert.assertTrue(createSessionWithLoginFunctionPage.verifyActionMessage(actualgetStatusCodestr, TestDataTable.get("getStatusCode"), "getStatusCode"));
		
	
	}

}
