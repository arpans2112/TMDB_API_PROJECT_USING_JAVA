package soFi.TMDB.Api.AbstractBase;

import static com.jayway.restassured.RestAssured.when;

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

import com.jayway.restassured.response.Response;

import soFi.TMDB.Api.Utilities.PropertyFileReader;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;


public class AbstractBaseTestCase {
	
	
	public   Xls_Reader TestData = null;
	public   Hashtable<String, String> TestDataTable = null;
	public   Response response = null;
	public   TestUtil testutil = new TestUtil();
	
	

	

	 public  String TestCaseName = null;
	 
	
	 
   @BeforeSuite
   public void beforeSuite(){
	   

   }
	 
	 
	 
	
	@BeforeTest
	public void beforeTest(){
		
		
		
	}
	
	
	
	@BeforeClass
	public void beforeClass(){ 
		
		
	}
	
	

	@BeforeMethod
    	public  void  beforemethod(Method method ){
		
	    String classname = getClass().getSimpleName();
 	    String methodName = method.getName();
		System.out.println("Class Name : " + classname + " Method Name: " + methodName);
		
		
		  
	}
	
	
	@AfterMethod
	public  void afterMethod(ITestResult result){}
		
    
	
		
				
	
	
	
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
	
	
           
     	}
