package soFi.TMDB.Api.Utilities;

import java.util.Hashtable;

public class TestUtil {

	boolean cleanDatafirstTime = true ;
	
	// finds if the test suite is runnable 
		public  boolean isSuiteRunnable(Xls_Reader xls , String suiteName){
			boolean isExecutable=false;
			for(int i=2; i <= xls.getRowCount("Test Suite") ;i++ ){
				//String suite = xls.getCellData("Test Suite", "TSID", i);
				//String runmode = xls.getCellData("Test Suite", "Runmode", i);
			
				if(xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)){
					if(xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable=true;
					}else{
						isExecutable=false;
					}
				}

			}
			xls=null; // release memory
			return isExecutable;
			
		}
		
		
		// returns true if runmode of the test is equal to Y
		public  boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName){
			boolean isExecutable=false;
			for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
				//String tcid=xls.getCellData("Test Cases", "TCID", i);
				//String runmode=xls.getCellData("Test Cases", "Runmode", i);
				//System.out.println(tcid +" -- "+ runmode);
				
				
				if(xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)){
					if(xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")){
						isExecutable= true;
					}else{
						isExecutable= false;
					}
				}
			}
			
			return isExecutable;
			
		}
		
		
		
		
		// checks RUnmode for dataSet
		public  String[] getDataSetRunmodes(Xls_Reader xlsFile,String sheetName){
			String[] runmodes=null;
			if(!xlsFile.isSheetExist(sheetName)){
				xlsFile=null;
				sheetName=null;
				runmodes = new String[1];
				runmodes[0]="Y";
				xlsFile=null;
				sheetName=null;
				return runmodes;
			}
			runmodes = new String[xlsFile.getRowCount(sheetName)-1];
			for(int i=2;i<=runmodes.length+1;i++){
				runmodes[i-2]=xlsFile.getCellData(sheetName, "Runmode", i);
			}
			xlsFile=null;
			sheetName=null;
			return runmodes;
			
		}

		// update results for a particular data set	
		public  void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum,String result){	
			xls.setCellData(testCaseName, "Results", rowNum, result);
		}
		
		// return the row num for a test
		public  int getRowNum(Xls_Reader xls, String id){
			for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
				String tcid=xls.getCellData("Test Cases", "TCID", i);
				
				if(tcid.equals(id)){
					xls=null;
					return i;
				}
				
				
			}
			
			return -1;
		}
		
		
		//New methods adding 
		
		//Methods to Get the TestData into a Two dimensional array that has data in form of HashTable
		public  Object[][] getTestData(Xls_Reader TestData , String SheetName , String TestDataRowName ){
			
              int TestDataRowNameStartIndex = 0;
			  int TotalRowCount = TestData.getRowCount(SheetName);
			  
              
              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
            	  
            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
            		  TestDataRowNameStartIndex =  rownum;
            		  break;
            	  }
              }
			
			System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
			
			int KeyRowIndex = TestDataRowNameStartIndex + 1;
			int TotalDataColumns = 0;
			while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
				
				TotalDataColumns++;
				
			}
			
			System.out.println("Total Data Columns : " + TotalDataColumns);
			
			int DataRowIndex= KeyRowIndex +1 ;
			int TotalDataRows= 0;
			while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
				
				TotalDataRows ++;
			}
			
			System.out.println("Total Data Rows : " + TotalDataRows);
			
			//Get the Data now 
			Object[][] data = new Object[TotalDataRows][1];
			Hashtable<String, String> table = null;
			
			for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){
				
				table = new Hashtable<String, String>();
				
				for(int colnum=0; colnum<TotalDataColumns;colnum++){
					
					table.put(TestData.getCellData(SheetName, colnum, KeyRowIndex), TestData.getCellData(SheetName, colnum, rowNum));
					System.out.print( " [ " + TestData.getCellData(SheetName, colnum, KeyRowIndex) + " = " +  TestData.getCellData(SheetName, colnum, rowNum)  + " ] --");
				}
				
				data[rowNum-DataRowIndex][0] = table;
			}
			
			
			return data;
			
		}
		
	  
		//Methods to Get the Test Data into of a single row for which the RowID is defined as a Two dimensional array that has data in form of HashTable
	
		public  Hashtable<String, String> getTestData(Xls_Reader TestData , String SheetName , String TestDataRowName , String RowID ){
			
              int TestDataRowNameStartIndex = 0;
			  int TotalRowCount = TestData.getRowCount(SheetName);
			  
              
              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
          //  	  String startIndex = TestData.getCellData(SheetName, 0 , rownum);
            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
            		  TestDataRowNameStartIndex =  rownum;
            		  break;
            	  }
              }
			
			System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
			
			int KeyRowIndex = TestDataRowNameStartIndex + 1;
			int TotalDataColumns = 0;
			while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
				
				TotalDataColumns++;
				
			}
			
			System.out.println("Total Data Columns : " + TotalDataColumns);
			
			int DataRowIndex= KeyRowIndex +1 ;
			int TotalDataRows= 0;
			while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
				
				TotalDataRows ++;
			}
			
			System.out.println("Total Data Rows : " + TotalDataRows);
			
			//Get the Data now 
			
			Hashtable<String, String> TestDatatable = new Hashtable<String, String>();
			
			for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
	
				//	System.out.println(TestData.getCellData(SheetName, "RowID", KeyRowIndex, rowNum));
			   //   String rowdatavalue =TestData.getCellData(SheetName, 1 , rowNum);
	
				
				if(TestData.getCellData(SheetName, "RowID", KeyRowIndex, rowNum).equalsIgnoreCase(RowID)){
						
						for(int colnum=0; colnum<TotalDataColumns;colnum++){
							
							TestDatatable.put(TestData.getCellData(SheetName, colnum, KeyRowIndex), TestData.getCellData(SheetName, colnum, rowNum));
							System.out.print( " [ " + TestData.getCellData(SheetName, colnum, KeyRowIndex) + " = " +  TestData.getCellData(SheetName, colnum, rowNum)  + " ] --");
						
						}
						
						return TestDatatable;
					}
					
				
		
					
			}
			
			
			return null;
			
		}
		
		
		public void setTestData( String fileLocation, String SheetName , String TestDataRowName , String RowID , String columnName , String data ){
			
			
			//Xls_Reader TestData
			Xls_Reader	TestData = new Xls_Reader(fileLocation);
			
	           int TestDataRowNameStartIndex = 0;
				  int TotalRowCount = TestData.getRowCount(SheetName);
				  
	              
	              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
	          //  	  String startIndex = TestData.getCellData(SheetName, 0 , rownum);
	            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
	            		  TestDataRowNameStartIndex =  rownum;
	            		  break;
	            	  }
	              }
				
			//	System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
				
				int KeyRowIndex = TestDataRowNameStartIndex + 1;
				int TotalDataColumns = 0;
				while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
					
					TotalDataColumns++;
					
				}
				
			//	System.out.println("Total Data Columns : " + TotalDataColumns);
				
				int DataRowIndex= KeyRowIndex +1 ;
				int TotalDataRows= 0;
				while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
					
					TotalDataRows ++;
				}
				
			//	System.out.println(" Total Data Rows : " + TotalDataRows);	
				
				for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
					
					/*	String rowdatavalueByRowno =TestData.getCellData(SheetName, 1 , rowNum);
						String rowdatavalueByColumnName = TestData.getCellData(SheetName, "RowID" , rowNum ,  KeyRowIndex);
						System.out.println("RowDataValue : " + rowdatavalueByRowno + rowdatavalueByColumnName );*/
						
							if(TestData.getCellData(SheetName, "RowID" , KeyRowIndex, rowNum ).equalsIgnoreCase(RowID)){
								
								    TestData.setCellData(SheetName, columnName, KeyRowIndex , rowNum, data);
								  
									
								}
								
							}
							
					}
		
		
		public void clearTestData(String fileLocation, String SheetName , String TestDataRowName  , String columnName){
			

			//Xls_Reader TestData
			Xls_Reader	TestData = new Xls_Reader(fileLocation);
			
	           int TestDataRowNameStartIndex = 0;
				  int TotalRowCount = TestData.getRowCount(SheetName);
				  
	              
	              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
	          //  	  String startIndex = TestData.getCellData(SheetName, 0 , rownum);
	            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
	            		  TestDataRowNameStartIndex =  rownum;
	            		  break;
	            	  }
	              }
				
			//	System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
				
				int KeyRowIndex = TestDataRowNameStartIndex + 1;
				int TotalDataColumns = 0;
				while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
					
					TotalDataColumns++;
					
				}
				
			//	System.out.println("Total Data Columns : " + TotalDataColumns);
				
				int DataRowIndex= KeyRowIndex +1 ;
				int TotalDataRows= 0;
				while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
					
					TotalDataRows ++;
				}
				
                     for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
					
                    	 TestData.setCellData(SheetName, columnName, KeyRowIndex , rowNum, "");
								
							}
			
		}
		
		
	public void setTestData( String fileLocation, String SheetName , String TestDataRowName  , String columnName , String data , String RowID, String seqNo){
			
		
			//Xls_Reader TestData
			Xls_Reader	TestData = new Xls_Reader(fileLocation);
			
	           int TestDataRowNameStartIndex = 0;
				  int TotalRowCount = TestData.getRowCount(SheetName);
				  
	              
	              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
	          //  	  String startIndex = TestData.getCellData(SheetName, 0 , rownum);
	            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
	            		  TestDataRowNameStartIndex =  rownum;
	            		  break;
	            	  }
	              }
				
			//	System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
				
				int KeyRowIndex = TestDataRowNameStartIndex + 1;
				int TotalDataColumns = 0;
				while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
					
					TotalDataColumns++;
					
				}
				
			//	System.out.println("Total Data Columns : " + TotalDataColumns);
				
				int DataRowIndex= KeyRowIndex +1 ;
				int TotalDataRows= 0;
				while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
					
					TotalDataRows ++;
				}
				
			//	System.out.println(" Total Data Rows : " + TotalDataRows);	
				
				
				if(seqNo.equals("")){
					
					
					for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
						
						/*	String rowdatavalueByRowno =TestData.getCellData(SheetName, 1 , rowNum);
							String rowdatavalueByColumnName = TestData.getCellData(SheetName, "RowID" , rowNum ,  KeyRowIndex);
							System.out.println("RowDataValue : " + rowdatavalueByRowno + rowdatavalueByColumnName );*/
							
								if(TestData.getCellData(SheetName, "RowID" , KeyRowIndex, rowNum ).equalsIgnoreCase(RowID)){
									
							    	TestData.setCellData(SheetName, columnName, KeyRowIndex , rowNum, data);
							    	}
							    	
								}
				
			              	}else { 
			              		
			              		
			              		for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
									
									/*	String rowdatavalueByRowno =TestData.getCellData(SheetName, 1 , rowNum);
										String rowdatavalueByColumnName = TestData.getCellData(SheetName, "RowID" , rowNum ,  KeyRowIndex);
										System.out.println("RowDataValue : " + rowdatavalueByRowno + rowdatavalueByColumnName );*/
			              			          String  rowId =    TestData.getCellData(SheetName, "RowID" , KeyRowIndex, rowNum );
			              			          String  seqID =    TestData.getCellData(SheetName, "SequnceNumber" , KeyRowIndex, rowNum );
			              			
			              			            		
											if((rowId+seqID).equalsIgnoreCase(RowID+seqNo)){
												
										    	TestData.setCellData(SheetName, columnName, KeyRowIndex , rowNum, data);
										    	
											}
										    	
											}
							
			              	}
						
						
					}
	
	
	public void setTestDataForSpecificRow( String fileLocation, String SheetName , String TestDataRowName  , String columnName , String data , String rowIDColumnName ,String rowID){
		
		
		//Xls_Reader TestData
		Xls_Reader	TestData = new Xls_Reader(fileLocation);
		
           int TestDataRowNameStartIndex = 0;
			  int TotalRowCount = TestData.getRowCount(SheetName);
			  
              
              for (int rownum=1 ; rownum<=TotalRowCount ; rownum++){
          //  	  String startIndex = TestData.getCellData(SheetName, 0 , rownum);
            	  if(TestDataRowName.equalsIgnoreCase(TestData.getCellData(SheetName, 0 , rownum))){
            		  TestDataRowNameStartIndex =  rownum;
            		  break;
            	  }
              }
			
		//	System.out.println("TestDataRowName Starts From - " + TestDataRowNameStartIndex);
			
			int KeyRowIndex = TestDataRowNameStartIndex + 1;
			int TotalDataColumns = 0;
			while (!TestData.getCellData(SheetName, TotalDataColumns, KeyRowIndex).equals("")){
				
				TotalDataColumns++;
				
			}
			
		//	System.out.println("Total Data Columns : " + TotalDataColumns);
			
			int DataRowIndex= KeyRowIndex +1 ;
			int TotalDataRows= 0;
			while(!TestData.getCellData(SheetName, 0, (DataRowIndex+TotalDataRows)).equals("")){
				
				TotalDataRows ++;
			}
			
		//	System.out.println(" Total Data Rows : " + TotalDataRows);	
			
			
			for(int rowNum = DataRowIndex ; rowNum < (DataRowIndex+TotalDataRows); rowNum++ ){		
				
				/*	String rowdatavalueByRowno =TestData.getCellData(SheetName, 1 , rowNum);
					String rowdatavalueByColumnName = TestData.getCellData(SheetName, "RowID" , rowNum ,  KeyRowIndex);
					System.out.println("RowDataValue : " + rowdatavalueByRowno + rowdatavalueByColumnName );*/
					
						if(TestData.getCellData(SheetName, rowIDColumnName , KeyRowIndex, rowNum ).equalsIgnoreCase(rowID)){
							
					    	TestData.setCellData(SheetName, columnName, KeyRowIndex , rowNum, data);
					    	}
					    	
						}
	
	     }
			
}
		

