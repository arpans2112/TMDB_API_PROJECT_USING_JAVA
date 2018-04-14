package soFi.TMDB.Api.AbstractBase;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import soFi.TMDB.Api.Utilities.PropetyFileReader;
import soFi.TMDB.Api.Utilities.TestUtil;
import soFi.TMDB.Api.Utilities.Xls_Reader;


public class AbstractBaseTestCase {
	
	
	 public   static  Xls_Reader TestData = null;
	 public   Hashtable<String, String> TestDataTable = null;
	 public   static TestUtil testutil = null; 

	 
	 //DDBC Connection Variables
	 public  Connection connection = null;
	 public  Statement st = null;
	 public  ResultSet rs = null;
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
 	    
		/* To get the method and class name in before method 
		    String classname = getClass().getSimpleName();
    	    String methodName = method.getName();
    	    TestLog = extent.startTest(classname);
    	    or
		    TestLog = extent.startTest(methodName);
		    TestLog.log(LogStatus.INFO,  method.getName());*/
		
		
		//Driver Initialization
	
		
		
		//JDBC connection
		
		try {
		
			
			Class.forName(PropetyFileReader.configpropertyReader("dbDriver")).newInstance();
			connection = DriverManager.getConnection(PropetyFileReader.configpropertyReader("dbHOSTURL"),PropetyFileReader.configpropertyReader("dbHOSTUser"), PropetyFileReader.configpropertyReader("dbHOSTPwd"));
			st =	connection.createStatement();
			st.setQueryTimeout(15);
			
			if (connection.isClosed()){
				System.out.println("JDBC Connection Got Failed");
			/*	TestLog.log(LogStatus.FAIL, "JDBC Connection Got Failed");*/
			}
			System.out.println("DB Connected Sucessfully");
			 /*   TestLog.log(LogStatus.PASS, "DB Connected Sucessfully");*/
			
		} catch (Exception e) {
			   System.out.println("JDBC Connection Got Failed");
			   /* TestLog.log(LogStatus.FAIL, "JDBC Connection Got Failed");*/
			     e.printStackTrace();
		} 
		
			
		  
	}
	
	
	@AfterMethod
	public  void afterMethod(ITestResult result){
		
		//Db Connection 
		try {
			if ((connection!=null) && ! (connection.isClosed())){
				
				connection.close();
			//	TestLog.log(LogStatus.INFO, "DB Connection Closed Sucessfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			  // TestLog.log(LogStatus.INFO, "DB Connection is Not Closed");
			   e.printStackTrace();
		}
		
		
	/*	//Extent Report Status 
		if (result.getStatus() == ITestResult.FAILURE){
		     System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = Failed ]");
		//	TestLog.log(LogStatus.FAIL,   result.getName() + " Failed " );
		
		//	TestLog.log(LogStatus.FAIL, result.getThrowable());
		
		} else if (result.getStatus() == ITestResult.SKIP){
        	
			System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = Skipped ]");
        //	TestLog.log(LogStatus.SKIP, result.getName() + " Skipped ");
        	//TestLog.log(LogStatus.SKIP, result.getThrowable());
        	
        }else if(result.getStatus() == ITestResult.SUCCESS){
        	
        	System.out.println(" [Test Case Name = " + result.getName() + " ] " + " [ Status = PASSED ]");
        	//TestLog.log(LogStatus.PASS, result.getName() + " PASSED ");
*/        	
        	
        	
        	
        }
		
    
	
		
				
	
	
	
	@AfterClass
	public void afterClass(){
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
		   
		 
		   
		   
		   
		   
	}
	
	@AfterTest
	public void afterTest1(){
		
		 
		
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
	
	
	public static String getTestDataRowName(String SheetName){
		
		return SheetName + "_TestData";
		
	}
	/*

	



	/*
	 * Create the Xls_Reader Object for a file
	 * */
      public static  Xls_Reader AccessTestDataFile(String file){	   
	  TestData = new Xls_Reader(file);
	   return TestData;
      }


     


	
	/*
	 * Author  : Arpan Saini
	 * Purpose : To Take the screen shot and add that in Extent Report
	 * */
	
/*	 public void takeScreenShot(){
  		 
  		 Date date = new Date();
       	 String ScreenShoteFileName =	date.toString().replaceAll(":", "_").replaceAll(" ", "_") + ".png";
       	 String Filepath = System.getProperty("user.dir") + Config.ScreenshotLocation + ScreenShoteFileName;
  	  	java.io.File srcFile =   ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		 try {
			org.apache.commons.io.FileUtils.copyFile(srcFile, new java.io.File(Filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
  		 
  		 TestLog.log(LogStatus.INFO, TestLog.addScreenCapture(Filepath));
  	 }
*/
	 /*
	  * Author  : Arpan Saini
	  * Purpose : To Execute the Query 
	  * */
	
	 public void executeQuery(String query){
		 
		 try {
			rs = st.executeQuery(query);
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 /*
	  * Author  : Arpan Saini
	  * Purpose : Hashtable will store  keys values as 1, 2, 3 , 4 ..... and the values corresponding to keys will be in  order in which the columns are written in the Query
	  *           Example 1. if Query : Select customer_no , brch_no , Loan_Number from borm ; 
	  *           then HashTable will store the values as ( key1 = 1 , value = customer_no ) , ( key1 = 2 , value = brch_no ) , ( key1 = 3 , value = Loan_Number )
	  * */
	 
       public Hashtable<String, String> getDbData(int NumberofColumns) { 
    	   
    	   Hashtable<String, String> getdbData = new  Hashtable<String ,String>();
    	   
		 
		try {
		//	System.out.println(rs.next());
			 while(rs.next()){
//				 System.out.println(rs.next());
				 for(int i=1 ; i <= NumberofColumns ; i++ ){
					 
					String j =  Integer.toString(i);
					if(j==null){
						return null;
					}
					getdbData.put(j, rs.getString(i));
					 
					 
				 }
				 
				 return getdbData; 
			 }
		 return null;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		 
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
