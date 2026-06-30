package com.orangehrm.loginfeature.utilitiesclass;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name="inputs")
    public Object[][] getData(){
        // Build the relative path context string dynamically
        String path = ConfigReader.getProperty("excel.path1") + ConfigReader.getProperty("login.file1");
        
        // Pass file path, sheet tab name, and table tracker name
        return ExcelUtility.getTestData(path, "LoginTests", "Invalid_Login");
    }
}