package com.orangehrm.loginfeature.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.loginfeature.driverfactory.DriverFactory;
import com.orangehrm.loginfeature.driverfactory.DriverManager;
import com.orangehrm.loginfeature.extentReports.ExtentReportFactory;
import com.orangehrm.loginfeature.pages.LoginPage;
import com.orangehrm.loginfeature.utilitiesclass.ConfigReader;
import com.orangehrm.loginfeature.utilitiesclass.ExcelUtility;
import com.orangehrm.loginfeature.utilitiesclass.ExcelUtility_SingleDataSet;


public class BaseTest {

	private WebDriver driver;
	protected LoginPage lpa;
	private static final Logger log = LogManager.getLogger(BaseTest.class);
	private ExtentReports report;
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		log.info("Initializing Test Suite Configurations...");
		 ConfigReader.loadProperties();
		 report = ExtentReportFactory.getInstance();
			String path2 =
					ConfigReader.getProperty("excel.path2")
					+
					ConfigReader.getProperty("login.file2");
		 ExcelUtility_SingleDataSet.setExcelFile(path2, "Sheet1");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		log.info("Starting class: " + getClass().getSimpleName());
		
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	public void beforeMethod(Method method, String browser) {

		ExtentTest t = report.createTest(getClass().getSimpleName() + " - " + method.getName());
        extentTest.set(t);
		DriverManager.setDriver(
		        DriverFactory.createDriver(browser)
		);

		driver = DriverManager.getDriver();

		getTest().info("Browser [" + browser + "] launched.");
        driver.get(ConfigReader.getProperty("url"));
		driver.manage().window().maximize();
		int wait =
				Integer.parseInt(
				ConfigReader.getProperty("implicit.wait")
				);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

		lpa = new LoginPage(driver);

		getTest().info("Navigation to OrangeHRM Portal complete.");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult testResult) throws IOException {
		
		
		if(testResult.getStatus() == ITestResult.FAILURE) {
			getTest().info("test failed");
			String fileName =
					LocalDateTime.now()
					.format(DateTimeFormatter.ofPattern(
					"yyyyMMdd_HHmmss"))
					+ ".png";
			String directory =
			        ConfigReader.getProperty("screenshot.path");

			if(directory == null) {
			    directory = "screenshots/";
			}
			if(driver != null) {

			    File sourceFile =
			       ((TakesScreenshot)driver)
			       .getScreenshotAs(OutputType.FILE);

			    FileUtils.copyFile(
			        sourceFile,
			        new File(directory,fileName)
			    );
			}
			String path = directory + fileName;
			System.out.println(path);
			getTest().fail("Test failed")
		    .addScreenCaptureFromPath(path);
		}
		if(testResult.getStatus() == ITestResult.SUCCESS) {
			getTest().info("test passed");
		}
		if(driver != null) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
		getTest().info("Browser closed");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Completed class: " + getClass().getSimpleName());
		
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		log.info("Report flushed");
		if(report != null) {
	        report.flush();
	    }
	}
	
}
