package com.orangehrm.loginfeature.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.orangehrm.loginfeature.base.BaseTest;

public class LoginFieldTests extends BaseTest{
	
	private static final Logger log = LogManager.getLogger(LoginFieldTests.class);

	@Test(dependsOnMethods = {"verifyPasswordFieldAvailablity"},alwaysRun= true )
	public void verifyUserNameFieldAvailablity() {
		log.info("Starting userName field availablity test");

		Assert.assertNotNull(lpa.usernameTextfield());
		log.info("UserName field availablity test is passed");
	}
	
	@Test(timeOut =100)
	public void verifyPasswordFieldAvailablity() throws InterruptedException {
		log.info("Starting password field availablity test");

		Assert.assertNotNull(lpa.passwordTextfield());
		log.info("Password field availablity test is passed");
	}
	
	@Test(dependsOnMethods = {"verifyUserNameFieldAvailablity"} )
	public void verifyAddingDatatoUserName() {
		log.info("Starting adding data to userName field test");

		lpa.enterUserName("Admin");
		Assert.assertEquals(lpa.usernameTextfield().getAttribute("value"), "Admin");
		log.info("Adding data to userName field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyAddingDatatoPassword() {
		log.info("Starting adding data to password field test");

		lpa.enterPassword("admin123");
		Assert.assertEquals(lpa.passwordTextfield().getAttribute("value"), "admin123");
		log.info("Adding data to password field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyClearingDataFromUserName() {
		log.info("Starting clearing data from userName field test");

		lpa.enterUserName("Admin");
		lpa.clearUserName();
		Assert.assertEquals(lpa.usernameTextfield().getAttribute("value"), "");
		log.info("Clearing data from userName field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyClearingDataFromPassword() {
		log.info("Starting clearing data from password field test");

		lpa.enterPassword("admin123");
		lpa.clearPassword();
		Assert.assertEquals(lpa.passwordTextfield().getAttribute("value"), "");
		log.info("Clearing data from password field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyErrorMessageOnClearingUserName() {
		log.info("Starting required error message on clearing userName field test");
		
		lpa.enterUserName("Admin");
		lpa.clearUserName();
		if(lpa.usernameTextfield().getAttribute("value").isEmpty()) {
			Assert.assertEquals(lpa.userNameRequiredErrorMessageText(),"Required");
		}
		
		log.info("Required error message on clearing userName field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyErrorMessageOnClearingPassword() {
		log.info("Starting required error message on clearing password field test");
		
		lpa.enterPassword("admin123");
		lpa.clearPassword();
		if(lpa.passwordTextfield().getAttribute("value").isEmpty()) {
			Assert.assertEquals(lpa.passwordRequiredErrorMessageText(),"Required");
		}
		log.info("Required error message on clearing password field test is passed");
	}
	
	
	@Test(enabled=false)
	public void verifyUserNameAcceptsOnlyANCharacters() {
		log.info("Starting adding of AlphaNumeric characters to userName Field test");
		
		lpa.enterUserName("Admin11239Ramaswamy1");
		String addedvalue = lpa.usernameTextfield().getAttribute("value");
		Assert.assertTrue(addedvalue.matches("[a-zA-Z0-9]+"));
		
		log.info("Adding of AlphaNumeric characters to userName field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyPasswordAcceptsOnlyANCharacters() {
		log.info("Starting adding of AlphaNumeric characters to password Field test");
		
		
		lpa.enterPassword("admin112331111swamy1");
		String addedvalue = lpa.passwordTextfield().getAttribute("value");
		Assert.assertTrue(addedvalue.matches("[a-zA-Z0-9]+"));
		
		log.info("Adding of AlphaNumeric characters to password field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyUserNameAccepts20ANCharacters() {
		log.info("Starting adding of 20AN characters to userName Field test");
		
		lpa.enterUserName("Admin11239Ramaswamy1");
		String addedvalue = lpa.usernameTextfield().getAttribute("value");
		int userNamelength = addedvalue.length();
		Assert.assertEquals(userNamelength, 20);
		
		log.info("Adding of 20AN characters to userName field test is passed");
	}
	
	@Test(enabled=false)
	public void verifyPasswordAccepts20Characters() {
		log.info("Starting adding of 20AN characters to password Field test");
		
		lpa.enterPassword("admin112331111swamy1");
		String addedvalue = lpa.passwordTextfield().getAttribute("value");
		int passwordlength = addedvalue.length();
		Assert.assertEquals(passwordlength, 20);
		
		log.info("Adding of 20AN characters to password field test is passed");
	}
}
