package com.orangehrm.loginfeature.utilitiesclass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	public static void waitForElement(WebDriver driver, By locator) {

		int timeout = Integer.parseInt(
                ConfigReader.getProperty("timeout")
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
	}
	
	public static void waitForElementClickable(WebDriver driver, By locator) {

	    int timeout = Integer.parseInt(
	            ConfigReader.getProperty("timeout")
	    );

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(timeout));

	    wait.until(
	            ExpectedConditions.elementToBeClickable(locator)
	    );
	}
	
	public static void waitForElementPresent(WebDriver driver, By locator) {

	    int timeout = Integer.parseInt(
	            ConfigReader.getProperty("timeout")
	    );

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(timeout));

	    wait.until(
	            ExpectedConditions.presenceOfElementLocated(locator)
	    );
	}

}