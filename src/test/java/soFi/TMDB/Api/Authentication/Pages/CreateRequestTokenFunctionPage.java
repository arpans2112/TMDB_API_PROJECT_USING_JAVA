package soFi.TMDB.Api.Authentication.Pages;

import static com.jayway.restassured.RestAssured.when;

import java.util.Hashtable;

import org.testng.internal.TestMethodWithDataProviderMethodWorker;

import com.jayway.restassured.response.Response;

import soFi.TMDB.Api.AbstractBase.AbstractBasePage;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

public class CreateRequestTokenFunctionPage extends AbstractBasePage{

	public static String createRequestTokenResource = "/3/authentication/token/new";
	public static String createRequestTokenendpoint = null;
	public static String CreateRequestTokenUrl=null;

	public CreateRequestTokenFunctionPage(Hashtable<String, String> TestDataTable)  {
		super(TestDataTable);

		// TODO Auto-generated constructor stub
	}



	public static String request_token = "JSON_PATH=request_token";
	public static String expires_at = "JSON_PATH=expires_at";
	public static String success = "JSON_PATH=success";

	public  String urlCreateRequestToken(){

		return PropertyFileReader.configpropertyReader("AuthenticationEndPoint") + createRequestTokenResource;
	}


	

	
      public void  verifySuccess(Response response , String expectedSuccessMessage){
    	  
    	  
    	  
      }

}
