package com.orangehrm.loginfeature.utilitiesclass;

import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    

    private static Properties properties;
    private static final Logger log = LogManager.getLogger(ConfigReader.class);
	 
    public static void loadProperties() {
        try {
            properties = new Properties();

            InputStream input = ConfigReader.class
                                    .getClassLoader()
                                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("CRITICAL RUNTIME ERROR: 'config.properties' was not found on the framework classpath!");
            }
	            
            properties.load(input);
            

            if (properties.isEmpty()) {
                throw new RuntimeException("CRITICAL RUNTIME ERROR: 'config.properties' exists but contains zero configurations!");
            }

        } catch (Exception e) {
            log.error("Failed to load environment property configuration files", e);
            throw new RuntimeException("Framework initialization failed due to file reading error.", e);
        }
    }
	 
    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }

        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("CONFIGURATION FAULT: The key '" + key 
                + "' does not exist inside your config.properties file! Execution halted.");
        }

        String cleanedValue = value.trim();

        if (cleanedValue.isEmpty()) {
            throw new RuntimeException("CONFIGURATION FAULT: The key '" + key 
                + "' exists in config.properties but has no value assigned to it! Execution halted.");
        }

        return cleanedValue;
    }

    public static int getIntProperty(String key) {
        String valueStr = getProperty(key); 
        try {
            return Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("CONFIGURATION FAULT: The key '" + key 
                + "' has a value of '" + valueStr + "' which cannot be converted to a valid integer!");
        }
    }
}