package soFi.TMDB.Api.AbstractBase;


import java.util.Hashtable;

import org.apache.poi.xssf.model.ThemesTable.ThemeElement;
import org.testng.Assert;
import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import groovyjarjarantlr.collections.impl.Vector;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;


public class AbstractBasePage {

 
 public   Xls_Reader TestData = null;
 public   Hashtable<String , String> TestDataTable = null;
 public   TestUtil testutil = null;
 public   ValidatableResponse validatableResponse = null;
 public   Response response = null;
 public   Vector vector =  null;



 
//Constructor:
  public AbstractBasePage (Hashtable<String , String> TestDataTable){
	  
	  this.TestDataTable = TestDataTable;
	  testutil = new TestUtil();
	  System.out.println(getClass().getSimpleName());
	 
  }
  

  /****************************************************************************REST ASSURED API Methods ***********************************************************************/ 
     
  
  
        //Validate the expected getStatuCode with the actual get status of the response passed to the method
        public void validategetStatusCode(Response response , String expectedgetstatusCode){
        	        	
    		
    		//Getting the actual Response code of the API
    		int getStatusCode = response.getStatusCode();
    		String actualgetStatusCode = Integer.toString(getStatusCode);
    		System.out.println("actualgetStatusCode : " + actualgetStatusCode);
    		//Verify the actual Response Code with the Expected Response Code
    		Assert.assertEquals(actualgetStatusCode, expectedgetstatusCode);
        }
  
  
	     
	     public Boolean verifyActionMessage(String actualActionMessage , String expectedActionMessage){
	    	 
    	    
    	   	 
    	   	 if(actualActionMessage.contains(expectedActionMessage)){
 
    		     return true;
    	   	 }   else {
    	   		 
    		     return false;
    	   		 
    	   	 }
    		
     }
	     
	     
  	     
		 
 /****************************************************************************Core Java Methods ***********************************************************************/ 
		 
		 
			/*
			 * Author : Arpan Saini
			 * Purpose : To replace something in actual string with some other String
			 * */
		 
		 public String getReplacedString(String Acutal , String Replace){
				                                     
				String FinalString = Acutal.replace("[!commontext]", Replace);
				return FinalString;
				
			}
		 
	    
}


