package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends base{
	SearchPage searchPage;
	public Search() {
		super ();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver= intilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test (priority  = 1)
	public void verifySearchWithValidProduct() throws InterruptedException {
		
		HomePage homePage= new HomePage (driver);
		homePage.enterSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		//SearchPage searchPage= new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"valid product HP is not displayed");
		Thread.sleep(2000);
	}
	
	@Test (priority = 2)
	public void verifySearchWithInvalidProduct() throws InterruptedException {
		HomePage homePage= new HomePage (driver);
		homePage.enterSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
		Thread.sleep(2000);
		String actualSearchMessage = searchPage.retrieveNoProductMessage();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("abcd"),"No product message in search result is not displayed ");
		//noProductInSearchResults
		
	} 
	
	@Test(priority= 3,dependsOnMethods= {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() throws InterruptedException {
		HomePage homePage= new HomePage (driver);
		searchPage= homePage.clickOnSearchButton();
		Thread.sleep(2000);
		String actualSearchMessage = searchPage.retrieveNoProductMessage();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductInSearchResults"),"No product message in search result is not displayed ");
		Thread.sleep(2000);
	}

 }
