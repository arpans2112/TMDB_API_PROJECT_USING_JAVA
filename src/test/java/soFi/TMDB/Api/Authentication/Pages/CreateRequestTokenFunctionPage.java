package soFi.TMDB.Api.Authentication.Pages;

import java.util.Hashtable;

import soFi.TMDB.Api.AbstractBase.AbstractBasePage;

public class CreateRequestTokenFunctionPage extends AbstractBasePage{

	public static String createRequestTokenResource = "//3//authentication//token//new";
	public static String createRequestTokenApiKeyParameter = "?api_key=";
	
	public CreateRequestTokenFunctionPage(Hashtable<String, String> TestDataTable) {
		super(TestDataTable);
		// TODO Auto-generated constructor stub
	}
	
	
	public static String request_token = "JSON_PATH=request_token";
	public static String expires_at = "JSON_PATH=expires_at";
	public static String success = "JSON_PATH=success";
	
	
	
	
	
}
