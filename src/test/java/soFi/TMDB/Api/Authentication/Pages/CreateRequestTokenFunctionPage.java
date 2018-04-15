package soFi.TMDB.Api.Authentication.Pages;

import java.util.Hashtable;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;

import soFi.TMDB.Api.AbstractBase.AbstractBasePage;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

public class CreateRequestTokenFunctionPage extends AbstractBasePage{

	public static String createRequestTokenResource = "/3/authentication/token/new";
	public static String createRequestTokenendpoint = null;
	public static String CreateRequestTokenUrl=null;

	public CreateRequestTokenFunctionPage(Hashtable<String, String> TestDataTable , ExtentTest TestLog )  {
		super(TestDataTable, TestLog);

		// TODO Auto-generated constructor stub
	}



	public static String request_token = "request_token";
	public static String expires_at = "expires_at";
	public static String success = "success";
	public static String status_message = "status_message";
	public static String status_code = "status_code";

		public  String urlCreateRequestToken(){
	
			return PropertyFileReader.configpropertyReader("AuthenticationEndPoint") + createRequestTokenResource;
		}


	

	
      public void  VerifySuccess(Response response , String expectedSuccessMessage){
    	  
    	            String actualvalue = response.
    	        		                 then().
    	        		                 contentType(ContentType.JSON).
    	                                 extract().
    	                                 path(success);
    	                                 
    	          System.out.println("Actual success: " + actualvalue);
    	  
      }

}
