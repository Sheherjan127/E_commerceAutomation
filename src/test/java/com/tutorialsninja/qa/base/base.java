package com.tutorialsninja.qa.base;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public base() {

		//	public WebDriver openApplication(String browserName){
		//		System.setProperty("webdriver.http.factory", "jdk-http-client");
		//		driver = new ChromeDriver();
		//		driver.get("https://tutorialsninja.com/demo/");
		//		driver.manage().window().maximize();
		//		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		//		
		//		return driver;
		//	}
		//	

		//public void loadPropertiesFile() {
		prop= new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\Config.properties");
		try {
			FileInputStream fis= new FileInputStream(propFile);
			prop.load(fis);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		dataProp= new Properties();
		File dataPropFile= new File(System.getProperty("user.dir")+"\\Testdata.properties");
		try {
			FileInputStream datafis= new FileInputStream(dataPropFile);
			dataProp.load(datafis);
		}
		catch(Throwable e) { 
			e.printStackTrace();
		}

	}

	public  WebDriver intilizeBrowserAndOpenApplicationURL(String browserName) {

		if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} 

		return driver;
		
	}
}


