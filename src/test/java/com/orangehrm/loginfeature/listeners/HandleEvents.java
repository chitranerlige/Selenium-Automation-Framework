package com.orangehrm.loginfeature.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HandleEvents implements WebDriverListener {

	@Override
	public void afterClick(WebElement element) {
		// TODO Auto-generated method stub
		System.out.println("After clicking on element " + element.toString());
	}

	@Override
	public void beforeClick(WebElement element) {
		// TODO Auto-generated method stub
		System.out.println("before clicking on element " + element.toString());
	}


}