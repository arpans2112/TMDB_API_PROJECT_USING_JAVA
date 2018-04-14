package soFi.TMDB.Api.AbstractBase;
import java.util.Hashtable;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;


public class AbstractBasePage {

 
 public   Xls_Reader TestData = null;
 public   Hashtable<String , String> TestDataTable = null;
 public   TestUtil testutil = null; 


 
//Constructor:
  public AbstractBasePage (Hashtable<String , String> TestDataTable){
	  
	  this.TestDataTable = TestDataTable;
	  testutil = new TestUtil();
	  System.out.println(getClass().getSimpleName());
	 
  }
  

  /****************************************************************************Selenium API Methods ***********************************************************************/ 
   
   /*
    * Author : Arpan Saini
    * Purpose: To get the Object ID out of Object passed as an Argument eg. object name=userid then it will return only userid
    * */
   public  String getObjectid(String Object){
	  
	String ObjectID = null;
	  if(Object.contains("NAME=")){
		   
		  ObjectID = Object.substring(5);
		   }else if(Object.contains("ID=")){
			   
			   ObjectID = Object.substring(3);
		   }else if(Object.contains("XPATH=")){
			   
			   ObjectID = Object.substring(6);
		   }
	  
	  return ObjectID;
  }
   
   		 
		 
	
	  	 
	  	 
	    		
	     
	     
	     
	     public Boolean verifyActionMessage(String actualActionMessage , String expectedActionMessage){
	    	 
    	    
    	   	 
    	   	 if(actualActionMessage.contains(expectedActionMessage)){
    		
    	 //  		 TestLog.log(LogStatus.PASS, testLogActionMessageString(" [ Method = verifyActionMessage ] " + " [ Actual Contains Expected = true ] "  + " [ Actual Action Message  = " + actualActionMessage  + " ] " + " [ Expected Action Message  = " + expectedActionMessage  + " ] " , ZionsBancsConstants.GreenColour) );
    		     return true;
    	   	 }   else {
    	   		 
    	   //		 TestLog.log(LogStatus.FAIL, testLogActionMessageString(" [ Method = verifyActionMessage ] " + " [ Actual Contains Expected = false ] "  + " [ Actual Action Message  = " + actualActionMessage  + " ] " + " [ Expected Action Message  = " + expectedActionMessage  + " ] " , ZionsBancsConstants.RedColour) );
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


