package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Readconfigfile {

	Properties pro; // there is specific class called properties .need to create an object for Properties class
	
	public  Readconfigfile() {

			
			
			File src = new File("./Configuration/Configuration.properties");
			
		
		try {
			
			FileInputStream fis = new 	FileInputStream(src);
			
			pro = new Properties();
			pro.load(fis);
			
		}catch (Exception e) {
			
			System.out.println("Exception is " + e.getMessage());
		}

		}

		// we have loaded the file 
		// now we need to read and return the value 
		// so need to create a method for every variable in the Config.properties file 
		
		public String getApplicationURL() {
			
			String url=pro.getProperty("BaseURL");
			return url;
		}
		
		
	public String getEmail() {
			
			String email=pro.getProperty("Email");
			return email;
		}
		
	public String getPassword() {
		
		String pwd=pro.getProperty("Password");
		return pwd;
	}

	public String getChromepath() {
		
		String Chpath=pro.getProperty("Chromepath");
		return Chpath;
	}
		
	public String getEdgepath() {
		
		String edgepath=pro.getProperty("Edgepath");
		return edgepath;
	}
	public String getIEpath() {
		
		String IEpath=pro.getProperty("IEpath");
		return IEpath;
	}
	public String getFirefoxpath() {
		
		String Firefoxpath=pro.getProperty("Firefoxpath");
		return Firefoxpath;
	}

	public String getReportConfigPath(){
		String reportConfigPath = pro.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
	}


