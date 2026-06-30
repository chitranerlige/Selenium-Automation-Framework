package com.orangehrm.loginfeature.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.orangehrm.loginfeature.base.BaseTest;
import com.orangehrm.loginfeature.utilitiesclass.TestData;
import com.orangehrm.loginfeature.utilitiesclass.ExcelUtility_SingleDataSet;


//@Listeners(CustomListener2.class)
public class LoginTests extends BaseTest{
	
	private static final Logger log = LogManager.getLogger(LoginTests.class);

	
	@Test(groups = {"valid"}, dataProvider="inputs",dataProviderClass=TestData.class)
	public void verifyValidLoginByFindingLoginButton(String input1,String input2) throws Exception {
		
		log.info("Starting valid login test by clicking login button");
		log.info("START | user=" + input1 + " | pass=" + input2);
		
		lpa.enterUserName(input1);
		lpa.enterPassword(input2);
		lpa.clickLoginButton();
		
		try {
		    Assert.assertEquals(lpa.dashBoard().getText(), "Dashboard");
		    log.info("PASS | user=" + input1);
		    ExcelUtility_SingleDataSet.setCellData("Pass", 1, 2);
		}
		catch (Exception e) {
		    //log.error("FAIL | user=" + input1 + " | reason=" + e.getMessage());
		    //throw e;
			log.info("fail | user=" + input1);
		}
		
	}
	
	
	@Test(groups = {"valid"})
	public void verifyValidLoginByUsingEnterKey() {
		
		getTest().info("Starting valid login test");
		
		lpa.enterUserName("Admin");
		lpa.enterPassword("admin123");
		lpa.enterActionFromLogin();
		
		Assert.assertEquals(lpa.dashBoard().getText(), "Dashboard");
		getTest().info("Login test passed");
		Reporter.log("valid Login test passed by pressing enter key",true);
	}
	
	//@Test(groups = {"invalid"})
	public void verifyLoginWithInvalidUserName() {
		
		log.info("Starting invalid username login test");
		
		lpa.enterUserName("Admin4444");
		lpa.enterPassword("admin123");
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.invalidCredentialsErrorMessageText(),"Invalid credentials");
		log.info("Invalid username login test passed");
	}
	
	//@Test(groups = {"invalid"})
	public void verifyLoginWithInvalidPassword() {
		
		log.info("Starting invalid password login test");
		
		lpa.enterUserName("Admin");
		lpa.enterPassword("admin123111");
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.invalidCredentialsErrorMessageText(),"Invalid credentials");
		log.info("Invalid password login test passed");
	}
	
	//@Test(groups = {"invalid"})
	public void verifyLoginWithInvalidUserNamePassword() {
		
		log.info("Starting invalid username and password login test");
		
		lpa.enterUserName("Admin111");
		lpa.enterPassword("admin123111");
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.invalidCredentialsErrorMessageText(),"Invalid credentials");
		log.info("Invalid username and password login test passed");
	}

	//@Test(groups = {"invalid","No values"})
	public void verifyLoginWithoutUserName() {
		
		log.info("Starting login test without adding username");
		
		lpa.enterPassword("admin123");
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.userNameRequiredErrorMessageText(),"Required");
		log.info("login without adding username test passed");
	}
	
	//@Test(groups = {"invalid","No values"})
	public void verifyLoginWithoutPassword() {
		
		log.info("Starting login test without adding password");
		
		lpa.enterUserName("Admin");
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.passwordRequiredErrorMessageText(),"Required");
		log.info("login without adding password test passed");
	}
	
	//@Test(groups = {"No values"})
	public void verifyLoginWithoutUserNamePassword() {
		
		log.info("Starting login test without adding username and password");
		
		lpa.clickLoginButton();
		
		Assert.assertEquals(lpa.userNameRequiredErrorMessageText(),"Required");
		Assert.assertEquals(lpa.passwordRequiredErrorMessageText(),"Required");
		log.info("login without adding username and  password test passed");
	}

	
}
