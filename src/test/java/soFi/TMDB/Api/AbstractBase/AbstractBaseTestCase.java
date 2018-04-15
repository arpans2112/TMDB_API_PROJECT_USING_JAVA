package soFi.TMDB.Api.AbstractBase;



import java.lang.reflect.Method;
import java.util.Hashtable;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import soFi.TMDB.Api.Utilities.ExtentManager;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;


public class AbstractBaseTestCase {
	
	
	public   Xls_Reader TestData = null;
	public   Hashtable<String, String> TestDataTable = null;
	public   Response response = null;
	public   ValidatableResponse validatableResponse = null;
	public   TestUtil testutil = new TestUtil();
	
	 //Extent Report Variables
	 public   static  ExtentReports extent = null;
	 public   ExtentTest TestLog= null;

	

	 public  String TestCaseName = null;
	 public String classname = null;
	 
	
	 
   @BeforeSuite
   public void beforeSuite(){
	   

   }
	 
	 
	 
	
	@BeforeTest
	public void beforeTest(){
		
		  extent = ExtentManager.getInstance(); 
		
	}
	
	
	
	@BeforeClass
	public void beforeClass(){ 
		
		
	}
	
	

	@BeforeMethod
    	public  void  beforemethod(Method method ){
		
	     classname = getClass().getSimpleName();
 	    String methodName = method.getName();
		System.out.println("Class Name : " + classname + " Method Name: " + methodName);
		
		
		  
	}
	
	
	@AfterMethod
	public  void afterMethod(ITestResult result){
		
		//Extent Report Status 
		if (result.getStatus() == ITestResult.FAILURE){
		     System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = Failed ]");
			TestLog.log(LogStatus.FAIL, testLogTestCaseDescriptionString(classname + " Failed "));
			
			TestLog.log(LogStatus.FAIL, result.getThrowable());
		
		} else if (result.getStatus() == ITestResult.SKIP){
        	
			System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = Skipped ]");
        	TestLog.log(LogStatus.SKIP, testLogTestCaseDescriptionString(classname + " Skipped "));
        	TestLog.log(LogStatus.SKIP, result.getThrowable());
        	
        }else if(result.getStatus() == ITestResult.SUCCESS){
        	
        	System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = PASSED ]");
        	TestLog.log(LogStatus.PASS, testLogTestCaseDescriptionString(classname + " PASSED "));
        	
        	
        }
		
		
		  if(extent!=null){
				 
				extent.endTest(TestLog);
			    extent.flush(); 
			    
			 
			}
		
	}
		
    
	
		
				
	
	
	
	@AfterClass
	public void afterClass(){	   
		   
		   
	}
	
	@AfterTest
	public void afterTest(){
		
		 
		
	}
	
	@AfterSuite
	public void afterSuite()
	{
		  
		
	}
	
	
	public void startTestcase(Hashtable<String, String> TestDataTable){
		
		TestCaseName = TestDataTable.get("TestCaseDescription");
	}
	

	/*
	 * Author : To chcek if the runmode of the TestData is Y or N in the Input Sheet
	 * 
	 * */
	public void checkTestDataRunMode(Hashtable<String,String> TestDataTable){
		
       if(!TestDataTable.get("RunMode").equalsIgnoreCase("Y")){
    	   	
       	//    TestLog.log(LogStatus.SKIP, testLogActionMessageString("[Test Data RowID = " + TestDataTable.get("RowID")+"]  " + "[RunMode = " + TestDataTable.get("RunMode") + "]"));
			 throw new SkipException("[RowID = "  +   TestDataTable.get("RowID")+"]" + "[RunMode = " + TestDataTable.get("RunMode") + "]" );
		 }
		
	    }
	


	



	/*
	 * Create the Xls_Reader Object for a file
	 * */
       public  Xls_Reader AccessTestDataFile(String file){	   
	   TestData = new Xls_Reader(file);
	   return TestData;
      }
	

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
    				
    				//To make specific changes to TestCase Description Message in Extent Report	
    			
    				public String testLogTestCaseDescriptionString(String Message){
    					
    					return "<p><span style='font-weight:bold; color:yellow; font-size:150%'>" + Message + "</span></p>" ;
    					
    				}
    				
    				
    				 public String testLogActionMessageString(String Message , String Color){
    						
    		        	 if(Color.equals("green"))
    			         return "<p><span style='font-weight:bold; color:green; font-size:120%'>" + Message + "</span></p>" ;
    		        	 else if(Color.equals("red"))
    		        	 return "<p><span style='font-weight:bold; color:red; font-size:120%'>" + Message + "</span></p>" ;
    		        	 else if(Color.equals("blue"))
    		        	 return "<p><span style='font-weight:bold; color:blue; font-size:150%'>" + Message + "</span></p>" ;
    		     
    		        	 return "<p><span style='font-weight:bold; color:White; font-size:120%'>" + Message + "</span></p>" ;
    					}
       
           
     	}
