package soFi.TMDB.Api.TestSuite1.TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;


import static com.jayway.restassured.RestAssured.*;
import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Authentication.Pages.CreateRequestTokenFunctionPage;
import soFi.TMDB.Api.DataProviders.AuthenticationTestDataProvider;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

 
public class TS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	
	@Test(dataProvider="tS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void tS01_TC01_Authencation_CreateTestToken_Get(Hashtable<String, String> TestDataTable){
		  
		 TestLog = extent.startTest(TestDataTable.get("TestCaseName"));
		
		createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable);
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
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
		//String actualrequesttoken = validatableResponse.extract().path(CreateRequestTokenFunctionPage.request_token);
		String actualsuccess = validatableResponse.extract().path(CreateRequestTokenFunctionPage.success).toString();
		//String actualexpires_at = validatableResponse.extract().path(CreateRequestTokenFunctionPage.expires_at);
		int actualgetstatusCode = validatableResponse.extract().response().getStatusCode();
		String actualgetStatusCodestr = Integer.toString(actualgetstatusCode);
		
		
		//Step3: Validate Success message is True
		Assert.assertEquals(actualsuccess, TestDataTable.get("success"));
		
		//Step4: Validate getStatusCode
		Assert.assertEquals(actualgetStatusCodestr, TestDataTable.get("getStatusCode"));
		
		  	
		
		
	}

}
