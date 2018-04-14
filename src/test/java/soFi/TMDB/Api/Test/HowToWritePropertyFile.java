package soFi.TMDB.Api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;
import soFi.TMDB.Api.Configuration.Config;

public class HowToWritePropertyFile {
	
	@Test
	public void writePropertyFile() throws IOException{
		
               
		
				System.out.println(Config.environmentFileLocationProperty);
				File f = new File(Config.environmentFileLocationProperty);
		        Properties properties = new Properties();
		        FileOutputStream fos = new FileOutputStream(f);
		        properties.setProperty("EnvironmentURL", "https:www.facebook.com");
		        properties.setProperty("SystemUser", "Arpan Saini");
		     
	}

}
