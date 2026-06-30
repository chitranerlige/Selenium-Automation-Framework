package com.orangehrm.loginfeature.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.orangehrm.loginfeature.listeners.HandleEvents;

public class DriverFactory {

	
	public static WebDriver createDriver(String browser) {
		
		if(browser==null)
		{
		 throw new IllegalArgumentException("Browser cannot be null");
		}
		
		WebDriver driver = switch(browser.toLowerCase()) {

	        case "chrome" -> new ChromeDriver();

	        case "edge" -> new EdgeDriver();

	        default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);

	    };
	    return new EventFiringDecorator<>(
	            new HandleEvents())
	            .decorate(driver);
	}
    
}
