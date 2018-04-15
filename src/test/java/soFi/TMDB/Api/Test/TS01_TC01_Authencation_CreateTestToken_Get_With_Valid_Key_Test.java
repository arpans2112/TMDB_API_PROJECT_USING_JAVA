package soFi.TMDB.Api.Test;

import java.util.Hashtable;  
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.*;
import soFi.TMDB.Api.AbstractBase.AbstractBaseTestCase;
import soFi.TMDB.Api.Authentication.Pages.CreateRequestTokenFunctionPage;
import soFi.TMDB.Api.DataProviders.AuthenticationTestDataProvider;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

 
public class TS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_Test extends AbstractBaseTestCase{
	
	
	public  CreateRequestTokenFunctionPage  createRequestTokenFunctionPage = null;
	
	@Test(dataProvider="tS01_TC01_Authencation_CreateTestToken_Get_With_Valid_Key_TestData" , dataProviderClass = AuthenticationTestDataProvider.class)
	public void tS01_TC01_Authencation_CreateTestToken_Get(Hashtable<String, String> TestDataTable){
		
		//Step 2:  Get Method Call
		  
		
		createRequestTokenFunctionPage = new CreateRequestTokenFunctionPage(TestDataTable);
		//Step 1 : Get Create Request Token API  URL that includes End point and Resource
		String createReequestTokenURL = createRequestTokenFunctionPage.urlCreateRequestToken();
		
		validatableResponse = given().
				              param("api_key", PropertyFileReader.configpropertyReader("api_key"))
		                         
		                      .when()
				              //"https://api.themoviedb.org/3/authentication/token/new?api_key=b0c049db340cadd406e370aea287faff"
		                      .get(createReequestTokenURL)
				              .then()
				              .contentType(ContentType.JSON);
		           
		String requesttoken = validatableResponse.extract().path("request_token");
		String success = validatableResponse.extract().path("success").toString();
		String expires_at = validatableResponse.extract().path("expires_at");
		int status_Code = validatableResponse.extract().response().getStatusCode();
		System.out.println(requesttoken);
		System.out.println(success);
		System.out.println(expires_at);
		System.out.println("Status Code : " + status_Code);
				              
				              
		
	}

}
