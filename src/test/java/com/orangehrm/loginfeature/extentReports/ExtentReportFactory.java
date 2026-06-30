package com.orangehrm.loginfeature.extentReports;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orangehrm.loginfeature.utilitiesclass.ConfigReader;
import com.aventstack.extentreports.ExtentReports;

public class ExtentReportFactory {
	private static ExtentReports report;
	public static ExtentReports getInstance() {
		
		if(report == null) {

			String path = ConfigReader.getProperty("report.path");

            ExtentSparkReporter spark =
                new ExtentSparkReporter(path);

            report = new ExtentReports();
            report.attachReporter(spark);

            report.setSystemInfo("Tester", "Your Name");
            report.setSystemInfo("Framework", "TestNG");
        }

        return report;

	}
}
