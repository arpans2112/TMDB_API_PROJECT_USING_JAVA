package soFi.TMDB.Api.AbstractBase;


import java.util.Hashtable; 
import org.testng.Assert;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import soFi.TMDB.Api.Configuration.Config;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;
 

public class AbstractBasePage {

 
 public   Xls_Reader TestData = null;
 public   Hashtable<String , String> TestDataTable = null;
 public   TestUtil testutil = null;
 public   ValidatableResponse validatableResponse = null;
 public   Response response = null;
 public    ExtentTest TestLog = null;



 
//Constructor:
  public AbstractBasePage (Hashtable<String , String> TestDataTable , ExtentTest TestLog){
	  
	  this.TestDataTable = TestDataTable;
	  this.TestLog = TestLog;
	  testutil = new TestUtil();
	  System.out.println(getClass().getSimpleName());
	  TestLog.log(LogStatus.INFO,  testLogPageHeadingMessage(getClass().getSimpleName()));
     
	 
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
  
  
	     
        public Boolean verifyActionMessage(String actualActionMessage , String expectedActionMessage , String verifyingKey){
	    	 
    	    
   	   	 
   	   	 if(actualActionMessage.contains(expectedActionMessage)){
   		
   	   		 TestLog.log(LogStatus.PASS, testLogActionMessageString(" [ Method = verifyActionMessage ] " + " [ Actual Contains Expected = true ] "  + " [ Actual " + verifyingKey + " = " + actualActionMessage  + " ] " + " [ Expected " + verifyingKey + "  = " + expectedActionMessage  + " ] " , Config.GreenColour) );
   		     return true;
   	   	 }   else {
   	   		 
   	   		 TestLog.log(LogStatus.FAIL, testLogActionMessageString(" [ Method = verifyActionMessage ] " + " [ Actual Contains Expected = false ] "  + " [ Actual " + verifyingKey + "  = " + actualActionMessage  + " ] " + " [ Expected " + verifyingKey + "  = " + expectedActionMessage  + " ] " , Config.RedColour) );
   		     return false;
   	   		 
   	   	 }
   		
    } 
	     
	     
		 public boolean verifyText(String actualText , String expectedText , String variableName , Boolean ExpectedResult){
				
		    if(ExpectedResult) {
		    	
		    	if (actualText.equals(expectedText)){
					 
					 System.out.println("[ Method = verifyText ]" + " [ Actual Result = true ] " + " [ Expected Result = true ] "  + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ");
					 TestLog.log(LogStatus.INFO, testLogActionMessageString("[ Method = verifyText ] " + " [ Actual Result = true ] " + " [ Expected Result = true ] "   + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ", Config.GreenColour));
					 
					 return true;
					 
				 }else {
					 
					 System.out.println(" [ Method = verifyText ] "  + " [ Actual Result = false ] " + " [ Expected Result = true ] "   + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ");
					 TestLog.log(LogStatus.INFO, testLogActionMessageString("[ Method = verifyText ] " + " [ Actual Result = false ] " + " [ Expected Result = true ] "   + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ", Config.RedColour));
					 
					 return false;
				 }
		    	
		    }else {
		    	
		    	if (actualText.equals(expectedText)){
					 
					 System.out.println("[ Method = verifyText ]" + " [ Actual Result = true ] " + " [ Expected Result = false ] "  + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ");
					 TestLog.log(LogStatus.INFO, testLogActionMessageString("[ Method = verifyText ] " + " [ Actual Result = true ] " + " [ Expected Result = false ] "   + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ", Config.RedColour));
					 
					 return false;
					 
				 }else {
					 
					 System.out.println(" [ Method = verifyText ] " + " [ Actual Result = false ] " + " [ Expected Result = false ] "  + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ");
					 TestLog.log(LogStatus.INFO, testLogActionMessageString("[ Method = verifyText ] "  + " [ Actual Result = false ] " + " [ Expected Result = false ] "   + " [ " + variableName + " Expected Value = " + expectedText + " ] "  + " [ " + variableName + " Actual Value = " + actualText + " ] ", Config.GreenColour));
					 
					 return true;
				 }
		    	
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

		    /****************************************************************************Extent Report Methods *********************************************************/  
		    
			    //To make specific changes to Heading in Extent Report	
				public String testLogTestStepMessage(String Message){
					
					return "<p><span style='font-weight:bold; color:blue; font-size:200%; text-transform: uppercase; '>" + Message + "</span></p>" ;
				}
		    
			
			  //To make specific changes to Heading in Extent Report	
					public String testLogPageHeadingMessage(String Message){
						
						return "<p><span style='font-weight:bold; color:blue; font-size:200%; background-color:Ivory;'>" + Message + "</span></p>" ;
					}
					
			 //To make specific changes to Action Message in Extent Report	
					public String testLogActionMessageString(String Message){
						
						if (Message.contains("O.K."))
						return "<p><span style='font-weight:bold; color:green; font-size:120%'>" + Message + "</span></p>" ;
						else 
						 return "<p><span style='font-weight:bold; color:red; font-size:120%'>" + Message + "</span></p>" ;
					}
					
			  
					 public String testLogActionMessageString(String Message , String Color){
							
			        	 if(Color.equals("green"))
				         return "<p><span style='font-weight:bold; color:green; font-size:120%'>" + Message + "</span></p>" ;
			        	 else if(Color.equals("red"))
			        	 return "<p><span style='font-weight:bold; color:red; font-size:120%'>" + Message + "</span></p>" ;
			        	 else if(Color.equals("blue"))
				         return "<p><span style='font-weight:bold; color:blue; font-size:120%'>" + Message + "</span></p>" ;
			        	 
			        	 return "<p><span style='font-weight:bold; color:white; font-size:120%'>" + Message + "</span></p>" ;
			     
						}
		    
}


