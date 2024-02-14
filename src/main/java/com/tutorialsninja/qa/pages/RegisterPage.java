package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;

	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAdderssWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarning;
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarning;
	
public RegisterPage(WebDriver driver) {

	this.driver= driver;
	PageFactory.initElements(driver, this);
}

public void enterFirstName(String firstNameText) {
	firstNameField.sendKeys(firstNameText);
}
public void enterLastName(String lastNameText) {
	lastNameField.sendKeys(lastNameText);
}
public void enterEmailAddress(String emailText) {
	emailAddressField.sendKeys(emailText);
}
public void enterTelephoneNumber(String telephoneText) {
	telephoneField.sendKeys(telephoneText);
}
public void enterPassword(String passwordText) {
	passwordField.sendKeys(passwordText);
}

public void enterConfirmPassword(String confirmPasswordText) {
	passwordConfirmField.sendKeys(confirmPasswordText);
}
public void selectPrivacyPolicy() {
	privacyPolicyField.click();
}

public AccountSuccessPage clickOnContinueButton() {
	continueButton.click();
	return new AccountSuccessPage(driver);
}

public void selectYesNewsLetterOption() {
	yesNewsLetterOption.click();
}

public String retrieveDuplicateEmailAddressWarning() {
	String duplicateEmailWarningText = duplicateEmailAdderssWarning.getText();
	return duplicateEmailWarningText;
}

public String retrievePrivacyPolicyWarning() {
	String privacyPolicyWarningText= privacyPolicyWarning.getText();
	return privacyPolicyWarningText;
}

public String retrieveFirstNameWarning() {
	String firstNameWarningText= firstNameWarning.getText();
	return firstNameWarningText;
	
	
}
public String retrieveLastNameWarning() {
	String lastNameWarningText= lastNameWarning.getText();
	return lastNameWarningText;

}
public String retrieveemailWarning() {
	String emailWarningText= emailWarning.getText();
	return emailWarningText;
}
public String retrievetelephoneWarning() {
	String telephoneWarningText= telephoneWarning.getText();
	return telephoneWarningText;
}
public String retrievetelepasswordWarning() {
	String passwordWarningText= passwordWarning.getText();
	return passwordWarningText;
}

public AccountSuccessPage registerWithMandatoryField (String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
	firstNameField.sendKeys(firstNameText);
	lastNameField.sendKeys(lastNameText);
	emailAddressField.sendKeys(emailText);
	telephoneField.sendKeys(telephoneText);
	passwordField.sendKeys(passwordText);
	passwordConfirmField.sendKeys(confirmPasswordText);
	privacyPolicyField.click();
	continueButton.click();
	return new AccountSuccessPage(driver);
	
	
}
public AccountSuccessPage registerWithAllFields (String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
	firstNameField.sendKeys(firstNameText);
	lastNameField.sendKeys(lastNameText);
	emailAddressField.sendKeys(emailText);
	telephoneField.sendKeys(telephoneText);
	passwordField.sendKeys(passwordText);
	passwordConfirmField.sendKeys(confirmPasswordText);
	privacyPolicyField.click();
	continueButton.click();
	yesNewsLetterOption.click();
	return new AccountSuccessPage(driver);
	
}

public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastnameWarning, String ExpectedEmailWarning, String expectedTelephoneWarning, String expectedPasswordWarning) {
	
	//String actualPrivacyPolicyWarningText= privacyPolicyWarning.getText();
	boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPasswordWarning);
	
	//String actualFirstNameWarningText= firstNameWarning.getText();
	boolean firstNameWarningStatus = firstNameWarning.getText().contains(expectedFirstNameWarning);
	boolean lastNameWarningStatus = lastNameWarning.getText().contains(expectedLastnameWarning);
	//String actualEmailWarningText= emailWarning.getText();
	boolean emailWarningStatus = emailWarning.getText().contains(ExpectedEmailWarning);
	boolean telephoneWarningStatus = telephoneWarning.getText().contains(expectedTelephoneWarning);
	boolean passwordWarningStatus = passwordWarning.getText().contains(expectedPasswordWarning);
	return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus; 
}


}
