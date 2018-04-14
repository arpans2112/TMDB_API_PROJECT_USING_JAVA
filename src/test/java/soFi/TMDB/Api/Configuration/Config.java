package soFi.TMDB.Api.Configuration;

public class Config {



	//Environment Config file Variables
	public static final String environmentFileName = "TMDB_API_QA_Environment.properties";    
	public static final String environmentFileLocationProperty= System.getProperty("user.dir") + "\\src\\test\\java\\soFi\\TMDB\\Api\\Configuration\\" + environmentFileName;



	//InputFile Location
	public static final String TestDataFileLocation = System.getProperty("user.dir") + "\\src\\test\\java\\soFi\\TMDB\\Api\\InputData\\";



}
