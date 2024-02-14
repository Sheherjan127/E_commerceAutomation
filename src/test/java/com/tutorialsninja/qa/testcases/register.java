package com.tutorialsninja.qa.testcases;


//import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utilities.utilities;


public class register extends base {
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public register() {
		super ();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver= intilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		Thread.sleep(2000);
		HomePage homepage= new HomePage(driver);
		registerPage= homepage.navigateToRegisterPage();//code optimized
		//homepage.clickOnMyAccount();
		//registerPage = homepage.selectRegisterOption();
	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test (priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() throws InterruptedException{
		Thread.sleep(2000);
		accountSuccessPage= registerPage.registerWithMandatoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
			
		/*registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage =registerPage.clickOnContinueButton();*/
		
		Thread.sleep(2000);
		
		//AccountSuccessPage accountSuccessPage= new AccountSuccessPage(driver);
		String actualSuccessHeading= accountSuccessPage.retrieveaccountSuccessPageHeading();
		//String actualSuccessHeading = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")).getText();
		Assert.assertEquals(accountSuccessPage.retrieveaccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
		}
	@Test (priority =2)
	public void verifyRegisteringAnAccountWithAllFields() throws InterruptedException {
		accountSuccessPage= registerPage.registerWithMandatoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
		//String actualSuccessHeading = accountSuccessPage.retrieveaccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retrieveaccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
		
	}

	//public String generateEmailWithTimeStamp() {
	
		//Date date = new Date();
		 //String timestamp = date.toString().replace(" ","_").replace(":", "_");
		  //return "sheher"+timestamp+"@gmail.com";
	//}
	@Test(priority =3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() throws InterruptedException {
		accountSuccessPage= registerPage.registerWithMandatoryField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"),prop.getProperty("validPassword"));
		Thread.sleep(2000);
	    String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message is not displayed");
		
	}
	
	@Test (priority=4)
	public void verifyRegisteringAnAccountWithoutAllDetails() throws InterruptedException{
		Thread.sleep(2000);
		registerPage.clickOnContinueButton();	
		//String AactualPrivacyPolicy = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")),"Password warning message(s) are not displayed"); 
		Thread.sleep(2000);
		
		
		
/*registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(),dataProp.getProperty("firstNameWarning"),"First name warning is not displayed");
		Thread.sleep(2000);
		
		Assert.assertEquals(registerPage.retrieveLastNameWarning(),dataProp.getProperty("lastnameWarning"),"Last name warning is not displayed");
		Thread.sleep(2000);
		
		Assert.assertEquals(registerPage.retrieveemailWarning(),dataProp.getProperty("emailWarning"),"Email warning is not displayed");
		
		Assert.assertEquals(registerPage.retrievetelephoneWarning(),dataProp.getProperty("telephoneWarning"),"Telephone warning is not displayed");
		Thread.sleep(2000);
		
		Assert.assertEquals(registerPage.retrievetelepasswordWarning(),dataProp.getProperty("passwordWarning")
		*/
	}

}




	


