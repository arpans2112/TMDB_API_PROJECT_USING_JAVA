package soFi.TMDB.Api.Utilities;

import java.io.File;  
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import soFi.TMDB.Api.Configuration.Config;

public class ExtentManager {
		
	
	public static ExtentReports extent = null;

	public static String ExtentReportPath = null;

	public static ExtentReports getInstance() {
		
		
		if (extent == null) {
			
			
			
			Date d=new Date();
			
			
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			
				ExtentReportPath = Config.extentReportLocation + fileName;
				System.out.println("Extent Report Path : " + ExtentReportPath);
			
			 String ReportconfigPath = Config.extentReportConfigXmlLocation ;
			
			extent = new ExtentReports(ExtentReportPath, true, DisplayOrder.NEWEST_FIRST , NetworkMode.OFFLINE);
			extent.loadConfig(new File(ReportconfigPath));
			// optional
			extent.addSystemInfo("Rest Assured", "2.9.1").addSystemInfo("Environment", "TMDB_API_QA");
			
			
					
		}
		
		return extent;
	}
	
}
