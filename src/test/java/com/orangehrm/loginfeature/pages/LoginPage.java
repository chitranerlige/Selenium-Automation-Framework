package com.orangehrm.loginfeature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement usernameTextfield() {
		return driver.findElement(By.name("username"));
	}
	
	public WebElement passwordTextfield() {
		return driver.findElement(By.name("password"));
	}
	
	
	public WebElement loginButton() {
		return driver.findElement(By.xpath("//button[contains(text(),Login)]"));

	}
	
	public WebElement forgotPasswordText() {
		return driver.findElement(By.xpath("//p[contains(@class,'orangehrm-login-forgot-header')]"));

	}
	
	public WebElement orangeHRMText() {
		return driver.findElement(By.xpath("//div//p[@class ='oxd-text oxd-text--p orangehrm-copyright'][1]"));

	}

	public WebElement orangeHRMLink() {
		return driver.findElement(By.xpath("//a[@href='http://www.orangehrm.com']"));

	}
	
	public WebElement linkedInICon() {
		return driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/orangehrm/mycompany/']"));

	}
	
	public WebElement faceBookICon() {
		return driver.findElement(By.xpath("//a[@href='https://www.facebook.com/OrangeHRM/']"));

	}
	
	public WebElement twitterICon() {
		return driver.findElement(By.xpath("//a[@href='https://twitter.com/orangehrm?lang=en']"));

	}
	
	public WebElement youtubeICon() {
		return driver.findElement(By.xpath("//a[@href='https://www.youtube.com/c/OrangeHRMInc']"));

	}
	
	public WebElement appHeading() {
		return driver.findElement(By.xpath("//img[@alt='company-branding']"));

	}
	
	public WebElement appLogo() {
		return driver.findElement(By.xpath("//div[@class='orangehrm-login-logo-mobile']/img[@alt='orangehrm-logo']"));

	}
	
	public WebElement loginHeading() {
		return driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']"));

	}
	
	public WebElement dashBoard() {
		return driver.findElement(By.xpath("//h6[text()='Dashboard']"));

	}
	
	public WebElement invalidCredentialsMessage() {
		return driver.findElement(By.xpath("//p[text()='Invalid credentials']"));

	}
	
	public WebElement userNameRequiredMssage() {
		return  driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span"));

	}
	
	public String userNameRequiredErrorMessageText() {
		return userNameRequiredMssage().getText();
	}
	
	public WebElement passwordRequiredMssage() {
		return driver.findElement(By.xpath("//input[@name='password']/parent::div/following-sibling::span"));

	}
	
	public String passwordRequiredErrorMessageText() {
		return passwordRequiredMssage().getText();
	}
	
	public String invalidCredentialsErrorMessageText() {
		return invalidCredentialsMessage().getText();
	}
	
	//actions
	public void enterUserName(String userName) {
		usernameTextfield().sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		passwordTextfield().sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton().click();
	}
	
	public void clickForgotPasswordButton() {
		forgotPasswordText().click();
	}
	
	public void clearUserName() {
		usernameTextfield().clear();
		if(usernameTextfield().getAttribute("value") !=null){
			usernameTextfield().sendKeys(Keys.CONTROL + "a");
			usernameTextfield().sendKeys(Keys.DELETE);
		}
	}
	
	public void clearPassword() {
		passwordTextfield().clear();
		if(passwordTextfield().getAttribute("value") !=null){
			passwordTextfield().sendKeys(Keys.CONTROL + "a");
			passwordTextfield().sendKeys(Keys.DELETE);
		}
	}
	
	public void clickOrangeHRMLink() {
		orangeHRMLink().click();
	}
	
	
	public void clickLinkedInIcon() {
		linkedInICon().click();
	}
	
	public void clickfacebookIcon() {
		faceBookICon().click();
	}
	
	public void clickTwitterIcon() {
		twitterICon().click();
	}
	
	public void clickYoutubeIcon() {
		youtubeICon().click();
	}
	
	public void enterActionFromUserName() {
		usernameTextfield().sendKeys(Keys.ENTER);
	}
	
	public void enterActionFromPassword() {
		passwordTextfield().sendKeys(Keys.ENTER);
	}
	
	
	public void enterActionFromLogin() {
		loginButton().sendKeys(Keys.ENTER);
	}
	
	
	public void tabActionFromUserName() {
		usernameTextfield().sendKeys(Keys.TAB);
	}
	
	public void tabActionFromPassword() {
		passwordTextfield().sendKeys(Keys.TAB);
	}
	
	
}
