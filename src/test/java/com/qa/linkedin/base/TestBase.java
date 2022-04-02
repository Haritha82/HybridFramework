package com.qa.linkedin.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	

	private Logger log = Logger.getLogger(TestBase.class);
	
	/*
	 * Read the property file from properties file
	 */

	public String readPropertyValue(String key) throws IOException
	{
			log.info("create object for properties class");
			Properties prop=new Properties();
			log.info("Read the properties file");
			
			FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));
			prop.load(fis);
			
			return prop.getProperty(key);	
}

	@BeforeSuite
	public void setup() throws IOException  {
		log.info("Started executing the @BeforeSuite");
		log.debug("Fetching browser value from congig.properties file");
		
		log.debug("Launching the browser and application");
		String browserName = readPropertyValue("browser");
		
		log.debug("Launching the" + browserName + "browser");

		//String browser;
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			
			driver = new EdgeDriver();
			
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			//create object for firefox 
			
			
			
			driver = new FirefoxDriver();
			
		}
		
		log.info("Maximize the window");
		driver.manage().window().maximize();

		log.info("Add implicitwait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		log.info("create object for WebDriverWait class");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		log.debug("Launching the application:"+readPropertyValue("applicationurl"));
		driver.get(readPropertyValue("applicationurl"));
    
}

@AfterSuite
public void afterClass()
{
if(driver!=null)
{
	log.debug("close the browser");
	driver.quit();
}
}
}