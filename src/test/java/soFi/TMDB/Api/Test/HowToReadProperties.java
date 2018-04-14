package soFi.TMDB.Api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.testng.annotations.Test;

import soFi.TMDB.Api.Configuration.Config;

public class HowToReadProperties {
	
	@Test
	public void writePropertyFile() throws IOException{
		
               //Code To Read the Property File		
				System.out.println(Config.environmentFileLocationProperty);
		        Properties properties = new Properties();
		        FileInputStream fileInputStream  = new FileInputStream(Config.environmentFileLocationProperty);
		        //Reads a property list (key and element pairs) from the input byte stream
		        properties.load(fileInputStream);
		        System.out.println("API_KEY : " + properties.getProperty("Api_Key"));
		     /*
	           //Code To Read the Entire Property File
				FileInputStream fileInput = new FileInputStream(Config.environmentFileLocationProperty);
				Properties properties = new Properties();
				properties.load(fileInput);
				fileInput.close();

				Enumeration<Object> enuKeys = properties.keys();
				while (enuKeys.hasMoreElements()) {
					String key = (String) enuKeys.nextElement();
					String value = properties.getProperty(key);
					System.out.println(key + ": " + value);
				}*/
	}
	}


