package com.tutorialsninja.qa.testcases;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.accessibility.AccessibleExtendedText;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utilities.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class login extends base {
//for driver.quit()/ replacement
	LoginPage loginPage;
	//global variable for login page & instead of LoginPage loginPage= new loginPage(driver);
	AccountPage accountPage;
	public login() {
		super ();
	}
	
    public WebDriver driver;
	@BeforeMethod
		public void setup() throws InterruptedException {
		
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			//loadPropertiesFile();
			driver= intilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			Thread.sleep(2000);
			HomePage homepage= new HomePage(driver);
			loginPage= homepage.navigateToLoginPage();
			//homepage.clickOnMyAccount();
			//loginPage = homepage.selectLoginOption();
		    //Instead of- 
			//driver.findElement(By.linkText("Login")).click();
		
		    }
	 
	@AfterMethod
	public void tearDown(){
		// Code for releasing resources or resetting state goes here 
		driver.quit();
		
	}
	

    @Test(priority=1, dataProvider="validCredentialsSupplier") 
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException
	{
    	Thread.sleep(2000);
    	accountPage= loginPage.login(email, password);// optimize code
    	//loginPage.enterEmailAddress(email);
    	//loginPage.enterPassword(password);
    	//AccountPage accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue (accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information");
		
		
	} 
    
    @DataProvider(name= "validCredentialsSupplier")
    public Object[][] supplyTestData() {
    	
    	//excel file sheet name= login
    	Object[][]data= utilities.getTestDataFromExcel("login");
    	
    	//Object [][] data = {{"sheher@gmail.com","12345"},
    		//	{"sheherjan1@gmail.com","12345"},
    	       // {"sheherjan2@gmail.com","12345"}};
    return data;
    	}

		@Test(priority=2)
		public void verifyLoginWithInvalidCredentials() throws InterruptedException 
		{
			Thread.sleep(2000);
			loginPage.login(utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));// optimize code
			Thread.sleep(2000);
			Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")),"Expected warning message is not displayed");
		    //optimized code
		} 
		
		@Test(priority=3)
		public void verifyLoginWithValidEmailInvalidPassword() throws InterruptedException 
		{
			Thread.sleep(2000);
			loginPage.enterEmailAddress(prop.getProperty("validEmail"));
			Thread.sleep(2000);
			loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
			loginPage.clickOnLoginButton();
			/*String actualWarningMessage= loginPage.retrieveEmailPasswordNoMatchingWarningMessageText();
			String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchingWarning");
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");
			*/
			Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")),"Expected warning message is not displayed");
			}
		@Test(priority=4)
		public void verifyLoginWithValidPasswordInvalidEmail() throws InterruptedException 
		{
			Thread.sleep(2000);
			loginPage.enterEmailAddress(utilities.generateEmailWithTimeStamp());
			Thread.sleep(2000);
			loginPage.enterPassword(prop.getProperty("validPassword"));
			loginPage.clickOnLoginButton();
			Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")),"Expected warning message is not displayed");
			
			}
		
		@Test(priority=5)
		public void verifyLoginWithoutCredentials() throws InterruptedException
		{
			Thread.sleep(2000);
			loginPage.enterEmailAddress("");
			Thread.sleep(2000);
			loginPage.enterPassword("");
			loginPage.clickOnLoginButton();
			Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")),"Expected warning message is not displayed");
			
		}
		
		
		//public static String generateEmailWithTimeStamp() {
			// TODO Auto-generated method stub
			  //Date date= new Date();
			  //String timestamp = date.toString().replace(" ","_").replace(":", "_");
			  //return "sheher"+timestamp+"@gmail.com"; 
		      //return "sheher"+time
		
		public static String generateGmailValueDynamically() { 
			LocalTime currentTime = LocalTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");//DateTimeFormatter.ofPattern("HHmmss")//DateTimeFormatter.ofPatternof that in case dismissible of the importance  
			String formattedTime = currentTime.format(formatter );
			return "sheher"+formattedTime+"@gmail.com";
			}
		//return localtime = localtime.now(); return localtime.now(); return "sheher"+formattedTime+"@gmail.com;
		//local time = localtime.now()
		//return localtime = localtime.now(); return localtime.now(); return "sheher"+formattedTime+"@gmail.com;
        //return localtime = localtime.now(); return localtime.now(); return "sheherjan"
	    //return localtime = localtime.now(); return localtime.n
		
}
