package soFi.TMDB.Api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
import soFi.TMDB.Api.Configuration.Config;

public class HowToWritePropertyFile {
	
	@Test
	public void writePropertyFile() throws IOException{
		
	   
		
				System.out.println(Config.environmentFileLocationProperty);
		  	    String path = Config.environmentFileLocationProperty;
		    	
		    	FileInputStream in = new FileInputStream(path);
		    	Properties props = new Properties();
		    	props.load(in);
		    	in.close();
		     
		    	props.setProperty("request_token", "https:www.facebook.com");
		       // properties.replace("request_token", "https:www.facebook.com");
		   
		       
		       // FileWriter writer = new FileWriter(f);
		        FileOutputStream fileOutputStream = new FileOutputStream(path);
		        props.store(fileOutputStream, "Storing values");
		        fileOutputStream.close();
		        
		    	
		    	
		    	
	}

}
