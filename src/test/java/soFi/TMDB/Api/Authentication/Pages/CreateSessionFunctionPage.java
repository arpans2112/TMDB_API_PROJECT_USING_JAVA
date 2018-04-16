package soFi.TMDB.Api.Authentication.Pages;

import java.util.Hashtable; 
import com.relevantcodes.extentreports.ExtentTest;
import soFi.TMDB.Api.AbstractBase.AbstractBasePage;
import soFi.TMDB.Api.Utilities.PropertyFileReader;

public class CreateSessionFunctionPage extends AbstractBasePage{

	public static String createSessionResource = "/3/authentication/session/new";
	public static String createSessionUrl = null;

	public CreateSessionFunctionPage(Hashtable<String, String> TestDataTable , ExtentTest TestLog )  {
		super(TestDataTable, TestLog);

		// TODO Auto-generated constructor stub
	}



	//JSON PATH
	public static String success = "success";
	public static String session_id = "session_id";
	public static String status_code = "status_code";
	public static String status_message = "status_message";
	

		public  String urlcreateSession(){
	
			return PropertyFileReader.configpropertyReader("AuthenticationEndPoint") + createSessionResource;
		}



}
