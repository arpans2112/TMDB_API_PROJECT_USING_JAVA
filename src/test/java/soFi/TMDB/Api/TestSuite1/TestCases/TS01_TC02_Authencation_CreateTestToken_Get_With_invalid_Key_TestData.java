package soFi.TMDB.Api.TestSuite1.TestCases;

import java.util.Hashtable;  
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Authentication.Pages.CreateRequestTokenFunctionPage;
import soFi.TMDB.Api.DataProviders.AuthenticationTestDataProvider;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

 
public class TS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	
	@Test(dataProvider="tS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void tS01_TC02_Authencation_CreateTestToken_Get_With_invalid_Key_TestData(Hashtable<String, String> TestDataTable){
		
		//Step 2:  Get Method Call
		  
		
		createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable);
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		String createReequestTokenURL = createRequestTokenFunctionPage.urlCreateRequestToken();
		
		//Step 2: Set parameter 
		 response = given().
				    param("api_key", PropertyFileReader.configpropertyReader("api_key")).
		           
		//Step 2 : Get the response of the API
                    when().get(createReequestTokenURL);
		
		//Step 3: Validating Expected HTTP Status Code with the Expected Status Code. 
		createRequestTokenFunctionPage.validategetStatusCode(response , TestDataTable.get("ExpectedgetStatusCode"));
	
	  	
	}

}
