package com.orangehrm.loginfeature.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.orangehrm.loginfeature.listeners.HandleEvents;

public class DriverFactory {

	
	public static WebDriver createDriver(String browser) {
		
		if(browser==null)
		{
		 throw new IllegalArgumentException("Browser cannot be null");
		}
		
		WebDriver driver = switch(browser.toLowerCase()) {

		case "chrome" -> {
			// Configure Chrome options for Headless execution on CI/CD
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--window-size=1920,1080");
			yield new ChromeDriver(chromeOptions);
		}

		case "edge" -> {
			// Configure Edge options for Headless execution on CI/CD
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless");
			edgeOptions.addArguments("--no-sandbox");
			edgeOptions.addArguments("--disable-dev-shm-usage");
			edgeOptions.addArguments("--window-size=1920,1080");
			yield new EdgeDriver(edgeOptions);
		}

		default -> throw new IllegalArgumentException(
				"Unsupported browser: " + browser);

	    };
	    return new EventFiringDecorator<>(
	            new HandleEvents())
	            .decorate(driver);
	}
    
}
